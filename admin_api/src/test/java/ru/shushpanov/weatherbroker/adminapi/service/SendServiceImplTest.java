package ru.shushpanov.weatherbroker.adminapi.service;

import org.apache.activemq.artemis.jms.client.ActiveMQConnectionFactory;
import org.apache.activemq.artemis.jms.client.ActiveMQConnectionForContext;
import org.apache.activemq.artemis.jms.client.ActiveMQXAJMSContext;
import org.apache.activemq.artemis.jms.client.ThreadAwareContext;
import org.easymock.EasyMockRule;
import org.easymock.Mock;
import org.easymock.MockType;
import org.easymock.TestSubject;
import org.junit.Rule;
import org.junit.Test;
import ru.shushpanov.weatherbroker.error.exeption.EmptyOrNullCityException;
import ru.shushpanov.weatherbroker.error.exeption.WeatherBrokerServiceException;
import ru.shushpanov.weatherbroker.messageservice.model.City;
import ru.shushpanov.weatherbroker.messageservice.model.XmlModel;
import ru.shushpanov.weatherbroker.messageservice.service.XmlServiceImpl;

import javax.jms.JMSProducer;
import javax.jms.Topic;

import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.replay;
import static org.easymock.EasyMock.verify;

public class SendServiceImplTest {
    private String city = "Penza";
    private String emptyCity = "";
    private String nullCity = null;
    private String TEST_STRING = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?><City name=\"Penza\"/>";
    private XmlModel cityModel = new City(city);

    @Rule
    public EasyMockRule em = new EasyMockRule(this);

    @Mock
    private XmlServiceImpl service;

    @Mock
    private ActiveMQConnectionFactory connectionFactory;

    @Mock
    private ActiveMQConnectionForContext connectionForContext;

    @Mock
    private ThreadAwareContext threadAwareContext;

    @Mock(MockType.NICE)
    private ActiveMQXAJMSContext context;

    @Mock
    private JMSProducer producer;

    @Mock
    private Topic topic;

    @TestSubject
    private SendServiceImpl sendService = new SendServiceImpl(service);

    @Test
    public void testSend() throws WeatherBrokerServiceException {
        expect(service.createXmlMessage(cityModel)).andStubReturn(TEST_STRING);
        expect(connectionFactory.createContext()).andStubReturn(context);
        expect(context.createProducer()).andStubReturn(producer);
        expect(producer.send(topic, TEST_STRING)).andStubReturn(producer);
        replay(context);
        replay(topic);
        replay(connectionFactory);
        replay(service);
        sendService.send(city);
        verify(service);
    }

    @Test(expected = EmptyOrNullCityException.class)
    public void testSendEmptyCity() throws WeatherBrokerServiceException {
        sendService.send(emptyCity);
    }

    @Test(expected = EmptyOrNullCityException.class)
    public void testSendNullCity() throws WeatherBrokerServiceException {
        sendService.send(nullCity);
    }
}