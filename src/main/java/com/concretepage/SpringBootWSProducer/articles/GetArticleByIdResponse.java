//
// Este archivo ha sido generado por la arquitectura JavaTM para la implantación de la referencia de enlace (JAXB) XML v2.2.7 
// Visite <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Todas las modificaciones realizadas en este archivo se perderán si se vuelve a compilar el esquema de origen. 
// Generado el: 2019.02.15 a las 12:47:47 PM PET 
//


package com.concretepage.SpringBootWSProducer.articles;

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
 *         &lt;element name="articleInfo" type="{http://www.concretepage.com/article-ws}articleInfo"/>
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
    "articleInfo"
})
@XmlRootElement(name = "getArticleByIdResponse")
public class GetArticleByIdResponse {

    @XmlElement(required = true)
    protected ArticleInfo articleInfo;

    /**
     * Obtiene el valor de la propiedad articleInfo.
     * 
     * @return
     *     possible object is
     *     {@link ArticleInfo }
     *     
     */
    public ArticleInfo getArticleInfo() {
        return articleInfo;
    }

    /**
     * Define el valor de la propiedad articleInfo.
     * 
     * @param value
     *     allowed object is
     *     {@link ArticleInfo }
     *     
     */
    public void setArticleInfo(ArticleInfo value) {
        this.articleInfo = value;
    }

}
