import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class MovieEditWindow extends JFrame{
    private JTextField textFieldName;
    private JTextField textFieldDuration;
    private JTextArea textAreaDescription;
    private JButton cancelButton;
    private JButton saveButton;
    private JRadioButton imaxRadioButton;
    private JRadioButton dolbyAtmosRadioButton;
    private JComboBox comboBoxGenre;
    private JButton addGenreButton;
    private JPanel mainPanel;
    private Movie movie;

    public MovieEditWindow(Movie movie) throws HeadlessException {
        super("Editar: " + movie.getName());
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setContentPane(mainPanel);
        pack();

        this.movie = movie;

        textFieldName.setText(movie.getName());
        textFieldDuration.setText(String.valueOf(movie.getDuration()));
        textAreaDescription.setText(movie.getDescription());

        for(Genre genre: AppData.getInstance().getGenreList()){
            comboBoxGenre.addItem(genre.getName());
        }
        int movieGenreIndex = AppData.getInstance().getGenreList().indexOf(movie.getGenre());
        comboBoxGenre.setSelectedIndex(movieGenreIndex);

        imaxRadioButton.setSelected(movie.isImax());
        dolbyAtmosRadioButton.setSelected(movie.isDolbyAtmos());

        this.saveButton.addActionListener(this::saveButtonPerformed);
        this.cancelButton.addActionListener(this::cancelButtonPerformed);
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

        movie.updateMovie(title,duration,description,genre,imax,dolbyAtmos);

        new MovieManagerWindow().setVisible(true);
        dispose();
    }

    private void cancelButtonPerformed(ActionEvent e){
        new MovieManagerWindow().setVisible(true);
        dispose();
    }
}
