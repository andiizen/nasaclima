package ar.com.ada.api.nasaclima.services;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.com.ada.api.nasaclima.entities.Pais;
import ar.com.ada.api.nasaclima.repos.PaisRepository;

@Service
public class PaisService {
    @Autowired
    PaisRepository repo;

    // Creacion de un pais
    public void crearPais(Pais pais) {
        repo.save(pais);

    }

    // Lista de paises sin temperaturas
    public List<Pais> listarPaises() {

        return repo.findAll();

    }

    // Info particular de un pais.

    public Pais devolverPaisPorId(int codigoPais) {

        Optional<Pais> cOptional = repo.findById(codigoPais);

        if (cOptional.isPresent()) {

            return cOptional.get();

        }
        return null;
    }

    // Actualizar nombre pais

    public void actualizarNombrePais(Pais paisOriginal, String nombre) {

        paisOriginal.setNombre(nombre);

        //hasta aca llegué

    }

}