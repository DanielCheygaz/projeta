import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.*;
import java.util.List;

public class TicketsPerSessionSaleWindow extends JFrame {
    private Session session;
    private JPanel seatPanel;
    private JButton confirmButton;
    private JButton cancelButton;
    private List<Seat> selectedSeats = new ArrayList<>();
    private Map<Seat, String> seatTypes = new HashMap<>();
    private JFrame previousWindow;

    public TicketsPerSessionSaleWindow(Session session, JFrame previousWindow) {
        this.session = session;
        this.previousWindow = previousWindow;

        setTitle("Venda de Bilhetes - Sessão " + session.getID());
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);

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
            if (previousWindow != null) {
                previousWindow.setVisible(true);
            }
            dispose();
        });

        setLayout(new BorderLayout());
        add(new JScrollPane(seatPanel), BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel();
        bottomPanel.add(confirmButton);
        bottomPanel.add(cancelButton);
        add(bottomPanel, BorderLayout.SOUTH);
    }

    private void toggleSeatSelection(SeatButton button) {
        Seat seat = button.getSeat();
        if (selectedSeats.contains(seat)) {
            selectedSeats.remove(seat);
            seatTypes.remove(seat);
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

        AppData appData = AppData.getInstance();
        if (appData.getActiveSale() == null) {
            appData.startSale();
        }

        Sale currentSale = appData.getActiveSale();

        for (Seat seat : selectedSeats) {
            // Pergunta o tipo se ainda não tiver sido escolhido
            String tipoSelecionado = seatTypes.get(seat);
            if (tipoSelecionado == null) {
                String[] tipos = {"normal", "estudante", "idoso"};
                tipoSelecionado = (String) JOptionPane.showInputDialog(
                        this,
                        "Escolha o tipo de bilhete para o lugar " + seat.toString(),
                        "Tipo de Bilhete",
                        JOptionPane.PLAIN_MESSAGE,
                        null,
                        tipos,
                        "normal"
                );

                if (tipoSelecionado == null) continue; // cancelado
                seatTypes.put(seat, tipoSelecionado);
            }

            double preco = switch (tipoSelecionado) {
                case "estudante" -> 7.0;
                case "idoso" -> 6.0;
                default -> 10.0;
            };

            session.occupySeat(seat);
            int ticketId = appData.getTicketList().size() + 1;
            Ticket ticket = new Ticket(ticketId, session, preco, tipoSelecionado);
            appData.addTicket(ticket);
            currentSale.addLine(new SaleLine(ticket));
        }

        JOptionPane.showMessageDialog(this, "Bilhetes adicionados à fatura com sucesso!");
        previousWindow.setVisible(true);
        dispose();
    }
}
