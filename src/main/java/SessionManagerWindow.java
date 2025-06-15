import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;
import java.text.SimpleDateFormat;

public class SessionManagerWindow extends JFrame implements ManagerInterface<Session> {
    private JButton backButton;
    private JTable sessionsTable;
    private JPanel mainPanel;
    private JButton addSessionButton;
    private JButton removeSessionButton;
    private JScrollPane scrollPane;
    private JButton editSessionButton;
    private String[] columns;
    private DefaultTableModel tableModel;
    private SimpleDateFormat dateFormat;
    private JFrame previousWindow;

    public SessionManagerWindow(JFrame previousWindow){
        super("Gestor de Sessões");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setContentPane(mainPanel);
        pack();

        this.previousWindow = previousWindow;

        sessionsTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        scrollPane.getViewport().setBackground(Color.decode("2894892"));
        String[] columns = {"ID da Sessão","Filme","Sala","Data","Duração"};
        this.columns = columns;

        dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm");
        refreshData();

        this.addSessionButton.addActionListener(this::addSessionButtonPerformed);
        this.editSessionButton.addActionListener(this::editSessionButtonPerformed);
        this.removeSessionButton.addActionListener(this::removeSessionButtonPerformed);
        this.backButton.addActionListener(this::backButtonPerformed);
    }

    public void refreshData(){
        tableModel = new DefaultTableModel(columns,0);
        for(Session session: AppData.getInstance().getSessionList()){
            Object[] row = {
                    session.getID(),
                    session.getMovie().getName(),
                    session.getRoom().getRoomNumber(),
                    dateFormat.format(session.getDate()),
                    session.getMovie().getDuration()
            };
            tableModel.addRow(row);
        }
        sessionsTable.setModel(tableModel);
    }

    private void addSessionButtonPerformed(ActionEvent e){
        new SessionAddWindow(this).setVisible(true);
        setVisible(false);
    }

    private void editSessionButtonPerformed(ActionEvent e){
        int selectedRow = getSelectedRow();
        if(selectedRow==-1){
            return;
        }

        Session session = AppData.getInstance().getSessionList().get(selectedRow);
        new SessionEditWindow(this, session).setVisible(true);
        setVisible(false);
    }

    private void removeSessionButtonPerformed(ActionEvent e){
        int selectedRow = getSelectedRow();
        if(selectedRow==-1){
            return;
        }

        Session session = AppData.getInstance().getSessionList().get(selectedRow);
        if(!canBeDeleted(session)){
            new ErrorWindow("Esta sessão está associada a algum bilhete!");
        }

        AppData.getInstance().removeSession(session);
        tableModel.removeRow(selectedRow);
    }

    public boolean canBeDeleted(Session session){
        for(Ticket ticket : AppData.getInstance().getTicketList()){
            if(ticket.getSession() == session){
                return false;
            }
        }
        return true;
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
        previousWindow.setVisible(true);
        setVisible(false);
    }

    public static void main(String[] args){
        new SessionManagerWindow(new MainWindow()).setVisible(true);
    }
}
