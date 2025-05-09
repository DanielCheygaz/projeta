import javax.swing.*;
import java.awt.event.ActionEvent;

public class SalesManagerWindow extends JFrame{
    private JPanel mainPanel;
    private JButton backButton;
    private JButton manageBundleButton;
    private JButton salesHistoryButton;

    public SalesManagerWindow(){
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setContentPane(mainPanel);
        pack();

        this.backButton.addActionListener(this::backButtonPerformed);
        this.manageBundleButton.addActionListener(this::manageBundleButtonPerformed);
        this.salesHistoryButton.addActionListener(this::salesHistoryButtonPerformed);
    }

    private void backButtonPerformed(ActionEvent e){
        new SalesMainWindow().setVisible(true);
        dispose();
    }

    private void manageBundleButtonPerformed(ActionEvent e){
        new BundleManagerWindow().setVisible(true);
        dispose();
    }

    private void salesHistoryButtonPerformed(ActionEvent e){
        new SalesHistoryWindow().setVisible(true);
        dispose();
    }

    public static void main(String[] args){
        new SalesManagerWindow().setVisible(true);
    }
}
