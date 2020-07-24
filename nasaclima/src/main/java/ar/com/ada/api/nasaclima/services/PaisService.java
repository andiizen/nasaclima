package ar.com.ada.api.nasaclima.services;

import java.util.*;

import ar.com.ada.api.nasaclima.entities.*;
import ar.com.ada.api.nasaclima.repositories.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaisService {

    @Autowired
    PaisRepository paisRepo;

    public void crearPais(int codigoPais, String nombre) {

        Pais pais = new Pais();

        pais.setCodigoPais(codigoPais);
        pais.setNombre(nombre);

        this.grabar(pais);

    }

    public void grabar(Pais pais) {
        paisRepo.save(pais);
    }

    public List<Pais> listarPaises() {
        return paisRepo.findAll();
    }

    public Pais buscarPorId(int codigoPais) {

        Optional<Pais> pais = paisRepo.findById(codigoPais);

        if (pais.isPresent()) {
            return pais.get();
        }
        return null;

    }

    public void actualizarNombrePais(Pais paisOriginal, String nombre) {

        paisOriginal.setNombre(nombre);

        this.grabar(paisOriginal);

    }

}