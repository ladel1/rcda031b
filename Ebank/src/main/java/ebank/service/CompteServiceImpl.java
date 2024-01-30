package ebank.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import ebank.entity.Compte;
import ebank.repository.CompteRepository;
import jakarta.transaction.Transactional;

@org.springframework.stereotype.Service
public class CompteServiceImpl implements CompteService {

	@Autowired
	private CompteRepository compteRepository;
	
	@Override
	public Compte getById(long id) {		
		return compteRepository.getReferenceById(id);
	}

	@Override
	public void add(Compte t) {
		compteRepository.save(t);
	}

	@Override
	public void update(Compte t) {
		compteRepository.save(t);
	}

	@Override
	public void remove(Compte t) {
		compteRepository.delete(t);
	}

	@Override
	public void crediter(Compte compte, float montant) {
		compte.setSolde( compte.getSolde() + montant);
		update(compte);
	}

	@Override
	public void debiter(Compte compte, float montant) throws Exception {
		// validation
		if( compte.getSolde() - montant < 0 ) throw new Exception("Solde insuffisant!");
		compte.setSolde( compte.getSolde() - montant);
		update(compte);		
	}

	@Override
	@Transactional
	public void virement(Compte emitteur, Compte beneficiaire, float montant) throws Exception {
		debiter(emitteur, montant);
		crediter(beneficiaire, montant);
	}

	@Override
	public List<Compte> getAll() {
		// TODO Auto-generated method stub
		return compteRepository.findAll();
	}

}
