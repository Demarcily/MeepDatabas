import javax.swing.*;
import java.awt.*;

public class View {
  private JPanel mainpanel;
  private JPanel outputpanel;
  private JScrollPane scrollPane;

  public View() {
    mainpanel = new JPanel();
    outputpanel = new JPanel();

    mainpanel.setBackground(new Color(66, 66, 66));
    outputpanel.setPreferredSize(new Dimension(300,500));
    outputpanel.setBorder(BorderFactory.createLineBorder(Color.black));
    outputpanel.setBackground(new Color(112, 112, 112));
    scrollPane = new JScrollPane(outputpanel);
    scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
    mainpanel.add(scrollPane);
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


}
