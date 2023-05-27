package br.com.amaral.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.amaral.domain.Promotion;

public interface IPromotionRepository extends JpaRepository<Promotion, Long>{

}
