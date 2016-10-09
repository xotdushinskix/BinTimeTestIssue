REST application to parse text files in 3 parallel way. 

Used technologies:
1) Java 7;
2) Maven;
3) MySQL DB;
4) Spring framework;
5) Hibernate ORM;
6) Tomcat8;


SQL dump:
CREATE TABLE `testrest`.`text_file` (
  `word_id` INT NOT NULL AUTO_INCREMENT,
  `value` VARCHAR(45) NULL,
  `count` INT NULL,
  PRIMARY KEY (`word_id`));


Requirements for uploaded files:
1) Each word should separated by comma and space;

For the most simple and comfortable testing has created jsp page to make POST action to required REST controller method (upload.jsp);

Steps to open and deploy project:
1) git clone https://github.com/xotdushinskix/BinTimeTestTask.git;
2) Add to cloned project Tomcat container;
3) Create DB with required sql dump;
4) Deploy project and navigate on http://localhost:your_port/upload.jsp to make POST request. 
5) On /upload.jsp page at once created 5 button for attachment text file to test app with more than 3 files --> http://joxi.ru/KAxnMwQI4ypEqm
6) After that navigate to http://localhost:your_port/words and check JSON response --> http://joxi.ru/Grq1BwqcN3wEJr
7) Some comments about program logic has written in controller class (about POST);
