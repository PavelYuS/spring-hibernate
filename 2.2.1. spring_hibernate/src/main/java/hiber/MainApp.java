package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
   public static void main(String[] args) throws SQLException {
      AnnotationConfigApplicationContext context = 
            new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);

      User Berik = new User("Berik", "Ivanov", "Ivanov@gmail.com");
      User Serik = new User("Serik", "Petrov", "Petrov@gmail.com");
      User Ermek = new User("Ermek", "Sidorov", "Sidorov@gmail.com");

      Car Haval = new Car("Habal", 400);
      Car Joolion = new Car("Joolion", 100);
      Car Omoda = new Car("Omoda", 200);

      Berik.setUserCar(Haval);
      Serik.setUserCar(Joolion);
      Ermek.setUserCar(Omoda);

      userService.add(Berik);
      userService.add(Serik);
      userService.add(Ermek);

      List<User> users = userService.listUsers();
      for (User user : users) {
         System.out.println("Id = "+user.getId());
         System.out.println("First Name = "+user.getFirstName());
         System.out.println("Last Name = "+user.getLastName());
         System.out.println("Email = "+user.getEmail());
         System.out.println();
      }
      List<User> usersCar = userService.listUsers();
      for (User user : usersCar) {
         System.out.println(user.getUserCar());
      }
      System.out.println(userService.getUserByCar("Omoda", 200));
      context.close();
   }
}
