public class Room {
    private int roomNumber;
    private int numberRows;
    private int numberColumns;
    private boolean imax;
    private boolean dolbyAtmos;

    public Room(int roomNumber, int numberColumns, int numberRows, boolean imax, boolean dolbyAtmos) {
        this.roomNumber = roomNumber;
        this.numberColumns = numberColumns;
        this.numberRows = numberRows;
        this.imax = imax;
        this.dolbyAtmos = dolbyAtmos;
    }


    public int getNumberOfSeats() {
        return numberColumns*numberColumns;
    }

    public void setNumberOfSeats(int numberColumns, int numberRows) {
        this.numberColumns = numberColumns;
        this.numberRows = numberRows;
    }

    public int getNumberRows() {
        return numberRows;
    }

    public void setNumberRows(int numberRows) {
        this.numberRows = numberRows;
    }

    public int getNumberColumns() {
        return numberColumns;
    }

    public void setNumberColumns(int numberColumns) {
        this.numberColumns = numberColumns;
    }


    public boolean isImax() {
        return imax;
    }

    public void setImax(boolean imax) {
        this.imax = imax;
    }

    public boolean isDolbyAtmos() {
        return dolbyAtmos;
    }

    public void setDolbyAtmos(boolean dolbyAtmos) {
        this.dolbyAtmos = dolbyAtmos;
    }

    public Room(int roomNumber) {
        this.roomNumber = roomNumber;
    }

    public int getRoomNumber() {
        return roomNumber;
    }
}
