package com.concretepage.SpringBootWSProducer.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import com.concretepage.SpringBootWSProducer.entity.Persona;

public interface PersonaRepository extends CrudRepository<Persona, Long>{
 Persona findByPersonaId(long personaId);
 
 List<Persona> findByNombreAndApellido(String nombre, String apellido);
}
