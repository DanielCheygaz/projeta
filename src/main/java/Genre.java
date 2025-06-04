public class Genre {
    private String name;

    public Genre(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if(name.isBlank()){
            throw new IllegalArgumentException("O nome não pode estar vazio");
        }
        this.name = name;
    }
}
