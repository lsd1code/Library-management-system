package library_management_system;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Database {
  private static final String url = "jdbc:postgresql://localhost:5432/lib";
  private static final String username = "postgres";
  private static final String password = "3691215";

  public static Connection getConnection() throws SQLException {
    return DriverManager.getConnection(url, username, password);
  }

  public static void closeConnection(Connection connection) throws SQLException {
    connection.close();
  }

  public static void closePreparedStatement(PreparedStatement ps) throws SQLException {
    ps.close();
  }
}
