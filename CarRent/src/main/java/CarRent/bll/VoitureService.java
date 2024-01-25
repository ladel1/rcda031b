package CarRent.bll;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import CarRent.bo.Voiture;
import CarRent.dal.VoitureDao;

@Service
public class VoitureService implements VoitureManager {

	@Autowired
	private VoitureDao voitureDao;
	
	@Override
	public Voiture getOne(long id) {
		return voitureDao.getReferenceById(id);
	}

	@Override
	public List<Voiture> getAll() {
		return voitureDao.findAll();
	}

	@Override
	public void add(Voiture voiture) {
		voitureDao.save(voiture);
	}

	@Override
	public void edit(Voiture voiture) {
		voitureDao.save(voiture);
	}

	@Override
	public void delete(Voiture voiture) {
		voitureDao.delete(voiture);
	}
	
	public List<Voiture> lesVoituresDispo(){
		return voitureDao.trouverVoituresDispo();
	}

}
