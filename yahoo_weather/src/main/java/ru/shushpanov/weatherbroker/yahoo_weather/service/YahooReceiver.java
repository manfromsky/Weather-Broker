package ru.shushpanov.weatherbroker.yahoo_weather.service;

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
 * Сервис принимает JMS сообщения с названием города и делает запрос погоды через Yahoo Weather Api
 */
@MessageDriven(name = "YahooReceiver", activationConfig = {
        @ActivationConfigProperty(propertyName = "destinationLookup", propertyValue = "java:jboss/weatherTopic"),
        @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Topic"),
        @ActivationConfigProperty(propertyName = "acknowledgeMode", propertyValue = "Auto-acknowledge")})
public class YahooReceiver implements MessageListener {

    private final Logger log = LoggerFactory.getLogger(YahooReceiver.class);

    private ForecastService service;

    @Inject
    public YahooReceiver(ForecastService service) {
        this.service = service;
    }

    public YahooReceiver() {
    }

    @Override
    public void onMessage(Message message) {

        String city = "";
        try {
            city = ((TextMessage) message).getText();
            service.createAndSendMessage(city);
            log.info("Received message: {}", city);
        } catch (JMSException | WeatherBrokerServiceException e) {
            throw new RuntimeException(
                    String.format("An error occurred while reading jms message containing city: %s", city),
                    e
            );
        }
    }
}
