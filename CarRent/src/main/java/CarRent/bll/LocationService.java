package CarRent.bll;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import CarRent.bo.Location;
import CarRent.dal.LocationDao;

@Service
public class LocationService implements Manager<Location> {
	@Autowired
	private LocationDao locationDao;
	
	@Override
	public Location getOne(long id) {
		// TODO Auto-generated method stub
		return locationDao.getReferenceById(id);
	}

	@Override
	public List<Location> getAll() {
		// TODO Auto-generated method stub
		return locationDao.findAll();
	}

	@Override
	public void add(Location entity) {
		// TODO Auto-generated method stub
		locationDao.save(entity);
	}

	@Override
	public void edit(Location entity) {
		// TODO Auto-generated method stub
		locationDao.save(entity);
	}

	@Override
	public void delete(Location entity) {
		// TODO Auto-generated method stub
		locationDao.delete(entity);
	}

}
