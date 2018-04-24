package ru.shushpanov.weatherbroker.admin_api.service.impl;

import ru.shushpanov.weatherbroker.admin_api.model.City;
import ru.shushpanov.weatherbroker.admin_api.service.SendService;
import ru.shushpanov.weatherbroker.message_service.service.MessageService;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.jms.Topic;

@Stateless
public class SendServiceImpl implements SendService {

    private final MessageService messageService;

    @Inject
    public SendServiceImpl(MessageService messageService) {
        this.messageService = messageService;
    }

    @Override
    public void createAndSendMessage(String name) {
        City city = new City();
        city.setName(name);
        String message = messageService.createXmlMessage(city);
    }

    private void sendMessage(String message) {

    }
}
