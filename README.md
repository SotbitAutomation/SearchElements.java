1. Webdriver 
Для обновления webdriver (драйвер от хрома для управления браузером) нужно скачать с оф сайта https://chromedriver.chromium.org/downloads  
версию драйвера равную текущей версии своего хром драйвера 
http://joxi.ru/8Anl4oYHNxqgem  и заменить свой старый драйвер в папке с проектом  http://joxi.ru/Vm6YgxEH3qxkkr


2. Работа с allure
Для заппуска аллюр отчета нужно в коммандной строке выполнить команду "allure serve" + путь к лог файлам проекта
http://joxi.ru/82QXRQ1c8b1ZBm

3. Для подключения аллюр фреймворка на стороннем компе нужно добавить путь до драйвера аllure в переменной среды "path" 
http://joxi.ru/BA0Yy0JH1oBQvr

4. Я храню драйвера для мавена, аллюра, вебдрайвера в папке resources для быстрой и простой передачи проекта на другой ком (так делать конечно нельзя)
проект может переместиться, новый начаться, а переменные среды будут тянуться из старого места  
поэтому по хорошему копируем драйвера с папки ресурсов проекта куда нибудь в корень диска C, и туда уже ссылаем переменные среды
http://joxi.ru/LmGWYeKSBdR83m  - аллюр и мавен                                   
А для вебдрайвера достаточно просто поменять путь к файлу при объявлении "driver" http://joxi.ru/Y2LadYOcxGnBzA



