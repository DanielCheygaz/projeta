import javax.swing.*;

public class SeatButton extends JButton {
    private final Seat seat;

    public SeatButton(Seat seat) {
        super("[" + (seat.getRow() + 1) + "," + (seat.getColumn() + 1) + "]");
        this.seat = seat;
    }

    public Seat getSeat() {
        return seat;
    }
}
