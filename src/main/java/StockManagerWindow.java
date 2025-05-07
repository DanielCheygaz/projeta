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

    public StockManagerWindow(){
        super("Gestor de Stock");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setContentPane(mainPanel);
        pack();
        scrollPane.getViewport().setBackground(Color.decode("2894892"));
        String[] columns = {"Nome do produto","Unidades","Preço Unidade"};

        tableModel = new DefaultTableModel(columns,0);


        for(Stock stock: AppData.getInstance().getStockList()){
            Object[] row = {stock.getProduct().getName(),stock.getUnits(),stock.getProduct().getPrice()};
            tableModel.addRow(row);
        }
        productsTable.setModel(tableModel);
        productsTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        this.addProductButton.addActionListener(this::addButtonPerformed);
        this.editProductButton.addActionListener(this::editProductButtonPerformed);
        this.buyProductButton.addActionListener(this::buyProductButtonPerformed);
        this.removeProductButton.addActionListener(this::removeProductPerformed);
        this.backButton.addActionListener(this::backButtonPerformed);
    }

    private void addButtonPerformed(ActionEvent e){
        new ProductAddWindow().setVisible(true);
        dispose();
    }

    private void editProductButtonPerformed(ActionEvent e){
        int selectedRow = getSelectedRow();

        if(selectedRow==-1){
            return;
        }

        Stock stock = AppData.getInstance().getStockList().get(selectedRow);
        new ProductEditWindow(stock,selectedRow).setVisible(true);
        dispose();
    }

    private void buyProductButtonPerformed(ActionEvent e){
        int selectedRow = getSelectedRow();

        if(selectedRow==-1){
            return;
        }

        Stock stock = AppData.getInstance().getStockList().get(selectedRow);
        new ProductBuyWindow(stock).setVisible(true);
        dispose();
    }

    private void removeProductPerformed(ActionEvent e){
        int selectedRow = getSelectedRow();

        if(selectedRow==-1){
            return;
        }

        Stock stock = AppData.getInstance().getStockList().get(selectedRow);
        AppData.getInstance().removeStock(stock);
        tableModel.removeRow(selectedRow);
    }

    private void backButtonPerformed(ActionEvent e){
        new MainWindow().setVisible(true);
        dispose();
    }

    private int getSelectedRow(){
        int selectedRow = productsTable.getSelectedRow();
        if(selectedRow==-1){
            new ErrorWindow("Selecione primeiro um produto").setVisible(true);
            return -1;
        }
        return selectedRow;
    }

    public static void main(String[] args){
        new StockManagerWindow().setVisible(true);
    }
}
