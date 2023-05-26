package br.com.amaral.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.springframework.format.annotation.NumberFormat;
import org.springframework.format.annotation.NumberFormat.Style;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@SuppressWarnings("serial")
@Entity
@Table(name = "promocoes")
public class Promocao implements Serializable {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank()
	@Column(name = "title", nullable = false)
	private String titulo;
	
	@NotBlank()
	@Column(name = "promotion_Link", nullable = false)
	private String linkPromocao;
	
	@Column(nullable = false)
	private String site;
	
	@Column(name = "description")
	private String descricao;
	
	@Column(name = "image_link",nullable = false)
	private String linkImagem;
	
	@NotNull()
	@NumberFormat(style = Style.CURRENCY, pattern = "#,##0.00")
	@Column(name = "promotion_price",nullable = false)
	private BigDecimal preco;
	
	@Column(name = "total_likes")
	private int likes;
	
	@Column(name = "registration_Date", nullable = false)
	private LocalDateTime dtCadastro;

	@NotNull()
	@ManyToOne
	@JoinColumn(name = "category_fk")
	private Category category;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getLinkPromocao() {
		return linkPromocao;
	}

	public void setLinkPromocao(String linkPromocao) {
		this.linkPromocao = linkPromocao;
	}

	public String getSite() {
		return site;
	}

	public void setSite(String site) {
		this.site = site;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getLinkImagem() {
		return linkImagem;
	}

	public void setLinkImagem(String linkImagem) {
		this.linkImagem = linkImagem;
	}

	public BigDecimal getPreco() {
		return preco;
	}

	public void setPreco(BigDecimal preco) {
		this.preco = preco;
	}

	public int getLikes() {
		return likes;
	}

	public void setLikes(int likes) {
		this.likes = likes;
	}

	public LocalDateTime getDtCadastro() {
		return dtCadastro;
	}

	public void setDtCadastro(LocalDateTime dtCadastro) {
		this.dtCadastro = dtCadastro;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	@Override
	public String toString() {
		return "Promocao [id=" + id + ", titulo=" + titulo + ", linkPromocao=" + linkPromocao + ", site=" + site
				+ ", descricao=" + descricao + ", linkImagem=" + linkImagem + ", preco=" + preco + ", likes=" + likes
				+ ", dtCadastro=" + dtCadastro + ", category=" + category + "]";
	}
	
	
}
