import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;

public class EditSaleWindow extends JFrame{
    private JPanel mainPanel;
    private JScrollPane scrollPane;
    private JTable barProductsTable;
    private JButton finishEditButton;
    private JButton finishSaleButton;
    private JButton removeSelectedProductButton;
    private JButton backButton;

    private JFrame previousWindow;

    public EditSaleWindow(JFrame previousWindow) {
        super("Edição de Venda");
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
        this.finishEditButton.addActionListener(this::finishEditButtonPerformed);
        this.removeSelectedProductButton.addActionListener(this::removeSelectedProductButtonPerformed);
    }

    private void backButtonPerformed(ActionEvent e){
        if (previousWindow != null) {
            previousWindow.setVisible(true);
        }
        dispose();
    }

    private void finishSaleButtonPerformed(ActionEvent e){
        JOptionPane.showMessageDialog(this, "Venda finalizada com sucesso!");
        new SalesWindow().setVisible(true);
        dispose();
    }

    private void removeSelectedProductButtonPerformed(ActionEvent e){
        int selectedRow = barProductsTable.getSelectedRow();
        if (selectedRow != -1) {
            DefaultTableModel model = (DefaultTableModel) barProductsTable.getModel();
            model.removeRow(selectedRow);
            JOptionPane.showMessageDialog(this, "Produto removido com sucesso!");
        } else {
            JOptionPane.showMessageDialog(this, "Selecione um produto para remover.");
        }
    }

    private void finishEditButtonPerformed(ActionEvent e){
        JOptionPane.showMessageDialog(this, "Edição de venda finalizada com sucesso!");
        if (previousWindow != null) {
            previousWindow.setVisible(true);
        }
        dispose();
    }

    public static void main(String[] args) {
        new EditSaleWindow(null).setVisible(true);
    }
}
