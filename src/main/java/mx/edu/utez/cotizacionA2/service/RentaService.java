package mx.edu.utez.cotizacionA2.service;

import mx.edu.utez.cotizacionA2.controller.dto.RequestRentaDTO;
import mx.edu.utez.cotizacionA2.controller.dto.ResponseRentaDTO;
import mx.edu.utez.cotizacionA2.exception.customException.CustomBadRequestException;
import org.springframework.stereotype.Service;

@Service
public class RentaService {

    public ResponseRentaDTO cotizarRenta(RequestRentaDTO data) {
        String tipo = data.getTipoVehiculo().toUpperCase();

        if (!tipo.equals("COMPACTO") && !tipo.equals("SEDAN") && !tipo.equals("SUV") && !tipo.equals("CAMIONETA")) {
            throw new CustomBadRequestException("El tipo de vehículo debe ser COMPACTO, SEDAN, SUV o CAMIONETA");
        }


        if (tipo.equals("CAMIONETA") && data.getEdadConductor() < 25) {
            throw new CustomBadRequestException("Para rentar una CAMIONETA el conductor debe tener al menos 25 años");
        }

        double costoDiario = 0.0;
        switch (tipo) {
            case "COMPACTO":
                costoDiario = 550.0;
                break;
            case "SEDAN":
                costoDiario = 700.0;
                break;
            case "SUV":
                costoDiario = 950.0;
                break;
            case "CAMIONETA":
                costoDiario = 1200.0;
                break;
        }

        double costoRenta = costoDiario * data.getDiasRenta();

        if (data.getDiasRenta() >= 7) {
            costoRenta = costoRenta * 0.90;
        }

        int kmIncluidos = data.getDiasRenta() * 100;
        double cargoKmAdicionales = 0.0;
        if (data.getKilometrosEstimados() > kmIncluidos) {
            cargoKmAdicionales = (data.getKilometrosEstimados() - kmIncluidos) * 4.0;
        }

        double cargoEdad = 0.0;
        if (data.getEdadConductor() >= 18 && data.getEdadConductor() <= 24) {
            cargoEdad = (costoRenta + cargoKmAdicionales) * 0.15;
        }

        double cargoSeguro = 0.0;
        if (Boolean.TRUE.equals(data.getSeguroCompleto())) {
            cargoSeguro = 180.0 * data.getDiasRenta();
        }

        double total = costoRenta + cargoKmAdicionales + cargoEdad + cargoSeguro;

        ResponseRentaDTO respuesta = new ResponseRentaDTO();
        respuesta.setCliente(data.getNombreCliente());
        respuesta.setCostoTotal(total);
        respuesta.setMensaje("Cotización de renta calculada exitosamente");

        return respuesta;
    }
}