import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class MainWindow extends JFrame{
    private JButton manageSessionsButton;
    private JButton salesButton;
    private JButton manageStockButton;
    private JButton manageRoomButton;
    private JPanel mainPanel;
    private JButton statisticsButton;
    private JButton manageMoviesButton;

    public MainWindow() throws HeadlessException {
        super("Cinema Projeta");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setContentPane(mainPanel);
        pack();

        this.manageMoviesButton.addActionListener(this::manageMoviesButtonPerformed);
        this.manageSessionsButton.addActionListener(this::manageSessionsButtonActionPerformed);
        this.manageStockButton.addActionListener(this::manageStockButtonActionPerformed);
        this.salesButton.addActionListener(this::manageSalesButtonActionPerformed);
        this.manageRoomButton.addActionListener(this::manageRoomButtonActionPerformed);
        this.statisticsButton.addActionListener(this::statisticsButtonActionPerformed);
    }

    private void manageMoviesButtonPerformed(ActionEvent e){
        new MovieManagerWindow().setVisible(true);
        setVisible(false);
    }

    private void statisticsButtonActionPerformed(ActionEvent e){
        new StatisticsWindow().setVisible(true);
        setVisible(false);
    }

    private void manageRoomButtonActionPerformed(ActionEvent e){
        new RoomManagerWindow().setVisible(true);
        setVisible(false);
    }

    private void manageSessionsButtonActionPerformed(ActionEvent e){
        new SessionManagerWindow().setVisible(true);
        setVisible(false);
    }

    private void manageStockButtonActionPerformed(ActionEvent e){
        new StockManagerWindow().setVisible(true);
        setVisible(false);
    }

    private void manageSalesButtonActionPerformed(ActionEvent e){
        new SalesMainWindow().setVisible(true);
        setVisible(false);
    }

    public static void main(String[] args){new MainWindow().setVisible(true);}
}
