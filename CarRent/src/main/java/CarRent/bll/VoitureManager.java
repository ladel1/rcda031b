package CarRent.bll;

import java.util.List;

import CarRent.bo.Voiture;

public interface VoitureManager extends Manager<Voiture> {
	List<Voiture> lesVoituresDispo();
}
