import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;

public class BarProductsSaleWindow extends JFrame {
    private JPanel mainPanel;
    private JScrollPane scrollPane;
    private JTable barProductsTable;
    private JButton editSaleButton;
    private JButton finishSaleButton;
    private JButton backButton;
    private JButton addBarProductToSale;

    private JFrame previousWindow;

    public BarProductsSaleWindow(JFrame previousWindow) {
        super("Venda de Produtos de Bar");
        this.previousWindow = previousWindow;

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setContentPane(mainPanel);
        pack();

        scrollPane.getViewport().setBackground(Color.decode("2894892"));

        String[] columns = {"Nome do Produto", "Preço"};
        DefaultTableModel tableModel = new DefaultTableModel(columns, 0);
        for (Product product : AppData.getInstance().getProductList()) {
            Object[] row = {product.getName(), product.getPrice()};
            tableModel.addRow(row);
        }
        barProductsTable.setModel(tableModel);

        this.backButton.addActionListener(this::backButtonPerformed);
        this.finishSaleButton.addActionListener(this::finishSaleButtonPerformed);
        this.editSaleButton.addActionListener(this::editSaleButtonPerformed);
        this.addBarProductToSale.addActionListener(this::addBarProductToSalePerformed);
    }

    private void finishSaleButtonPerformed(ActionEvent e) {
        JOptionPane.showMessageDialog(this, "Venda finalizada com sucesso!");
        new SalesWindow().setVisible(true);
        dispose();
    }

    private void editSaleButtonPerformed(ActionEvent e) {
        new EditSaleWindow(this).setVisible(true);
        dispose();
    }

    private void addBarProductToSalePerformed(ActionEvent e) {
        int selectedRow = barProductsTable.getSelectedRow();
        if (selectedRow != -1) {
            DefaultTableModel model = (DefaultTableModel) barProductsTable.getModel();
            String productName = (String) model.getValueAt(selectedRow, 0);
            double productPrice = (double) model.getValueAt(selectedRow, 1);
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
        new BarProductsSaleWindow(null).setVisible(true);
    }
}




