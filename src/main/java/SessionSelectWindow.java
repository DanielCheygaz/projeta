import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;

public class SessionSelectWindow extends JFrame {
    private JPanel mainPanel;
    private JTable sessionTable;
    private JButton addBarProductsButton;
    private JButton finishSaleButton;
    private JButton editSaleButton;
    private JButton backButton;
    private JButton openSelectedSessionButton;
    private JScrollPane scrollPane;
    private Ticket currentTicket = null;

    public SessionSelectWindow() {
        super("Venda de Bilhetes");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setContentPane(mainPanel);
        pack();
        scrollPane.getViewport().setBackground(Color.decode("2894892"));

        String[] columns = {"ID da Sessão", "Nome do filme", "Hora da Sessão"};
        DefaultTableModel tableModel = new DefaultTableModel(columns, 0);
        for (Session session : AppData.getInstance().getSessionList()) {
            Object[] row = {session.getID(), session.getMovie().getName(), session.getDate()};
            tableModel.addRow(row);
        }

        sessionTable.setModel(tableModel);
        sessionTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        this.backButton.addActionListener(this::backButtonPerformed);
        this.finishSaleButton.addActionListener(this::finishSaleButtonPerformed);
        this.editSaleButton.addActionListener(this::editSaleButtonPerformed);
        this.addBarProductsButton.addActionListener(this::addBarProductsButtonPerformed);
        this.openSelectedSessionButton.addActionListener(this::openSelectedSessionButtonPerformed);

    }

    private void backButtonPerformed(ActionEvent e){
        new SalesMainWindow().setVisible(true);
        dispose();
    }

    private void finishSaleButtonPerformed(ActionEvent e){

        int selectedRow = sessionTable.getSelectedRow();

        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Selecione uma sessão antes de finalizar a venda.");
            return;
        }

        int sessionId = (int) sessionTable.getValueAt(selectedRow, 0);
        Session selectedSession = AppData.getInstance().getSessionList()
                .stream().filter(s -> s.getID() == sessionId).findFirst().orElse(null);

        if (selectedSession != null) {
            // Simular venda de bilhete
            int newTicketId = AppData.getInstance().getTicketList().size() + 1;
            currentTicket = new Ticket(newTicketId, selectedSession, 10.0, "normal");
            AppData.getInstance().addTicket(currentTicket);

            JOptionPane.showMessageDialog(this, "Venda finalizada com sucesso!\nBilhete ID: " + newTicketId);


        } else {
            JOptionPane.showMessageDialog(this, "Erro: sessão não encontrada.");
        }
        new SalesMainWindow().setVisible(true);
        dispose();
    }

    private void editSaleButtonPerformed(ActionEvent e){
        new ReceiptEditWindow(this).setVisible(true);
        dispose();
    }

    private void addBarProductsButtonPerformed(ActionEvent e){

        if (currentTicket != null) {
            new BarProductsSaleWindow(this, currentTicket).setVisible(true);
            dispose();
        } else {
            new BarProductsSaleWindow(this,null).setVisible(true);
            dispose();

        }
    }

    private void openSelectedSessionButtonPerformed(ActionEvent e) {
        int selectedRow = sessionTable.getSelectedRow();
        if (selectedRow != -1) {
            int sessionId = (int) sessionTable.getValueAt(selectedRow, 0);
            Session selectedSession = AppData.getInstance().getSessionList()
                    .stream().filter(s -> s.getID() == sessionId).findFirst().orElse(null);

            if (selectedSession != null) {
                new TicketsPerSessionSaleWindow(selectedSession).showWindow();
                setVisible(false);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Selecione uma sessão para pesquisar bilhetes.");
        }
    }


    public static void main(String[] args){
        new SessionSelectWindow().setVisible(true);
    }
}
