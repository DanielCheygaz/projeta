
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

        // Sample data for the table
        tableModel.addRow(new Object[]{"001", "2025-05-08", "15.90€"});
        tableModel.addRow(new Object[]{"002", "2025-05-07", "22.50€"});
        tableModel.addRow(new Object[]{"003", "2025-05-06", "9.80€"});

        salesTable.setModel(tableModel);
        scrollPane.setViewportView(salesTable);
    }

    private void backButtonActionPerformed(ActionEvent e) {
        new SalesManagerWindow().setVisible(true);
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
