import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;

public class Model {
  Connection conn;

  public Model() {
    String username = JOptionPane.showInputDialog(null, "Enter username");
    JPasswordField pf = new JPasswordField();
    JOptionPane.showConfirmDialog(null, pf, "Enter Password", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
    String password = new String(pf.getPassword());

    try {
      // Set up connection to database
      conn = DriverManager.getConnection(
              "jdbc:mysql://db.umea-ntig.se:3306/te19? " +
                      "allowPublicKeyRetrieval=true&useSSL=false&serverTimezone=UTC",
              username, password);
    } catch (SQLException e) {
      e.printStackTrace();
      System.err.println("Failed to log in, check your database and credentials and try again. Shutting down...");
      System.exit(0);
    }
  }


  public ArrayList<String> getDatabaseContent() {
    ArrayList<String> result = new ArrayList<>();
    try {
      Statement stmt = conn.createStatement();
      String SQLQuery = "SELECT * FROM limmuy_meeps";
      ResultSet rset = stmt.executeQuery(SQLQuery);

      while (rset.next()) {
        result.add(rset.getInt("id") + ", " +
                rset.getString("body") + ", " +
                rset.getTime("created") + ", " +
                rset.getTime("edited") + "\n");
      }

    } catch (SQLException e) {
      e.printStackTrace();
      System.err.println("Something went wrong, unable to get data");
    }
    return result;
  }

  public void create(String body) {
    if (body.length() > 3) {
      try {
        Statement stmt = conn.createStatement();
        String SQLUpdate = "INSERT INTO limmuy_meeps (body) VALUES ('" + body + "')";
        stmt.executeUpdate(SQLUpdate);

      } catch (SQLException e) {
        e.printStackTrace();
        System.err.println("Failed to post meep");
        }
    } else {
      System.out.println("Post is too short, atleast 4 letters");
    }
  }
}
