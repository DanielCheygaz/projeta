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

        this.saveButton.addActionListener(this::saveButtonPerformed);
        this.cancelButton.addActionListener(this::cancelButtonPerformed);
        this.comboBoxGenre.addActionListener(this::comboBoxGenrePerformed);
        this.addGenreButton.addActionListener(this::addGenreButtonPerformed);

    }

    private void saveButtonPerformed(ActionEvent e){
        String title = textFieldName.getText();
        if(title.isBlank()){
            new ErrorWindow("Tem de introduzir um título").setVisible(true);
            return;
        }

        int duration;
        // verificar que os valores do preço e unidades inseridos contêm apenas números
        try {
            duration = Integer.valueOf(textFieldDuration.getText());
        }catch(NumberFormatException ex){
            new ErrorWindow("A duração tem de ser um número inteiro." + ex.getMessage()).setVisible(true);
            return;
        }

        int genreIndex = comboBoxGenre.getSelectedIndex();
        Genre genre = AppData.getInstance().getGenreList().get(genreIndex);
        String description = textAreaDescription.getText();
        boolean imax = imaxRadioButton.isSelected();
        boolean dolbyAtmos = dolbyAtmosRadioButton.isSelected();

        AppData.getInstance().addMovie(new Movie(title,duration,description,genre,imax,dolbyAtmos));

        new MovieManagerWindow().setVisible(true);
        dispose();
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
