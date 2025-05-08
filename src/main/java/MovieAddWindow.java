import javax.swing.*;
import java.awt.*;

public class MovieAddWindow extends JFrame{
    private JTextField textFieldName;
    private JSpinner unitsSpinner;
    private JTextField textFieldPrice;
    private JButton cancelButton;
    private JButton saveButton;
    private JPanel mainPanel;

    public MovieAddWindow() throws HeadlessException {
        super("Adicionar Filme");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setContentPane(mainPanel);
        pack();
    }
}
