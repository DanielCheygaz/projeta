import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

public class BarProductsSaleWindow extends JFrame {
    private JPanel mainPanel;
    private JScrollPane scrollPane;
    private JTable barProductsTable;
    private JButton editSaleButton;
    private JButton finishSaleButton;
    private JButton backButton;
    private JButton addBarProductToSale;
    private JFrame previousWindow;
    private Ticket currentTicket;

    private java.util.List<Product> currentSaleProducts = new ArrayList<>();

    public BarProductsSaleWindow(JFrame previousWindow, Ticket ticket) {
        super("Venda de Produtos de Bar");
        this.previousWindow = previousWindow;
        this.currentTicket = ticket;

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setContentPane(mainPanel);
        pack();

        // Mantido como estava no seu código original
        scrollPane.getViewport().setBackground(Color.decode("2894892"));

        String[] columns = {"Nome do Produto", "Preço"};
        DefaultTableModel tableModel = new DefaultTableModel(columns, 0);
        for (Product product : AppData.getInstance().getProductList()) {
            if(product.getName().equals("KitKat")) {
                Object[] row = {product.getName(), product.getPrice()-product.getPrice()*0.2};
                tableModel.addRow(row);
            }
            else{
                Object[] row = {product.getName(), product.getPrice()};
                tableModel.addRow(row);
            }
        }
        barProductsTable.setModel(tableModel);

        barProductsTable.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value,
                                                           boolean isSelected, boolean hasFocus,
                                                           int row, int column) {
                Component cell = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

                String productName = table.getValueAt(row, 0).toString();

                if (isSelected) {
                    // Se estiver selecionado, usa as cores de seleção padrão (independente de ser KitKat ou não)
                    cell.setBackground(table.getSelectionBackground());
                    cell.setForeground(table.getSelectionForeground());
                } else if (productName.equals("KitKat")) {
                    // Se for KitKat e NÃO estiver selecionado, usa laranja claro com texto preto
                    cell.setBackground(new Color(106, 209, 138));
                    cell.setForeground(Color.BLACK);
                } else {
                    // Todos os outros produtos não selecionados → fundo branco e texto preto
                    cell.setBackground(Color.WHITE);
                    cell.setForeground(Color.BLACK);
                }

                return cell;
            }
        });





        this.backButton.addActionListener(this::backButtonPerformed);
        this.finishSaleButton.addActionListener(this::finishSaleButtonPerformed);
        this.editSaleButton.addActionListener(this::editSaleButtonPerformed);
        this.addBarProductToSale.addActionListener(this::addBarProductToSalePerformed);
    }
    private double calcularTotalProdutos() {
        double total = 0;
        for (Product p : currentSaleProducts) {
            total += p.getPrice() * p.getUnits();
        }
        return total;
    }
    private void finishSaleButtonPerformed(ActionEvent e) {
        if (currentSaleProducts.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Nenhum produto foi adicionado à venda.");
            return;
        }


        if (currentTicket == null) {
            // Criar ticket só para produtos de bar, se não veio de uma sessão
            currentTicket = new Ticket(
                    AppData.getInstance().getTicketList().size() + 1,
                    null,
                    calcularTotalProdutos(),
                    "bar"
            );
            AppData.getInstance().addTicket(currentTicket);
        }

        for (Product product : currentSaleProducts) {
            currentTicket.addBarProduct(product);
        }

        JOptionPane.showMessageDialog(this, "Venda finalizada com sucesso!");
        new SalesMainWindow().setVisible(true);
        dispose();
    }

    private void editSaleButtonPerformed(ActionEvent e) {
        new ReceiptEditWindow(this).setVisible(true);
        dispose();
    }

    private void addBarProductToSalePerformed(ActionEvent e) {
        int selectedRow = barProductsTable.getSelectedRow();
        if (selectedRow != -1) {
            DefaultTableModel model = (DefaultTableModel) barProductsTable.getModel();
            String productName = (String) model.getValueAt(selectedRow, 0);
            double productPrice = (double) model.getValueAt(selectedRow, 1);



            Product selectedProduct = new Product(productName, productPrice, 1);
            currentSaleProducts.add(selectedProduct);
            AppData.getInstance().addSoldProduct(selectedProduct);

            JOptionPane.showMessageDialog(this, "Produto adicionado à venda: " + productName + " - Preço: " + productPrice);
        } else {
            JOptionPane.showMessageDialog(this, "Selecione um produto para adicionar à venda.");
        }
    }

    private void backButtonPerformed(ActionEvent e) {
        if (previousWindow != null) {
            previousWindow.setVisible(true);
        }
        dispose();
    }


    public static void main(String[] args) {
        new BarProductsSaleWindow(null,null).setVisible(true);
    }
}
