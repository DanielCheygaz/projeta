import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;

public class RoomDetailsWindow extends JFrame {
    private JPanel mainPanel;
    private JButton backButton;
    private JScrollPane scrollPane;
    private JTable roomTable;
    private Room room;

    public RoomDetailsWindow(Room room){
        super("Detalhes da Sala");
        this.room = room;
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        setContentPane(mainPanel);
        pack();
        scrollPane.getViewport().setBackground(Color.decode("2894892"));

        String[] columns = {"Numero da sala", "numeroLugares", "IMAX", "Dolby Atmos"};
        DefaultTableModel tableModel = new DefaultTableModel(columns, 0);

        Object[] row = {
                room.getRoomNumber(),
                room.getNumeroLugares(),
                room.isImax(),
                room.isDolbyAtmos()
        };
        tableModel.addRow(row);

        roomTable.setModel(tableModel);
        this.backButton.addActionListener(this::backButtonActionPerformed);

    }

    private void backButtonActionPerformed(ActionEvent e){
        new RoomManagerWindow().setVisible(true);
        setVisible(false);
    }
}
