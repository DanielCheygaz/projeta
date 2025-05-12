import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class StatisticsWindow extends JFrame{
    private JPanel mainPanel;
    private JButton moviesStatisticsButton;
    private JButton barProductsStatisticsButton;
    private JButton sessionStatisticsButton;
    private JButton backButton;
    private JButton ticketStatisticsButton;


    public StatisticsWindow() throws HeadlessException {
        super("Statistics");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setContentPane(mainPanel);
        pack();

        this.moviesStatisticsButton.addActionListener(this::moviesStatisticsButtonActionPerformed);
        this.barProductsStatisticsButton.addActionListener(this::barProductsStatisticsButtonActionPerformed);
        this.sessionStatisticsButton.addActionListener(this::sessionStatisticsButtonActionPerformed);
        this.ticketStatisticsButton.addActionListener(this::ticketStatisticsButtonPerformed);
        this.backButton.addActionListener(this::backButtonPerformed);

    }

    private void backButtonPerformed(ActionEvent e){
        new MainWindow().setVisible(true);
        dispose();
    }

    private void moviesStatisticsButtonActionPerformed(ActionEvent e){
        new StatisticsMoviesWindow().setVisible(true);
        setVisible(false);
    }

    private void barProductsStatisticsButtonActionPerformed(ActionEvent e){
        new StatisticsBarProductsWindow().setVisible(true);
        setVisible(false);
    }

    private void sessionStatisticsButtonActionPerformed(ActionEvent e){
        new StatisticsSessionWindow().setVisible(true);
        setVisible(false);
    }

    private void ticketStatisticsButtonPerformed(ActionEvent e){
        new StatisticsTicketWindow().setVisible(true);
    }

}
