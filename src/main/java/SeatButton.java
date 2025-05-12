import javax.swing.*;
import java.awt.*;

public class SeatButton extends JButton {
    private int state;
    private int row;
    private int column;

    public SeatButton(int row, int column) {
        this.row = row;
        this.column = column;
        setPreferredSize(new Dimension(100, 30));
    }

    public void setState(int state) {
        this.state = state;
        switch (state) {
            case 0: // Available
                setText("Livre");
                setBackground(Color.GREEN);
                break;
            case 1: // Selected
                setText("Normal");
                setBackground(Color.YELLOW);
                break;
            case 2: // Sold
                setText("Vendido");
                setForeground(Color.WHITE);
                setBackground(Color.RED);
                break;
            case 3: // Estudante
                setText("Estudante");
                setBackground(Color.PINK);
                break;
            case 4: // Idoso
                setText("Idoso");
                setBackground(Color.ORANGE);
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
