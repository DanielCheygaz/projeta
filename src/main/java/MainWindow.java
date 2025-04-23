import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class MainWindow extends JFrame{
    private JButton manageSessionsButton;
    private JButton button2;
    private JButton manageStockButton;
    private JButton button4;
    private JPanel mainPanel;

    public MainWindow() throws HeadlessException {
        super("Cinema Projeta");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setContentPane(mainPanel);
        pack();
        this.manageSessionsButton.addActionListener(this::manageSessionsButtonActionPerformed);
        this.manageStockButton.addActionListener(this::manageStockButtonActionPerformed);
    }

    private void manageSessionsButtonActionPerformed(ActionEvent e){
        new SessionManagerWindow().setVisible(true);
        setVisible(false);
    }
    private void manageStockButtonActionPerformed(ActionEvent e){
        new StockManagerWindow().setVisible(true);
        setVisible(false);
    }

    public static void main(String[] args){new MainWindow().setVisible(true);}
}
