import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class GenreAddWindow extends JFrame{
    private JPanel mainPanel;
    private JTextField textFieldName;
    private JButton cancelButton;
    private JButton saveButton;
    private GenreManagerWindow previousWindow;

    public GenreAddWindow(GenreManagerWindow previousWindow) throws HeadlessException {
        super("Adicionar Categoria");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setContentPane(mainPanel);
        pack();

        this.previousWindow = previousWindow;

        this.saveButton.addActionListener(this::saveButtonPerformed);
        this.cancelButton.addActionListener(this::cancelButtonPerformed);
    }

    private void saveButtonPerformed(ActionEvent e){
        String name = textFieldName.getText();
        if(name.isBlank()){
            new ErrorWindow("Tem de inserir o nome do Género a adicionar.").setVisible(true);
            return;
        }

        Genre genre = new Genre(name);
        AppData.getInstance().addGenre(genre);

        previousWindow.refreshData();
        previousWindow.setVisible(true);
        dispose();
    }

    private void cancelButtonPerformed(ActionEvent e){
        previousWindow.setVisible(true);
        dispose();
    }
}
