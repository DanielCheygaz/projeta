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
    private JButton selectSessionButton;
    private JScrollPane scrollPane;

    public SessionSelectWindow() {
        super("Venda de Bilhetes");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setContentPane(mainPanel);
        pack();
        scrollPane.getViewport().setBackground(Color.decode("2894892"));

        String[] columns = {"ID da Sessão", "Nome do filme", "Hora da Sessão"};
        DefaultTableModel tableModel = new DefaultTableModel(columns, 0);
        for (Session session : AppData.getInstance().getSessionList()) {
            Object[] row = {session.getID(), session.getMovie().getName(), session.getData()};
            tableModel.addRow(row);
        }
        sessionTable.setModel(tableModel);

        this.backButton.addActionListener(this::backButtonPerformed);
        this.finishSaleButton.addActionListener(this::finishSaleButtonPerformed);
        this.editSaleButton.addActionListener(this::editSaleButtonPerformed);
        this.addBarProductsButton.addActionListener(this::addBarProductsButtonPerformed);
        this.selectSessionButton.addActionListener(this::selectSessionButtonPerformed);

    }

    private void backButtonPerformed(ActionEvent e){
        new SalesMainWindow().setVisible(true);
        dispose();
    }

    private void finishSaleButtonPerformed(ActionEvent e){
        JOptionPane.showMessageDialog(this, "Venda finalizada com sucesso!");
        new SalesMainWindow().setVisible(true);
        dispose();
    }

    private void editSaleButtonPerformed(ActionEvent e){
        new SaleEditWindow(this).setVisible(true);
        dispose();
    }

    private void addBarProductsButtonPerformed(ActionEvent e){
        new BarProductsSaleWindow(this).setVisible(true);
        dispose();
    }

    private void selectSessionButtonPerformed(ActionEvent e){
        Session selectedSession = sessionTable.getSelectedRow() != -1 ? AppData.getInstance().getSessionList().get(sessionTable.getSelectedRow()) : null;
        int selectedRow = selectedSession.getID();
        int numberOfRows = selectedSession.getRoom().getNumberRows();
        int numberOfColumns = selectedSession.getRoom().getNumberColumns();
        if (selectedRow != -1) {
            DefaultTableModel model = (DefaultTableModel) sessionTable.getModel();
            int sessionId = (int) model.getValueAt(selectedRow, 0);
            System.out.println(sessionId);
            new TicketsPerSessionSaleWindow(this, sessionId, numberOfRows, numberOfColumns).setVisible(true);
        } else {
            JOptionPane.showMessageDialog(this, "Selecione uma sessão para pesquisar bilhetes.");
        }
    }

    public static void main(String[] args){
        new SessionSelectWindow().setVisible(true);
    }
}
