package com.dexsys.telegrammbot.repository;

import com.dexsys.telegrammbot.domain.User;
import com.dexsys.telegrammbot.domain.UserStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class ConnectionFactory implements IRepository {
    private static final Logger log = LoggerFactory.getLogger(ConnectionFactory.class);
    private final String tableName = "users";
    private final String jdbcDriver = "org.postgresql.Driver";
    private final String url = "jdbc:postgresql://ec2-3-218-75-21.compute-1.amazonaws.com:5432/d2tlq0pc7d1i7e";
    private final String user = "kwooypjvvqhfbi";
    private final String password = "05df0ae32828a2a3e76bb60c864d98ac1834132a90202e51526d3aa2451d9b83";



    public Connection getConnection() {
        Connection connection;
        try {
            Class.forName(jdbcDriver);
            log.info("Driver initialize");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("JDBC Driver not initialize");
        }
        try {
            connection = DriverManager.getConnection(url, user, password);
            connection.setAutoCommit(false);
            log.info("connection done");
            return connection;
        } catch (SQLException e) {
            throw new RuntimeException("Connection filed");
        }
    }

    public Statement createStatement(Connection connection) {
        try {
            log.info("statement created");
            return connection.createStatement();
        } catch (SQLException e) {
            throw new RuntimeException("create statement field");
        }
    }

    @Override
    public void create(long chatId, String userName, UserStatus userStatus) {
        if (read(chatId) != null) {
            log.info("user not created, was find in the base");
            return;
        }
        try (Connection connection = getConnection();
             Statement statement = createStatement(connection)) {
            StringBuilder query = new StringBuilder()
                    .append("INSERT INTO ")
                    .append(tableName)
                    .append(" (chatid, username, user_status)")
                    .append(" VALUES (")
                    .append(chatId)
                    .append(", '")
                    .append(userName)
                    .append("', '")
                    .append(userStatus)
                    .append("');");
            statement.executeUpdate(query.toString());
            log.info("INSERT query was sending");
            connection.commit();
        } catch (SQLException e) {
            throw new RuntimeException("query was not sending");
        }
    }

    @Override
    public User read(long chatId) {
        User user = null;
        ResultSet resultSet = null;
        try (Connection connection = getConnection();
             Statement statement = createStatement(connection)) {
            StringBuilder query = new StringBuilder()
                    .append("SELECT * FROM ")
                    .append(tableName)
                    .append(" WHERE chatid = ")
                    .append(chatId)
                    .append(";");
            resultSet = statement.executeQuery(query.toString());
            log.info("query to find user was sending");
        } catch (SQLException e) {
            throw new RuntimeException("query was not sending");
        }
        try {
            if (resultSet != null && resultSet.next()) {
                try {
                    String userName = resultSet.getString("username");
                    String birthDate = resultSet.getString("birthdate");
                    String phone = resultSet.getString("phone");
                    UserStatus userStatus = UserStatus.valueOf(resultSet.getString("user_status"));
                    user = User.builder()
                            .phone(phone)
                            .chatId(chatId)
                            .birthDate(birthDate)
                            .userName(userName)
                            .userStatus(userStatus)
                            .build();
                    log.info(user + " was created");
                } catch (SQLException e) {
                    throw new RuntimeException("query not was reading");
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("resultSet.next() not was reading");
        }
        return user;
    }

    @Override
    public User read(String phone) {
        User user = null;
        ResultSet resultSet = null;
        try (Connection connection = getConnection();
             Statement statement = createStatement(connection)) {
            StringBuilder query = new StringBuilder()
                    .append("SELECT * FROM ")
                    .append(tableName)
                    .append(" WHERE phone = '")
                    .append(phone)
                    .append("';");
            resultSet = statement.executeQuery(query.toString());
            log.info("query to find user was sending");
        } catch (SQLException e) {
            throw new RuntimeException("query not was sending");
        }
        try {
            if (resultSet != null && resultSet.next()) {
                try {
                    String userName = resultSet.getString("username");
                    String birthDate = resultSet.getString("birthdate");
                    long chatId = resultSet.getLong("chatid");
                    UserStatus userStatus = UserStatus.valueOf(resultSet.getString("user_status"));
                    user = User.builder()
                            .phone(phone)
                            .chatId(chatId)
                            .birthDate(birthDate)
                            .userName(userName)
                            .userStatus(userStatus)
                            .build();
                    log.info(user + " was created");
                } catch (SQLException throwables) {
                    throw new RuntimeException("query not was reading");
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("resultSet.next() not was reading");
        }
        return user;
    }

    @Override
    public List<User> readAll() {
        ResultSet resultSet = null;
        try (Connection connection = getConnection();
             Statement statement = createStatement(connection)) {
            StringBuilder query = new StringBuilder()
                    .append("SELECT * FROM ")
                    .append(tableName)
                    .append(";");
            resultSet = statement.executeQuery(query.toString());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        List<User> userList = new ArrayList<>();
        try {
            while (resultSet != null && resultSet.next()) {
                try {
                    String userName = resultSet.getString("username");
                    long chatId = resultSet.getLong("chatid");
                    String birthDate = resultSet.getString("birthdate");
                    String phone = resultSet.getString("phone");
                    UserStatus userStatus = UserStatus.valueOf(resultSet.getString("user_status"));
                    User user = User.builder()
                            .phone(phone)
                            .chatId(chatId)
                            .birthDate(birthDate)
                            .userName(userName)
                            .userStatus(userStatus)
                            .build();
                    userList.add(user);
                } catch (SQLException e) {
                    throw new RuntimeException("error reading field from DB");
                }
            }
            log.info(userList + " was created");
        } catch (SQLException e) {
            throw new RuntimeException("resultSet.next() not was reading");
        }
        return userList;
    }

    @Override
    public boolean delete(long chatId) {
        try (Connection connection = getConnection();
             Statement statement = createStatement(connection)) {
            StringBuilder query = new StringBuilder()
                    .append("DELETE FROM ")
                    .append(tableName)
                    .append(" WHERE chatid = ")
                    .append(chatId)
                    .append(";");
            statement.execute(query.toString());
            log.info("user was deleted");
            connection.commit();
            return true;
        } catch (SQLException e) {
            throw new RuntimeException("query not was sending");
        }
    }

    public boolean updatePhone(String phone, long chatId) {
        try (Connection connection = getConnection();
             Statement statement = createStatement(connection)) {
            StringBuilder query = new StringBuilder()
                    .append("UPDATE ")
                    .append(tableName)
                    .append(" SET phone = '")
                    .append(phone)
                    .append("' WHERE chatid = ")
                    .append(chatId)
                    .append(";");
            statement.execute(query.toString());
            log.info(phone + " was updated");
            connection.commit();
            return true;
        } catch (SQLException e) {
            throw new RuntimeException("query not was sending");
        }
    }

    public boolean updateBirthDate(String birthDate, long chatId) {
        try (Connection connection = getConnection();
             Statement statement = createStatement(connection)) {
            StringBuilder query = new StringBuilder()
                    .append("UPDATE ")
                    .append(tableName)
                    .append(" SET birthDate = '")
                    .append(birthDate)
                    .append("' WHERE chatid = ")
                    .append(chatId)
                    .append(";");
            statement.execute(query.toString());
            log.info(birthDate + " was updated");
            connection.commit();
            return true;
        } catch (SQLException e) {
            throw new RuntimeException("query not was sending");
        }
    }

    public boolean updateUserStatus(UserStatus userStatus, long chatId) {
        try (Connection connection = getConnection();
             Statement statement = createStatement(connection)) {
            StringBuilder query = new StringBuilder()
                    .append("UPDATE ")
                    .append(tableName)
                    .append(" SET user_status = '")
                    .append(userStatus)
                    .append("' WHERE chatid = ")
                    .append(chatId)
                    .append(";");
            statement.execute(query.toString());
            log.info(userStatus + " was updated");
            connection.commit();
            return true;
        } catch (SQLException e) {
            throw new RuntimeException("query not was sending");
        }
    }
}
