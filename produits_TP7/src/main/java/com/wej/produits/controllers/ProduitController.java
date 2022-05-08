package com.wej.produits.controllers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.wej.produits.entities.Categorie;
import com.wej.produits.entities.Produit;
import com.wej.produits.service.CatService;
import com.wej.produits.service.ProduitService;
@Controller
public class ProduitController {
@Autowired
ProduitService produitService;
@Autowired
CatService catService;
@RequestMapping("/showCreate")
public String showCreate(ModelMap modelMap)
{
List<Categorie> cats = catService.findAll();
modelMap.addAttribute("categories", cats);
modelMap.addAttribute("produit", new Produit());
modelMap.addAttribute("mode", "new");
return "formProduit";
}
@RequestMapping("/saveProduit")
public String saveProduit(ModelMap modelMap,@Valid Produit produit,
BindingResult bindingResult)
{
	List<Categorie> cats = catService.findAll();
	modelMap.addAttribute("categories", cats);
System.out.println(produit);
if (bindingResult.hasErrors()) return "formProduit";
produitService.saveProduit(produit);
return "redirect:/ListeProduits";
}

@RequestMapping("/showCreateCat")
public String showCreateCat(ModelMap modelMap)
{
modelMap.addAttribute("categories", new Categorie());
modelMap.addAttribute("mode", "new");
return "formCategorie";
}
@RequestMapping("/saveCategorie")
public String saveCategorie(@ModelAttribute("categorie") Categorie categorie,ModelMap modelMap) throws ParseException 
{
Categorie saveCategorie = catService.saveCategorie(categorie);
return "redirect:/ListeCat";
}

@RequestMapping("/ListeCat")
public String listeProduits(ModelMap modelMap)
{
List <Categorie> cats = catService.findAll();
modelMap.addAttribute("categories", cats);
return "ListeCat";
}

@RequestMapping("/ListeProduits")
public String listeProduits(ModelMap modelMap,
@RequestParam (name="page",defaultValue = "0") int page,
@RequestParam (name="size", defaultValue = "3") int size)
{
Page<Produit> prods = produitService.getAllProduitsParPage(page, size);
modelMap.addAttribute("produits", prods);
 modelMap.addAttribute("pages", new int[prods.getTotalPages()]);
modelMap.addAttribute("currentPage", page);
return "listeProduits";
}


@RequestMapping("/supprimerProduit")
public String supprimerProduit(@RequestParam("id") Long id,
ModelMap modelMap,
@RequestParam (name="page",defaultValue = "0") int page,
@RequestParam (name="size", defaultValue = "3") int size)
{
produitService.deleteProduitById(id);
Page<Produit> prods = produitService.getAllProduitsParPage(page, 
size);
modelMap.addAttribute("produits", prods);
modelMap.addAttribute("pages", new int[prods.getTotalPages()]);
modelMap.addAttribute("currentPage", page);
modelMap.addAttribute("size", size);
return "listeProduits";
}
@RequestMapping("/supprimerCategorie")
public String supprimerCategorie(@RequestParam("id") Long id,
 ModelMap modelMap)
{ 
catService.deleteCategorieById(id);
List<Categorie> cats = catService.findAll();
modelMap.addAttribute("categories", cats);
return "ListeCat";
}

@RequestMapping("/modifierProduit")
public String editerProduit(@RequestParam("id") Long id,ModelMap modelMap)
{
Produit p= produitService.getProduit(id);
List<Categorie> cats = catService.findAll();
cats.remove(p.getCategorie());
modelMap.addAttribute("categories", cats);
modelMap.addAttribute("produit", p);
modelMap.addAttribute("mode", "edit");
return "formProduit";
}
@RequestMapping("/updateProduit")
public String updateProduit(@ModelAttribute("produit") Produit produit,
@RequestParam("date") String date,ModelMap modelMap) throws ParseException {
	//conversion de la date 
	 SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
	 Date dateCreation = dateformat.parse(String.valueOf(date));
	 produit.setDateCreation(dateCreation);
	 
	 produitService.updateProduit(produit);
	 List<Produit> prods = produitService.getAllProduits();
	 modelMap.addAttribute("produits", prods);
	return "listeProduits";
	}
@RequestMapping("/modifierCategorie")
public String editerCategorie(@RequestParam("id") Long id,ModelMap modelMap)
{
Categorie c= catService.getCategorie(id);
modelMap.addAttribute("categories", c);
modelMap.addAttribute("mode", "edit");
return "formCategorie";
}
@RequestMapping("/updateCategorie")
public String updateCategorie(@ModelAttribute("categorie") Categorie categorie,ModelMap modelMap) throws ParseException {
	catService.updateCategorie(categorie);
	 List<Categorie> cats = catService.findAll();
	 modelMap.addAttribute("categories", cats);
	return "ListeCat";
	}
}