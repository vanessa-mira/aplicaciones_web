package mx.edu.utez.cotizacionA2.service;

import mx.edu.utez.cotizacionA2.controller.dto.RequestHospedajeDTO;
import mx.edu.utez.cotizacionA2.controller.dto.ResponseHospedajeDTO;
import mx.edu.utez.cotizacionA2.exception.customException.CustomBadRequestException;
import org.springframework.stereotype.Service;

@Service
public class HospedajeService {

    public ResponseHospedajeDTO cotizarHospedaje(RequestHospedajeDTO data) {
        String tipo = data.getTipoHabitacion().toUpperCase();
        String temp = data.getTemporada().toUpperCase();

        if (!tipo.equals("INDIVIDUAL") && !tipo.equals("DOBLE") && !tipo.equals("SUITE")) {
            throw new CustomBadRequestException("El tipo de habitación debe ser INDIVIDUAL, DOBLE o SUITE");
        }


        if (!temp.equals("BAJA") && !temp.equals("REGULAR") && !temp.equals("ALTA")) {
            throw new CustomBadRequestException("La temporada debe ser BAJA, REGULAR o ALTA");
        }

        if (tipo.equals("INDIVIDUAL") && data.getNumeroHuespedes() > 1) {
            throw new CustomBadRequestException("La habitación INDIVIDUAL solo permite máximo 1 persona");
        }
        if (tipo.equals("DOBLE") && data.getNumeroHuespedes() > 2) {
            throw new CustomBadRequestException("La habitación DOBLE solo permite máximo 2 personas");
        }
        if (tipo.equals("SUITE") && data.getNumeroHuespedes() > 4) {
            throw new CustomBadRequestException("La habitación SUITE solo permite máximo 4 personas");
        }

        double costoNoche = 0.0;
        switch (tipo) {
            case "INDIVIDUAL":
                costoNoche = 700.0;
                break;
            case "DOBLE":
                costoNoche = 1100.0;
                break;
            case "SUITE":
                costoNoche = 1800.0;
                break;
        }

        double costoHospedaje = costoNoche * data.getNumeroNoches();


        if (temp.equals("BAJA")) {
            costoHospedaje -= costoHospedaje * 0.10; // Descuento del 10%
        } else if (temp.equals("ALTA")) {
            costoHospedaje += costoHospedaje * 0.25; // Cargo del 25%
        }


        if (data.getNumeroNoches() >= 7) {
            costoHospedaje -= costoHospedaje * 0.08;
        }

        double costoDesayuno = 0.0;
        if (Boolean.TRUE.equals(data.getIncluyeDesayuno())) {
            costoDesayuno = data.getNumeroHuespedes() * data.getNumeroNoches() * 150.0;
        }


        double costoEstacionamiento = 0.0;
        if (Boolean.TRUE.equals(data.getIncluyeEstacionamiento())) {
            costoEstacionamiento = data.getNumeroNoches() * 100.0;
        }


        double subtotal = costoHospedaje + costoDesayuno + costoEstacionamiento;


        double impuesto = subtotal * 0.04;


        double total = subtotal + impuesto;

        ResponseHospedajeDTO respuesta = new ResponseHospedajeDTO();
        respuesta.setHuesped(data.getNombreHuesped());
        respuesta.setSubtotal(subtotal);
        respuesta.setImpuesto(impuesto);
        respuesta.setCostoTotal(total);
        respuesta.setMensaje("Cotización de hospedaje calculada exitosamente");

        return respuesta;
    }
}