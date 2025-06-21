import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.*;
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

    private Map<String, Boolean> discountedProducts = new HashMap<>();
    private List<Bundle> appliedBundles = new ArrayList<>();

    public BarProductsSaleWindow(JFrame previousWindow, Ticket ticket) {
        super("Venda de Produtos de Bar");
        this.previousWindow = previousWindow;
        this.currentTicket = ticket;

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setContentPane(mainPanel);
        pack();

        scrollPane.getViewport().setBackground(Color.decode("2894892"));

        String[] columns = {"Nome do Produto", "Preço"};
        DefaultTableModel tableModel = new DefaultTableModel(columns, 0);
        for (Product product : AppData.getInstance().getProductList()) {
            //TODO: Implementar lógica de desconto
            Object[] row = {product.getName(), product.getPrice()};
            tableModel.addRow(row);
        }
        barProductsTable.setModel(tableModel);

        barProductsTable.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value,
                                                           boolean isSelected, boolean hasFocus,
                                                           int row, int column) {
                Component cell = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                String productName = table.getValueAt(row, 0).toString();
                boolean isDiscounted = discountedProducts.getOrDefault(productName, false);

                if (isSelected) {
                    cell.setBackground(table.getSelectionBackground());
                    cell.setForeground(table.getSelectionForeground());
                } else if (isDiscounted) {
                    cell.setBackground(new Color(200, 255, 200));
                    cell.setForeground(new Color(0, 128, 0));
                    cell.setFont(cell.getFont().deriveFont(Font.BOLD | Font.ITALIC));
                } else {
                    cell.setBackground(Color.WHITE);
                    cell.setForeground(Color.BLACK);
                    cell.setFont(cell.getFont().deriveFont(Font.PLAIN));
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
        return AppData.getInstance().getActiveSale().getTotalPrice();
    }

    private void finishSaleButtonPerformed(ActionEvent e) {
        Sale activeSale = AppData.getInstance().getActiveSale();
        if (activeSale == null || activeSale.getSaleLines().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Nenhum bilhete/produto foi adicionado à venda.");
            return;
        }

        new ReceiptEditWindow(this).setVisible(true);
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

            String input = JOptionPane.showInputDialog(this, "Quantas unidades de " + productName + " deseja adicionar?");
            if (input == null) return;

            int units;
            try {
                units = Integer.parseInt(input);
                if (units <= 0) throw new NumberFormatException();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Quantidade inválida.");
                return;
            }

            AppData appData = AppData.getInstance();
            if (appData.getActiveSale() == null) appData.startSale();

            Sale sale = appData.getActiveSale();
            Product product = new Product(productName, productPrice, units);
            sale.addLine(new SaleLine(product, units));
            appData.addSoldProduct(product);

            JOptionPane.showMessageDialog(this, "Produto adicionado: " + productName + " x" + units);
            applyEligibleBundlesIfAny();
            barProductsTable.repaint();
        } else {
            JOptionPane.showMessageDialog(this, "Selecione um produto para adicionar.");
        }
    }

    private void applyEligibleBundlesIfAny() {
        AppData appData = AppData.getInstance();
        Sale sale = appData.getActiveSale();

        // Só aplica bundles se houver pelo menos um Ticket na venda
        boolean hasTicket = sale.getSaleLines().stream()
                .anyMatch(line -> line.getItem() instanceof Ticket);

        if (!hasTicket) return;

        for (Bundle bundle : appData.getBundleList()) {
            if (appliedBundles.contains(bundle)) continue;

            appliedBundles.add(bundle);

            for (Product p : bundle.getProducts()) {
                discountedProducts.put(p.getName(), true);
            }

            JOptionPane.showMessageDialog(this,
                    "Bundle aplicado automaticamente: " + bundle.getName());

            break; // Apenas um bundle aplicado por venda
        }
    }

    private void backButtonPerformed(ActionEvent e) {
        if (previousWindow != null) {
            previousWindow.setVisible(true);
        }
        dispose();
    }

}
