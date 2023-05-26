package br.com.amaral.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.amaral.domain.Promocao;

public interface IPromocaoRepository extends JpaRepository<Promocao, Long>{

}
