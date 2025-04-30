import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;

public class RoomManagerWindow extends JFrame{
    private JPanel mainPanel;
    private JButton backButton;
    private JScrollPane scrollPane;
    private JButton adicionarSalaButton;
    private JButton removerSalaButton;
    private JButton showRoomButton;
    private JTable roomTable;
    private Room roomSelected;

    public RoomManagerWindow(){
        super("Gestor de Salas");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setContentPane(mainPanel);
        pack();

        scrollPane.getViewport().setBackground(Color.decode("2894892"));
        String[] columns = {"Numero da sala"};

        DefaultTableModel tableModel = new DefaultTableModel(columns,0);
        for(Room room: AppData.getInstance().getRoomList()){
            Object[] row = {
                    "Sala numero: " +
                    room.getRoomNumber()
            };
            tableModel.addRow(row);
        }
        roomTable.setModel(tableModel);
        roomTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        this.showRoomButton.addActionListener(this::verSalaButtonActionPerformed);
        this.backButton.addActionListener(this::backButtonActionPerformed);
        this.adicionarSalaButton.addActionListener(this::adicionarSalaButtonActionPerformed);
    }

    private void verSalaButtonActionPerformed(ActionEvent e){

        int selectedRow = roomTable.getSelectedRow();

        roomSelected = AppData.getInstance().getRoomList().get(selectedRow);
        dispose();
        new RoomDetailsWindow(roomSelected).setVisible(true);
        setVisible(false);
    }

    private void adicionarSalaButtonActionPerformed(ActionEvent e){

    }

    private void backButtonActionPerformed(ActionEvent e){
        new MainWindow().setVisible(true);
        setVisible(false);
    }



    private void backButtonPerformed(ActionEvent e){
        new MainWindow().setVisible(true);
        dispose();
    }

    public static void main(String[] args){
        new RoomManagerWindow().setVisible(true);
    }


}
