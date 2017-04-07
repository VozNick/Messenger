package com.example.vmm408.messenger.models;

public class UserModel {
    private String login;
    private String password;
    private String name;
    private String lastName;
    private int age;
    private String about;

    public UserModel() {
    }

    public UserModel(String login, String password,
                     String name, String lastName,
                     int age, String about) {
        this.login = login;
        this.password = password;
        this.name = name;
        this.lastName = lastName;
        this.age = age;
        this.about = about;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getLogin() {
        return login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getAge() {
        return age;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public String getAbout() {
        return about;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserModel userModel = (UserModel) o;

        if (age != userModel.age) return false;
        if (login != null ? !login.equals(userModel.login) : userModel.login != null) return false;
        if (password != null ? !password.equals(userModel.password) : userModel.password != null)
            return false;
        if (name != null ? !name.equals(userModel.name) : userModel.name != null) return false;
        if (lastName != null ? !lastName.equals(userModel.lastName) : userModel.lastName != null)
            return false;
        return about != null ? about.equals(userModel.about) : userModel.about == null;
    }

    @Override
    public int hashCode() {
        int result = login != null ? login.hashCode() : 0;
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 31 * result + age;
        result = 31 * result + (about != null ? about.hashCode() : 0);
        return result;
    }
}
