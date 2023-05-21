# OpenTickets

Проект - пример для использования при изучении Spring Framework. Представляет собой бэкенд сервис для регистрации и погашения билетов.
В данном проекте использованы следующий технологический стэк:
* Java в качестве языка программирования;
* Spring Boot (контейнер сервлетов, реализация бизнес-логики);
* Java Persistence API - для реализации ORM взаимодействия с базой данных;
* Spring Security - авторизация и аутентификация пользователей, настройка безопасности для эндпоинтов;
* JWT - для использования в качестве сессии пользователя.

## Настройка проекта

Основные настройки проекта находятся в файле application.properties:
```properties
spring.datasource.driver-class-name=org.postgresql.Driver
spring.datasource.url=jdbc:postgresql://localhost:5432/tickets_db
spring.datasource.username=user
spring.datasource.password=password
spring.jpa.hibernate.ddl-auto=update
```
Для работы проекта требуется создать базу данных в СУБД PostgreSQL. Способы установки представленны в [данном гайде](https://docs.google.com/document/d/1hfu2vIab9lL6AD2bxUmMZYSmvtYYJF1RS_0BbKAHqy4/edit).

## Endpoints

Основные эндпоинты приложения:

### Auth


**Регистрация пользователя**


```url
[HTTP POST] /auth/register
```

Принимает JSON следующего формата:
```json
{
    "email":"string",
    "password":"string"
}
```

Возвращает JSON в формате:

```json
{
    "email": "string",
    "uuid": "UUID",
    "token": "string"
}
```

**Авторизация пользователя**

```url
[HTTP POST] /auth/login
```

Принимает JSON следующего формата:
```json
{
    "email":"string",
    "password":"string"
}
```

Возвращает JSON в формате:

```json
{
    "email": "string",
    "token": "string"
}
```

В обоих случаях, возвращаемый токен надо включать в заголовки запросов к эндпоинтам для регистрации или погашения билетов. 
При этом, для погашения билетов привилегии пользователя должны быть админские.

При запуске приложения в базу данных вносятся два тестовых пользователя с правами USER и ADMIN соответственно, так же для первого пользователя создаётся тестовый билет.

JSON тело с привилегиями USER:
```json
{
    "email":"greatoldfag@gmail.com",
    "password":"password"
}
```
JSON тело с привилегиями ADMIN:

```json
{
    "email":"admin@gmail.com",
    "password":"password"
}
```

### Tickets

**Регистрация билета**
```url
[HTTP POST] /tickets/register
```

Принимает JSON в формате:
```json
{
    "eventName":"string",
    "eventDate":"YYYY-MM-DD",
    "ticketType":"string",
    "emailOwner":"string"
}
```

Возвращает JSON:

```json
{
    "eventName": "string",
    "ticketId": "UUID",
    "ownerEmail": "string",
    "eventDate": "YYYY-MM-DD"
}
```

**Погашение билета (ADMIN only)**

```url
[HTTP POST] /tickets/redeem
```

Принимает JSON в формате:
```json
{
    "ticketId":"UUID"
}
```

Возвращает JSON:
```json
{
    "ticketId": "UUID",
    "eventName": "string",
    "eventDate": "YYYY-MM-DD",
    "ticketType": "string",
    "emailOwner": "string",
    "redeemed": "boolean"
}
```

**Получить список всех зарегистрированных билетов (ADMIN only)**

```url
[HTTP GET] /tickets/
```

Возвращает JSON:
```json
{
    "tickets": [
        {
            "ticketId": "string",
            "eventName": "string",
            "eventDate": "YYYY-MM-DD",
            "ticketType": "string",
            "emailOwner": "string",
            "redeemed": "boolean"
        }
    ]
}
```

