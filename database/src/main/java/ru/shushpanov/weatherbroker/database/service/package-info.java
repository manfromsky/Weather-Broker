/**
 * Пакет представляет собой сервис для получения информации о прогнозе погоды и сохранению ее в базу данных
 * {@link ru.shushpanov.weatherbroker.database.service.DataReceiver} Сервис принимает JMS сообщение с информацией
 * о прогнозе погоды и делает запрос на сохранения ее а базу данных
 * {@link ru.shushpanov.weatherbroker.database.service.DataService} Сервис преобразует информацию о пронозе погоды
 * в сущности для последующего сохранения в базу данных
 */
package ru.shushpanov.weatherbroker.database.service;