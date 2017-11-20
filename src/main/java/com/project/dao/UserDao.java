package com.project.dao;

import com.project.beans.User;
import com.project.database.ConnectionManager;
import java.sql.*;

public class UserDao {

    private static final String INSERT_USER = "INSERT INTO registered_users(login, password, sex, age, comment, email, friendsLogins)VALUES(?, ?, ?, ?, ?, ?, ?)";
    private static final String SELECT_USER_BY_LOGIN = "SELECT login, password, sex, age, comment, email, friendsLogins FROM registered_users WHERE login IN(?) ;";

    public void addUserToDatabase(User user){
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = ConnectionManager.getConnection();
            preparedStatement = connection.prepareStatement(INSERT_USER);
            preparedStatement.setString(1,user.getLogin());
            preparedStatement.setString(2,user.getPassword());
            preparedStatement.setString(3,user.getSex());
            preparedStatement.setInt(4,user.getAge());
            preparedStatement.setString(5,user.getComment());
            preparedStatement.setString(6,user.getEmail());
            preparedStatement.setString(7,user.getFriendsLogins());
            int id = preparedStatement.executeUpdate();
            System.out.println("Connecting to database...");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                preparedStatement.close();
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public User getUserByLogin(String login){
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ConnectionManager connectionManager = new ConnectionManager();
        ResultSet resultSet = null;
        User result = null;
        try {
            connection = connectionManager.getConnection();
            preparedStatement = connection.prepareStatement(SELECT_USER_BY_LOGIN);
            preparedStatement.setString(1,login);
            resultSet = preparedStatement.executeQuery();
            result = new User(resultSet.getString("login"),resultSet.getString("password"),false,
                    resultSet.getString("sex"), resultSet.getInt("age"), resultSet.getString("comment"),  resultSet.getString("email") );
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                preparedStatement.close();
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    public boolean checkIfUserExists(String login){
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ConnectionManager connectionManager = new ConnectionManager();
        ResultSet resultSet = null;
        boolean result = false;
        try {
            connection = connectionManager.getConnection();
            preparedStatement = connection.prepareStatement(SELECT_USER_BY_LOGIN);
            preparedStatement.setString(1,login);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                result = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                preparedStatement.close();
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return result;
    }
}
