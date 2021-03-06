package ru.shushpanov.weatherbroker.adminapi.service;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.shushpanov.weatherbroker.error.exeption.EmptyOrNullCityException;
import ru.shushpanov.weatherbroker.error.exeption.WeatherBrokerServiceException;
import ru.shushpanov.weatherbroker.messageservice.model.City;
import ru.shushpanov.weatherbroker.messageservice.service.XmlService;

import javax.annotation.Resource;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.jms.ConnectionFactory;
import javax.jms.JMSContext;
import javax.jms.JMSProducer;
import javax.jms.Topic;

/**
 * {@inheritDoc}
 */
@RequestScoped
public class SendServiceImpl implements SendService {
    private final Logger log = LoggerFactory.getLogger(SendServiceImpl.class);
    private static final String WEATHER_TOPIC = "java:jboss/weatherTopic";
    private static final String CONNECTION = "java:comp/DefaultJMSConnectionFactory";

    @Resource(name = WEATHER_TOPIC)
    private Topic topic;

    @Resource(name = CONNECTION)
    private ConnectionFactory connection;

    private XmlService xmlService;

    @Inject
    public SendServiceImpl(XmlService xmlService) {
        this.xmlService = xmlService;
    }

    public SendServiceImpl() {
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void send(String city) throws WeatherBrokerServiceException {
        if (StringUtils.isBlank(city)) {
            throw new EmptyOrNullCityException("Please enter the name of the city");
        }
        City writeCity = new City(city);
        String message = xmlService.createXmlMessage(writeCity);
        try (JMSContext context = connection.createContext()) {
            JMSProducer producer = context.createProducer();
            producer.send(topic, message);
        }
        log.info("Message to send: {}", city);
    }
}

