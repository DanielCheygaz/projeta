import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class MovieAddWindow extends JFrame{
    private JTextField textFieldName;
    private JButton cancelButton;
    private JButton saveButton;
    private JPanel mainPanel;
    private JTextField textFieldDuration;
    private JRadioButton imaxRadioButton;
    private JRadioButton dolbyAtmosRadioButton;
    private JComboBox comboBoxGenre;
    private JButton addGenreButton;
    private JTextArea textAreaDescription;

    public MovieAddWindow() throws HeadlessException {
        super("Adicionar Filme");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setContentPane(mainPanel);
        pack();


        comboBoxGenre.setName("Categorias");

        for(Genre genre: AppData.getInstance().getGenreList()){
            comboBoxGenre.addItem(genre.getName());
        }


        this.comboBoxGenre.addActionListener(this::comboBoxGenrePerformed);
        this.addGenreButton.addActionListener(this::addGenreButtonPerformed);

    }

    private void comboBoxGenrePerformed(ActionEvent e){
        comboBoxGenre.repaint();
    }

    private void addGenreButtonPerformed(ActionEvent e){
        new GenreAddWindow().setVisible(true);
    }

    private void cancelButtonPerformed(ActionEvent e){
        new MovieManagerWindow().setVisible(true);
        dispose();
    }

    public static void main(String[] args){
        new MovieAddWindow().setVisible(true);
    }
}
