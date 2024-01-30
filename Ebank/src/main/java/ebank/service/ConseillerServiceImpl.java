package ebank.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import ebank.entity.Conseiller;
import ebank.repository.ConseillerRepository;

@org.springframework.stereotype.Service
public class ConseillerServiceImpl implements Service<Conseiller> {

	@Autowired
	private ConseillerRepository conseillerRepository;

	@Override
	public Conseiller getById(long id) {
		// TODO Auto-generated method stub
		return conseillerRepository.getReferenceById(id);
	}

	@Override
	public void add(Conseiller t) {
		// TODO Auto-generated method stub
		conseillerRepository.save(t);
	}

	@Override
	public void update(Conseiller t) {
		// TODO Auto-generated method stub
		conseillerRepository.save(t);
	}

	@Override
	public void remove(Conseiller t) {
		// TODO Auto-generated method stub
		conseillerRepository.delete(t);
	}

	@Override
	public List<Conseiller> getAll() {
		// TODO Auto-generated method stub
		return null;
	}
	

}
