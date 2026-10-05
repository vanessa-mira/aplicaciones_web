package mx.edu.utez.cotizacionA2.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ResponseEnvioDTO {
    private Double volumenCm3;
    private Double costoTotal;
    private String mensaje;
}