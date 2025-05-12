import javax.swing.*;
import java.awt.event.ActionEvent;
import javax.swing.table.DefaultTableModel;

public class StatisticsBarProductsWindow extends JFrame{
    private JPanel mainPanel;
    private JButton backButton;
    private JScrollPane scrollPane;
    private JTable salesTable;

    public StatisticsBarProductsWindow(){
        setTitle("Estatísticas de Produtoes de Bar");
        setContentPane(mainPanel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();

        backButton.addActionListener(this::backButtonActionPerformed);



        // Sample data for the table
        String[] columns = {"Nome do Produto", "Quantidade", "Preço Total"};
        DefaultTableModel tableModel = new DefaultTableModel(columns, 0);

// Adiciona os produtos com preços unitários fictícios:
        tableModel.addRow(new Object[]{"Cerveja", 68, 68 * 1.5});
        tableModel.addRow(new Object[]{"Skittles", 56, 56 * 0.9});
        tableModel.addRow(new Object[]{"Pipocas Salgadas", 48, 48 * 1});
        tableModel.addRow(new Object[]{"Gelatina", 39, 39 * 0.6});
        tableModel.addRow(new Object[]{"Tabaco", 35, 35 * 4.5});
        tableModel.addRow(new Object[]{"Snickers", 20, 20 * 1.0});







        salesTable.setModel(tableModel);
        scrollPane.setViewportView(salesTable);
    }

    private void backButtonActionPerformed(ActionEvent e) {
        new StatisticsWindow().setVisible(true);
        setVisible(false);
    }
}
