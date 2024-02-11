package ModelsEntities;
public class User {

    private String name;
    private String surname;
    private  String email;
    private int age;
    private String password;

    private String city;
    private String address;
    private int postindex;

    private double balance;


    public User() {
    }




    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setAge(int age) {this.age = age;}

    public void setPassword(String password) {this.password = password;}

    public void setBalance(double balance) {this.balance = balance;}


    public void setAddress(String address) {
        this.address = address;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setPostindex(int postindex) {
        this.postindex = postindex;
    }


    public int getPostindex() {
        return postindex;
    }

    public String getAddress() {
        return address;
    }

    public String getCity() {
        return city;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getSurname() {
        return surname;
    }

    public int getAge() {return age;}

    public String getPassword() {return password;}

}
