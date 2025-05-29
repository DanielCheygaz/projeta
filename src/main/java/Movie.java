public class Movie {
    private String name;
    private int duration;
    private String description;
    private Genre genre;
    private boolean imax;
    private boolean dolbyAtmos;

    public Movie(String name, int duration, String description, Genre genre, boolean imax, boolean dolbyAtmos) {
        this.name = name;
        this.duration = duration;
        this.description = description;
        this.genre = genre;
        this.imax = imax;
        this.dolbyAtmos = dolbyAtmos;
    }

    public String getName() {
        return name;
    }

    public int getDuration() {
        return duration;
    }

    public String getDescription() {
        return description;
    }

    public Genre getGenre() {
        return genre;
    }

    public boolean isImax() {
        return imax;
    }

    public boolean isDolbyAtmos() {
        return dolbyAtmos;
    }

    public void updateMovie(String name, int duration, String description, Genre genre, boolean imax, boolean dolbyAtmos){
        this.name = name;
        this.duration = duration;
        this.description = description;
        this.genre = genre;
        this.imax = imax;
        this.dolbyAtmos = dolbyAtmos;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "name='" + name + '\'' +
                ", duration=" + duration +
                ", description='" + description + '\'' +
                ", genre=" + genre +
                ", imax=" + imax +
                ", dolbyAtmos=" + dolbyAtmos +
                '}';
    }
}
