package ru.shushpanov.weatherbroker.admin_api.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.shushpanov.weatherbroker.admin_api.model.City;
import ru.shushpanov.weatherbroker.admin_api.service.SendService;
import ru.shushpanov.weatherbroker.message_service.service.MessageService;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.jms.JMSContext;
import javax.jms.Topic;

@Stateless
public class SendServiceImpl implements SendService {
    private static final Logger LOG = LoggerFactory.getLogger(SendServiceImpl.class);

    @Resource(name = "${city.topic}")
    private Topic topic;

    private final MessageService messageService;
    private final JMSContext context;

    @Inject
    public SendServiceImpl(MessageService messageService, JMSContext context) {
        this.messageService = messageService;
        this.context = context;
    }

    @Override
    public void createAndSendMessage(String name) {
        City city = new City();
        city.setName(name);
        String message = messageService.createXmlMessage(city);
        LOG.info("Отправляемый объект" + city);
        context.createProducer().send(topic, message);


    }
}
