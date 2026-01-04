package com.bearclawvisions.utils;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;

import java.io.StringReader;
import java.io.StringWriter;

public class XmlUtils {

    /**
     * Serializes an object to XML string
     *
     * @param <T>                  Type of object to serialize
     * @param obj                  Object to serialize
     * @param omitXmlDeclaration   Whether to omit XML declaration
     * @param indent               Whether to format with indentation
     * @return XML string representation
     * @throws JAXBException if serialization fails
     */
    public static <T> String toXml(T obj, boolean omitXmlDeclaration, boolean indent) throws JAXBException {
        if (obj == null) {
            throw new IllegalArgumentException("Object cannot be null");
        }

        try {
            JAXBContext context = JAXBContext.newInstance(obj.getClass());
            Marshaller marshaller = context.createMarshaller();

            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, indent);
            marshaller.setProperty(Marshaller.JAXB_FRAGMENT, omitXmlDeclaration);
            marshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");

            StringWriter stringWriter = new StringWriter();
            marshaller.marshal(obj, stringWriter);

            return stringWriter.toString();
        } catch (JAXBException e) {
            throw new JAXBException("Failed to serialize object to XML", e);
        }
    }

    /**
     * Convenience method that serializes with default settings
     * (omit XML declaration and indent enabled)
     *
     * @param <T> Type of object to serialize
     * @param obj Object to serialize
     * @return XML string representation
     * @throws JAXBException if serialization fails
     */
    public static <T> String toXml(T obj) throws JAXBException {
        return toXml(obj, true, true);
    }

    /**
     * Deserializes XML string to object of type T
     *
     * @param <T>       Type to deserialize to
     * @param xmlString XML string to deserialize
     * @param type      The class type to deserialize to
     * @return Deserialized object
     * @throws JAXBException if deserialization fails
     */
    public static <T> T fromXml(String xmlString, Class<T> type) throws JAXBException {
        if (xmlString == null || xmlString.isBlank()) {
            throw new IllegalArgumentException("XML string cannot be null or empty");
        }

        JAXBContext context = JAXBContext.newInstance(type);
        Unmarshaller unmarshaller = context.createUnmarshaller();

        StringReader stringReader = new StringReader(xmlString);
        Object result = unmarshaller.unmarshal(stringReader);

        if (result == null) {
            throw new IllegalStateException("Failed to deserialize XML to type " + type.getSimpleName());
        }

        return type.cast(result);
    }
}
