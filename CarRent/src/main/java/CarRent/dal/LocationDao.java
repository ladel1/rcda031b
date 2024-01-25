package CarRent.dal;

import org.springframework.data.jpa.repository.JpaRepository;

import CarRent.bo.Location;

public interface LocationDao extends JpaRepository<Location, Long> {

}
