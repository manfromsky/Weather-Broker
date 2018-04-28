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
        } catch (JMSException e) {
            throw new RuntimeException("Произошла ошибка при чтении jms сообщения содержащего city: "
                    + city, e);
        }
        log.info("Received message: " + city);
        try {
            service.createAndSendMessage(city);
        } catch (WeatherBrokerServiceException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }
}
