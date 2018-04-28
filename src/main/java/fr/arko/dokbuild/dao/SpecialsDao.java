package fr.arko.dokbuild.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import fr.arko.dokbuild.domain.Specials;


public interface SpecialsDao extends JpaRepository<Specials, Integer> {
	
	@Query("SELECT s FROM Specials s INNER JOIN CardSpecials cs ON s.id=cs.specialId WHERE cs.cardId = :cardId ORDER BY cs.lvStart, cs.eballNumStart, cs.cardId ")
	List<Specials> findByCardId(@Param("cardId") Integer cardId);
	
}
