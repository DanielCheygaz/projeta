import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class StockManagerWindow extends JFrame{
    private JTable productsTable;
    private JButton voltarButton;
    private JPanel mainPanel;
    private JButton adicionarProdutoButton;
    private JButton removerProdutoButton;
    private JButton adicionarUnidadesButton;
    private JButton removerUnidadesButton;
    private JButton alterarPreçoButton;
    private JScrollPane scrollPane;

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }

    public StockManagerWindow(String title){
        super(title);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setContentPane(mainPanel);
        pack();
        scrollPane.getViewport().setBackground(Color.decode("2894892"));
        String[] columns = {"Nome do produto","Unidades","Preço Unidade"};

        DefaultTableModel tableModel = new DefaultTableModel(columns,0);

        for(Product product: AppData.getInstance().getProductList()){
            Object[] row = {product.getName(),product.getUnits(),product.getPrice()};
            tableModel.addRow(row);
        }
        productsTable.setModel(tableModel);

    }
    public static void main(String[] args){
        new StockManagerWindow("Stock Manager Window").setVisible(true);
    }
}
