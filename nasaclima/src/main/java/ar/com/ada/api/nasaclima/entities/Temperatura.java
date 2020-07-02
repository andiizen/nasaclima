package ar.com.ada.api.nasaclima.entities;

import java.math.BigDecimal;
import javax.persistence.*;

@Entity
@Table(name = "temperatura")
public class Temperatura {
    @Id
    @Column(name = "temperatura_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int temperaturaId;
    private int año;
    private BigDecimal grados;
    @Column(name = "codigo_pais")
    private int codigoPais;
    @ManyToOne
    @JoinColumn(name = "codigo_pais", referencedColumnName = "codigo_pais")
    private Pais pais;

    public int getTemperaturaId() {
        return temperaturaId;
    }

    public void setTemperaturaId(int temperaturaId) {
        this.temperaturaId = temperaturaId;
    }

    public int getAño() {
        return año;
    }

    public void setAño(int año) {
        this.año = año;
    }

    public BigDecimal getGrados() {
        return grados;
    }

    public void setGrados(BigDecimal grados) {
        this.grados = grados;
    }

    public int getCodigoPais() {
        return codigoPais;
    }

    public void setCodigoPais(int codigoPais) {
        this.codigoPais = codigoPais;
    }

    public Pais getPais() {
        return pais;
    }

    public void setPais(Pais pais) {
        this.pais = pais;
        this.pais.getTemperaturas().add(this);
    }

}