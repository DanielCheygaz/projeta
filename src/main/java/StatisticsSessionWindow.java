import javax.swing.*;
import java.awt.event.ActionEvent;
import javax.swing.table.DefaultTableModel;

public class StatisticsSessionWindow extends JFrame{
    private JPanel mainPanel;
    private JButton backButton;
    private JScrollPane scrollPane;
    private JTable salesTable;

    public StatisticsSessionWindow(){
        setTitle("Estatísticas de Filmes");
        setContentPane(mainPanel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();


        backButton.addActionListener(this::backButtonActionPerformed);

        String[] columns = {"ID da sessão", "Taxa de ocupação da sala"};
        DefaultTableModel tableModel = new DefaultTableModel(columns, 0);

        // Sample data for the table
        tableModel.addRow(new Object[]{"2", "89%"});
        tableModel.addRow(new Object[]{"1", "75%"});
        tableModel.addRow(new Object[]{"4", "50%"});
        tableModel.addRow(new Object[]{"3", "25%"});
        tableModel.addRow(new Object[]{"5", "10%"});

        salesTable.setModel(tableModel);
        scrollPane.setViewportView(salesTable);
    }

    private void backButtonActionPerformed(ActionEvent e) {
        new StatisticsWindow().setVisible(true);
        setVisible(false);
    }

}
