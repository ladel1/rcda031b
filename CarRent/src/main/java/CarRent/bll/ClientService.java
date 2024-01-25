package CarRent.bll;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import CarRent.bo.Client;
import CarRent.dal.ClientDao;
@Service
public class ClientService implements Manager<Client> {

	@Autowired
	private ClientDao clientDao;
	
	@Override
	public Client getOne(long id) {
		// TODO Auto-generated method stub
		return clientDao.getReferenceById(id);
	}

	@Override
	public List<Client> getAll() {
		// TODO Auto-generated method stub
		return clientDao.findAll();
	}

	@Override
	public void add(Client entity) {
		clientDao.save(entity);
	}

	@Override
	public void edit(Client entity) {
		// TODO Auto-generated method stub
		clientDao.save(entity);
	}

	@Override
	public void delete(Client entity) {
		// TODO Auto-generated method stub
		clientDao.delete(entity);
	}

}
