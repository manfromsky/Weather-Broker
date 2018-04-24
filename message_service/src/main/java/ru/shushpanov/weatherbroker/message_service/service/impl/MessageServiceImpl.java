package ru.shushpanov.weatherbroker.message_service.service.impl;

import ru.shushpanov.weatherbroker.message_service.service.MessageService;

import javax.ejb.Stateless;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

@Stateless
public class MessageServiceImpl implements MessageService {

    /**
     * {@inheritDoc}
     */
    @Override
    public String createXmlMessage(Object xml) {
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
    @Override
    public Object readXmlMessage(Object model, String xml) {
        byte[] array = xml.getBytes();
        try (InputStream inputStream = new ByteArrayInputStream(array)) {
            JAXBContext jaxbContext = JAXBContext.newInstance(model.getClass());
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            model = unmarshaller.unmarshal(inputStream);
        } catch (JAXBException | IOException e) {
            e.printStackTrace();
        }
        return model;
    }
}
