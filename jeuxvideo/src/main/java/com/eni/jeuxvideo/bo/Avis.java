package com.eni.jeuxvideo.bo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.ToString;

@Data
@Entity
public class Avis {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private int note;
	
	@Column(length = 300)
	private String contenu;
	@Column(length = 30)
	private String auteur;
	
	@ToString.Exclude
	@ManyToOne
	private Game game;
	
}
