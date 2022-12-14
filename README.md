# Explore With Me

### Бэкенд приложения для поиска событий поблизости.

### Стек: Java, Spring Boot, Docker, Hibernate, Lombok, ModelMapper, WebClient, PostgreSQL, CriteriaApi

Приложение, в котором пользователи могут предложить какое-либо событие, например посещение выставки или поход в кино, и набрать компанию для участия в нём.
Приложение даёт возможность создавать подборки событий, а пользователи могут выбрать любое событие и оставить заявку на участие в нём.
Приложение состоит из двух сервисов: основной и сервис статистики.
Сервис статистики собирает информацию о просмотрах пользователем списка событий и полной информации конкретного события, чтобы на основе этих данных можно было выбрать наиболее популярные мероприятия.
Основной сервис реализует следующий функционал:
- для пользователей без авторизации
- - просмотр полной информации о событии
- - просмотр списка событий
- - просмотр подборки событий
- для авторизованных пользователей
- - создание/изменение/удаление события
- - создание/удаление заявки на участие в событии
- - подтверждение/отклонение заявки на участие в событии
- - создание/изменение/удаление комментария события
- для администратора приложения
- - публикация/отклонение события
- - создание/изменение/удаление категорий событий
- - создание/удаление пользователей
- - создание/изменение/удаление подборок событий

### Микросервисы:
1. Основной сервис - содержит основную бизнес логику приложения.  
_[Server](https://petstore.swagger.io/?url=https://raw.githubusercontent.com/aBoyarov/java-explore-with-me/develop/ewm-main-service-spec.json)_


2. Сервис статистики - позволяет собирать статистику просмотров  
_[Stats](https://petstore.swagger.io/?url=https://raw.githubusercontent.com/aBoyarov/java-explore-with-me/develop/ewm-stats-service-spec.json)_

### Запуск приложения
В корневой директории вызывть команду _docker-compose up_
