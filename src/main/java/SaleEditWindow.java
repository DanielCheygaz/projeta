import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;

public class SaleEditWindow extends JFrame{
    private JPanel mainPanel;
    private JScrollPane scrollPane;
    private JTable barProductsTable;
    private JButton finishEditButton;
    private JButton finishSaleButton;
    private JButton removeSelectedProductButton;
    private JButton backButton;

    private JFrame previousWindow;

    public SaleEditWindow(JFrame previousWindow) {
        super("Edição de Venda");
        this.previousWindow = previousWindow;

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setContentPane(mainPanel);
        pack();
        scrollPane.getViewport().setBackground(Color.decode("2894892"));
        String[] columns = {"Quantidade", "Nome do Produto", "Preço"};
        DefaultTableModel tableModel = new DefaultTableModel(columns, 0);
        float i=1;
        for (Product product : AppData.getInstance().getProductList()) {
            Object[] row = {i, product.getName(), i*product.getPrice()};
            tableModel.addRow(row);
            i+=1.5;
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
        new SalesMainWindow().setVisible(true);
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
        new SaleEditWindow(null).setVisible(true);
    }
}
