import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;

public class MovieManagerWindow extends JFrame {
    private JButton backButton;
    private JScrollPane scrollPane;
    private JTable moviesTable;
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
        moviesTable.setModel(tableModel);
        moviesTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        this.addMovieButton.addActionListener(this::addButtonPerformed);
        this.removeMovieButton.addActionListener(this::removeMoviePerformed);
        this.editMovieButton.addActionListener(this::editProductButtonPerformed);
        this.backButton.addActionListener(this::backButtonPerformed);
    }

    private void addButtonPerformed(ActionEvent e){
        new ProductAddWindow().setVisible(true);
        dispose();
    }

    private void editProductButtonPerformed(ActionEvent e){
        int selectedRow = getSelectedRow();

        if(selectedRow==-1){
            return;
        }

        Stock stock = AppData.getInstance().getStockList().get(selectedRow);
        new ProductEditWindow(stock,selectedRow).setVisible(true);
        dispose();
    }


    private void removeMoviePerformed(ActionEvent e){
        int selectedRow = getSelectedRow();

        if(selectedRow==-1){
            return;
        }

        Movie movie = AppData.getInstance().getMovieList().get(selectedRow);
        AppData.getInstance().removeMovie(movie);
        tableModel.removeRow(selectedRow);
    }

    private void backButtonPerformed(ActionEvent e){
        new MainWindow().setVisible(true);
        dispose();
    }

    private int getSelectedRow(){
        int selectedRow = moviesTable.getSelectedRow();
        if(selectedRow==-1){
            new ErrorWindow("Selecione primeiro um produto").setVisible(true);
            return -1;
        }
        return selectedRow;
    }

    public static void main(String[] args){
        new MovieManagerWindow().setVisible(true);
    }
}