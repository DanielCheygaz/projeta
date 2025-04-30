import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;

public class TicketsPerSessionSaleWindow extends JFrame {
    private JPanel mainPanel;
    private JScrollPane scrollPane;
    private JTable ticketTable;
    private JButton editSaleButton;
    private JButton finishSaleButton;
    private JButton addBarProductsButton;
    private JButton addTicketButton;
    private JButton backButton;

    private JFrame previousWindow;

    public TicketsPerSessionSaleWindow(JFrame previousWindow, int sessionID) {
        super("Venda de Bilhetes");
        this.previousWindow = previousWindow;

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setContentPane(mainPanel);
        pack();

        scrollPane.getViewport().setBackground(Color.decode("2894892"));

        //adicionar lugar para os bilhetes
        String[] columns = {"ID do Bilhete", "Sala", "Valor do Bilhete"};
        DefaultTableModel tableModel = new DefaultTableModel(columns, 0);
        for (Ticket ticket : AppData.getInstance().getTicketList()) {
            if (ticket.getSession().getID() == sessionID) {
                Object[] row = {ticket.getId(), ticket.getSession().getRoom().getRoomNumber(), ticket.getPrice()};
                tableModel.addRow(row);
            }
        }
        ticketTable.setModel(tableModel);

        this.backButton.addActionListener(this::backButtonPerformed);
        this.finishSaleButton.addActionListener(this::finishSaleButtonPerformed);
        this.editSaleButton.addActionListener(this::editSaleButtonPerformed);
        this.addTicketButton.addActionListener(this::addTicketButtonPerformed);
        this.addBarProductsButton.addActionListener(this::addBarProductsButtonPerformed);
    }

    private void backButtonPerformed(ActionEvent e) {
        previousWindow.setVisible(true);
        dispose();
    }

    private void finishSaleButtonPerformed(ActionEvent e) {
        JOptionPane.showMessageDialog(this, "Venda finalizada com sucesso!");
        previousWindow.setVisible(true);
        dispose();
    }

    private void editSaleButtonPerformed(ActionEvent e) {
        new SaleEditWindow(this).setVisible(true);
        dispose();
    }

    private void addBarProductsButtonPerformed(ActionEvent e) {
        new BarProductsSaleWindow(this).setVisible(true);
        dispose();
    }

    private void addTicketButtonPerformed(ActionEvent e) {
        int selectedRow = ticketTable.getSelectedRow();
        if (selectedRow != -1) {
            JOptionPane.showMessageDialog(this, "Bilhetes adicionados com sucesso");
            previousWindow.setVisible(true);
            dispose();
        } else {
            new ErrorWindow("Selecione um bilhete para adicionar à venda.").setVisible(true);
        }
    }

    public static void main(String[] args) {
        new TicketsPerSessionSaleWindow(null, 1).setVisible(true);
    }
}


