package com.fast_board.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;
import java.util.Objects;

@ToString
@Getter
@Setter
public class User
{
    private String id;
    private String pwd;
    private String name;
    private String email;
    private Date birth;
    private String sns;
    private Date reg_date;


    public User()
    {

    }

    public User(String id, String pwd, String name, String email, Date birth, String sns, Date reg_date)
    {
        this.id = id;
        this.pwd = pwd;
        this.name = name;
        this.email = email;
        this.birth = birth;
        this.sns = sns;
        this.reg_date = reg_date;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o)
        {
            return true;
        }
        if (o == null || getClass() != o.getClass())
        {
            return false;
        }
        User user = (User) o;
        return id.equals(user.id) && Objects.equals(pwd, user.pwd) && Objects.equals(name, user.name) && Objects.equals(email, user.email) && Objects.equals(birth, user.birth) && Objects.equals(sns, user.sns);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(id, pwd, name, email, birth, sns, reg_date);
    }

}
