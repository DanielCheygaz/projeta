import javax.swing.*;
import java.awt.*;

public class MovieManagerWindow extends JFrame {
    private JButton backButton;
    private JScrollPane scrollPane;
    private JTable productsTable;
    private JButton addMovieButton;
    private JButton removeMovieButton;
    private JButton editMovieButton;
    private JPanel mainPanel;

    public MovieManagerWindow() throws HeadlessException {
        super("Gestor de Filmes");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setContentPane(mainPanel);
        pack();
    }
    public static void main(String[] args){
        new MovieManagerWindow().setVisible(true);
    }

}
