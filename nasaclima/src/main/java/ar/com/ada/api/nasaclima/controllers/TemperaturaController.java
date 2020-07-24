package ar.com.ada.api.nasaclima.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import ar.com.ada.api.nasaclima.entities.*;
import ar.com.ada.api.nasaclima.models.requests.*;
import ar.com.ada.api.nasaclima.models.responses.*;
import ar.com.ada.api.nasaclima.services.*;

@RestController
public class TemperaturaController {

    @Autowired
    TemperaturaService tempService;

    @Autowired
    PaisService paisService;

    @PostMapping("/temperaturas")
    public ResponseEntity<GenericResponse> crearTemperatura(@RequestBody TemperaturaRequest tempRequest) {

        Temperatura temperatura = tempService.crearTemperatura(tempRequest.codigoPais, tempRequest.anio,
                tempRequest.grados);

        GenericResponse res = new GenericResponse();

        if (temperatura != null) {
            res.isOk = true;
            res.id = tempRequest.codigoPais;
            res.message = "Se creo una temperatura con exito";

            return ResponseEntity.ok(res);

        } else {
            res.isOk = false;
            res.message = "Ya se ha cargado una temperatura en el año especificado";

            return ResponseEntity.badRequest().body(res);
        }

    }

    @GetMapping("/temperaturas/paises/{codigoPais}")
    public ResponseEntity<?> listarTemperaturasPorPais(@PathVariable int codigoPais) {

        Pais pais = paisService.buscarPorId(codigoPais);

        if (pais != null) {

            List<Temperatura> temperaturasPorPais = new ArrayList();

            temperaturasPorPais = pais.getTemperaturas();

            return ResponseEntity.ok(temperaturasPorPais);
            
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

    @DeleteMapping("/temperaturas/{id}")
    public ResponseEntity<?> borrarTemperatura(@PathVariable int id){

        Temperatura temperatura = tempService.buscarPorId(id);

        if(temperatura != null){

            tempService.borrarTemperatura(temperatura);

            GenericResponse res = new GenericResponse();
            res.isOk = true;
            res.id = temperatura.getTemperaturaId();
            res.message = "La temperatura fue eliminada con exito";

       return ResponseEntity.ok(res); 
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

    @GetMapping("/temperaturas/anios/{anio}")
    public ResponseEntity<?> listarTemperaturasPorAnio(@PathVariable int anio){

        List<TemperaturaAnioResponse> temp = new ArrayList<>(); 

        temp = tempService.listarTemperaturasPorAnio(anio);

        if (temp.isEmpty()) {            

            GenericResponse resp = new GenericResponse();
            resp.isOk = false;            
            resp.message = "No hay resultados";

            return ResponseEntity.ok(resp);
        }

        return ResponseEntity.ok(temp); 

    }

    @GetMapping("/temperaturas/maximas/{codigoPais}")
    public ResponseEntity<?> listarTemperaturasMaximas(@PathVariable int codigoPais){

        TemperaturaMax  tempMax = tempService.buscarTempMax(codigoPais);
        
        return ResponseEntity.ok(tempMax); 

    }

}