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
    private JFrame previousWindow;

    public SessionSelectWindow(JFrame previousWindow) {
        super("Venda de Bilhetes");
        this.previousWindow = previousWindow;
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
        if(previousWindow != null) {
            previousWindow.setVisible(true);
        } else {
            new SalesMainWindow().setVisible(true);
        }
        dispose();
    }

    private void finishSaleButtonPerformed(ActionEvent e){
        // Here you would typically finalize the sale, e.g., save to a database or print a receipt.

        JOptionPane.showMessageDialog(this, "Venda finalizada com sucesso!");
        dispose();
    }

    private void editSaleButtonPerformed(ActionEvent e){
        new ReceiptEditWindow(this).setVisible(true);
        dispose();
    }

    private void addBarProductsButtonPerformed(ActionEvent e){
        new BarProductsSaleWindow(this).setVisible(true);
        dispose();
    }

    private void openSelectedSessionButtonPerformed(ActionEvent e) {
        int selectedRow = sessionTable.getSelectedRow();
        if (selectedRow != -1) {
            int sessionId = (int) sessionTable.getValueAt(selectedRow, 0);
            Session selectedSession = AppData.getInstance().getSessionList()
                    .stream().filter(s -> s.getID() == sessionId).findFirst().orElse(null);

            if (selectedSession != null) {
                new TicketsPerSessionSaleWindow(selectedSession, this).setVisible(true);
                setVisible(false);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Selecione uma sessão para pesquisar bilhetes.");
        }
    }
}
