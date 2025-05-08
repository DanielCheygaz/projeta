import javax.swing.*;
import java.awt.*;

public class MovieAddWindow extends JFrame{
    private JTextField textFieldName;
    private JButton cancelButton;
    private JButton saveButton;
    private JPanel mainPanel;
    private JTextField textFieldDuration;
    private JTextArea textAreaDescription;
    private JRadioButton imaxRadioButton;
    private JRadioButton dolbyAtmosRadioButton;
    private JComboBox comboBox1;

    public MovieAddWindow() throws HeadlessException {
        super("Adicionar Filme");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setContentPane(mainPanel);
        pack();


    }
}
