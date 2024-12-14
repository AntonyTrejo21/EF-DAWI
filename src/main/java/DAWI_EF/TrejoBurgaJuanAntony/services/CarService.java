package DAWI_EF.TrejoBurgaJuanAntony.services;

import DAWI_EF.TrejoBurgaJuanAntony.DTO.CarDTO;
import DAWI_EF.TrejoBurgaJuanAntony.DTO.CarDetailDTO;

import java.util.List;
import java.util.Optional;

public interface CarService {
    List<CarDTO> getAllCars() throws Exception;
    Optional<CarDetailDTO> getCarById(int id) throws Exception;
    boolean updateCar(CarDTO carDto) throws Exception;
    boolean deleteCarById(int id) throws Exception;
    boolean addCar(CarDetailDTO carDetailDTO) throws Exception;
}
