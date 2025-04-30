import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;

public class FinishSaleWindow extends JFrame {
    private JPanel mainPanel;
    private JScrollPane scrollPane;
    private JTable barProductsTable;
    private JButton editSaleButton;
    private JButton backButton;
    private JButton finishSaleButton;

    public FinishSaleWindow() {
        super("Finalizar Venda");
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

    }

    private void backButtonPerformed(ActionEvent e) {
        new BarProductsSaleWindow(this).setVisible(true);
        dispose();
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

    public static void main(String[] args) {
        new FinishSaleWindow().setVisible(true);
    }
}
