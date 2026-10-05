package mx.edu.utez.cotizacionA2.controller.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class RequestRentaDTO {

    @NotBlank(message = "El nombre del cliente es obligatorio")
    private String nombreCliente;

    @NotNull(message = "La edad del conductor es obligatoria")
    @Min(value = 18, message = "El conductor debe tener al menos 18 años")
    private Integer edadConductor;

    @NotBlank(message = "El tipo de vehículo es obligatorio")
    private String tipoVehiculo;

    @NotNull(message = "Los días de renta son obligatorios")
    @Min(value = 1, message = "Los días de renta deben ser al menos 1")
    @Max(value = 30, message = "La renta no puede superar los 30 días")
    private Integer diasRenta;

    @NotNull(message = "Los kilómetros estimados son obligatorios")
    @PositiveOrZero(message = "Los kilómetros estimados no pueden ser negativos")
    @Max(value = 5000, message = "Los kilómetros estimados no pueden superar los 5,000 km")
    private Integer kilometrosEstimados;

    @NotNull(message = "La indicación del seguro es obligatoria")
    private Boolean seguroCompleto;
}