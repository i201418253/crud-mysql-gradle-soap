package com.concretepage.SpringBootWSProducer.endpoints;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.concretepage.SpringBootWSProducer.entity.Persona;
import com.concretepage.SpringBootWSProducer.personas.AddPersonaRequest;
import com.concretepage.SpringBootWSProducer.personas.AddPersonaResponse;
import com.concretepage.SpringBootWSProducer.personas.DeletePersonaRequest;
import com.concretepage.SpringBootWSProducer.personas.DeletePersonaResponse;
import com.concretepage.SpringBootWSProducer.personas.GetAllPersonaResponse;
import com.concretepage.SpringBootWSProducer.personas.GetPersonaByIdRequest;
import com.concretepage.SpringBootWSProducer.personas.GetPersonaByIdResponse;
import com.concretepage.SpringBootWSProducer.personas.PersonaInfo;
import com.concretepage.SpringBootWSProducer.personas.ServiceStatus;
import com.concretepage.SpringBootWSProducer.personas.UpdatePersonaRequest;
import com.concretepage.SpringBootWSProducer.personas.UpdatePersonaResponse;
import com.concretepage.SpringBootWSProducer.service.AIpersonaService;

@Endpoint
public class PersonaEndpoint {
	private static final String NAMESPACE_URI = "http://www.concretepage.com/persona-ws";
	@Autowired
	private AIpersonaService personaService;

	/*Metodo listar por ID */
	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "getPersonaByIdRequest")
	@ResponsePayload
	public GetPersonaByIdResponse getPersona(@RequestPayload GetPersonaByIdRequest request) {
		GetPersonaByIdResponse response = new GetPersonaByIdResponse();
		PersonaInfo personaInfo = new PersonaInfo();
		BeanUtils.copyProperties(personaService.getPersonaById(request.getPersonaId()), personaInfo);
		response.setPersonaInfo(personaInfo);

		return response;
	}

	/* Listar todos */
	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "getAllPersonaRequest")
	@ResponsePayload
	public GetAllPersonaResponse getAllPersona() {
		GetAllPersonaResponse response = new GetAllPersonaResponse();
		List<PersonaInfo> personaInfolist = new ArrayList<>();
		List<Persona> personaList = personaService.getAllPersonas();
		for (int i = 0; i < personaList.size(); i++) {
			PersonaInfo obj = new PersonaInfo();
			BeanUtils.copyProperties(personaList.get(i), obj);
			personaInfolist.add(obj);
		}
		response.getPersonaInfo().addAll(personaInfolist);
		return response;
	}

	/* Metodo para agregar personas */
	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "addPersonaRequest")
	@ResponsePayload
	public AddPersonaResponse addPersona(@RequestPayload AddPersonaRequest request) {
		AddPersonaResponse response = new AddPersonaResponse();
		ServiceStatus serviceStatus = new ServiceStatus();
		Persona persona = new Persona();
		persona.setNombre(request.getNombre());
		persona.setApellido(request.getApellido());
		boolean flag = personaService.addPersona(persona);
		if (flag == false) {
			serviceStatus.setStatusCode("CONFLICT");
			serviceStatus.setMessage("Contenido ya disponible");
			response.setServiceStatus(serviceStatus);
		} else {
			PersonaInfo personaInfo = new PersonaInfo();
			BeanUtils.copyProperties(persona, personaInfo);
			response.setPersonaInfo(personaInfo);
			serviceStatus.setStatusCode("SUCCESS");
			serviceStatus.setMessage("Contenido añadido con éxito");
			response.setServiceStatus(serviceStatus);
		}
		return response;
	}

	/* metodo de eliminacion de personas */
	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "updatePersonaRequest")
	@ResponsePayload
	public UpdatePersonaResponse updatePersona(@RequestPayload UpdatePersonaRequest request) {
		Persona persona = new Persona();
		BeanUtils.copyProperties(request.getPersonaInfo(), persona);
		personaService.updatePersona(persona);
		ServiceStatus serviceStatus = new ServiceStatus();
		serviceStatus.setStatusCode("SUCCESS");
		serviceStatus.setMessage("Se ah actualizado con exito");
		UpdatePersonaResponse response = new UpdatePersonaResponse();
		response.setServiceStatus(serviceStatus);
		return response;

	}

	/* Metodo de elimiancion de personas en el crud */
	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "deletePersonaRequest")
	@ResponsePayload
	public DeletePersonaResponse deletePersona(@RequestPayload DeletePersonaRequest request) {
		Persona persona = personaService.getPersonaById(request.getPersonaId());
		ServiceStatus serviceStatus = new ServiceStatus();
		if (persona == null) {
			serviceStatus.setStatusCode("FAIL");
			serviceStatus.setMessage("El contenido no disponible");
		} else {
			personaService.deletePersona(persona);
			serviceStatus.setStatusCode("SUCCESS");
			serviceStatus.setMessage("El contenido eleiminado con éxito!");
		}
		DeletePersonaResponse response = new DeletePersonaResponse();
		response.setServiceStatus(serviceStatus);
		return response;

	}

}
