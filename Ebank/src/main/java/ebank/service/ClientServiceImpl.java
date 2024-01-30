package ebank.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;


import ebank.entity.Client;
import ebank.repository.ClientRepository;

@org.springframework.stereotype.Service
public class ClientServiceImpl implements Service<Client> {

	@Autowired
	private ClientRepository clientRepository;
	
	@Override
	public Client getById(long id) {
		return clientRepository.getReferenceById(id);
	}

	@Override
	public void add(Client t) {
		clientRepository.save(t);
	}

	@Override
	public void update(Client t) {
		clientRepository.save(t);
	}

	@Override
	public void remove(Client t) {
		clientRepository.delete(t);
	}

	@Override
	public List<Client> getAll() {
		// TODO Auto-generated method stub
		return clientRepository.findAll();
	}

}
