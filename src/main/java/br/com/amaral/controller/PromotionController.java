package br.com.amaral.controller;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jakarta.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.amaral.domain.Category;
import br.com.amaral.domain.Promotion;
import br.com.amaral.repository.ICategoryRepository;
import br.com.amaral.repository.IPromotionRepository;

@Controller
@RequestMapping("/promotion")
public class PromotionController {
	
	private static Logger log = LoggerFactory.getLogger(PromotionController.class);
	
	@Autowired
	private IPromotionRepository promotionRepository;
	
	@Autowired
	private ICategoryRepository categoryRepository;
	
	@PostMapping("/save")
	public ResponseEntity<?> salvarPromocao(@Valid Promotion promotion, BindingResult result) {
		
		if (result.hasErrors()) {
			
			Map<String, String> errors = new HashMap<>();
			for (FieldError error : result.getFieldErrors()) {
				errors.put(error.getField(), error.getDefaultMessage());
			}
			return ResponseEntity.unprocessableEntity().body(errors);
		}

		log.info("Promotion {}", promotion.toString());
		promotion.setRegistrationDate(LocalDateTime.now());
		promotionRepository.save(promotion);
		return ResponseEntity.ok().build();
	}
	
	@ModelAttribute("categories")
	public List<Category> getCategories() {
		
		return categoryRepository.findAll();
	}

	@GetMapping("/add")
	public String abrirCadastro() {
		
		return "promo-add";
	}
}
