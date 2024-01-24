package com.eni.jeuxvideo.bo;

import java.time.LocalDate;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
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
	
	@Column(length = 50)
	private String titre;
	
	@Column(length = 50)
	private String platform;
	
	@Column(length = 20)
	private String mode;
	
	@Column(length = 50)
	private String editeur;
	
	@ManyToOne
	@JoinColumn(name = "category_id")
	private Category category;
	
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
