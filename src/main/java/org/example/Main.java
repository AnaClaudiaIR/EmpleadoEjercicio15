package org.example;

import java.sql.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        try (Connection connection = DriverManager.getConnection(
                DBConfig.getUrl(),
                DBConfig.getUser(),
                DBConfig.getPassword()
        );  Statement statement = connection.createStatement()) {

            //String con la sentencia
            String sql = "SELECT NOMBRE_EMP, NOMBRE_DEP " +
                    "FROM EMPLEADO2 JOIN DEPARTAMENTO USING (ID_DEP) "
                    + "ORDER BY NOMBRE_EMP";

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();

            //Mientras haya resultados, los guarda en las variables
            while (resultSet.next()) {
                String empleado = resultSet.getString("NOMBRE_EMP");
                String departamento = resultSet.getString("NOMBRE_DEP");

                System.out.println("Empleado: " + empleado + " - Departamento: " + departamento);
            }
        } catch (SQLException e){
            System.out.println("ERROR --> "+e.getMessage());
        }
    }
}
