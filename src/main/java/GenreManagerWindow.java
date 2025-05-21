import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;

public class GenreManagerWindow extends JFrame{
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
        this.backButton.addActionListener(this::backButtonPerformed);
    }

    private void addGenreButtonPerformed(ActionEvent e){
        new GenreAddWindow().setVisible(true);
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
