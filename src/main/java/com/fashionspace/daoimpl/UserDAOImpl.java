package com.fashionspace.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.fashionspace.dao.UserDAO;
import com.fashionspace.model.User;
import com.fashionspace.util.DBConnection;

public class UserDAOImpl implements UserDAO {

    private Connection connection;

    public UserDAOImpl() {

        try {

            connection = DBConnection.getConnection();

        } catch (Exception e) {

            e.printStackTrace();
        }
    }

    @Override
    public boolean registerUser(User user) {

        boolean status = false;

        try {

            String query =
                    "insert into users(full_name,email,phone,password,gender,address) values(?,?,?,?,?,?)";

            PreparedStatement ps =
                    connection.prepareStatement(query);

            ps.setString(1, user.getFullName());
            ps.setString(2, user.getEmail());
            ps.setString(3, user.getPhone());
            ps.setString(4, user.getPassword());
            ps.setString(5, user.getGender());
            ps.setString(6, user.getAddress());

            int rows = ps.executeUpdate();

            if (rows > 0) {

                status = true;
            }

        } catch (Exception e) {

            e.printStackTrace();
        }

        return status;
    }

    @Override
    public User loginUser(
            String email,
            String password) {

        User user = null;

        try {

            String query =
                    "select * from users where email=? and password=?";

            PreparedStatement ps =
                    connection.prepareStatement(query);

            ps.setString(1, email);
            ps.setString(2, password);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                user = new User();

                user.setUserId(rs.getInt("user_id"));
                user.setFullName(rs.getString("full_name"));
                user.setEmail(rs.getString("email"));
                user.setPhone(rs.getString("phone"));
                user.setPassword(rs.getString("password"));
                user.setGender(rs.getString("gender"));
                user.setAddress(rs.getString("address"));
            }

        } catch (Exception e) {

            e.printStackTrace();
        }

        return user;
    }

    @Override
    public User getUserById(int userId) {

        User user = null;

        try {

            String query =
                    "select * from users where user_id=?";

            PreparedStatement ps =
                    connection.prepareStatement(query);

            ps.setInt(1, userId);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                user = new User();

                user.setUserId(rs.getInt("user_id"));
                user.setFullName(rs.getString("full_name"));
                user.setEmail(rs.getString("email"));
                user.setPhone(rs.getString("phone"));
                user.setPassword(rs.getString("password"));
                user.setGender(rs.getString("gender"));
                user.setAddress(rs.getString("address"));
            }

        } catch (Exception e) {

            e.printStackTrace();
        }

        return user;
    }

    @Override
    public boolean updateUser(User user) {

        boolean status = false;

        try {

            String query =
                    "update users set full_name=?, phone=?, gender=?, address=? where user_id=?";

            PreparedStatement ps =
                    connection.prepareStatement(query);

            ps.setString(1, user.getFullName());
            ps.setString(2, user.getPhone());
            ps.setString(3, user.getGender());
            ps.setString(4, user.getAddress());
            ps.setInt(5, user.getUserId());

            int rows = ps.executeUpdate();

            if (rows > 0) {

                status = true;
            }

        } catch (Exception e) {

            e.printStackTrace();
        }

        return status;
    }

    @Override
    public boolean deleteUser(int userId) {

        boolean status = false;

        try {

            String query =
                    "delete from users where user_id=?";

            PreparedStatement ps =
                    connection.prepareStatement(query);

            ps.setInt(1, userId);

            int rows = ps.executeUpdate();

            if (rows > 0) {

                status = true;
            }

        } catch (Exception e) {

            e.printStackTrace();
        }

        return status;
    }

    @Override
    public boolean isEmailExists(String email) {

        boolean status = false;

        try {

            String query =
                    "select * from users where email=?";

            PreparedStatement ps =
                    connection.prepareStatement(query);

            ps.setString(1, email);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                status = true;
            }

        } catch (Exception e) {

            e.printStackTrace();
        }

        return status;
    }

    @Override
    public boolean updatePassword(
            String email,
            String newPassword) {

        boolean status = false;

        try {

            String query =
                    "update users set password=? where email=?";

            PreparedStatement ps =
                    connection.prepareStatement(query);

            ps.setString(1, newPassword);
            ps.setString(2, email);

            int rows = ps.executeUpdate();

            if (rows > 0) {

                status = true;
            }

        } catch (Exception e) {

            e.printStackTrace();
        }

        return status;
    }
}