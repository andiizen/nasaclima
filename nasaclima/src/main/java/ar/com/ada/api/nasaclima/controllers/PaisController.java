package ar.com.ada.api.nasaclima.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import ar.com.ada.api.nasaclima.entities.*;
import ar.com.ada.api.nasaclima.models.requests.PaisRequest;
import ar.com.ada.api.nasaclima.models.responses.*;
import ar.com.ada.api.nasaclima.services.*;

@RestController
public class PaisController {

    @Autowired
    PaisService paisService;

    @PostMapping("/paises")
    public ResponseEntity<GenericResponse> crearPais(@RequestBody PaisRequest pais) {

        paisService.crearPais(pais.codigoPais, pais.nombre);

        GenericResponse res = new GenericResponse();
        res.isOk = true;
        res.id = pais.codigoPais;
        res.message = "Se creó un país exitosamente";

        return ResponseEntity.ok(res);

    }

    @GetMapping("/paises")
    public ResponseEntity<List<Pais>> listarPaises() {
        
        return ResponseEntity.ok(paisService.listarPaises()); 
 
    } 

    @GetMapping("/paises/{codigoPais}")
    public ResponseEntity<Pais> buscarPorId(@PathVariable int codigoPais){

        Pais pais = paisService.buscarPorId(codigoPais);

        if (pais != null) {
            return ResponseEntity.ok(pais);
        }
       
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

    @PutMapping("/paises/{codigoPais}")
    public ResponseEntity<GenericResponse> actualizarNombrePais(@PathVariable int codigoPais, @RequestBody String nombre) {

        Pais paisOriginal = paisService.buscarPorId(codigoPais);

        if (paisOriginal != null) {

            paisService.actualizarNombrePais(paisOriginal, nombre);

            GenericResponse res = new GenericResponse();
            res.isOk = true;
            res.id = codigoPais;
            res.message = "Se ha actualizado un país con éxito";
    
            return ResponseEntity.ok(res);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

}