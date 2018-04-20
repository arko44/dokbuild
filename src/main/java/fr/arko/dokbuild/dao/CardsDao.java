package fr.arko.dokbuild.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import fr.arko.dokbuild.domain.Cards;


public interface CardsDao extends JpaRepository<Cards, Integer> {
	
	@Query("SELECT c FROM Cards c INNER JOIN Cards c2 ON c.awakedCardId=c2.id WHERE c.name like %:name% and c.rarity in :rarity and c2.element in :element and c.cost > 1")
	Page<Cards> searchCard(@Param("name") String name, @Param("rarity") List<Integer> rarity, @Param("element") List<Integer> element, Pageable pageable);
	
}
