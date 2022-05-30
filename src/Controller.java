import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Controller {
  private Model model = new Model();
  private View view = new View();
  private JFrame frame;
  private JButton button;

  public Controller() {
    frame = new JFrame();
    frame.setPreferredSize(new Dimension(900,600));
    frame.add(view.getMainPanel());

    //Buttons
    button = new JButton("Fetch");
    button.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent actionEvent) {
        model.fetchData(view.getOutputPanel(), frame);
      }
    });
    view.getMainPanel().add(button);

    button = new JButton("Upload");
    button.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent actionEvent) {
        model.createMeep(view.getPostText().getText());
        model.fetchData(view.getOutputPanel(), frame);
      }
    });
    view.getMainPanel().add(button);

    button = new JButton("Delete");
    button.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent actionEvent) {
        model.deleteMeep(view.getIDText().getText());
        model.fetchData(view.getOutputPanel(), frame);

      }
    });
    view.getMainPanel().add(button);

    button = new JButton("Edit");
    button.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent actionEvent) {
        model.updateMeep(view.getIDText().getText(), view.getPostText().getText());
        model.fetchData(view.getOutputPanel(), frame);
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



