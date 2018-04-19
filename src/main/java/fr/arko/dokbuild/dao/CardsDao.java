package fr.arko.dokbuild.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.arko.dokbuild.domain.Cards;


public interface CardsDao extends JpaRepository<Cards, Long> {
	
}
