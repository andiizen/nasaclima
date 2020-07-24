package ar.com.ada.api.nasaclima.models.responses;

import java.util.*;

public class TemperaturaResponse {

    private int codigoPais; 
    private List temperaturas = new ArrayList<>(); 

    public int getCodigoPais() {
        return codigoPais;
    }

    public void setCodigoPais(int codigoPais) {
        this.codigoPais = codigoPais;
    }

    public List getTemperaturas() {
        return temperaturas;
    }

    public void setTemperaturas(List temperaturas) {
        this.temperaturas = temperaturas;
    }
    
}