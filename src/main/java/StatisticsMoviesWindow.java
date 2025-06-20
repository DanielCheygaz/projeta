import javax.swing.*;
import java.awt.event.ActionEvent;
import javax.swing.table.DefaultTableModel;
import java.util.List;

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
        List<GenreStats> stats = AppData.getInstance().getTopSellingGenres();
        for (GenreStats stat : stats) {
            tableModel.addRow(new Object[]{stat.getGenre(), String.format("%.2f%%", stat.getPercentage())});
        }

        salesTable.setModel(tableModel);
        scrollPane.setViewportView(salesTable);
    }

    private void backButtonActionPerformed(ActionEvent e) {
        new StatisticsWindow().setVisible(true);
        setVisible(false);
    }

}
