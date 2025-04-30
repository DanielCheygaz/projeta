import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class CreateRoomWindow  extends JFrame{
    private JPanel mainPanel;
    private JCheckBox dolbyAtmosCheckBox;
    private JCheckBox imaxCheckBox;
    private JTextField numeroLugaresText;
    private JTextField numeroSalaText;
    private JPanel createPanel;
    private JFormattedTextField createRoom;
    private JButton criarSalaButton;

    public CreateRoomWindow() throws HeadlessException {
        super("Cinema Projeta");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setContentPane(mainPanel);
        mainPanel.setBackground(Color.decode("2894892"));
        pack();

        //this.criarSalaButton.addActionListener(this::criarSalaButtonActionPerformed);

    }

    private void criarSalaButtonActionPerformed(ActionEvent e) {
        //new Room(createRoom,numeroLugaresText,imaxCheckBox.isSelected(),dolbyAtmosCheckBox.isSelected());
    }
    public static void main(String[] args){new CreateRoomWindow().setVisible(true);}

}
