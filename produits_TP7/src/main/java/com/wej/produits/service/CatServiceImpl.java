package com.wej.produits.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wej.produits.entities.Categorie;
import com.wej.produits.repos.CatRepository;


@Service
public class CatServiceImpl implements CatService {

    @Autowired
    private CatRepository catRepository;

    @Override
    public List <Categorie> findAll() {
        return catRepository.findAll();
    }

	@Override
	public Categorie saveCategorie(Categorie c) {
		return catRepository.save(c);
	}

	@Override
	public Categorie updateCategorie(Categorie c) {
		return catRepository.save(c);
	}

	@Override
	public void deleteCategorie(Categorie c) {
		catRepository.delete(c);
	}

	@Override
	public void deleteCategorieById(Long idCat) {
		catRepository.deleteById(idCat);
	}

	@Override
	public Categorie getCategorie(Long idCat) {
		return catRepository.findById(idCat).get();
	}

   
}

