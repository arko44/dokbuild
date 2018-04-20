package fr.arko.dokbuild.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.arko.dokbuild.domain.LeaderSkills;


public interface LeaderSkillsDao extends JpaRepository<LeaderSkills, Integer> {
	
}
