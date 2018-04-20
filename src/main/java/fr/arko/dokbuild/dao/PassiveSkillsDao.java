package fr.arko.dokbuild.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.arko.dokbuild.domain.PassiveSkills;


public interface PassiveSkillsDao extends JpaRepository<PassiveSkills, Integer> {
	
}
