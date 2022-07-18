

package pl.coderslab.utils;

import java.sql.SQLException;

public class MainUserDao {
    public static void main(String[] args) throws SQLException {

        UserDao userDao1 = new UserDao();
        User[] users = userDao1.findAll();

        for (User u :
                users) {
            System.out.println(u);
        }
    }
}
