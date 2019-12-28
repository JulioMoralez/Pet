package pack.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.sql.*;

@Controller
public class HelloController {

    @Value("${spring.datasource.url}")
    private String url;

    @Value("${spring.datasource.username}")
    private String username;

    @Value("${spring.datasource.password}")
    private String password;



    @GetMapping("/hello")
    String sayHello(Model model) {
        model.addAttribute("message","hello");

        Connection connection=null;
        try {
            System.out.println("111");
            connection = DriverManager.getConnection(url, username, password);
            Statement statement = connection.createStatement();
            statement.execute("CREATE TABLE IF NOT EXISTS person (ID serial PRIMARY KEY, name   varchar(40));");
            System.out.println("222");
//            ResultSet resultSet = statement.executeQuery("SELECT * FROM fix_user");
//            while (resultSet.next()){
//                System.out.println(resultSet.getString(2));
//            }
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

        return "index";
    }
}
