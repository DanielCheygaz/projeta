
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
            String data = ticket.getTimestamp().toLocalDate().toString();

            double total = ticket.getPrice(); // valor base (bilhete)



            String totalStr = String.format("%.2f€", total);

            tableModel.addRow(new Object[]{id, data, totalStr});
        }

        salesTable.setModel(tableModel);
        scrollPane.setViewportView(salesTable);
    }

    private void backButtonActionPerformed(ActionEvent e) {
        new SalesMainWindow().setVisible(true);
        setVisible(false);
    }

    private void showSaleDetailsButtonActionPerformed(ActionEvent e) {
        int selectedRow = salesTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Seleciona uma venda da tabela.");
            return;
        }

        String idStr = salesTable.getValueAt(selectedRow, 0).toString();
        int ticketId = Integer.parseInt(idStr); // assume IDs únicos

        Ticket selectedTicket = null;
        for (Ticket t : AppData.getInstance().getTicketList()) {
            if (t.getId() == ticketId) {
                selectedTicket = t;
                break;
            }
        }

        if (selectedTicket == null) {
            JOptionPane.showMessageDialog(this, "Erro: bilhete não encontrado.");
            return;
        }

        StringBuilder message = new StringBuilder("Detalhes da Venda:\n\n");
        message.append("ID: ").append(String.format("%03d", selectedTicket.getId())).append("\n");
        message.append("Data: ").append(selectedTicket.getTimestamp().toLocalDate()).append("\n");
        message.append("Total: ").append(String.format("%.2f€", selectedTicket.getPrice())).append("\n\n");

        message.append("Tipo de Bilhete: ").append(selectedTicket.getTicketType()).append("\n");

        if (selectedTicket.getSession() != null) {
            message.append("Sessão: ").append(selectedTicket.getSession().getMovie().getName())
                    .append(" - ").append(selectedTicket.getSession().getDate()).append("\n");
        }

        message.append("\nProdutos de Bar:\n");
        if (selectedTicket.getBarProducts().isEmpty()) {
            message.append("Nenhum produto de bar.\n");
        } else {
            for (Product p : selectedTicket.getBarProducts()) {
                message.append("- ").append(p.getName()).append(" (").append(String.format("%.2f€", p.getPrice())).append(")\n");
            }
        }

        JOptionPane.showMessageDialog(this, message.toString());
    }

    public static void main(String[] args) {
            SalesHistoryWindow window = new SalesHistoryWindow();
            window.setVisible(true);
    }

}
