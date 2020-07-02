package ar.com.ada.api.nasaclima.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ar.com.ada.api.nasaclima.entities.*;

@Repository
public interface TemperaturaRepository extends JpaRepository<Temperatura, Integer> {

}