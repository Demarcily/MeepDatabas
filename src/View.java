import javax.swing.*;
import java.awt.*;

public class View {
  private JPanel mainpanel;
  private JPanel outputpanel;
  private JScrollPane scrollPane;
  private JTextArea postText;
  private JTextArea IDText;

  public View() {
    mainpanel = new JPanel();
    outputpanel = new JPanel();
    postText = new JTextArea();
    IDText = new JTextArea();
    postText.setPreferredSize(new Dimension(100,20));
    postText.setText("Text here");
    IDText.setPreferredSize(new Dimension(100,20));
    IDText.setText("ID here");


    mainpanel.setBackground(new Color(66, 66, 66));
    outputpanel.setPreferredSize(new Dimension(300,500));
    outputpanel.setBorder(BorderFactory.createLineBorder(Color.black));
    outputpanel.setBackground(new Color(112, 112, 112));
    scrollPane = new JScrollPane(outputpanel);
    scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
    mainpanel.add(scrollPane);
    mainpanel.add(postText);
    mainpanel.add(IDText);
  }

  public JPanel getMainPanel() {
    return mainpanel;
  }

  public JPanel getOutputPanel() {
    return outputpanel;
  }

  public JScrollPane getScrollPane() {
    return scrollPane;
  }

  public JTextArea getPostText() {
    return postText;
  }

  public JTextArea getIDText() {
    return IDText;
  }


}
