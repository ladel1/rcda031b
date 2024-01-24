package com.eni.jeuxvideo.bo;

import java.time.LocalDate;
import java.util.List;

import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "GAMES")
@Data
@NoArgsConstructor
public class Game {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id ;
	
	@NotBlank(message = "Le champs titre ne peut être vide!")
	@Length(
			min = 3,
			max = 50,
			message = "Le champs titre doit être entre 3 et 50 caractères!"
			)
	@Column(length = 50)
	private String titre;
	
	@NotBlank(message = "Le champs platforme ne peut être vide!")
	@Column(length = 50)
	private String platform;
	
	@NotBlank(message = "Le champs mode ne peut être vide!")
	@Column(length = 20)
	private String mode;
	
	@NotBlank(message = "Le champs éditeur ne peut être vide!")
	@Column(length = 50)
	private String editeur;
	
	@ManyToMany
	@JoinTable(
			name = "games_categories",
			joinColumns = @JoinColumn(name="game_id"),
			inverseJoinColumns = @JoinColumn(name="category_id")
			)
	private List<Category> categories;
	
	@Column(length = 300)
	private String description;
	
	@Column
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate dateSortie;
	
	@Column
	private int pegi;
	
	@OneToMany
	@JoinColumn(name="game_id")
	private List<Avis> avis;

}
