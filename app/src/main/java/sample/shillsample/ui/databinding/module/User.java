package sample.shillsample.ui.databinding.module;

/**
 * Created by we on 2016/12/19.
 */

public class User {

    private String firstName;
    private String lastName;
    private int age;
    private String displayName;

    public String getDisplayName() {
        return firstName + " " + lastName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public User(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public User( String firstName,String lastName, int age) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.age = age;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public boolean isAdult() {
        return age >= 18;
    }
}
