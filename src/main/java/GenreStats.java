public class GenreStats {
    private String genre;
    private double percentage;

    public GenreStats(String genre, double percentage) {
        this.genre = genre;
        this.percentage = percentage;
    }

    public String getGenre() {
        return genre;
    }

    public double getPercentage() {
        return percentage;
    }
}