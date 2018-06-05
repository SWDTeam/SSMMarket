package ssm.db;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {
  public static Connection getConnection() {
    try {
      Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
      Connection conn = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=ssmmarket", "sa", "sa12345678");
      return conn;
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }
}
