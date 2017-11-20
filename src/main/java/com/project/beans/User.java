package com.project.beans;

import javax.websocket.Session;

public class User implements java.lang.Comparable {
    private String login;
    private String password;
    private boolean isOnline;
    private String sex;
    private int age;
    private String comment;
    private String email;
    private String friendsLogins;
    private Session session;

    public User(String login, String password) {
        this.login = login;
        this.password = password;
        this.sex = null;
        this.comment = null;
        this.email = null;
        this.friendsLogins = "";

    }

    public User(User user) {
        this.login = user.getLogin();
        this.password = user.getPassword();
        this.sex = user.getSex();
        this.comment = user.getComment();
        this.email = user.getComment();
        this.friendsLogins = "";

    }

    public User(String login, String password, boolean isOnline, String sex, int age, String comment, String email) {
        this.login = login;
        this.password = password;
        this.sex = sex;
        this.age = age;
        this.comment = comment;
        this.email = email;
        this.friendsLogins = "";
    }

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }

    public String getPassword() {
        return password;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public boolean isOnline() {
        return isOnline;
    }

    public void setOnline(boolean online) {
        isOnline = online;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFriendsLogins() {
        return friendsLogins;
    }

    public void setFriendsLogins(String friendsLogins) {
        this.friendsLogins = friendsLogins;
    }

    public boolean equals(Object obj) {
        User user = (User) obj;
        if (user.getPassword().equals(this.password) && user.getLogin().equals(this.login)) {
            return true;
        } else {
            return false;
        }
    }


    @Override
    public int compareTo(Object o) {
        User user = (User) o;
        if (user.getPassword().equals(this.password) && user.getLogin().equals(this.login)) {
            return 0;
        } else if (user.getAge() > this.getAge()) {
            return -1;
        } else {
            return 1;
        }
    }
}
