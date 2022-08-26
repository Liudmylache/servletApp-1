package com.example.demo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CoffeeRepository {

   /* public static void main(String[] args) throws SQLException {
        getConnection();

        Coffee coffee = new Coffee();

        coffee.setName("Matcha");
        coffee.setPrice(7.30);
        coffee.setSugar(true);
        coffee.setMilk(false);
        coffee.setCoffeeBeans(15);
        save(coffee);
    }

    */

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
        return connection;
    }

    public static int save(Coffee coffee) throws SQLException {
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
        } return status;
    }

    public static int update(Coffee coffee) throws SQLException {

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
        } return status;
    }

    public static int delete(int id) {

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
        return status;
    }

    public static Coffee getCoffeeById(int id) throws SQLException {

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
        } return coffee;
    }

    public static List<Coffee> getAllCoffee() throws SQLException {

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
        } return listCoffees;
    }
}
