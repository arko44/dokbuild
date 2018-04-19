package fr.arko.dokbuild.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import fr.arko.dokbuild.domain.Cards;
import fr.arko.dokbuild.enumeration.Rarity;


public interface CardsDao extends JpaRepository<Cards, Long> {
	
	Page<Cards> findByRarityInAndElementIn(List<Integer> rarity, List<Integer> element, Pageable pageable);
	
}
