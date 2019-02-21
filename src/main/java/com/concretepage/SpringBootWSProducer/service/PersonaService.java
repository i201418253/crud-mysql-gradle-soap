package com.concretepage.SpringBootWSProducer.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import com.concretepage.SpringBootWSProducer.entity.Persona;
import com.concretepage.SpringBootWSProducer.repository.PersonaRepository;

@Service
public class PersonaService implements AIpersonaService {

	private PersonaRepository personaRepository;

	@Override
	public List<Persona> getAllPersonas() {
		List<Persona> list = new ArrayList<>();
		personaRepository.findAll().forEach(e -> list.add(e));
		return list;
	}

	@Override
	public Persona getPersonaById(long personaId) {
		Persona obj = personaRepository.findByPersonaId(personaId);
		return obj;
	}

	@Override
	public synchronized boolean addPersona(Persona persona) {
		List<Persona> list = personaRepository.findByNombreAndApellido(persona.getNombre(), persona.getApellido());
		if (list.size() > 0) {
			return false;
		} else {
			persona = personaRepository.save(persona);
			return true;
		}
	}

	@Override
	public void updatePersona(Persona persona) {
		personaRepository.save(persona);

	}

	@Override
	public void deletePersona(Persona persona) {
		personaRepository.delete(persona);

	}

}
