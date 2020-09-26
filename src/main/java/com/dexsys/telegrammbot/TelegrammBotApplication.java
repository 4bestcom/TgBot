package com.dexsys.telegrammbot;

import com.dexsys.telegrammbot.Data.Model;
import com.dexsys.telegrammbot.Data.Person;
import com.dexsys.telegrammbot.Data.Student;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TelegrammBotApplication {
	private static final Logger log = LoggerFactory.getLogger(TelegrammBotApplication.class);

	public static void main(String[] args) {
		log.info("the app starts");
		log.info("create object Model through @AllArgsConstructor and @Data");
		Model model = new Model("Ivan", "Ivanov", "+79001234567");
		System.out.println(model.toString());
		System.out.println("Фамилия модели" + model.getLastName());
		log.info("create object Model through @Builder, @Setter and @Getter");
		Person person = Person.builder().age(22).firstName("Vasilii").build();
		person.setLastName("Petrov");
		System.out.println("Возраст студента " + person.getAge());
		log.info("create object Student through @RequiredArgsConstructor, @Getter and @ToString");
		Student student = new Student("Petr", "Magomedov", "Philosofy", 24);
		System.out.println(student.getCourse());
		System.out.println(student.toString());

		SpringApplication.run(TelegrammBotApplication.class, args);


	}

}
