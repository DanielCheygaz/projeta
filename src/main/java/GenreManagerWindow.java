import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;

public class GenreManagerWindow extends JFrame implements ManagerInterface<Genre>{
    private JButton backButton;
    private JScrollPane scrollPane;
    private JTable genresTable;
    private JButton addGenreButton;
    private JButton removeGenreButton;
    private JButton editGenreButton;
    private JPanel mainPanel;
    private DefaultTableModel tableModel;

    public GenreManagerWindow() throws HeadlessException {
        super("Gestão de Géneros");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setContentPane(mainPanel);
        pack();

        scrollPane.getViewport().setBackground(Color.decode("2894892"));
        String[] columns = {"Nome"};

        tableModel = new DefaultTableModel(columns,0);

        for(Genre genre: AppData.getInstance().getGenreList()){
            Object[] row = {
                    genre.getName()
            };
            tableModel.addRow(row);
        }
        genresTable.setModel(tableModel);
        genresTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        this.addGenreButton.addActionListener(this::addGenreButtonPerformed);
        this.removeGenreButton.addActionListener(this::removeGenreButtonPerformed);
        this.editGenreButton.addActionListener(this::editGenreButtonPerformed);
        this.backButton.addActionListener(this::backButtonPerformed);
    }

    private void addGenreButtonPerformed(ActionEvent e){
        new GenreAddWindow().setVisible(true);
        dispose();
    }

    private void removeGenreButtonPerformed(ActionEvent e){
        int selectedRow = genresTable.getSelectedRow();
        if(selectedRow==-1){
            new ErrorWindow("Selecione primeiro um género").setVisible(true);
            return;
        }

        Genre genre = AppData.getInstance().getGenreList().get(selectedRow);
        if(!canBeDeleted(genre)){
            new ErrorWindow("Este género está associado a um filme!").setVisible(true);
            return;
        }
        AppData.getInstance().removeGenre(genre);
        tableModel.removeRow(selectedRow);
    }

    public boolean canBeDeleted(Genre genre){
        for(Movie movie : AppData.getInstance().getMovieList()){
            if(movie.getGenre() == genre){
                return false;
            }
        }
        return true;
    }

    private void editGenreButtonPerformed(ActionEvent e){
        int selectedRow = genresTable.getSelectedRow();
        if(selectedRow==-1){
            new ErrorWindow("Selecione primeiro um género").setVisible(true);
            return;
        }

        Genre genre = AppData.getInstance().getGenreList().get(selectedRow);
        new GenreEditWindow(genre).setVisible(true);
        dispose();
    }

    private void backButtonPerformed(ActionEvent e){
        new MovieManagerWindow().setVisible(true);
        dispose();
    }

    public static void main(String[] args){
        new GenreManagerWindow().setVisible(true);
    }
}
