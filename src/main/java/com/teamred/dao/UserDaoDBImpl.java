package com.teamred.dao;

import com.teamred.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoDBImpl implements Dao<User> {

    private static Connection getConnection() throws SQLException {

        Connection conn = null;
        String url = "jdbc:postgresql://34.116.212.97:5432/testdb";
        String username = "postgres";
        String password = "redteamdbpass";

        conn = DriverManager.getConnection(url, username, password);

        System.out.println();

        return conn;
    }

    @Override
    public List<User> getAll() throws SQLException {
        List<User> list = new ArrayList<>();
        try (Connection conn = UserDaoDBImpl.getConnection()) {

            PreparedStatement preparedStatement = conn.prepareStatement("SELECT * FROM users");
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                User user = new User();
                user.setUserId(rs.getInt(1));
                user.setUserName(rs.getString(2));
                user.setPassword(rs.getString(3));
                user.setUserAge(rs.getInt(4));
                list.add(user);
            }
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public User get(Integer userId) throws SQLException{

        User user = new User();

        try (Connection conn = UserDaoDBImpl.getConnection()) {
            PreparedStatement preparedStatement = conn.prepareStatement("SELECT userName,password,userAge,email) FROM users WHERE userId=?");
            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                user.setUserId(rs.getInt(1));
                user.setUserName(rs.getString(2));
                user.setPassword(rs.getString(3));
                user.setUserAge(rs.getInt(4));
            }
            return user;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public User add(User user) throws SQLException {

        try (Connection conn = UserDaoDBImpl.getConnection()) {
            int status;
            PreparedStatement preparedStatement = conn.prepareStatement("INSERT INTO users (userName, password, userAge, email) VALUES(?,?,?,?)");
            preparedStatement.setString(1, user.getUserName());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setInt(3, user.getUserAge());
            preparedStatement.setString(4, user.getEmail());

            status = preparedStatement.executeUpdate();

            return user;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public User update(User user) throws SQLException {

        User newUser = null;
        try (Connection conn = UserDaoDBImpl.getConnection()) {
            int status;
            int userId = user.getUserId();
            PreparedStatement preparedStatement = conn.prepareStatement("UPDATE users SET userName=?,password=?,userAge=?,email=? where userId=?");
            preparedStatement.setString(1, user.getUserName());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setInt(3, user.getUserAge());
            preparedStatement.setString(4, user.getEmail());

            status = preparedStatement.executeUpdate();

            newUser = this.get(userId);

            return newUser;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public User delete(Integer userId) throws SQLException {

        User user = null;
        try (Connection conn = UserDaoDBImpl.getConnection()) {

            int status;
            
            user = this.get(userId);

            PreparedStatement preparedStatement = conn.prepareStatement("DELETE FROM users where userId=?");
            preparedStatement.setLong(1, userId);

            status = preparedStatement.executeUpdate();

            return user;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}