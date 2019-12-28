package pack.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pack.model.Person;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Controller
public class HelloController {

    @Value("${spring.datasource.url}")
    private String url;

    @Value("${spring.datasource.username}")
    private String username;

    @Value("${spring.datasource.password}")
    private String password;

    List<Person> persons = new ArrayList<>();

    @GetMapping("/hello")
    String sayHello(Model model) {


        Connection connection=null;
        try {
            connection = DriverManager.getConnection(url, username, password);
            Statement statement = connection.createStatement();
            statement.execute("CREATE TABLE IF NOT EXISTS person (ID serial PRIMARY KEY, name   varchar(40));");
            statement.execute("INSERT INTO person (name) VALUES ('Sam');");
            statement.execute("INSERT INTO person (name) VALUES ('Max');");
            ResultSet resultSet = statement.executeQuery("SELECT * FROM person");
            while (resultSet.next()){
                persons.add(new Person(resultSet.getInt(1), resultSet.getString(2)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (connection!=null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        persons.forEach(System.out::println);

        model.addAttribute("message","hello");
        model.addAttribute("persons",persons);


        return "index";
    }
}
