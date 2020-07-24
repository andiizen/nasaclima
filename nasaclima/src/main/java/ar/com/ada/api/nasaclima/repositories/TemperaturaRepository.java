package ar.com.ada.api.nasaclima.repositories;

import java.util.List;
import ar.com.ada.api.nasaclima.entities.*;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TemperaturaRepository extends JpaRepository<Temperatura, Integer> {

    List<Temperatura> findByAnio(int anio);

    @Query("Select t FROM Temperatura t WHERE t.pais.codigoPais = :codigoPais")
    List<Temperatura> findAllByCodigoPais(@Param("codigoPais") int codigoPais);
    
}