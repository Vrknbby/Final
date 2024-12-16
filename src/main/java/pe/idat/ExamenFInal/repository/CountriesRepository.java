package pe.idat.ExamenFInal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.idat.ExamenFInal.model.Countries;

public interface CountriesRepository extends JpaRepository<Countries, String> { }
