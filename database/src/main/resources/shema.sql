USE weather_forecast;

CREATE TABLE IF NOT EXISTS forecast(
id   INT  NOT NULL AUTO_INCREMENT PRIMARY KEY,
forecast_date DATE NOT NULL UNIQUE ,
forecast_day VARCHAR(3)NOT NULL,
read_city VARCHAR(255) NOT NULL,
high_temp VARCHAR(3) NOT NULL,
low_temp VARCHAR(3) NOT NULL,
description VARCHAR(255)NOT NULL,
version              INTEGER NOT NULL,
);

CREATE INDEX ix_forecast_city
  ON forecast (readCity);