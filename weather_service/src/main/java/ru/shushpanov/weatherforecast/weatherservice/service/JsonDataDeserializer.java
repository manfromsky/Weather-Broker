package ru.shushpanov.weatherforecast.weatherservice.service;

import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.map.DeserializationContext;
import org.codehaus.jackson.map.JsonDeserializer;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

@Component
public class JsonDataDeserializer extends JsonDeserializer<Date> {

    private SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss", Locale.US);

    @Override
    public Date deserialize(JsonParser jsonParser, DeserializationContext deserializationContext)
            throws IOException {
        String str = jsonParser.getText().trim();

        try {
            return dateFormat.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return deserializationContext.parseDate(str);
    }
}
