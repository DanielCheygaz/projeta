import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;

public class MovieManagerWindow extends JFrame implements ManagerInterface<Movie>{
    private JButton backButton;
    private JScrollPane scrollPane;
    private JTable moviesTable;
    private JButton addMovieButton;
    private JButton removeMovieButton;
    private JButton editMovieButton;
    private JPanel mainPanel;
    private String[] columns;
    private JButton manageGenresButton;
    private DefaultTableModel tableModel;
    private MainWindow previousWindow;

    public MovieManagerWindow(MainWindow previousWindow) throws HeadlessException {
        super("Gestor de Filmes");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setContentPane(mainPanel);
        pack();

        this.previousWindow = previousWindow;

        scrollPane.getViewport().setBackground(Color.decode("2894892"));
        moviesTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        String[] columns = {"Nome","Duração","Género", "Descrição","IMAX","Dolby Atmos"};
        this.columns = columns;
        refreshData();

        this.addMovieButton.addActionListener(this::addMovieButtonPerformed);
        this.removeMovieButton.addActionListener(this::removeMoviePerformed);
        this.editMovieButton.addActionListener(this::editMovieButtonPerformed);
        this.manageGenresButton.addActionListener(this::manageGenresButtonPerformed);
        this.backButton.addActionListener(this::backButtonPerformed);
    }

    public void refreshData(){
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
    }

    private void addMovieButtonPerformed(ActionEvent e){
        new MovieAddWindow(this).setVisible(true);
        setVisible(false);
    }

    private void editMovieButtonPerformed(ActionEvent e){
        int selectedRow = getSelectedRow();

        if(selectedRow==-1){
            return;
        }

        Movie movie = AppData.getInstance().getMovieList().get(selectedRow);
        new MovieEditWindow(this, movie).setVisible(true);
        setVisible(false);
    }


    private void removeMoviePerformed(ActionEvent e){
        int selectedRow = getSelectedRow();

        if(selectedRow==-1){
            return;
        }

        Movie movie = AppData.getInstance().getMovieList().get(selectedRow);
        if(!canBeDeleted(movie)){
            new ErrorWindow("Este filme está associado a uma sessão!").setVisible(true);
            return;
        }

        AppData.getInstance().removeMovie(movie);
        tableModel.removeRow(selectedRow);
    }

    public boolean canBeDeleted(Movie movie){
        for(Session session : AppData.getInstance().getSessionList()){
            if(session.getMovie() == movie){
                return false;
            }
        }
        return true;
    }


    private void manageGenresButtonPerformed(ActionEvent e){
        new GenreManagerWindow(this).setVisible(true);
        setVisible(false);
    }

    private void backButtonPerformed(ActionEvent e){
        dispose();
        previousWindow.setVisible(true);
    }

    private int getSelectedRow(){
        int selectedRow = moviesTable.getSelectedRow();
        if(selectedRow==-1){
            new ErrorWindow("Selecione primeiro um filme").setVisible(true);
            return -1;
        }
        return selectedRow;
    }
}