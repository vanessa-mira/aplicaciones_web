package mx.edu.utez.cotizacionA2.service;

import mx.edu.utez.cotizacionA2.controller.dto.RequestEnvioDTO;
import mx.edu.utez.cotizacionA2.controller.dto.ResponseEnvioDTO;
import mx.edu.utez.cotizacionA2.exception.customException.CustomBadRequestException;
import org.springframework.stereotype.Service;

@Service
public class CotizacionService {

    public ResponseEnvioDTO cotizarEnvio(RequestEnvioDTO data) {
        // Validar tipo de envío
        if (!data.getTipoEnvio().equalsIgnoreCase("ESTANDAR") &&
                !data.getTipoEnvio().equalsIgnoreCase("EXPRESS") &&
                !data.getTipoEnvio().equalsIgnoreCase("MISMO_DIA")) {
            throw new CustomBadRequestException("El tipo de envío no es válido");
        }

        double volumen = data.getLargoCm() * data.getAnchoCm() * data.getAltoCm();

        if (volumen > 1000000) {
            throw new CustomBadRequestException("El paquete supera el volumen máximo permitido (1,000,000 cm³)");
        }

        double costo = 80.0;
        costo += data.getPesoKg() * 12.0;

        if (volumen > 50000) {
            costo += 100.0;
        }

        if ("EXPRESS".equalsIgnoreCase(data.getTipoEnvio())) {
            costo += costo * 0.40;
        } else if ("MISMO_DIA".equalsIgnoreCase(data.getTipoEnvio())) {
            costo += costo * 0.70;
        }

        if (data.getValorDeclarado() > 10000) {
            costo += data.getValorDeclarado() * 0.02;
        }

        ResponseEnvioDTO respuesta = new ResponseEnvioDTO();
        respuesta.setVolumenCm3(volumen);
        respuesta.setCostoTotal(costo);
        respuesta.setMensaje("Cotización calculada exitosamente");

        return respuesta;
    }
}