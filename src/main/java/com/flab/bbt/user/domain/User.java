package com.flab.bbt.user.domain;

public class User {
    private long id;
    private String email;
    private String password;
    private String name;
    private String phoneNo;

    public User(String email, String password, String name, String phoneNo) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.phoneNo = phoneNo;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", name='" + name + '\'' +
                ", phoneNo='" + phoneNo + '\'' +
                '}';
    }

    // builder 패턴 구현
    public static User.UserBuilder builder() {
        return new User.UserBuilder();
    }

    public static class UserBuilder {
        private String email;
        private String password;
        private String name;
        private String phoneNo;

        UserBuilder(){}

        public User.UserBuilder email(String email) {
            this.email = email;

            return this;
        }

        public User.UserBuilder password(String password) {
            this.password = password;

            return this;
        }

        public User.UserBuilder name(String name) {
            this.name = name;

            return this;
        }

        public User.UserBuilder phoneNo(String phoneNo) {
            this.phoneNo = phoneNo;

            return this;
        }

        public User build() {
            return new User(this.email,this.password,this.name,this.phoneNo);
        }

    }
}
