package ru.shushpanov.weatherbroker.adminapi.service.impl;

import org.omg.CORBA.PERSIST_STORE;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.shushpanov.weatherbroker.adminapi.service.SendService;
import ru.shushpanov.weatherbroker.messageservice.service.MessageService;
import ru.shushpanov.weatherbroker.messageservice.service.model.WriteCity;
import ru.shushpanov.weatherbroker.yahoo_weather.service.impl.Receiver;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.jms.ConnectionFactory;
import javax.jms.DeliveryMode;
import javax.jms.JMSContext;
import javax.jms.JMSRuntimeException;
import javax.jms.Topic;

@Stateless
public class SendServiceImpl implements SendService {
    private static final Logger LOG = LoggerFactory.getLogger(SendServiceImpl.class);

    private static final String WEATHER_TOPIC = "java:jboss/weatherTopic";
    private static final String CONNECTION = "java:comp/DefaultJMSConnectionFactory";

    @Resource(name = WEATHER_TOPIC)
    private Topic topic;

    @Resource(name = CONNECTION)
    private ConnectionFactory connection;

    private final MessageService messageService;

    @Inject
    public SendServiceImpl(MessageService messageService) {
        this.messageService = messageService;
    }

    @Override
    public void createAndSendMessage(String name) {
        WriteCity writeCity = new WriteCity();
        writeCity.setName(name);
        String message = messageService.createXmlMessage(writeCity);
        try (JMSContext context = connection.createContext()) {
            context.createProducer().setDeliveryMode(DeliveryMode.PERSISTENT).send(topic, message);
        } catch (JMSRuntimeException e) {
            e.printStackTrace();
        }


        LOG.info("Message to send: " + message);
    }
}
