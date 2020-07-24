package ar.com.ada.api.nasaclima.services;

import java.util.*;

import ar.com.ada.api.nasaclima.entities.*;
import ar.com.ada.api.nasaclima.models.responses.*;
import ar.com.ada.api.nasaclima.repositories.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TemperaturaService {

    @Autowired
    TemperaturaRepository tempRepo;

    @Autowired
    PaisService paisService;

    public Temperatura crearTemperatura(int codigoPais, int anio, double grados) {

        Temperatura temperatura = new Temperatura();

        temperatura.setPais(paisService.buscarPorId(codigoPais));
        temperatura.setAnio(anio);
        temperatura.setGrados(grados);

        if (existe(temperatura)) {

            this.grabar(temperatura);

            return temperatura;

        } else {

            return null;

        }

    }

    public boolean existe(Temperatura temperatura) {

        List<Temperatura> temperaturasExistentes = new ArrayList<>();

        temperaturasExistentes = tempRepo.findAll();

        for (int i = 0; i < temperaturasExistentes.size(); i++) {

            int codigoP = temperaturasExistentes.get(i).getPais().getCodigoPais();
            int anioT = temperaturasExistentes.get(i).getAnio();

            if (codigoP == temperatura.getPais().getCodigoPais() && anioT == temperatura.getAnio()) {

                return false;
            }

        }
        return true;

    }

    public void grabar(Temperatura temperatura) {
        tempRepo.save(temperatura);
    }

    public Temperatura buscarPorId(int id) {

        Optional<Temperatura> temperatura = tempRepo.findById(id);

        if (temperatura.isPresent()) {
            return temperatura.get();
        }
        return null;

    }

    public void borrarTemperatura(Temperatura temperatura) {

        temperatura.setAnio(0);

        this.grabar(temperatura);

    }

    public List<Temperatura> buscarPorAnio(int anio) {

        return tempRepo.findByAnio(anio);

    }

    public List<TemperaturaAnioResponse> listarTemperaturasPorAnio(int anio) {

        List<Temperatura> temperaturas = new ArrayList();
        temperaturas = this.buscarPorAnio(anio);

        List<TemperaturaAnioResponse> temps = new ArrayList();

        for (int i = 0; i < temperaturas.size(); i++) {

            TemperaturaAnioResponse t = new TemperaturaAnioResponse();
            t.nombrePais = temperaturas.get(i).getPais().getNombre();
            t.grados = temperaturas.get(i).getGrados();

            temps.add(t);

        }

        return temps;

    }

    public TemperaturaMax buscarTempMax(int codigoPais) {

        TemperaturaMax tempMax = new TemperaturaMax();

        tempMax.temperaturaMaxima = 0.00;

        List<Temperatura> temperaturas = new ArrayList<>();
        temperaturas = buscarPorPais(codigoPais);

        for (int i = 0; i < temperaturas.size(); i++) {

            if (temperaturas.get(i).getGrados() > tempMax.temperaturaMaxima) {

                tempMax.nombrePais = temperaturas.get(i).getPais().getNombre();
                tempMax.temperaturaMaxima = temperaturas.get(i).getGrados();
                tempMax.anio = temperaturas.get(i).getAnio();
                
            }
        }

        return tempMax;

    }

    public List<Temperatura> buscarPorPais(int codigoPais){

        return tempRepo.findAllByCodigoPais(codigoPais);

    }

}