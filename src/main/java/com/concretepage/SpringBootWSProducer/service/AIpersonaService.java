package com.concretepage.SpringBootWSProducer.service;

import java.util.List;

import com.concretepage.SpringBootWSProducer.entity.Persona;

public interface AIpersonaService {
	List<Persona> getAllPersonas();
    Persona getPersonaById(long personaId);
    boolean addPersona(Persona persona);
    void updatePersona(Persona persona);
    void deletePersona(Persona persona);
}
