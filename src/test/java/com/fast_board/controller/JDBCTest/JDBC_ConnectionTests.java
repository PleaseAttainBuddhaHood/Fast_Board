package com.fast_board.controller.JDBCTest;

import com.fast_board.domain.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "file:src/main/webapp/WEB-INF/spring/**/root-context.xml")
public class JDBC_ConnectionTests
{
    @Autowired
    DataSource ds;

    @Test
    public void insertTest() throws Exception
    {
        User user = new User("asdf4", "1234", "abc", "aaaa@aaa.com",
                              new Date(), "fb", new Date());

        deleteAll();

        int rowCnt = insertUser(user);

        assertEquals(1, rowCnt);
    }

    @Test
    public void selectUserTest() throws Exception
    {
        User user = selectUser("asdf");
    }

    public User selectUser(String id) throws Exception
    {
        Connection conn = ds.getConnection();

        String sql = "select * from user_info where id = ?";

        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, id);

        ResultSet rs = pstmt.executeQuery();

        if(rs.next())
        {
            User user = new User();

            user.setId(rs.getString(1));
            user.setPwd(rs.getString(2));
            user.setName(rs.getString(3));
            user.setEmail(rs.getString(4));
            user.setBirth(new Date(rs.getDate(5).getTime()));
            user.setSns(rs.getString(6));
            user.setReg_date(new Date(rs.getTimestamp(7).getTime()));

            return user;
        }

        return null;
    }

    private void deleteAll() throws Exception
    {
        Connection conn = ds.getConnection();

        String sql = "delete from user_info";

        PreparedStatement pstmt = conn.prepareStatement(sql);

        pstmt.executeUpdate();
    }

    public int insertUser(User user) throws Exception
    {
        Connection conn = ds.getConnection();

        String sql = "insert into user_info values(?, ?, ? ,?, ?, ?, now())";

        PreparedStatement pstmt = conn.prepareStatement(sql);

        pstmt.setString(1, user.getId());
        pstmt.setString(2, user.getPwd());
        pstmt.setString(3, user.getName());
        pstmt.setString(4, user.getEmail());
        pstmt.setDate(5, new java.sql.Date(user.getBirth().getTime()));
        pstmt.setString(6, user.getSns());

        return pstmt.executeUpdate();
    }

    @Test
    public void DBTest() throws Exception
    {
        Connection conn = ds.getConnection();

        System.out.println("conn = " + conn);
        assertNotNull(conn);
    }
}
