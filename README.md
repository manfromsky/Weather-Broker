# Weather Broker

## Подготовка к запуску сервер WildFly

Для данного приложения будет использоваться сервер WildFly версии 12.0.0.Final

Для запуска сервера перейдите в директорию:
```
$WILDFLY_HOME/bin 
```
 и выполните команду:
```
standalone.bat -c standalone-full.xml
```
Следующим шагом необходимо создать пользователя выполнив скрипт:
```
add-user.sh
```
после успешного создания польлзователя надо перейти по адресу:
```
http://localhost:9990
```
Перед нам откроется форма для входа в консоль администратора. Вводим данные созаднного ранее
 пользователя и заходим непосредственно в консоль.
 
 ## Создание Datasource
 
 В консоли администратора выбираем "Create datasource - start"
 
 ![Image alt](https://github.com/manfromsky/Weather-Broker/raw/master/image/create_datasource.PNG)
 
 В открывшемся меню выбираем "Subsystems - Datasources - Non-XA - Add"
 
 ![Image alt](https://github.com/manfromsky/Weather-Broker/raw/master/image/create_datasource1.PNG)
  
  
 ![Image alt](https://github.com/manfromsky/Weather-Broker/raw/master/image/create_datasource2.PNG)
  
 В открывшемся окне необходимо заполнить два поля:
 
 ![Image alt](https://github.com/manfromsky/Weather-Broker/raw/master/image/create_datasource3.PNG)
   
 В поле "Name" записываем "WeatherBrokerData",в поле "JNDI" записываем "java:/WeatherBrokerData"
 В следующем окне нажимаем кнопку "Next". В открывшемся окне необходимо заполнить поля 
 отмеченые на рисунке красным
 
 ![Image alt](https://github.com/manfromsky/Weather-Broker/raw/master/image/create_datasource4.PNG)
 
 В поле "Connection url" записываем url вашей базы данных, например 
 "jdbc:mysql://localhost:3306/weather_forecast". Поля "Username" и "Password": логин и пароль вашей
 базы данных.
 
 ## Создание Queue и Topic
 
 В консоли администратора выбираем "Create a JMS Queue - start".
 В открывшемся меню выбираем 
 "Subsystems - Messaging - ActiveMQ - Messaging Provider - default - Queues/Topics"
 
 ![Image alt](https://github.com/manfromsky/Weather-Broker/raw/master/image/jms.PNG)
  
 В открывшемся окне выбираем "JMS Queues/Topics - Queues - Add"
 
 ![Image alt](https://github.com/manfromsky/Weather-Broker/raw/master/image/jms1.PNG) 
 
 Следующее окно представляет собой форму для заполнения полей "Name" и "JNDI Names".
 Заполняем поля, "weatherQueue" и "java:jboss/weatherQueue" соответственно.
 
 Теперь необходимо проделать тоже самое для создания Topic:
 
 ![Image alt](https://github.com/manfromsky/Weather-Broker/raw/master/image/jms_topic.PNG) 
 
 Заполняем поля "Name" и "JNDI Names" "weatherTopic" и "java:jboss/weatherTopic" соответственно.
 
 ## Сборка проекта
 
 Сборка проекта выполняет командой:
 ```
 mvn clean install
 ```
 
 ## Deploy приложения
 
 Для деплоя приложения необходимо открыть консоль администратора и перейти "Deploy an Application - Start"
 
 ![Image alt](https://github.com/manfromsky/Weather-Broker/raw/master/image/deploy.PNG) 
 
 В открывшемся окне выбираем "Add"
 
 ![Image alt](https://github.com/manfromsky/Weather-Broker/raw/master/image/deploy1.PNG) 
 
 Отмечаем поле "Upload a new deployment" нажимаем "Next"
 
 ![Image alt](https://github.com/manfromsky/Weather-Broker/raw/master/image/deploy2.PNG)
 
 Нажимаем кнопку "Выбрать файл"
 
 ![Image alt](https://github.com/manfromsky/Weather-Broker/raw/master/image/deploy3.PNG)
 
 В открывшемся окне переходм в папку спроектом
 
 ![Image alt](https://github.com/manfromsky/Weather-Broker/raw/master/image/deploy4.PNG)
 
 Нас интересуют папки "admin_api", "database", "weather_service", "yahoo_weather"
 Заходим в папку "admin_api - target" и выбираем файл "admin_api-1.0-SNAPSHOT.war" 
 В папке "database - target" выбираем файл "Database-1.0-SNAPSHOT-jar-with-dependencies.jar"
 В папке "weather_service - target" выбираем файл "weather_service-1.0-SNAPSHOT.war" 
 В папке "yahoo_weather - target" выбираем файл "yahoo_weather-1.0-SNAPSHOT-jar-with-dependencies.jar" 
 Теперь сервис готов к работе
 
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

 в поле "Date" необходимо ввести дату прогноза погоды, а в поле "City" необходимо ввести
 название города латинскими буквами. После чего нажать кнопку "Ок"
 В результате клиент переходит на страницу с данными о погоде:
 
 ![Image alt](https://github.com/manfromsky/Weather-Broker/raw/master/image/result.PNG)

 ## Ввод неккоректных данных

 В случае ввода неккоректных данных в поля "Date" и "City", клиент увидит сообщение:
 
 ![Image alt](https://github.com/manfromsky/Weather-Broker/raw/master/image/error.PNG)