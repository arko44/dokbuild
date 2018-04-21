package fr.arko.dokbuild.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.arko.dokbuild.domain.CardCategories;


public interface CardCategoriesDao extends JpaRepository<CardCategories, Integer> {
	
	List<CardCategories> findByIdIn(List<Integer> idList);
	
}
