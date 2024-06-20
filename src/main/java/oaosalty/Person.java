package oaosalty;

public class Person {

    private String name;
    private int age;
    private boolean isCat;
    private boolean isVertolet;

    public Person(String name, int age, boolean isCat, boolean isVertolet) {
        this.name = name;
        this.age = age;
        this.isCat = isCat;
        this.isVertolet = isVertolet;
    }

    public String getName() {
        return name;
    }
}
