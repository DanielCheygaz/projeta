import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;

public class StockManagerWindow extends JFrame{
    private JTable productsTable;
    private JButton backButton;
    private JPanel mainPanel;
    private JButton addProductButton;
    private JButton removeProductButton;
    private JButton editProductButton;
    private JScrollPane scrollPane;
    private JButton buyProductButton;
    private DefaultTableModel tableModel;
    private MainWindow previousWindow;
    private String[] columns;

    public StockManagerWindow(MainWindow previousWindow){
        super("Gestor de Stock");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setContentPane(mainPanel);
        pack();

        this.previousWindow = previousWindow;

        scrollPane.getViewport().setBackground(Color.decode("2894892"));
        productsTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        String[] columns = {"Nome do produto","Unidades","Preço Unidade"};
        this.columns = columns;

        refreshData();

        this.addProductButton.addActionListener(this::addButtonPerformed);
        this.editProductButton.addActionListener(this::editProductButtonPerformed);
        this.buyProductButton.addActionListener(this::buyProductButtonPerformed);
        this.removeProductButton.addActionListener(this::removeProductPerformed);
        this.backButton.addActionListener(this::backButtonPerformed);
    }

    public void refreshData(){
        tableModel = new DefaultTableModel(columns,0);
        for(Product product: AppData.getInstance().getProductList()){
            Object[] row = {product.getName(),product.getUnits(),product.getPrice()};
            tableModel.addRow(row);
        }
        productsTable.setModel(tableModel);
    }

    private void addButtonPerformed(ActionEvent e){
        new ProductAddWindow(this).setVisible(true);
        setVisible(false);
    }

    private void editProductButtonPerformed(ActionEvent e){
        int selectedRow = getSelectedRow();

        if(selectedRow==-1){
            return;
        }

        Product product = AppData.getInstance().getProductList().get(selectedRow);
        new ProductEditWindow(this, product).setVisible(true);
        setVisible(false);
    }

    private void buyProductButtonPerformed(ActionEvent e){
        int selectedRow = getSelectedRow();

        if(selectedRow==-1){
            return;
        }

        Product product = AppData.getInstance().getProductList().get(selectedRow);
        new ProductBuyWindow(this, product).setVisible(true);
        setVisible(false);
    }

    private void removeProductPerformed(ActionEvent e){
        int selectedRow = getSelectedRow();

        if(selectedRow==-1){
            return;
        }

        Product product = AppData.getInstance().getProductList().get(selectedRow);
        AppData.getInstance().removeProduct(product);
        tableModel.removeRow(selectedRow);
    }

    private int getSelectedRow(){
        int selectedRow = productsTable.getSelectedRow();
        if(selectedRow==-1){
            new ErrorWindow("Selecione primeiro um produto").setVisible(true);
            return -1;
        }
        return selectedRow;
    }

    private void backButtonPerformed(ActionEvent e){
        previousWindow.setVisible(true);
        dispose();
    }

}
