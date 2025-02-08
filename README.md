Для запуска проекта необходимо

1) переместиться в папку с проектом

2) выполнить команду для создания docker-образа
   ```cmd
   docker build -t open_weather_build -f ./docker/Dockerfile .
   ```
3) выполнить команду для запуска проекта
   ```cmd
   docker-compose -f ./docker/docker-compose.yml up -d
   ```

Запущенный проект будет находится по адресу http://localhost:8082 
