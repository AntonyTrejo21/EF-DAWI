package DAWI_EF.TrejoBurgaJuanAntony.rest;

import DAWI_EF.TrejoBurgaJuanAntony.DTO.CarDTO;
import DAWI_EF.TrejoBurgaJuanAntony.DTO.CarDetailDTO;
import DAWI_EF.TrejoBurgaJuanAntony.response.*;
import DAWI_EF.TrejoBurgaJuanAntony.services.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/manage-car")
public class ManageCarApi {
    @Autowired
    CarService carService;

    @GetMapping("/all")
    public FindCarsResponse findCars(){
        try {
            List<CarDTO> cars = carService.getAllCars();
            if(!cars.isEmpty()){
                return new FindCarsResponse("01",null,cars);
            }else
                return new FindCarsResponse("02","Cars not found",null);
        }catch (Exception e){
            e.printStackTrace();
            return new FindCarsResponse("99","An error ocurred, please try again.",null);
        }
    }

    @GetMapping("/detail")
    public FindCarResponse findCar(@RequestParam(value = "id", defaultValue = "0") String id){
        try {
            Optional<CarDetailDTO> optional = carService.getCarById(Integer.parseInt(id));
            return optional.map(cars ->
                    new FindCarResponse("01", null, cars)
            ).orElse(
                    new FindCarResponse("02", "Cars not found", null)
            );
        }catch (Exception e){
            e.printStackTrace();
            return new FindCarResponse("99","An error ocurred, please try again.",null);
        }
    }

    @PutMapping("/update")
    public UpdateCarResponse updateCars(@RequestBody CarDTO carDto){
        try {
            if(carService.updateCar(carDto)){
                return new UpdateCarResponse("01",null);
            }else
                return new UpdateCarResponse("02","Car not found");
        }catch (Exception e){
            e.printStackTrace();
            return new UpdateCarResponse("99","An error ocurred, please try again.");
        }
    }

    @DeleteMapping("/delete/{id}")
    public DeleteCarResponse deleteCars(@PathVariable String id){
        try {
            if(carService.deleteCarById(Integer.parseInt(id))){
                return new DeleteCarResponse("01",null);
            }else
                return new DeleteCarResponse("02","Car not found");
        }catch (Exception e){
            e.printStackTrace();
            return new DeleteCarResponse("99","An error ocurred, please try again.");
        }
    }

    @PostMapping("/create")
    public CreateCarResponse createCar(@RequestBody CarDetailDTO carDetailDto) {

        try {
            if (carService.addCar(carDetailDto))
                return new CreateCarResponse("01", null);
            else
                return new CreateCarResponse("02", "Car already exists");

        } catch (Exception e) {
            e.printStackTrace();
            return new CreateCarResponse("99", "An error ocurred, please try again");

        }

    }
}
