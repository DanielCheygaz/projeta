import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.Calendar;

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


        tableModel.addRow(new Object[]{"Domingo", 0});
        tableModel.addRow(new Object[]{"Segunda-Feira", 0});
        tableModel.addRow(new Object[]{"Terça-Feira", 0});
        tableModel.addRow(new Object[]{"Quarta-Feira", 0});
        tableModel.addRow(new Object[]{"Quinta-Feira", 0});
        tableModel.addRow(new Object[]{"Sexta-Feira", 0});
        tableModel.addRow(new Object[]{"Sábado", 0});

        ticketsByDayTable.setModel(tableModel);
        scrollPane.setViewportView(ticketsByDayTable);

        for(Ticket ticket: AppData.getInstance().getTicketList()){
            Calendar c = Calendar.getInstance();
            c.setTime(ticket.getSession().getDate());
            int dayOfWeek = c.get(Calendar.DAY_OF_WEEK)-1;
            int value = (int)tableModel.getValueAt(dayOfWeek,1) + 1;
            tableModel.setValueAt(value,dayOfWeek,1);
        }

        backButton.addActionListener(this::backButtonActionPerformed);

    }
    private void backButtonActionPerformed(ActionEvent e) {
        new StatisticsWindow().setVisible(true);
        setVisible(false);
    }
}
