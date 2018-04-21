package fr.arko.dokbuild.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import fr.arko.dokbuild.domain.Cards;


public interface CardsDao extends JpaRepository<Cards, Integer> {
	
	@Query("SELECT c FROM Cards c INNER JOIN Cards c2 ON c.awakedCardId=c2.id "
			+ "WHERE (-1=:category OR :category in (c.cardCategory1Id, c.cardCategory2Id, c.cardCategory3Id, c.cardCategory4Id, c.cardCategory5Id, c.cardCategory6Id)) "
			+ " AND (-1=:link OR :link in (c.linkSkill1Id, c.linkSkill2Id, c.linkSkill3Id, c.linkSkill4Id, c.linkSkill5Id, c.linkSkill6Id, c.linkSkill7Id)) "
			+ " AND c.name like %:name% AND c.rarity IN :rarity AND c2.element IN :element AND c.cost > 1")
	Page<Cards> searchCard(@Param("name") String name, @Param("category") Integer category, @Param("link") Integer link, 
			@Param("rarity") List<Integer> rarity, @Param("element") List<Integer> element, Pageable pageable);
	
}
