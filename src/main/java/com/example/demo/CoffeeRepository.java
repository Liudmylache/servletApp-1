package com.example.demo;


import lombok.extern.slf4j.Slf4j;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

//@Logged
@Slf4j
public class CoffeeRepository {

    public static void main(String[] args) throws SQLException {

        getConnection();
        getOrderById(1);

        Check check = new Check();
        check.setGuestName("Mua");
        check.setQuantity(1);
        check.setCoffeeId(3);
        check.setTotal(7.30);

        add(check);
        System.out.println(check);

    }

    @Logged
    public static Connection getConnection() {

        Connection connection = null;
        String url = "jdbc:postgresql://localhost:5432/employees";
        String user = "postgres";
        String password = "1234";

        try {
            connection = DriverManager.getConnection(url, user, password);
            if (connection != null) {
                System.out.println("Connected to the PostgreSQL server successfully.");
            } else {
                System.out.println("Failed to make connection!");
            }
        } catch (SQLException sqlException) {
            System.out.println(sqlException);
        }
        log.info("connection");
        return connection;
    }


    public static int saveCoffee(Coffee coffee) throws SQLException {
        log.info("saving() coffee = {}", coffee);
        int status = 0;
        Connection connection = CoffeeRepository.getConnection();
        try {
            PreparedStatement ps = connection.prepareStatement("insert into coffee(name,price,sugar,milk,coffeebeans) values (?,?,?,?,?)");
            ps.setString(1, coffee.getName());
            ps.setDouble(2, coffee.getPrice());
            ps.setBoolean(3, coffee.isSugar());
            ps.setBoolean(4, coffee.isMilk());
            ps.setInt(5, coffee.getCoffeeBeans());

            status = ps.executeUpdate();


        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            connection.close();
            log.info("connection closed. Status:",status);
        } return status;
    }

    public static int update(Coffee coffee) throws SQLException {
        log.info("updating() coffee = {} ", coffee);

        int status = 0;

        Connection connection = CoffeeRepository.getConnection();
        try {
            PreparedStatement ps = connection.prepareStatement("update coffee set name=?,price=?,sugar=?,milk=?,coffeebeans=? where id=?");
            ps.setString(1, coffee.getName());
            ps.setDouble(2, coffee.getPrice());
            ps.setBoolean(3, coffee.isSugar());
            ps.setBoolean(4, coffee.isMilk());
            ps.setInt(5, coffee.getCoffeeBeans());
            ps.setInt(6, coffee.getId());

            status = ps.executeUpdate();

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        } finally {
            connection.close();
            log.info("connection closed. Status:" + status);
        } return status;
    }

    public static int deleteCoffee(int id) {
        log.info("deleting() coffee id:"+ id);

        int status = 0;

        try {
            Connection connection = CoffeeRepository.getConnection();
            PreparedStatement ps = connection.prepareStatement("delete from coffee where id=?");
            ps.setInt(1, id);
            status = ps.executeUpdate();

            connection.close();

        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        log.info("connection closed. Status:" + status);
        return status;
    }

    public static Coffee getCoffeeById(int id) throws SQLException {
        log.info("gettingCoffeeById() coffee id:" + id);

        Coffee coffee = new Coffee();
        Connection connection = CoffeeRepository.getConnection();

        try {
            PreparedStatement ps = connection.prepareStatement("select * from coffee where id=?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                coffee.setId(rs.getInt(1));
                coffee.setName(rs.getString(2));
                coffee.setPrice(rs.getDouble(3));
                coffee.setSugar(rs.getBoolean(4));
                coffee.setMilk(rs.getBoolean(5));
                coffee.setCoffeeBeans(rs.getInt(6));
            }

        } catch (SQLException exception) {
            exception.printStackTrace();
        } finally {
            connection.close();
            log.info("connection closed" );
        } return coffee;
    }

    public static List<Coffee> getAllCoffee() throws SQLException {
        log.info("gettingAllCoffee()");

        List<Coffee> listCoffees = new ArrayList<>();
        Connection connection = CoffeeRepository.getConnection();

        try {
            PreparedStatement ps = connection.prepareStatement("select * from coffee");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                Coffee coffee = new Coffee();

                coffee.setId(rs.getInt(1));
                coffee.setName(rs.getString(2));
                coffee.setPrice(rs.getDouble(3));
                coffee.setSugar(rs.getBoolean(4));
                coffee.setMilk(rs.getBoolean(5));
                coffee.setCoffeeBeans(rs.getInt(6));

                listCoffees.add(coffee);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connection.close();
            log.info("connection closed");
        } return listCoffees;
    }

    public static int add(Check check) throws SQLException {
        log.info("saving() check = {}", check);
        int status = 0;
        Connection connection = CoffeeRepository.getConnection();
        try {
            PreparedStatement ps = connection.prepareStatement("insert into checks(guestname,quantity,coffeeid,total) values (?,?,?,?)");
            ps.setString(1, check.getGuestName());
            ps.setInt(2, check.getQuantity());
            ps.setInt(3, check.getCoffeeId());
            ps.setDouble(4, check.getTotal());

            status = ps.executeUpdate();


        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            connection.close();
            log.info("connection closed. Status:",status);
        } return status;
    }

    public static Check getOrderById(int orderId) throws SQLException {
        log.info("gettingOrderById() order id:" + orderId);

        Check check = new Check();
        Connection connection = CoffeeRepository.getConnection();

        try {
            PreparedStatement ps = connection.prepareStatement("select * from checks where orderid=?");
            ps.setInt(1, orderId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                check.setOrderId(rs.getInt(1));
                check.setGuestName(rs.getString(2));
                check.setQuantity(rs.getInt(3));
                check.setCoffeeId(rs.getInt(4));
                check.setTotal(rs.getDouble(5));
            }

        } catch (SQLException exception) {
            exception.printStackTrace();
        } finally {
            connection.close();
            log.info("connection closed" );
        } return check;
    }
    public static List<Check> getAllCheck() throws SQLException {
        log.info("gettingAllCheck()");

        List<Check> listCheck = new ArrayList<>();
        Connection connection = CoffeeRepository.getConnection();

        try {
            PreparedStatement ps = connection.prepareStatement("select * from checks");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                Check check = new Check();

                check.setOrderId(rs.getInt(1));
                check.setGuestName(rs.getString(2));
                check.setQuantity(rs.getInt(3));
                check.setCoffeeId(rs.getInt(4));
                check.setTotal(rs.getDouble(5));

                listCheck.add(check);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connection.close();
            log.info("connection closed");
        } return listCheck;
    }
    public static int deleteCheck(int orderId) {
        log.info("deleting() check orderId:"+ orderId);

        int status = 0;

        try {
            Connection connection = CoffeeRepository.getConnection();
            PreparedStatement ps = connection.prepareStatement("delete from checks where orderid=?");
            ps.setInt(1, orderId);
            status = ps.executeUpdate();

            connection.close();

        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        log.info("connection closed. Status:" + status);
        return status;
    }
}
