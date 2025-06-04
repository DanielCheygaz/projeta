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
        this.removerSalaButton.addActionListener(this::removerSalaButtonActionPerformed);
    }

    private void removerSalaButtonActionPerformed(ActionEvent e){

        int selectedRow = roomTable.getSelectedRow();

        if(selectedRow==-1){
            new ErrorWindow("Selecione primeiro uma sala").setVisible(true);
            return;
        }
        roomSelected = AppData.getInstance().getRoomList().get(selectedRow);

        if(!canBeDeleted(roomSelected)){
            new ErrorWindow("Esta sala está associado a uma sessao!").setVisible(true);
            return;
        }
        AppData.getInstance().getRoomList().remove(selectedRow);
        dispose();
        new RoomManagerWindow().setVisible(true);
    }

    public boolean canBeDeleted(Room room){
        for(Session session : AppData.getInstance().getSessionList()){
            if(session.getRoom() == room){
                return false;
            }
        }
        return true;
    }

    private void verSalaButtonActionPerformed(ActionEvent e){

        int selectedRow = roomTable.getSelectedRow();

        roomSelected = AppData.getInstance().getRoomList().get(selectedRow);
        dispose();
        new RoomDetailsWindow(roomSelected).setVisible(true);
        setVisible(false);
    }

    private void adicionarSalaButtonActionPerformed(ActionEvent e){
        new RoomCreateWindow().setVisible(true);
        dispose();
    }

    private void backButtonActionPerformed(ActionEvent e){
        new MainWindow().setVisible(true);
        setVisible(false);
    }





    public static void main(String[] args){
        new RoomManagerWindow().setVisible(true);
    }


}
