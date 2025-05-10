import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.text.SimpleDateFormat;

public class SessionManagerWindow extends JFrame{
    private JButton backButton;
    private JTable sessionsTable;
    private JPanel mainPanel;
    private JButton addSessionButton;
    private JButton removeSessionButton;
    private JScrollPane scrollPane;
    private JButton editSessionButton;

    public SessionManagerWindow(){
        super("Gestor de Sessões");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setContentPane(mainPanel);
        pack();

        sessionsTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        scrollPane.getViewport().setBackground(Color.decode("2894892"));
        String[] columns = {"Filme","Sala","Data","Duração","Descrição"};

        DefaultTableModel tableModel = new DefaultTableModel(columns,0);
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm");
        for(Session session: AppData.getInstance().getSessionList()){
            Object[] row = {
              session.getMovie().getName(),
              session.getRoom().getRoomNumber(),
              dateFormat.format(session.getData()),
              session.getMovie().getDuration(),
              session.getMovie().getDescription()
            };
            tableModel.addRow(row);
        }
        sessionsTable.setModel(tableModel);
        this.editSessionButton.addActionListener(this::editSessionButtonPerformed);
        this.backButton.addActionListener(this::backButtonPerformed);
    }

    private void editSessionButtonPerformed(ActionEvent e){
        int selectedRow = getSelectedRow();
        if(selectedRow==-1){
            return;
        }

        Session session = AppData.getInstance().getSessionList().get(selectedRow);
        new SessionEditWindow(session).setVisible(true);
        dispose();
    }

    private int getSelectedRow(){
        int selectedRow = sessionsTable.getSelectedRow();

        if(selectedRow==-1){
            new ErrorWindow("Selecione primeiro uma sessão").setVisible(true);
            return -1;
        }

        return selectedRow;
    }

    private void backButtonPerformed(ActionEvent e){
        new MainWindow().setVisible(true);
        dispose();
    }

    public static void main(String[] args){
        new SessionManagerWindow().setVisible(true);
    }
}
