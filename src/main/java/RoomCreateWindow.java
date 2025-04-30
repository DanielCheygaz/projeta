import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class RoomCreateWindow extends JFrame{
    private JPanel mainPanel;
    private JCheckBox dolbyAtmosCheckBox;
    private JCheckBox imaxCheckBox;
    private JPanel createPanel;
    private JButton criarSalaButton;
    private JSpinner numeroSala;
    private JSpinner numeroLugares;
    private JButton cancelButton;

    public RoomCreateWindow() throws HeadlessException {
        super("Cinema Projeta");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setContentPane(mainPanel);
        mainPanel.setBackground(Color.decode("2894892"));
        pack();

        this.criarSalaButton.addActionListener(this::criarSalaButtonActionPerformed);
        this.cancelButton.addActionListener(this::cancelarButtonActionPerformed);
    }

    private void cancelarButtonActionPerformed(ActionEvent e){
        dispose();
        new RoomManagerWindow().setVisible(true);
    }
    private void criarSalaButtonActionPerformed(ActionEvent e) {
        int numeroSalaInt = (int) numeroSala.getValue();
        int numeroLugaresInt = (int) numeroLugares.getValue();

        if(numeroSalaInt <= 0){
            new ErrorWindow("Numero da sala invalido").setVisible(true);
            return;
        }

        if(numeroLugaresInt <= 0){
            new ErrorWindow("Numero de lugares invalido").setVisible(true);
            return;
        }
        Room novaSala = new Room(numeroSalaInt,numeroLugaresInt,imaxCheckBox.isSelected(),dolbyAtmosCheckBox.isSelected());
        AppData.getInstance().getRoomList().add(novaSala);
        dispose();
        new RoomManagerWindow().setVisible(true);

    }

    public static void main(String[] args){new RoomCreateWindow().setVisible(true);}

}
