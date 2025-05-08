import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class MovieManagerWindow extends JFrame {
    private JButton backButton;
    private JScrollPane scrollPane;
    private JTable productsTable;
    private JButton addMovieButton;
    private JButton removeMovieButton;
    private JButton editMovieButton;
    private JPanel mainPanel;
    private DefaultTableModel tableModel;

    public MovieManagerWindow() throws HeadlessException {
        super("Gestor de Filmes");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setContentPane(mainPanel);
        pack();

        scrollPane.getViewport().setBackground(Color.decode("2894892"));
        String[] columns = {"Nome","Duração","Género", "Descrição","IMAX","Dolby Atmos"};

        tableModel = new DefaultTableModel(columns,0);

        for(Movie movie: AppData.getInstance().getMovieList()){
            Object[] row = {
                    movie.getName(),
                    movie.getDuration(),
                    movie.getGenre().getName(),
                    movie.getDescription(),
                    movie.isImax() == true ? "Sim" : "Não",
                    movie.isDolbyAtmos() == true ? "Sim" : "Não"
            };
            tableModel.addRow(row);
        }
        productsTable.setModel(tableModel);
        productsTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    }
    public static void main(String[] args){
        new MovieManagerWindow().setVisible(true);
    }

}
