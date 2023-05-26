package br.com.amaral.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.amaral.domain.Category;

public interface ICategoryRepository extends JpaRepository<Category, Long>{

}
