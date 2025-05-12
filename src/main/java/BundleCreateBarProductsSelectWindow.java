import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;

public class BundleCreateBarProductsSelectWindow extends JFrame{
    private JPanel mainPanel;
    private JButton backButton;
    private JScrollPane scrollPaneProducts;
    private JTable productsTable;
    private JButton addProductButton;
    private JButton removeProdutoButton;
    private JButton finishBundleButton;
    private JTable addedProductsTable;
    private JScrollPane scrollPaneAddedProducts;

    public BundleCreateBarProductsSelectWindow(){
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setContentPane(mainPanel);
        pack();
        backButton.addActionListener(this::backButtonPerformed);
        addProductButton.addActionListener(this::addProductButtonPerformed);
        removeProdutoButton.addActionListener(this::removeProductButtonPerformed);
        finishBundleButton.addActionListener(this::finishBundleButtonPerformed);

        scrollPaneProducts.getViewport().setBackground(Color.decode("2894892"));
        scrollPaneAddedProducts.getViewport().setBackground(Color.decode("2894892"));

        String[] addedColumns = {
                "Nome do produto",
                "Unidades",
                "Desconto (%)",
                "Preço Unit. c/ Desconto",
                "Total"
        };
        DefaultTableModel addedTableModel = new DefaultTableModel(addedColumns, 0);
        addedProductsTable.setModel(addedTableModel);


        String[] columns = {"Nome do produto","Preço Unidade"};
        DefaultTableModel tableModel = new DefaultTableModel(columns,0);
        for(Stock stock: AppData.getInstance().getStockList()){
            Object[] row = {stock.getProduct().getName(),stock.getProduct().getPrice()};
            tableModel.addRow(row);
        }
        productsTable.setModel(tableModel);
    }

    private void backButtonPerformed(ActionEvent e){
        new BundleManagerWindow().setVisible(true);
        dispose();
    }

    private void addProductButtonPerformed(ActionEvent e) {
        int selectedRow = productsTable.getSelectedRow();

        if (selectedRow != -1) {
            DefaultTableModel sourceModel = (DefaultTableModel) productsTable.getModel();
            DefaultTableModel destModel = (DefaultTableModel) addedProductsTable.getModel();

            // Obter dados do produto selecionado
            String nomeProduto = (String) sourceModel.getValueAt(selectedRow, 0);
            double precoOriginal = (double) sourceModel.getValueAt(selectedRow, 1);

            // Abrir janela para selecionar unidades e desconto
            UnitsAndDiscountSelectWindow detailsWindow = new UnitsAndDiscountSelectWindow(this);
            detailsWindow.setVisible(true);
            int unidades = detailsWindow.getSelectedUnits();
            double desconto = detailsWindow.getSelectedDiscount();

            // Calcular preço com desconto e total
            double precoComDesconto = precoOriginal * (1 - desconto / 100.0);
            double total = precoComDesconto * unidades;

            // Adicionar à tabela de produtos adicionados
            Object[] row = {
                    nomeProduto,
                    unidades,
                    desconto,
                    String.format("%.2f", precoComDesconto),
                    String.format("%.2f", total)
            };
            destModel.addRow(row);

        } else {
            JOptionPane.showMessageDialog(this, "Por favor, selecione um produto para adicionar.");
        }
    }




    private void removeProductButtonPerformed(ActionEvent e) {
        int selectedRow = addedProductsTable.getSelectedRow();

        if (selectedRow != -1) {
            DefaultTableModel model = (DefaultTableModel) addedProductsTable.getModel();
            model.removeRow(selectedRow);
        } else {
            JOptionPane.showMessageDialog(this, "Por favor, selecione um produto para remover.");
        }
    }


    private void finishBundleButtonPerformed(ActionEvent e){
        new BundleManagerWindow().setVisible(true);
        dispose();
    }

    public static void main(String[] args) {
        new BundleCreateBarProductsSelectWindow().setVisible(true);
    }
}
