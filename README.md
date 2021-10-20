#Описание проекта
Создать сервис, который обращается к сервису курсов валют, и отображает gif:
если курс по отношению к USD за сегодня стал выше вчерашнего, то отдаем рандомную отсюда https://giphy.com/search/rich
если ниже - отсюда https://giphy.com/search/broke

##Ссылки
1. REST API курсов валют - https://docs.openexchangerates.org/
2. REST API гифок - https://developers.giphy.com/docs/api#quick-start-guide

##Must Have
1. Сервис на Spring Boot 2 + Java / Kotlin
Запросы приходят на HTTP endpoint (должен быть написан в соответствии с rest conventions), 
туда передается код валюты по отношению с которой сравнивается USD
2. Для взаимодействия с внешними сервисами используется Feign
3. Все параметры (валюта по отношению к которой смотрится курс, адреса внешних сервисов и т.д.) вынесены в настройки
4. На сервис написаны тесты (для мока внешних сервисов можно использовать @mockbean или WireMock)
5. Для сборки должен использоваться Gradle

#Endpoint
Для отображения гифки надо послать GET запрос по следующему endpoint'у.

`GET /money-check-service/currency`

В парараметры передается всего одно значение - код валюты. Имя параметра `desc`

Итоговый GET запрос для валюты "рубль" выглядит так

`GET /money-check-service/currency?desc=RUB`

Возвращает html-тег img, с ссылкой на гифку

`<img src="https://giphy.com/gifs/job-6AaB96ZVrUN0I"/>`

#Запуск

Запускается проект как и обычное java приложение

`java -jar .\MoneyCheck-1.0.0.jar`