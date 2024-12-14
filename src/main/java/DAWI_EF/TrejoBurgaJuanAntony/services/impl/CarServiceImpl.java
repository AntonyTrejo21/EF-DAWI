package DAWI_EF.TrejoBurgaJuanAntony.services.impl;

import DAWI_EF.TrejoBurgaJuanAntony.DTO.CarDTO;
import DAWI_EF.TrejoBurgaJuanAntony.DTO.CarDetailDTO;
import DAWI_EF.TrejoBurgaJuanAntony.models.Car;
import DAWI_EF.TrejoBurgaJuanAntony.repository.CarRepository;
import DAWI_EF.TrejoBurgaJuanAntony.services.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CarServiceImpl implements CarService {

    @Autowired
    CarRepository carRepository;
    @Override
    public List<CarDTO> getAllCars() throws Exception {
        List<CarDTO> cars = new ArrayList<CarDTO>();
        Iterable<Car> iterable = carRepository.findAll();;
        iterable.forEach(car -> {
            cars.add(new CarDTO(
                    car.getCarId(),
                    car.getOwnerName(),
                    car.getOwnerContact(),
                    car.getMileage(),
                    car.getInsuranceCompany(),
                    car.getInsurancePolicyNumber()
                                )
                    );
        });
        return cars;
    }

    @Override
    public Optional<CarDetailDTO> getCarById(int id) throws Exception {
        Optional<Car> optional = carRepository.findById(id);
        return optional.map(
                car -> new CarDetailDTO(
                        car.getCarId(),
                        car.getMake(),
                        car.getModel(),
                        car.getYear(),
                        car.getVin(),
                        car.getLicensePlate(),
                        car.getOwnerName(),
                        car.getOwnerName(),
                        car.getPurchaseDate(),
                        car.getMileage(),
                        car.getEngineType(),
                        car.getColor(),
                        car.getInsuranceCompany(),
                        car.getInsurancePolicyNumber(),
                        car.getRegistrationExpirationDate(),
                        car.getServiceDueDate()
                        )
        );
    }

    @Override
    public boolean updateCar(CarDTO carDto) throws Exception {
        Optional<Car> optional = carRepository.findById(carDto.carId());
        return optional.map(car ->{
            car.setOwnerName(carDto.ownerName());
            car.setOwnerContact(carDto.ownerContact());
            car.setMileage(carDto.mileage());
            car.setInsuranceCompany(carDto.insuranceCompany());
            car.setInsurancePolicyNumber(carDto.insurancePolicyNumber());
            carRepository.save(car);
            return true;
        }).orElse(false);
    }

    @Override
    public boolean deleteCarById(int id) throws Exception {
        Optional<Car> optional = carRepository.findById(id);
        return optional.map(car -> {
            carRepository.delete(car);
            return true;
        }).orElse(false);

    }

    @Override
    public boolean addCar(CarDetailDTO carDetailDTO) throws Exception {
        Optional<Car> optional = carRepository.findById(carDetailDTO.carId());
        if(optional.isEmpty()){
            Car car = new Car();
            car.setMake(carDetailDTO.make());
            car.setModel(carDetailDTO.model());
            car.setYear(carDetailDTO.year());
            car.setVin(carDetailDTO.vin());
            car.setLicensePlate(carDetailDTO.licensePlate());
            car.setOwnerName(carDetailDTO.ownerName());
            car.setOwnerContact(carDetailDTO.ownerContact());
            car.setPurchaseDate(carDetailDTO.purchaseDate());
            car.setMileage(carDetailDTO.mileage());
            car.setEngineType(carDetailDTO.engineType());
            car.setColor(carDetailDTO.color());
            car.setInsuranceCompany(carDetailDTO.insuranceCompany());
            car.setInsurancePolicyNumber(carDetailDTO.insurancePolicyNumber());
            car.setRegistrationExpirationDate(carDetailDTO.registrationExpirationDate());
            car.setServiceDueDate(carDetailDTO.serviceDueDate());
            carRepository.save(car);
            return true;
        }
        return false;
    }
}
