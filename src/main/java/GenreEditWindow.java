import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class GenreEditWindow extends JFrame{
    private JTextField textFieldName;
    private JButton cancelButton;
    private JButton saveButton;
    private JPanel mainPanel;
    private Genre genre;

    public GenreEditWindow(Genre genre) throws HeadlessException {
        super("Editar" + genre.getName());
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setContentPane(mainPanel);
        pack();

        this.genre = genre;

        textFieldName.setText(genre.getName());

        this.saveButton.addActionListener(this::saveButtonPerformed);
        this.cancelButton.addActionListener(this::cancelButtonPerformed);
    }

    private void saveButtonPerformed(ActionEvent e){
        String name = textFieldName.getText();
        if(name.isBlank()){
            new ErrorWindow("Tem de inserir o nome do Género a adicionar.").setVisible(true);
            return;
        }

        genre.setName(name);

        new GenreManagerWindow().setVisible(true);
        dispose();
    }

    private void cancelButtonPerformed(ActionEvent e){
        new GenreManagerWindow().setVisible(true);
        dispose();
    }
}
