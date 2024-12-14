package DAWI_EF.TrejoBurgaJuanAntony.response;

import DAWI_EF.TrejoBurgaJuanAntony.DTO.CarDetailDTO;

public record FindCarResponse(String code,
                              String error,
                              CarDetailDTO car) {
}
