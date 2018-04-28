USE weather_forecast;

CREATE TABLE IF NOT EXISTS forecast(
id   INT  NOT NULL AUTO_INCREMENT PRIMARY KEY,
DATE DATE NOT NULL,
DAY VARCHAR(10)NOT NULL,
readCity VARCHAR(255) NOT NULL,
high_temp INTEGER NOT NULL,
low_temp INTEGER NOT NULL,
description VARCHAR(255)NOT NULL,
version              INTEGER NOT NULL,
location_id INT          NOT NULL
);

CREATE INDEX ix_forecast_city
  ON forecast (readCity);