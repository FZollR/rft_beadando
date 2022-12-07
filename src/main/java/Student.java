public class Student {
    private String name;
    private String neptunKod;

    public Student(String name, String neptunKod) {
        this.name = name;
        this.neptunKod = neptunKod;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNeptunKod() {
        return neptunKod;
    }

    public void setNeptunKod(String neptunKod) {
        this.neptunKod = neptunKod;
    }
}
