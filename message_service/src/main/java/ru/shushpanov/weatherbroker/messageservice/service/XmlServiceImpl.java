package ru.shushpanov.weatherbroker.messageservice.service;

import ru.shushpanov.weatherbroker.error.exeption.WeatherBrokerServiceException;
import ru.shushpanov.weatherbroker.messageservice.model.XmlModel;

import javax.enterprise.context.RequestScoped;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

@RequestScoped
public class XmlServiceImpl implements XmlService {

    /**
     * {@inheritDoc}
     */
    public String createXmlMessage(XmlModel xml) throws WeatherBrokerServiceException {
        try (OutputStream os = new ByteArrayOutputStream()) {
            JAXBContext context = JAXBContext.newInstance(xml.getClass());
            Marshaller marshaller = context.createMarshaller();
            marshaller.marshal(xml, os);
            return os.toString();
        } catch (JAXBException | IOException e) {
            throw new WeatherBrokerServiceException(
                    String.format("Error trying to create xml from objects: %s", xml),
                    e
            );
        }
    }

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    public <T extends XmlModel> T readXmlMessage(String xml, Class<T> modelClass) throws WeatherBrokerServiceException {
        byte[] array = xml.getBytes();
        try (InputStream inputStream = new ByteArrayInputStream(array)) {
            JAXBContext jaxbContext = JAXBContext.newInstance(modelClass);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            Object result = unmarshaller.unmarshal(inputStream);
            if (result == null || !result.getClass().isAssignableFrom(modelClass)) {
                throw new WeatherBrokerServiceException(
                        String.format("Failed to generate object from xml string: %s", xml)
                );
            }
            return (T) result;
        } catch (JAXBException | IOException | ClassCastException e) {
            throw new WeatherBrokerServiceException(
                    String.format("An error occurred while trying to restore an object from an xml string: %s", xml)
            );
        }
    }
}
