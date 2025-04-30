import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;

public class StockManagerWindow extends JFrame{
    private JTable productsTable;
    private JButton backButton;
    private JPanel mainPanel;
    private JButton adicionarProdutoButton;
    private JButton removerProdutoButton;
    private JButton editProductButton;
    private JScrollPane scrollPane;

    public StockManagerWindow(){
        super("Gestor de Stock");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setContentPane(mainPanel);
        pack();
        scrollPane.getViewport().setBackground(Color.decode("2894892"));
        String[] columns = {"Nome do produto","Unidades","Preço Unidade"};

        DefaultTableModel tableModel = new DefaultTableModel(columns,0);

        for(Stock stock: AppData.getInstance().getStockList()){
            Object[] row = {stock.getProduct().getName(),stock.getUnits(),stock.getProduct().getPrice()};
            tableModel.addRow(row);
        }
        productsTable.setModel(tableModel);
        productsTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        this.editProductButton.addActionListener(this::editProductButtonPerformed);
        this.backButton.addActionListener(this::backButtonPerformed);
    }

    private void editProductButtonPerformed(ActionEvent e){
        int selectedRow = productsTable.getSelectedRow();

        if(selectedRow==-1){
            new ErrorWindow("Selecione primeiro um produto").setVisible(true);
            return;
        }

        Stock stock = AppData.getInstance().getStockList().get(selectedRow);
        dispose();
        new ProductEditWindow(stock,selectedRow).setVisible(true);
    }

    private void backButtonPerformed(ActionEvent e){
        new MainWindow().setVisible(true);
        dispose();
    }
    public static void main(String[] args){
        new StockManagerWindow().setVisible(true);
    }
}
