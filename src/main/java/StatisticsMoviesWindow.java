import javax.swing.*;
import java.awt.event.ActionEvent;
import javax.swing.table.DefaultTableModel;

public class StatisticsMoviesWindow extends JFrame{
    private JPanel mainPanel;
    private JButton backButton;
    private JScrollPane scrollPane;
    private JTable salesTable;

    public StatisticsMoviesWindow(){
        setTitle("Estatísticas de Filmes");
        setContentPane(mainPanel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();


        backButton.addActionListener(this::backButtonActionPerformed);

        String[] columns = {"Género", "Percentagem de vendas"};
        DefaultTableModel tableModel = new DefaultTableModel(columns, 0);

        // Sample data for the table
        tableModel.addRow(new Object[]{"Acção", "25%"});
        tableModel.addRow(new Object[]{"Animação", "25%"});
        tableModel.addRow(new Object[]{"Drama", "20%"});
        tableModel.addRow(new Object[]{"Comédia", "15%"});
        tableModel.addRow(new Object[]{"Terror", "10%"});
        tableModel.addRow(new Object[]{"Romance", "5%"});

        salesTable.setModel(tableModel);
        scrollPane.setViewportView(salesTable);
    }

    private void backButtonActionPerformed(ActionEvent e) {
        new StatisticsWindow().setVisible(true);
        setVisible(false);
    }

}
