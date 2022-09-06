package com.example.demo.order;

import com.example.demo.Logged;
import com.example.demo.coffee.Coffee;
import lombok.extern.slf4j.Slf4j;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Logged
@Slf4j
public class OrderRepository {

    public static void main(String[] args) throws SQLException {

        getConnection();
        System.out.println(getEntriesByCheckId(1));
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


    public static int addEntry(Order order) throws SQLException {
        log.info("saving() entry = {}", order);
        int status = 0;
        Connection connection = com.example.demo.order.OrderRepository.getConnection();
        try {

            final Coffee coffeeById = com.example.demo.coffee.CoffeeRepository.getCoffeeById(order.getCoffeeId());
            double coffeePrice = coffeeById.getPrice();

            order.setEntryTotal(coffeePrice * order.getQuantity());

            PreparedStatement ps = connection.prepareStatement("insert into orders(checkid,coffeeid,quantity,itemprice,entrytotal) values (?,?,?,?,?)");
            ps.setInt(1, order.getCheckId());
            ps.setInt(2, order.getCoffeeId());
            ps.setInt(3, order.getQuantity());
            ps.setDouble(4, coffeePrice);
            ps.setDouble(5, order.getEntryTotal());

            status = ps.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            connection.close();
            log.info("connection closed. Status:", status);
        }
        return status;
    }

    public static int updateEntry(Order order) throws SQLException {
        log.info("updating() entry = {} ", order);

        int status = 0;

        Connection connection = com.example.demo.order.OrderRepository.getConnection();
        try {
            PreparedStatement ps = connection.prepareStatement("update orders set checkid=?,coffeeid=?,quantity=?,itemprice=?,entrytotal=? where entryid=?");
            ps.setInt(1, order.getCheckId());
            ps.setInt(2, order.getCoffeeId());
            ps.setInt(3, order.getQuantity());
            ps.setDouble(4, order.getItemPrice());
            ps.setDouble(5, order.getEntryTotal());
            ps.setInt(6,order.getEntryId());

            status = ps.executeUpdate();

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        } finally {
            connection.close();
            log.info("connection closed. Status:" + status);
        }
        return status;
    }

    public static int deleteEntryById(int entryId) {
        log.info("deleting() entry id:" + entryId);

        int status = 0;

        try {
            Connection connection = com.example.demo.order.OrderRepository.getConnection();
            PreparedStatement ps = connection.prepareStatement("delete from orders where entryid=?");
            ps.setInt(1, entryId);
            status = ps.executeUpdate();

            connection.close();

        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        log.info("connection closed. Status:" + status);
        return status;
    }

    public static Order getEntryById(int entryId) throws SQLException {
        log.info("getting Entry by Id() entry id:" + entryId);

        Order order = new Order();
        Connection connection = com.example.demo.order.OrderRepository.getConnection();

        try {
            PreparedStatement ps = connection.prepareStatement("select * from orders where entryid=?");
            ps.setInt(1, entryId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                order.setEntryId(rs.getInt(1));
                order.setCheckId(rs.getInt(2));
                order.setCoffeeId(rs.getInt(3));
                order.setQuantity(rs.getInt(4));
                order.setItemPrice(rs.getDouble(5));
                order.setEntryTotal(rs.getDouble(6));
            }

        } catch (SQLException exception) {
            exception.printStackTrace();
        } finally {
            connection.close();
            log.info("connection closed");
        }
        return order;
    }

    public static List<Order> getAllOrders() throws SQLException {
        log.info("gettingAllOrders()");

        List<Order> listOfOrders = new ArrayList<>();
        Connection connection = com.example.demo.order.OrderRepository.getConnection();

        try {
            PreparedStatement ps = connection.prepareStatement("select * from orders");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                Order order = new Order();

                order.setEntryId(rs.getInt(1));
                order.setCheckId(rs.getInt(2));
                order.setCoffeeId(rs.getInt(3));
                order.setQuantity(rs.getInt(4));
                order.setItemPrice(rs.getDouble(5));
                order.setEntryTotal(rs.getDouble(6));


                listOfOrders.add(order);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connection.close();
            log.info("connection closed");
        }
        return listOfOrders;
    }

    public static List<Order> getEntriesByCheckId(int checkId) throws SQLException {
        log.info("getting all entries by check id" + checkId);

        List<Order> listOfEntries = new ArrayList<>();
        Connection connection = com.example.demo.order.OrderRepository.getConnection();
        log.info("creating a list of Entries");
        try {
            PreparedStatement ps = connection.prepareStatement("select * from orders where checkid=?");
            ps.setInt(1, checkId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                Order order = new Order();

                order.setEntryId(rs.getInt(1));
                order.setCheckId(rs.getInt(2));
                order.setCoffeeId(rs.getInt(3));
                order.setQuantity(rs.getInt(4));
                order.setItemPrice(rs.getDouble(5));
                order.setEntryTotal(rs.getDouble(6));


                listOfEntries.add(order);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connection.close();
            log.info("connection closed");
        }
        return listOfEntries;
    }


//    public static int add(Order order) throws SQLException {
//        log.info("saving() order = {}", order);
//        int status = 0;
//        Connection connection = com.example.demo.coffee.CoffeeRepository.getConnection();
//        try {
//
//            final Coffee coffeeById = com.example.demo.coffee.CoffeeRepository.getCoffeeById(order.getCoffeeId());
//            double coffeePrice = coffeeById.getPrice();
//
//            order.setTotal(coffeePrice * order.getQuantity());
//
//            PreparedStatement ps = connection.
//                    prepareStatement("insert into checks(guestname,quantity,coffeeid,total) values (?,?,?,?)");
//            ps.setString(1, order.getGuestName());
//            ps.setInt(2, order.getQuantity());
//            ps.setInt(3, order.getCoffeeId());
//            ps.setDouble(4, order.getTotal());
//
//
//            status = ps.executeUpdate();
//        } catch (SQLException ex) {
//            ex.printStackTrace();
//        } finally {
//            connection.close();
//            log.info("connection closed. Status:", status);
//        }
//        return status;
//    }
}
