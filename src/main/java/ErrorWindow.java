import javax.swing.*;
import javax.swing.plaf.multi.MultiLabelUI;
import java.awt.*;
import java.awt.event.ActionEvent;

public class ErrorWindow extends JFrame{
    private JLabel labelError;
    private JButton backButton;
    private JPanel mainPanel;

    public ErrorWindow(String error) throws HeadlessException {
        super("Erro");
        labelError.setText(error);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setContentPane(mainPanel);
        pack();
        this.backButton.addActionListener(this::backButtonPerformed);
    }

    private void backButtonPerformed(ActionEvent e){
        dispose();
    }
}
