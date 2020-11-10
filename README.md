# sprint-boot-state-machine
Sample spring boot application with spring statemachine

How to Run

gradle bootRun

Then you can use below API end ponits to test the app

1. Create order

HTTP Post to localhost:8080/order/

2. Get Order

HTTP Get to localhost:8080/order/{id}

3. Pay Order

HTTP Post to localhost:8080/order/pay/{id}

4. Fulfill Order

HTTP Post to localhost:8080/order/fulfill/1
