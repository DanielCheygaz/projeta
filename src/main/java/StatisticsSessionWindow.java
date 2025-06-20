import javax.swing.*;
import java.awt.event.ActionEvent;
import javax.swing.table.DefaultTableModel;
import java.util.List;

public class StatisticsSessionWindow extends JFrame{
    private JPanel mainPanel;
    private JButton backButton;
    private JScrollPane scrollPane;
    private JTable salesTable;

    public StatisticsSessionWindow(){
        setTitle("Estatísticas de Sessões");
        setContentPane(mainPanel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();


        backButton.addActionListener(this::backButtonActionPerformed);

        String[] columns = {"ID da sessão", "Taxa de ocupação da sala"};
        DefaultTableModel tableModel = new DefaultTableModel(columns, 0);

        // Sample data for the table
        List<SessionStats> stats = AppData.getInstance().getSessionOccupancyStats();
        for (SessionStats stat : stats) {
            tableModel.addRow(new Object[]{
                    stat.getSessionId(),
                    String.format("%.2f%%", stat.getOccupancyRate())
            });
        }

        salesTable.setModel(tableModel);
        scrollPane.setViewportView(salesTable);
    }

    private void backButtonActionPerformed(ActionEvent e) {
        new StatisticsWindow().setVisible(true);
        setVisible(false);
    }

}
