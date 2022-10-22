package repository.impl;

import entity.User;
import repository.UserRepository;
import repository.impl.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserConnection implements UserRepository {

    private Connection con;
    private PreparedStatement pstmt;
    private ResultSet rs;

    @Override
    public List<User> findAll() {
        String sql = "SELECT user_id, user_name as student_name FROM user";
        try{
            con = DBConnection.init();
            pstmt = con.prepareStatement(sql);
            rs = pstmt.executeQuery();
            List<User> userList = new ArrayList<>();
            while(rs.next()){
                User user = new User();
                user.setName(rs.getString("student_name"));
                userList.add(user);
            }
            return userList;
        }catch (SQLException e){
            e.printStackTrace();
        } finally {
            try{
                con.close();
                pstmt.close();
            }catch (SQLException e){
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    public User findById(long id) {
        String sql = "SELECT * FROM user WHERE id=?";
        try{
            con = DBConnection.init();
            pstmt = con.prepareStatement(sql);
            pstmt.setLong(1, id);
            rs = pstmt.executeQuery();
            if(rs.next()){
                User user = new User();
                user.setId((int) rs.getLong("id"));
                user.setName(rs.getString("name"));
                return user;
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            try{
                con.close();
                pstmt.close();
            }catch (SQLException e){
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    public List<User> findByName(String name) {
        String sql = "SELECT user_id, user_name as 'User Name' FROM user WHERE user_name LIKE ?";
        try{
            con = DBConnection.init();
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, "%" + name + "%");
            rs = pstmt.executeQuery();
            List<User> studentList = new ArrayList<>();
            while(rs.next()){
                User user = new User();
                user.setId((int) rs.getLong("id"));
                user.setName(rs.getString("user_name"));
                studentList.add(user);
            }
            return studentList;
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            try{
                con.close();
                pstmt.close();
            }catch (SQLException e){
                e.printStackTrace();
            }
        }
        return null;
    }
}
