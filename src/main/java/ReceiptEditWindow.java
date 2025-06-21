import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;

public class ReceiptEditWindow extends JFrame {
    private JPanel mainPanel;
    private JScrollPane scrollPane;
    private JTable barProductsTable;
    private JButton finishEditButton;
    private JButton finishSaleButton;
    private JButton removeSelectedProductButton;
    private JButton backButton;
    private JLabel totalLabel;

    private JFrame previousWindow;

    public ReceiptEditWindow(JFrame previousWindow) {
        super("Edição de Venda");
        this.previousWindow = previousWindow;

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(600, 400);
        setLayout(new BorderLayout());

        mainPanel = new JPanel(new BorderLayout());
        scrollPane = new JScrollPane();
        barProductsTable = new JTable();
        scrollPane.setViewportView(barProductsTable);
        scrollPane.getViewport().setBackground(Color.decode("2894892"));

        // Tabela
        String[] columns = {"Quantidade", "Nome do Produto", "Preço"};
        DefaultTableModel tableModel = new DefaultTableModel(columns, 0);
        barProductsTable.setModel(tableModel);

        // Carrega linhas reais da fatura
        Sale activeSale = AppData.getInstance().getActiveSale();
        if (activeSale != null) {
            for (SaleLine line : activeSale.getSaleLines()) {
                String name;
                int quantity = 1;
                double price;

                if (line.getTicket() != null) {
                    name = "Bilhete (" + line.getTicket().getTicketType() + ")";
                    price = line.getTicket().getPrice();
                } else if (line.getProduct() != null) {
                    name = line.getProduct().getName();
                    quantity = line.getQuantity();
                    price = line.getProduct().getPrice() * quantity;
                } else {
                    continue;
                }

                Object[] row = {quantity, name, String.format("%.2f", price)};
                tableModel.addRow(row);
            }
        }

        // Botões
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        finishEditButton = new JButton("Concluir Edição");
        finishSaleButton = new JButton("Finalizar Venda");
        removeSelectedProductButton = new JButton("Remover Produto");
        backButton = new JButton("Voltar");
        totalLabel = new JLabel();

        buttonPanel.add(removeSelectedProductButton);
        buttonPanel.add(finishEditButton);
        buttonPanel.add(finishSaleButton);
        buttonPanel.add(backButton);

        updateTotalLabel();

        // Layout
        mainPanel.add(scrollPane, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);
        add(mainPanel, BorderLayout.CENTER);
        add(totalLabel, BorderLayout.SOUTH);

        // Ações
        this.backButton.addActionListener(this::backButtonPerformed);
        this.finishSaleButton.addActionListener(this::finishSaleButtonPerformed);
        this.finishEditButton.addActionListener(this::finishEditButtonPerformed);
        this.removeSelectedProductButton.addActionListener(this::removeSelectedProductButtonPerformed);
    }

    private void updateTotalLabel() {
        Sale activeSale = AppData.getInstance().getActiveSale();
        double total = activeSale != null ? activeSale.getTotalPrice() : 0;
        totalLabel.setText("Total: " + String.format("%.2f", total) + " €");
        totalLabel.setHorizontalAlignment(SwingConstants.CENTER);
        totalLabel.setFont(new Font("Arial", Font.BOLD, 14));
        totalLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
    }

    private void backButtonPerformed(ActionEvent e) {
        if (previousWindow != null) {
            previousWindow.setVisible(true);
        }
        dispose();
    }

    private void finishSaleButtonPerformed(ActionEvent e) {
        AppData.getInstance().finalizeSale();
        JOptionPane.showMessageDialog(this, "Venda finalizada com sucesso!");
        new SalesMainWindow().setVisible(true);
        dispose();
    }

    private void removeSelectedProductButtonPerformed(ActionEvent e) {
        int selectedRow = barProductsTable.getSelectedRow();
        Sale activeSale = AppData.getInstance().getActiveSale();

        if (selectedRow != -1 && activeSale != null && selectedRow < activeSale.getSaleLines().size()) {
            activeSale.getSaleLines().remove(selectedRow);

            DefaultTableModel model = (DefaultTableModel) barProductsTable.getModel();
            model.removeRow(selectedRow);

            updateTotalLabel();
            JOptionPane.showMessageDialog(this, "Produto ou bilhete removido da fatura.");
        } else {
            JOptionPane.showMessageDialog(this, "Selecione uma linha válida para remover.");
        }
    }

    private void finishEditButtonPerformed(ActionEvent e) {
        JOptionPane.showMessageDialog(this, "Edição de venda finalizada com sucesso!");
        if (previousWindow != null) {
            previousWindow.setVisible(true);
        }
        dispose();
    }

    public static void main(String[] args) {
        new ReceiptEditWindow(null).setVisible(true);
    }
}
