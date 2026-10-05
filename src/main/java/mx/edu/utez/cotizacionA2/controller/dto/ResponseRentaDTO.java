package mx.edu.utez.cotizacionA2.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ResponseRentaDTO {
    private String cliente;
    private Double costoTotal;
    private String mensaje;
}