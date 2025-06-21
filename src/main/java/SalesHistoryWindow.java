import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.time.LocalDateTime;
import java.util.List;

public class SalesHistoryWindow extends JFrame {
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

        String[] columns = {"ID da Venda", "Data", "Total"};
        DefaultTableModel tableModel = new DefaultTableModel(columns, 0);

        for (Sale sale : AppData.getInstance().getSales()) {
            String idStr = String.format("%03d", sale.getId());

            // Usar timestamp do primeiro bilhete (se existir) como data da venda
            String dataStr = "Desconhecida";
            List<Ticket> tickets = sale.getTickets();
            if (!tickets.isEmpty()) {
                LocalDateTime timestamp = tickets.get(0).getTimestamp();
                dataStr = timestamp.toLocalDate().toString();
            }

            String totalStr = String.format("%.2f€", sale.getTotalPrice());
            tableModel.addRow(new Object[]{idStr, dataStr, totalStr});
        }

        salesTable.setModel(tableModel);
        scrollPane.setViewportView(salesTable);
    }

    private void backButtonActionPerformed(ActionEvent e) {
        new SalesMainWindow().setVisible(true);
        dispose();
    }

    private void showSaleDetailsButtonActionPerformed(ActionEvent e) {
        int selectedRow = salesTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Seleciona uma venda da tabela.");
            return;
        }

        String idStr = salesTable.getValueAt(selectedRow, 0).toString();
        int saleId = Integer.parseInt(idStr);

        Sale selectedSale = null;
        for (Sale sale : AppData.getInstance().getSales()) {
            if (sale.getId() == saleId) {
                selectedSale = sale;
                break;
            }
        }

        if (selectedSale == null) {
            JOptionPane.showMessageDialog(this, "Erro: venda não encontrada.");
            return;
        }

        StringBuilder message = new StringBuilder("Detalhes da Venda\n\n");
        message.append("ID da Venda: ").append(String.format("%03d", selectedSale.getId())).append("\n");
        message.append("Total: ").append(String.format("%.2f€", selectedSale.getTotalPrice())).append("\n\n");

        for (SaleLine line : selectedSale.getSaleLines()) {
            if (line.getTicket() != null) {
                Ticket t = line.getTicket();
                message.append("Bilhete (").append(t.getTicketType()).append("): ")
                        .append(String.format("%.2f€", t.getPrice())).append("\n");
            } else if (line.getProduct() != null) {
                message.append("Produto: ").append(line.getProduct().getName())
                        .append(" x").append(line.getQuantity()).append(" → ")
                        .append(String.format("%.2f€", line.getTotal())).append("\n");
            }
        }

        JOptionPane.showMessageDialog(this, message.toString());
    }

    public static void main(String[] args) {
        new SalesHistoryWindow().setVisible(true);
    }
}
