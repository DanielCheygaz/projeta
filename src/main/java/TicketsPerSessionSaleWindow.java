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
    private SeatButton[][] buttons;

    private JFrame previousWindow;

    public TicketsPerSessionSaleWindow(JFrame previousWindow, int sessionID, int numberOfRows, int numberOfColumns) {
        super("Venda de Bilhetes");
        this.previousWindow = previousWindow;

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setContentPane(mainPanel);
        pack();

        scrollPane.getViewport().setBackground(Color.decode("2894892"));

        this.buttons = new SeatButton[numberOfRows][numberOfColumns];

        JPanel seatPanel = new JPanel(new GridLayout(numberOfRows, numberOfColumns, 5, 5));
        for (int row = 0; row < numberOfRows; ++row) {
            for (int column = 0; column < numberOfColumns; ++column) {
                buttons[row][column] = new SeatButton(row, column);
                buttons[row][column].setState(0);
                //apenas para teste
                if(buttons[row][column].getColumn() == 2 && buttons[row][column].getRow() == 2) {
                    buttons[row][column].setState(2);
                }

                buttons[row][column].addActionListener(e -> {
                    SeatButton button = (SeatButton) e.getSource();
                    if (button.getState() == 0) {
                        button.setState(1);
                    } else if (button.getState() == 1) {
                        button.setState(0);
                    } else if (button.getState() == 2) {
                        JOptionPane.showMessageDialog(this, "Este lugar já está vendido.");
                    }
                });
                seatPanel.add(buttons[row][column]);
            }
        }
        scrollPane.setViewportView(seatPanel);

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
        new TicketsPerSessionSaleWindow(null, 1, 1, 1).setVisible(true);
    }
}


