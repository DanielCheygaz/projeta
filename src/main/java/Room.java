public class Room {
    private int roomNumber;
    private int numeroLugares;
    private boolean imax;
    private boolean dolbyAtmos;

    public Room(int roomNumber, int numeroLugares, boolean imax, boolean dolbyAtmos) {
        this.roomNumber = roomNumber;
        this.numeroLugares = numeroLugares;
        this.imax = imax;
        this.dolbyAtmos = dolbyAtmos;
    }

    public int getNumeroLugares() {
        return numeroLugares;
    }

    public void setNumeroLugares(int numeroLugares) {
        this.numeroLugares = numeroLugares;
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
