import javax.swing.*;
import java.awt.*;

public class SessionEditWindow extends JFrame{
    private JFormattedTextField formattedTextNome;
    private JFormattedTextField formattedTextUnits;
    private JFormattedTextField formattedTextPrice;
    private JButton cancelButton;
    private JButton saveButton;
    private JPanel mainPanel;

    public SessionEditWindow() throws HeadlessException {
        super("Edit Session");

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setContentPane(mainPanel);
        pack();
    }
}
