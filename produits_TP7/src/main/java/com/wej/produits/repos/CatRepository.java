package com.wej.produits.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.wej.produits.entities.Categorie;

@Repository
public interface CatRepository extends JpaRepository<Categorie, Long> {

}
