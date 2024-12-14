package DAWI_EF.TrejoBurgaJuanAntony.repository;

import DAWI_EF.TrejoBurgaJuanAntony.models.Car;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarRepository extends CrudRepository<Car, Integer> {
}
