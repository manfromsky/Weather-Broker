package ru.shushpanov.weatherbroker.database.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.shushpanov.weatherbroker.error.exeption.WeatherBrokerServiceException;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.inject.Inject;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

/**
 * Сервис получающий JMS сообщения с информацией о прогнозе погоды и делающий запрос для ее сохранения в базу данных
 */
@MessageDriven(name = "DataReceiver", activationConfig = {
        @ActivationConfigProperty(propertyName = "destinationLookup", propertyValue = "java:jboss/weatherQueue"),
        @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue"),
        @ActivationConfigProperty(propertyName = "acknowledgeMode", propertyValue = "Auto-acknowledge")})
public class DataReceiver implements MessageListener {

    private final Logger log = LoggerFactory.getLogger(DataReceiver.class);

    private DataService service;

    @Inject
    public DataReceiver(DataService service) {
        this.service = service;
    }

    public DataReceiver() {
    }

    @Override
    public void onMessage(Message message) {
        String xml = "";
        try {
            xml = ((TextMessage) message).getText();
            service.save(xml);
            log.info("Received message: " + xml);
        } catch (JMSException | WeatherBrokerServiceException e) {
            throw new RuntimeException(String.format("An error occurred while reading jms message " +
                    "containing xml: %s", xml), e);
        }
    }
}
