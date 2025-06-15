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
    private MovieManagerWindow previousWindow;
    private String[] columns;

    public GenreManagerWindow(MovieManagerWindow previousWindow) throws HeadlessException {
        super("Gestão de Géneros");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setContentPane(mainPanel);
        pack();

        this.previousWindow = previousWindow;

        scrollPane.getViewport().setBackground(Color.decode("2894892"));
        genresTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        String[] columns = {"Nome"};
        this.columns = columns;

        refreshData();

        this.addGenreButton.addActionListener(this::addGenreButtonPerformed);
        this.removeGenreButton.addActionListener(this::removeGenreButtonPerformed);
        this.editGenreButton.addActionListener(this::editGenreButtonPerformed);
        this.backButton.addActionListener(this::backButtonPerformed);
    }

    public void refreshData(){
        tableModel = new DefaultTableModel(columns,0);

        for(Genre genre: AppData.getInstance().getGenreList()){
            Object[] row = {
                    genre.getName()
            };
            tableModel.addRow(row);
        }
        genresTable.setModel(tableModel);
    }

    private void addGenreButtonPerformed(ActionEvent e){
        new GenreAddWindow(this).setVisible(true);
        setVisible(false);
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
        new GenreEditWindow(this, genre).setVisible(true);
        setVisible(false);
    }

    private void backButtonPerformed(ActionEvent e){
        previousWindow.setVisible(true);
        dispose();
    }
}
