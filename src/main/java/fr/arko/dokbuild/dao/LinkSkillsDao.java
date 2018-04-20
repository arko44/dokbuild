package fr.arko.dokbuild.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.arko.dokbuild.domain.LinkSkills;


public interface LinkSkillsDao extends JpaRepository<LinkSkills, Long> {
	
	List<LinkSkills> findByIdIn(List<Integer> idList);
	
}
