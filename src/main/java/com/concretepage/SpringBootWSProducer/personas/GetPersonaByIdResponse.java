//
// Este archivo ha sido generado por la arquitectura JavaTM para la implantación de la referencia de enlace (JAXB) XML v2.2.7 
// Visite <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Todas las modificaciones realizadas en este archivo se perderán si se vuelve a compilar el esquema de origen. 
// Generado el: 2019.02.21 a las 05:59:04 PM PET 
//


package com.concretepage.SpringBootWSProducer.personas;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para anonymous complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="personaInfo" type="{http://www.concretepage.com/persona-ws}personaInfo"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "personaInfo"
})
@XmlRootElement(name = "getPersonaByIdResponse")
public class GetPersonaByIdResponse {

    @XmlElement(required = true)
    protected PersonaInfo personaInfo;

    /**
     * Obtiene el valor de la propiedad personaInfo.
     * 
     * @return
     *     possible object is
     *     {@link PersonaInfo }
     *     
     */
    public PersonaInfo getPersonaInfo() {
        return personaInfo;
    }

    /**
     * Define el valor de la propiedad personaInfo.
     * 
     * @param value
     *     allowed object is
     *     {@link PersonaInfo }
     *     
     */
    public void setPersonaInfo(PersonaInfo value) {
        this.personaInfo = value;
    }

}
