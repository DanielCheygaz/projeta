import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class SelectTicketWindow extends JFrame {
    private JPanel mainPanel;
    private JButton backButton;
    private JComboBox comboBox1;
    private JButton confirmButton;

    public SelectTicketWindow() throws HeadlessException {
        super("Seleção de Ticket");

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setContentPane(mainPanel);
        pack();

       this.confirmButton.addActionListener(this::confirmButtonActionPerformed);
        this.backButton.addActionListener(this::backButtonActionPerformed);
    }

    private void confirmButtonActionPerformed(ActionEvent e) {
        setVisible(false);
    }

    private void backButtonActionPerformed(ActionEvent e){
        new RoomManagerWindow().setVisible(true);
        setVisible(false);
    }

}
