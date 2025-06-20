import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.LinkedList;

public class BundleCreateBarProductsSelectWindow extends JFrame {
    private JPanel mainPanel;
    private JButton backButton;
    private JScrollPane scrollPaneProducts;
    private JTable productsTable;
    private JButton addProductButton;
    private JButton removeProdutoButton;
    private JButton finishBundleButton;
    private JTable addedProductsTable;
    private JScrollPane scrollPaneAddedProducts;
    private JFrame previousWindow;

    public BundleCreateBarProductsSelectWindow(JFrame previousWindow) {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setContentPane(mainPanel);
        pack();
        this.previousWindow = previousWindow;
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

        String[] columns = {"Nome do produto", "Preço Unidade"};
        DefaultTableModel tableModel = new DefaultTableModel(columns, 0);
        for (Product product : AppData.getInstance().getProductList()) {
            Object[] row = {product.getName(), product.getPrice()};
            tableModel.addRow(row);
        }
        productsTable.setModel(tableModel);
    }

    private void backButtonPerformed(ActionEvent e) {
        if (previousWindow == null) {
            new MainWindow().setVisible(true);
        } else {
            previousWindow.setVisible(true);
        }
        dispose();
    }

    private void addProductButtonPerformed(ActionEvent e) {
        int selectedRow = productsTable.getSelectedRow();

        if (selectedRow != -1) {
            DefaultTableModel sourceModel = (DefaultTableModel) productsTable.getModel();
            DefaultTableModel destModel = (DefaultTableModel) addedProductsTable.getModel();

            String nomeProduto = (String) sourceModel.getValueAt(selectedRow, 0);
            double precoOriginal = (double) sourceModel.getValueAt(selectedRow, 1);

            UnitsAndDiscountSelectWindow detailsWindow = new UnitsAndDiscountSelectWindow(this);
            detailsWindow.setVisible(true);
            int unidades = detailsWindow.getSelectedUnits();
            double desconto = detailsWindow.getSelectedDiscount();

            double precoComDesconto = precoOriginal * (1 - desconto / 100.0);
            double total = precoComDesconto * unidades;

            Object[] row = {
                    nomeProduto,
                    unidades,
                    desconto,
                    precoComDesconto, // Adiciona o Double diretamente
                    total // Adiciona o Double diretamente
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

    private void finishBundleButtonPerformed(ActionEvent e) {
        DefaultTableModel model = (DefaultTableModel) addedProductsTable.getModel();
        if (model.getRowCount() == 0) {
            JOptionPane.showMessageDialog(this, "Adicione pelo menos um produto ao bundle.");
            return;
        }

        String nome = JOptionPane.showInputDialog(this, "Nome do Bundle:");
        if (nome == null || nome.trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Nome inválido.");
            return;
        }

        LinkedList<Product> produtosComDesconto = new LinkedList<>();
        for (int i = 0; i < model.getRowCount(); i++) {
            String nomeProduto = (String) model.getValueAt(i, 0);
            int unidades = (int) model.getValueAt(i, 1);
            double precoComDesconto = (Double) model.getValueAt(i, 3);

            Product p = new Product(nomeProduto, precoComDesconto, unidades);
            produtosComDesconto.add(p);
        }

        int novoId = AppData.getInstance().getBundleList().size() + 1;
        Bundle novoBundle = new Bundle(novoId, nome, produtosComDesconto);
        AppData.getInstance().getBundleList().add(novoBundle);

        JOptionPane.showMessageDialog(this, "Bundle criado com sucesso!");
        if (previousWindow != null) previousWindow.setVisible(true);
        dispose();
    }
}
