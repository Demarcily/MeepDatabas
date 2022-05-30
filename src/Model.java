import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.util.ArrayList;
import static java.lang.Integer.parseInt;

public class Model {
  private JTextArea outputText;
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

  public void createMeep(String body) {
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

  public void deleteMeep(String idString) {
    int id = parseInt(idString);
    try {
      Statement stmt = conn.createStatement();
      String SQLDelete = "DELETE FROM limmuy_meeps WHERE id = ('" + id + "')";
      stmt.executeUpdate(SQLDelete);
    } catch(SQLException e) {
      e.printStackTrace();
      System.err.println("Failed to find meep");
    }
  }

  public void updateMeep(String idString, String meep) {
    int id = parseInt(idString);
    try {
      Statement stmt = conn.createStatement();
      String SQLDelete = "UPDATE limmuy_meeps SET body = ('" + meep + "') WHERE id = ('" + id + "')";
      stmt.executeUpdate(SQLDelete);
    } catch(SQLException e) {
      e.printStackTrace();
      System.err.println("Failed to find update meep");
    }
  }

  public void fetchData(JPanel outputPanel, JFrame frame) {
    outputPanel.removeAll();
    frame.repaint();
    for (String s : getDatabaseContent()){
      outputText = new JTextArea();
      outputText.setText(s);
      outputText.setEditable(false);
      outputText.setForeground(Color.WHITE);
      outputText.setBackground(null);
      outputPanel.add(outputText);
    }
    frame.revalidate();
  }
}
