package com.satiric.doit.Tables;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.sql.Date;
import java.sql.Timestamp;

@Entity(name="users")
public class User {
     // This tells Hibernate to make a table out of this class
        @Id
        @GeneratedValue(strategy= GenerationType.AUTO)
        private Integer id;

        private String login;

        private String pass;

        private Timestamp date_create;

        private String first_name;

        private String second_name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setDate_create(Timestamp date_create) {
        this.date_create = date_create;
    }

    public String getLogin() {
            return login;
        }

        public String getPass() {
            return pass;
        }

        public void setPass(String pass) {
            this.pass = pass;
        }

        public Timestamp getDate_create() {
            return date_create;
        }

        public String getFirst_name() {
            return first_name;
        }

        public void setFirst_name(String first_name) {
            this.first_name = first_name;
        }

        public String getSecond_name() {
            return second_name;
        }

        public void setSecond_name(String second_name) {
            this.second_name = second_name;
        }
        public User(String login,String pass,Timestamp date_create,String first_name,String second_name) {
            this.login=login;
            this.pass=pass;
            this.date_create=date_create;
            this.first_name=first_name;
            this.second_name=second_name;
        }
        public User(){}
        public User(String login,String pass,Timestamp date_create) {
            this.login=login;
            this.pass=pass;
            this.date_create=date_create;
        }
}

