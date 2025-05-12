import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class StatisticsTicketWindow extends JFrame{
    private JButton backButton;
    private JScrollPane scrollPane;
    private JTable ticketsByDayTable;
    private JPanel mainPanel;

    public StatisticsTicketWindow() throws HeadlessException {
        super("Estaísticas dos Bilhetes");
        setContentPane(mainPanel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();

        String[] columns = {"Dia da semana", "Número de Bilhetes Vendidos"};
        DefaultTableModel tableModel = new DefaultTableModel(columns, 0);

        tableModel.addRow(new Object[]{"Segunda-Feira", "24"});
        tableModel.addRow(new Object[]{"Terça-Feira", "33"});
        tableModel.addRow(new Object[]{"Quarta-Feira", "19"});
        tableModel.addRow(new Object[]{"Quinta-Feira", "68"});
        tableModel.addRow(new Object[]{"Sexta-Feira", "83"});
        tableModel.addRow(new Object[]{"Sábado", "80"});
        tableModel.addRow(new Object[]{"Domingo", "78"});

        ticketsByDayTable.setModel(tableModel);
        scrollPane.setViewportView(ticketsByDayTable);
    }
}
