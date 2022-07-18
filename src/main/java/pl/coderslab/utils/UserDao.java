
package pl.coderslab.utils;

import java.sql.*;
import java.util.Arrays;

public class UserDao {
    private static final String CREATE_USER_QUERY =
            "INSERT INTO users (EMAIL, USERNAME, PASSWORD) VALUES (?,?,?)";

    private static final String UPDATE_USER_QUERY =
            "UPDATE users SET EMAIL = ?, USERNAME =?, PASSWORD =? WHERE ID = ?";

    private static final String SELECT_BY_ID_QUERY =
            "SELECT * FROM users WHERE ID = ?";

    private static final String ID_DELETE_QUERY =
            "DELETE FROM users WHERE ID = ?";

    private static final String SELECT_ALL_QUERY =
            "SELECT * FROM users";

    //metoda na poznanie klucza głównego dla wpisanego elementu - czyli id
//
//    public void showId() {
//
//        PreparedStatement preStmt = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
//
//        ResultSet rs = preStmt.getGeneratedKeys();
//        if (rs.next()) {
//            long id = rs.getLong(1);
//            System.out.println("Inserted ID: " = id);
//        }
//    }

    public String hashPassword(String password) {
        return org.mindrot.jbcrypt.BCrypt.hashpw(password, org.mindrot.jbcrypt.BCrypt.gensalt());
    }


    public User create(User user) {
        try (Connection conn = DbUtil.getConnection()) {
            PreparedStatement statement =
                    conn.prepareStatement(CREATE_USER_QUERY, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, user.getEmail());
            statement.setString(2, user.getUserName());
            statement.setString(3, hashPassword(user.getPassword()));
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                user.setId(resultSet.getInt(1));
            }
            return user;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public User read(int userId) throws SQLException {

//  private static final String SELECT_BY_ID_QUERY =
//   "SELECT * FROM users WHERE ID = ?";

        try (Connection conn = DbUtil.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement(SELECT_BY_ID_QUERY);
            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                User user = new User();
                user.setId(rs.getInt("ID"));
                user.setUserName(rs.getString("USERNAME"));
                user.setEmail(rs.getString("EMAIL"));
                user.setPassword(rs.getString("PASSWORD"));
                return user;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void update(User user) throws SQLException {

        try (Connection conn = DbUtil.getConnection()) {
            PreparedStatement pStmt = conn.prepareStatement(UPDATE_USER_QUERY);
            pStmt.setString(1, user.getEmail());
            pStmt.setString(2, user.getUserName());
            pStmt.setString(3, hashPassword(user.getPassword()));
            pStmt.setInt(4, user.getId());
            pStmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(int userId) throws SQLException {
//    private static final String ID_DELETE_QUERY =
//            "DELETE * FROM users WHERE ID = ?";

        try (Connection conn = DbUtil.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement(ID_DELETE_QUERY);
            stmt.setInt(1, userId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public User[] findAll() throws SQLException {

//        private static final String SELECT_ALL_QUERY =
//                "SELECT * FROM users";

        try (Connection connection = DbUtil.getConnection()) {
            PreparedStatement pstmt = connection.prepareStatement(SELECT_ALL_QUERY);
            ResultSet rs = pstmt.executeQuery();
            User[] users = new User[0];

            while (rs.next()) {
                User userTmp = new User();
                userTmp.setId(rs.getInt("ID"));
                userTmp.setUserName(rs.getString("USERNAME"));
                userTmp.setEmail(rs.getString("EMAIL"));
                userTmp.setPassword(rs.getString("PASSWORD"));
                users = addToArray(userTmp, users);
            }
            return users;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    private User[] addToArray(User user, User[] users) {
        User[] tmpUsers = Arrays.copyOf(users, users.length + 1);
        tmpUsers[users.length] = user;
        return tmpUsers;
    }
}

