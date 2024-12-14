package DAWI_EF.TrejoBurgaJuanAntony.response;

import DAWI_EF.TrejoBurgaJuanAntony.DTO.CarDTO;

public record FindCarsResponse(String code,
                               String error,
                               Iterable<CarDTO> cars) {
}
