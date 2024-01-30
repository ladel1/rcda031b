package ebank.service;

import ebank.entity.Compte;

public interface CompteService extends Service<Compte> {

	void crediter( Compte compte, float montant );
	void debiter( Compte compte, float montant ) throws Exception;
	void virement(Compte debiteur, Compte crediteur, float montant ) throws Exception;
	
}
