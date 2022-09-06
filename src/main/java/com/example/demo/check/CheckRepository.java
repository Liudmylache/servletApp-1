package com.example.demo.check;

import com.example.demo.Logged;
import com.example.demo.order.Order;
import lombok.extern.slf4j.Slf4j;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class CheckRepository {

//@Logged
    public static void main(String[] args) throws SQLException {


        getConnection();

        Check check = new Check();
        calculateTotal(6);
        System.out.println(check.total);
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

    @Logged
//    public static int saveCheck(Check check) throws SQLException {
//        log.info("saving() check = {}", check);
//        int status = 0;
//        Connection connection = com.example.demo.check.CheckRepository.getConnection();
//        try {
//            List <Order> orderByCheck = com.example.demo.order.OrderRepository.getEntriesByCheckId(check.getCheckId());
//
//            double totalByCheck = 0.00;
//            for (Order order: orderByCheck){
//                totalByCheck += order.getEntryTotal();
//            }
//            check.setTotal(totalByCheck);
//
//            PreparedStatement ps = connection.prepareStatement("insert into checks(guestname,total) values (?,?)");
//            ps.setString(1, check.getGuestName());
//            ps.setDouble(2, check.getTotal());
//
//            status = ps.executeUpdate();
//
//
//        } catch (SQLException ex) {
//            ex.printStackTrace();
//        } finally {
//            connection.close();
//            log.info("connection closed. Status:",status);
//        } return status;
//    }

    public static int saveCheck(Check check) throws SQLException {
        log.info("saving() check = {}", check);
        int status = 0;
        Connection connection = com.example.demo.check.CheckRepository.getConnection();
        try {

            PreparedStatement ps = connection.prepareStatement("insert into checks(guestname,total) values (?,?)");
            ps.setString(1, check.getGuestName());
            ps.setDouble(2, 0.00);

            status = ps.executeUpdate();


        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            connection.close();
            log.info("connection closed. Status:",status);
        } return status;
    }

    public static int calculateTotal(int checkId) throws SQLException {
        log.info("calculating total = {}", checkId);
        int status = 0;
        Connection connection = com.example.demo.check.CheckRepository.getConnection();
        try {
            List <Order> orderByCheck = com.example.demo.order.OrderRepository.getEntriesByCheckId(checkId);

            double totalByCheck = 0.00;
            for (Order order: orderByCheck){
                totalByCheck += order.getEntryTotal();
            }

            Check check = getCheckById(checkId);
            check.setTotal(totalByCheck);
            updateById(check);

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        } finally {
            connection.close();
            log.info("connection closed. Status:" + status);
        } return status;
    }

    @Logged
    public static int updateById(Check check) throws SQLException {
        log.info("updating() check = {} ", check);

        int status = 0;

        Connection connection = com.example.demo.check.CheckRepository.getConnection();
        try {
            PreparedStatement ps = connection.prepareStatement("update checks set guestname=?,total=? where checkid=?");
            ps.setString(1, check.getGuestName());
            ps.setDouble(2, check.getTotal());
            ps.setInt(3,check.getCheckId());

            status = ps.executeUpdate();

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        } finally {
            connection.close();
            log.info("connection closed. Status:" + status);
        } return status;
    }

    public static int deleteCheckById(int checkId) {
        log.info("deleting() check id:"+ checkId);

        int status = 0;

        try {
            Connection connection = com.example.demo.check.CheckRepository.getConnection();
            PreparedStatement ps = connection.prepareStatement("delete from checks where checkid=?");
            ps.setInt(1, checkId);
            status = ps.executeUpdate();

            connection.close();

        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        log.info("connection closed. Status:" + status);
        return status;
    }

    public static Check getCheckById(int checkId) throws SQLException {
        log.info("gettingCheckById() check id:" + checkId);

        Check check = new Check();
        Connection connection = com.example.demo.check.CheckRepository.getConnection();

        try {
            PreparedStatement ps = connection.prepareStatement("select * from checks where checkid=?");
            ps.setInt(1, checkId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                check.setCheckId(rs.getInt(1));
                check.setGuestName(rs.getString(2));
                check.setTotal(rs.getDouble(3));
            }

        } catch (SQLException exception) {
            exception.printStackTrace();
        } finally {
            connection.close();
            log.info("connection closed" );
        } return check;
    }

    public static List<Check> getAllChecks() throws SQLException {
        log.info("gettingAllChecks()");

        List<Check> listOfChecks = new ArrayList<>();
        Connection connection = com.example.demo.check.CheckRepository.getConnection();

        try {
            PreparedStatement ps = connection.prepareStatement("select * from checks");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                Check check = new Check();

                check.setCheckId(rs.getInt(1));
                check.setGuestName(rs.getString(2));
                check.setTotal(rs.getDouble(3));


                listOfChecks.add(check);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connection.close();
            log.info("connection closed");
        } return listOfChecks;
    }
}