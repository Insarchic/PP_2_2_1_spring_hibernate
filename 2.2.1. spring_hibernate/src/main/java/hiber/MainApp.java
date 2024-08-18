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

      User user1 = new User("User1", "Lastname1", "user1@mail.ru");
      Car Kia = new Car("Rio", 2010);
      user1.setCar(Kia);

      User user2 = new User("User2", "Lastname2", "user2@mail.ru");
      Car Nissan = new Car(" Spectra", 2003);
      user2.setCar(Nissan);

      User user3 = new User("User3", "Lastname3", "user3@mail.ru");
      Car BMW = new Car("m5 f90 competition", 2023);
      user3.setCar(BMW);

      User user4 = new User("User4", "Lastname4", "user4@mail.ru");
      Car Lamborghini = new Car("Urus", 2024);
      user4.setCar(Lamborghini);

      userService.add(user1);
      userService.add(user2);
      userService.add(user3);
      userService.add(user4);

      User userByCar = userService.getUserByModelAndSeries("Urus", 2024);
      if (userByCar != null) {
         Car car = userByCar.getCar();
         System.out.printf("User with model %s and series %d: %s%n",
                 car.getModel(),
                 car.getSeries(),
                 userByCar.getFirstName());
      } else {
         System.out.println("No user found with the specified car model and series.");
      }


      List<User> users = userService.listUsers();
      for (User user : users) {
         System.out.println("Id = "+user.getId());
         System.out.println("First Name = "+user.getFirstName());
         System.out.println("Last Name = "+user.getLastName());
         System.out.println("Email = "+user.getEmail());
         System.out.println();
      }


      context.close();
   }
}
