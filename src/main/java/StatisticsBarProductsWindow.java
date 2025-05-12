import javax.swing.*;
import java.awt.event.ActionEvent;
import javax.swing.table.DefaultTableModel;

public class StatisticsBarProductsWindow extends JFrame{
    private JPanel mainPanel;
    private JButton backButton;
    private JScrollPane scrollPane;
    private JTable salesTable;

    public StatisticsBarProductsWindow(){
        setTitle("Estatísticas de Filmes");
        setContentPane(mainPanel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();

        backButton.addActionListener(this::backButtonActionPerformed);

        String[] columns = {"Produto", "Unidades Vendidas"};
        DefaultTableModel tableModel = new DefaultTableModel(columns, 0);

        // Sample data for the table
        tableModel.addRow(new Object[]{"Snickers", "20"});
        tableModel.addRow(new Object[]{"Tabaco", "35"});
        tableModel.addRow(new Object[]{"Gelatina", "39"});
        tableModel.addRow(new Object[]{"Pipocas Salgadas", "48"});
        tableModel.addRow(new Object[]{"Skittles", "56"});
        tableModel.addRow(new Object[]{"Cerveja", "68"});

        salesTable.setModel(tableModel);
        scrollPane.setViewportView(salesTable);
    }

    private void backButtonActionPerformed(ActionEvent e) {
        new StatisticsWindow().setVisible(true);
        setVisible(false);
    }
}
