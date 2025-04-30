import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;

public class TicketSalesWindow extends JFrame {
    private JPanel mainPanel;
    private JTable ticketTable;
    private JButton addBarProductsButton;
    private JButton finishSaleButton;
    private JButton editSaleButton;
    private JButton backButton;
    private JButton addTicketToSaleButton;
    private JScrollPane scrollPane;

    public TicketSalesWindow() {
        super("Venda de Bilhetes");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setContentPane(mainPanel);
        pack();
        scrollPane.getViewport().setBackground(Color.decode("2894892"));
        String[] columns = {"ID do bilhete", "Filme da Sessão","Preço Total"};
        DefaultTableModel tableModel = new DefaultTableModel(columns, 0);
        for (Ticket ticket : AppData.getInstance().getTicketList()) {
            Object[] row = {ticket.getId(), ticket.getSession().getMovie().getName(), ticket.getPrice()};
            tableModel.addRow(row);
        }
        ticketTable.setModel(tableModel);

        this.backButton.addActionListener(this::backButtonPerformed);
        this.finishSaleButton.addActionListener(this::finishSaleButtonPerformed);
        this.editSaleButton.addActionListener(this::editSaleButtonPerformed);
        this.addBarProductsButton.addActionListener(this::addBarProductsButtonPerformed);
        this.addTicketToSaleButton.addActionListener(this::addTicketToSaleButtonPerformed);

    }

    private void backButtonPerformed(ActionEvent e){
        new MainWindow().setVisible(true);
        dispose();
    }

    private void finishSaleButtonPerformed(ActionEvent e){
        JOptionPane.showMessageDialog(this, "Venda finalizada com sucesso!");
        new SalesMainWindow().setVisible(true);
        dispose();
    }

    private void editSaleButtonPerformed(ActionEvent e){
        new SaleEditWindow(this).setVisible(true);
        dispose();
    }

    private void addBarProductsButtonPerformed(ActionEvent e){
        new BarProductsSaleWindow(this).setVisible(true);
        dispose();
    }

    private void addTicketToSaleButtonPerformed(ActionEvent e){
        int selectedRow = ticketTable.getSelectedRow();
        if (selectedRow != -1) {
            DefaultTableModel model = (DefaultTableModel) ticketTable.getModel();
            String ticketId = model.getValueAt(selectedRow, 0).toString();
            JOptionPane.showMessageDialog(this, "Bilhete " + ticketId + " adicionado à venda.");
        } else {
            JOptionPane.showMessageDialog(this, "Selecione um bilhete para adicionar à venda.");
        }
    }

    public static void main(String[] args){
        new TicketSalesWindow().setVisible(true);
    }
}
