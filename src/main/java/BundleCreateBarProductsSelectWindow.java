import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;

public class BundleCreateBarProductsSelectWindow extends JFrame{
    private JPanel mainPanel;
    private JButton backButton;
    private JScrollPane scrollPane;
    private JTable productsTable;
    private JButton addProductButton;
    private JButton removeProdutoButton;
    private JButton finishBundleButton;

    public BundleCreateBarProductsSelectWindow(){
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setContentPane(mainPanel);
        pack();
        backButton.addActionListener(this::backButtonPerformed);
        addProductButton.addActionListener(this::addProductButtonPerformed);
        removeProdutoButton.addActionListener(this::removeProductButtonPerformed);
        finishBundleButton.addActionListener(this::finishBundleButtonPerformed);

        scrollPane.getViewport().setBackground(Color.decode("2894892"));
        String[] columns = {"Nome do produto","Unidades","Preço Unidade"};
        DefaultTableModel tableModel = new DefaultTableModel(columns,0);
        for(Stock stock: AppData.getInstance().getStockList()){
            Object[] row = {stock.getProduct().getName(),stock.getUnits(),stock.getProduct().getPrice()};
            tableModel.addRow(row);
        }
        productsTable.setModel(tableModel);
    }

    private void backButtonPerformed(ActionEvent e){
        new BundleCreateTicketSelectWindow().setVisible(true);
        dispose();
    }

    private void addProductButtonPerformed(ActionEvent e){
        new BundleCreateAddProductWindow().setVisible(true);
        dispose();
    }

    private void removeProductButtonPerformed(ActionEvent e){

    }

    private void finishBundleButtonPerformed(ActionEvent e){
        new BundleManagerWindow().setVisible(true);
        dispose();
    }

    public static void main(String[] args) {
        new BundleCreateBarProductsSelectWindow().setVisible(true);
    }
}
