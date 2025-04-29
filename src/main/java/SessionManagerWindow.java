import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;

public class SessionManagerWindow extends JFrame{
    private JButton backButton;
    private JTable sessionsTable;
    private JPanel mainPanel;
    private JButton addSessionButton;
    private JButton removeSessionButton;
    private JScrollPane scrollPane;

    public SessionManagerWindow(){
        super("Gestor de Sessões");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setContentPane(mainPanel);
        pack();

        scrollPane.getViewport().setBackground(Color.decode("2894892"));
        String[] columns = {"Filme","Sala","Data","Duração","Descrição"};

        DefaultTableModel tableModel = new DefaultTableModel(columns,0);
        for(Session session: AppData.getInstance().getSessionList()){
            Object[] row = {
              session.getMovie().getName(),
              session.getRoom().getRoomNumber(),
              session.getData(),
              session.getMovie().getDuration(),
              session.getMovie().getDescription()
            };
            tableModel.addRow(row);
        }
        sessionsTable.setModel(tableModel);

        this.backButton.addActionListener(this::backButtonPerformed);
    }

    private void backButtonPerformed(ActionEvent e){
        new MainWindow().setVisible(true);
        dispose();
    }

    public static void main(String[] args){
        new SessionManagerWindow().setVisible(true);
    }
}
