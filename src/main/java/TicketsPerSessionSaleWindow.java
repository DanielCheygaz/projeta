import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;

public class TicketsPerSessionSaleWindow extends JFrame {
    private Session session;
    private JPanel seatPanel;
    private JButton confirmButton;
    private JButton cancelButton;
    private List<Seat> selectedSeats = new java.util.ArrayList<>();
    private JFrame previousWindow;

    public TicketsPerSessionSaleWindow(Session session, JFrame previousWindow) {
        this.session = session;
        this.previousWindow = previousWindow;

        setTitle("Venda de Bilhetes - Sessão " + session.getID());
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null); // centraliza a janela

        initComponents(session.getRoom().getNumberRows(), session.getRoom().getNumberColumns());
    }

    private void initComponents(int rows, int cols) {
        seatPanel = new JPanel(new GridLayout(rows, cols, 5, 5));

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                Seat seat = new Seat(r, c);
                SeatButton button = new SeatButton(seat);

                if (session.isSeatOccupied(seat)) {
                    button.setEnabled(false);
                    button.setBackground(Color.RED);
                } else {
                    button.addActionListener(e -> toggleSeatSelection(button));
                }

                seatPanel.add(button);
            }
        }

        confirmButton = new JButton("Adicionar Bilhetes");
        confirmButton.addActionListener(this::confirmTickets);

        cancelButton = new JButton("Cancelar");
        cancelButton.addActionListener(e -> {
            if(previousWindow != null) {
                previousWindow.setVisible(true);
            }
            dispose();
        });

        setLayout(new BorderLayout());
        add(new JScrollPane(seatPanel), BorderLayout.CENTER);
        add(confirmButton, BorderLayout.SOUTH);
    }

    private void toggleSeatSelection(SeatButton button) {
        Seat seat = button.getSeat();

        if (selectedSeats.contains(seat)) {
            selectedSeats.remove(seat);
            button.setBackground(null);
        } else {
            selectedSeats.add(seat);
            button.setBackground(Color.GREEN);
        }
    }

    private void confirmTickets(ActionEvent e) {
        if (selectedSeats.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Nenhum lugar selecionado.");
            return;
        }

        for (Seat seat : selectedSeats) {
            session.occupySeat(seat);
            // Criar ticket
            int newTicketId = AppData.getInstance().getTicketList().size() + 1;

            // Preço e tipo podem ser parametrizados depois
            Ticket ticket = new Ticket(newTicketId, session, 10.0, "normal");

            // Adicionar bilhete ao AppData
            AppData.getInstance().addTicket(ticket);
        }

        JOptionPane.showMessageDialog(this, "Bilhetes adicionados com sucesso!");
        new SessionSelectWindow(this).setVisible(true);
        dispose();
        new SessionSelectWindow(this).setVisible(true);

    }
}
