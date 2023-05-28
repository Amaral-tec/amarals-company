package br.com.amaral.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import br.com.amaral.domain.Promotion;

public interface IPromotionRepository extends JpaRepository<Promotion, Long>{

	@Transactional(readOnly = false)
	@Modifying
	@Query("UPDATE Promotion p SET p.likes = p.likes + 1 WHERE p.id = :id")
	void updateAddLikes(@Param("id") Long id);
	
	@Query("SELECT p.likes FROM Promotion p WHERE p.id = :id")
	int findLikesById(@Param("id") Long id);
	
	@Query("SELECT p FROM Promotion p WHERE p.site LIKE :site")
	Page<Promotion> findBySite(@Param("site") String site, Pageable pageable);
	
	@Query("SELECT DISTINCT p.site FROM Promotion p WHERE p.site LIKE %:site%")
	List<String> findSitesByTerm(@Param("site") String site);
	
}
