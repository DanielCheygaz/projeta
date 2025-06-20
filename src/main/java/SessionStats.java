public class SessionStats {
    private int sessionId;
    private double occupancyRate;

    public SessionStats(int sessionId, double occupancyRate) {
        this.sessionId = sessionId;
        this.occupancyRate = occupancyRate;
    }

    public int getSessionId() {
        return sessionId;
    }

    public double getOccupancyRate() {
        return occupancyRate;
    }
}