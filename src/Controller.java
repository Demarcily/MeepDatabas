import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Controller {
  private Model model = new Model();
  private View view = new View();
  private JFrame frame;
  private JButton button;
  private JTextArea outputText;

  public Controller() {
    frame = new JFrame();
    frame.setPreferredSize(new Dimension(800,700));
    frame.add(view.getMainPanel());

    //Buttons
    button = new JButton("Fetch");
    button.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent actionEvent) {
        view.getOutputPanel().removeAll();
        for (String s : model.getDatabaseContent()){
          outputText = new JTextArea();
          outputText.setText(s);
          outputText.setEditable(false);
          outputText.setForeground(Color.WHITE);
          outputText.setBackground(null);
          view.getOutputPanel().add(outputText);
        }
        frame.revalidate();
      }
    });
    view.getMainPanel().add(button);






    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setResizable(true);
    frame.pack();
    frame.setLocationRelativeTo(null);
    frame.setVisible(true);
    frame.requestFocus();
  }

  public static void main (String [] args) {
    Controller c = new Controller();

  }


}



