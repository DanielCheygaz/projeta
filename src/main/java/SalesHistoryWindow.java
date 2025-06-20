
import javax.swing.*;
import java.awt.event.ActionEvent;
import javax.swing.table.DefaultTableModel;

public class SalesHistoryWindow extends JFrame{
    private JPanel mainPanel;
    private JButton backButton;
    private JScrollPane scrollPane;
    private JTable salesTable;
    private JButton showSaleDetailsButton;

    public SalesHistoryWindow() {
        setTitle("Histórico de Vendas");
        setContentPane(mainPanel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();

        backButton.addActionListener(this::backButtonActionPerformed);
        showSaleDetailsButton.addActionListener(this::showSaleDetailsButtonActionPerformed);


        String[] columns = {"ID", "Data", "Total"};
        DefaultTableModel tableModel = new DefaultTableModel(columns, 0);

        for (Ticket ticket : AppData.getInstance().getTicketList()) {
            String id = String.format("%03d", ticket.getId());
            String data = ticket.getTimestamp().toLocalDate().toString(); // só a data
            String total = String.format("%.2f€", ticket.getPrice());

            tableModel.addRow(new Object[]{id, data, total});
        }

        salesTable.setModel(tableModel);
        scrollPane.setViewportView(salesTable);
    }

    private void backButtonActionPerformed(ActionEvent e) {
        setVisible(false);
    }

    private void showSaleDetailsButtonActionPerformed(ActionEvent e) {
        int selectedRow = salesTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Seleciona uma venda da tabela.");
            return;
        }

        String id = salesTable.getValueAt(selectedRow, 0).toString();
        String data = salesTable.getValueAt(selectedRow, 1).toString();
        String total = salesTable.getValueAt(selectedRow, 2).toString();

        String message = "Detalhes da Venda:\n\n" +
                "ID: " + id + "\n" +
                "Data: " + data + "\n" +
                "Total: " + total + "\n\n" +
                "Produtos:\n- Bilhete Sessão 1\n- Pipocas Médias\n- Bebida Grande";

        JOptionPane.showMessageDialog(this, message);
    }

    public static void main(String[] args) {
            SalesHistoryWindow window = new SalesHistoryWindow();
            window.setVisible(true);
    }

}
