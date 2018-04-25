package ru.shushpanov.weatherbroker.messageservice.service.impl;

import ru.shushpanov.weatherbroker.messageservice.service.MessageService;

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
public class MessageServiceImpl implements MessageService {

    /**
     * {@inheritDoc}
     */

    public  String createXmlMessage(Object xml) {
        String result = "";
        try (OutputStream os = new ByteArrayOutputStream()) {
            JAXBContext context = JAXBContext.newInstance(xml.getClass());
            Marshaller marshaller = context.createMarshaller();
            marshaller.marshal(xml, os);
            result = os.toString();
        } catch (JAXBException | IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * {@inheritDoc}
     */
    public Object readXmlMessage(Object model, String xml) {
        byte[] array = xml.getBytes();
        Object result = null;
        try (InputStream inputStream = new ByteArrayInputStream(array)) {
            JAXBContext jaxbContext = JAXBContext.newInstance(model.getClass());
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            result = unmarshaller.unmarshal(inputStream);
        } catch (JAXBException | IOException | ClassCastException e) {
            e.printStackTrace();
        }
        return result;
    }
}
