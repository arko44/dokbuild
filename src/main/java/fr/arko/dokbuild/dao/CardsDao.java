package fr.arko.dokbuild.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import fr.arko.dokbuild.domain.Cards;
import fr.arko.dokbuild.enumeration.Element;
import fr.arko.dokbuild.enumeration.Rarity;


public interface CardsDao extends JpaRepository<Cards, Long> {
	Page<Cards> findByRarityAndElement(Rarity rarity, Element element, Pageable pageable);
}
