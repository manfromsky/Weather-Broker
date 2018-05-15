# Weather Broker

## Заполнение базы информацией о прогнозе погоды

Для заполнения информацией о погоде базы данных необходимо перейти по адресу
```
http://localhost:8080/admin_api-1.0-SNAPSHOT/city
```
В появившемся окне:

![Image alt](https://github.com/manfromsky/Weather-Broker/raw/master/image/city_enter.PNG)

в поле необходимо ввести название интересуеющего вас города латинскими буквами, после чего 
нажать на кнопку "Отправить".

## Запрос прогноза погоды 

Для получения прогноза погоды необходимо перейти по адресу
```
http://localhost:8080/weather_service-1.0-SNAPSHOT/api/weatherbroker/forecast
```
В появившемся окне:

![Image alt](https://github.com/manfromsky/Weather-Broker/raw/master/image/forecast_request.PNG)

в поле "Date" необходимо ввести дату прогноза погоды, а в поле 