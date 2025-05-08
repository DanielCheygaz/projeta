import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class GenreAddWindow extends JFrame{
    private JPanel mainPanel;
    private JTextField textFieldName;
    private JButton cancelButton;
    private JButton saveButton;

    public GenreAddWindow() throws HeadlessException {
        super("Adicionar Categoria");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setContentPane(mainPanel);
        pack();

        this.saveButton.addActionListener(this::saveButtonPerformed);
        this.cancelButton.addActionListener(this::cancelButtonPerformed);
    }

    private void saveButtonPerformed(ActionEvent e){
        AppData.getInstance().addGenre(textFieldName.getText());
        dispose();
    }

    private void cancelButtonPerformed(ActionEvent e){
        dispose();
    }
}
