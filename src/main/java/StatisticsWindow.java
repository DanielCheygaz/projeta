import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class StatisticsWindow extends JFrame{
    private JPanel mainPanel;
    private JLabel produtosStatistics;
    private JLabel genresStatistics;
    private JLabel moviesStatistics;
    private JLabel ticketsStatistics;
    private JLabel sessionsStatistics;
    private JLabel roomsStatistics;
    private JButton backButton;

    public StatisticsWindow() throws HeadlessException {
        super("Statistics");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setContentPane(mainPanel);
        pack();

        produtosStatistics.setText(AppData.getInstance().getProductList().size()+"");
        genresStatistics.setText(AppData.getInstance().getGenreList().size()+"");
        moviesStatistics.setText(AppData.getInstance().getMovieList().size()+"");
        roomsStatistics.setText(AppData.getInstance().getRoomList().size()+"");
        sessionsStatistics.setText(AppData.getInstance().getSessionList().size()+"");
        ticketsStatistics.setText(AppData.getInstance().getTicketList().size()+"");

        this.backButton.addActionListener(this::backButtonPerformed);

    }

    private void backButtonPerformed(ActionEvent e){
        new MainWindow().setVisible(true);
        dispose();
    }
}
