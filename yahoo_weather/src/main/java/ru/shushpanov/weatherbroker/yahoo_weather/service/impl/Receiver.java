package ru.shushpanov.weatherbroker.yahoo_weather.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

@MessageDriven(name = "Receiver", activationConfig = {
        @ActivationConfigProperty(propertyName = "destinationLookup", propertyValue = "java:jboss/weatherTopic"),
        @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Topic"),
        @ActivationConfigProperty(propertyName = "acknowledgeMode", propertyValue = "Auto-acknowledge")})
public class Receiver implements MessageListener {

    private static final Logger LOG = LoggerFactory.getLogger(Receiver.class);

    @Override
    public void onMessage(Message message) {

        String s = "";
        try {
            s = ((TextMessage) message).getText();
        } catch (JMSException e) {
            e.printStackTrace();
        }
        LOG.info("Received message: " + s);
    }

}
