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
@Table(name = "promotions")
public class Promotion implements Serializable {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank()
	@Column(name = "title", nullable = false)
	private String title;
	
	@NotBlank()
	@Column(name = "promotion_Link", nullable = false)
	private String promotionLink;
	
	@Column(nullable = false)
	private String site;
	
	@Column(name = "description")
	private String description;
	
	@Column(name = "image_link",nullable = false)
	private String imageLink;
	
	@NotNull()
	@NumberFormat(style = Style.CURRENCY, pattern = "#,##0.00")
	@Column(name = "promotion_price",nullable = false)
	private BigDecimal price;
	
	@Column(name = "total_likes")
	private int likes;
	
	@Column(name = "registration_Date", nullable = false)
	private LocalDateTime registrationDate;

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

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getPromotionLink() {
		return promotionLink;
	}

	public void setPromotionLink(String promotionLink) {
		this.promotionLink = promotionLink;
	}

	public String getSite() {
		return site;
	}

	public void setSite(String site) {
		this.site = site;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getImageLink() {
		return imageLink;
	}

	public void setImageLink(String imageLink) {
		this.imageLink = imageLink;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public int getLikes() {
		return likes;
	}

	public void setLikes(int likes) {
		this.likes = likes;
	}

	public LocalDateTime getRegistrationDate() {
		return registrationDate;
	}

	public void setRegistrationDate(LocalDateTime registrationDate) {
		this.registrationDate = registrationDate;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	@Override
	public String toString() {
		return "Promotion [id=" + id + ", title=" + title + ", promotionLink=" + promotionLink + ", site=" + site
				+ ", description=" + description + ", imageLink=" + imageLink + ", price=" + price + ", likes=" + likes
				+ ", registrationDate=" + registrationDate + ", category=" + category + "]";
	}
	
}
