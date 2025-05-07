import javax.swing.*;
import java.awt.*;

public class SeatButton extends JButton {
    private int state;
    private int row;
    private int column;

    public SeatButton(int row, int column) {
        this.row = row;
        this.column = column;
    }

    public void setState(int state) {
        this.state = state;
        switch (state) {
            case 0: // Available
                setText("L");
                setBackground(Color.GREEN);
                break;
            case 1: // Selected
                setText("R");
                setBackground(Color.YELLOW);
                break;
            case 2: // Sold
                setText("S");
                setBackground(Color.RED);
                break;
            default:
                setText("");
                setBackground(null);
        }
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    public int getState() {
        return state;
    }
}
