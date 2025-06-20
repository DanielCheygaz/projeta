import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.HashMap;
import java.util.Map;
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

            Map<String, Product> stats = new HashMap<>();

            for (Product sold : AppData.getInstance().getSoldProducts()) {
                String name = sold.getName();
                if (stats.containsKey(name)) {
                    Product existing = stats.get(name);
                    existing.addUnits(sold.getUnits());  // soma unidades vendidas
                } else {
                    stats.put(name, new Product(name, sold.getPrice(), sold.getUnits()));
                }
            }


            for (Product p : stats.values()) {
                double total = p.getUnits() * p.getPrice();
                tableModel.addRow(new Object[]{p.getName(), p.getUnits(), String.format("€%.2f", total)});
            }



        salesTable.setModel(tableModel);
        scrollPane.setViewportView(salesTable);
    }

    private void backButtonActionPerformed(ActionEvent e) {
        new StatisticsWindow().setVisible(true);
        setVisible(false);
    }
}
