package mx.edu.utez.cotizacionA2.controller;

import jakarta.validation.Valid;
import mx.edu.utez.cotizacionA2.controller.dto.RequestEnvioDTO;
import mx.edu.utez.cotizacionA2.controller.dto.ResponseEnvioDTO;
import mx.edu.utez.cotizacionA2.controller.dto.RequestRentaDTO;
import mx.edu.utez.cotizacionA2.controller.dto.ResponseRentaDTO;
import mx.edu.utez.cotizacionA2.controller.dto.RequestHospedajeDTO;
import mx.edu.utez.cotizacionA2.controller.dto.ResponseHospedajeDTO;
import mx.edu.utez.cotizacionA2.service.CotizacionService;
import mx.edu.utez.cotizacionA2.service.RentaService;
import mx.edu.utez.cotizacionA2.service.HospedajeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/cotizacion")
public class MyController {

    private final CotizacionService cotizacionService;
    private final RentaService rentaService;
    private final HospedajeService hospedajeService;

    public MyController(
            CotizacionService cotizacionService,
            RentaService rentaService,
            HospedajeService hospedajeService
    ) {
        this.cotizacionService = cotizacionService;
        this.rentaService = rentaService;
        this.hospedajeService = hospedajeService;
    }

    @PostMapping("/envio")
    public ResponseEntity<ResponseEnvioDTO> cotizarEnvio(@RequestBody @Valid RequestEnvioDTO payload) {
        return ResponseEntity
                .status(200)
                .body(cotizacionService.cotizarEnvio(payload));
    }

    @PostMapping("/renta")
    public ResponseEntity<ResponseRentaDTO> cotizarRenta(@RequestBody @Valid RequestRentaDTO payload) {
        return ResponseEntity
                .status(200)
                .body(rentaService.cotizarRenta(payload));
    }

    @PostMapping("/hospedaje")
    public ResponseEntity<ResponseHospedajeDTO> cotizarHospedaje(@RequestBody @Valid RequestHospedajeDTO payload) {
        return ResponseEntity
                .status(200)
                .body(hospedajeService.cotizarHospedaje(payload));
    }
}