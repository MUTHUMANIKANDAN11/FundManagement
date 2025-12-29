package com.management.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.management.model.Sponsor;

public class SponsorDao {
	static String driver = "com.mysql.jdbc.Driver";
    static String url = "jdbc:mysql://localhost:3306/symposium_management";
    static String name = "root";
    static String password = "Mk11MYSQL@2004";

    private Sponsor formSponsor(ResultSet rs) throws SQLException {
        Sponsor sponsor = new Sponsor(rs.getString("name"), rs.getString("contact_info"));
        sponsor.setSponsor_id(rs.getInt("sponsor_id"));
        sponsor.setState(rs.getBoolean("state"));
        
        return sponsor;
    }
    
    public List<Sponsor> getAllSponsors(int dept_id) {
        String query = "select * from Sponsors where dept_id = ?";
        
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        List<Sponsor> sponsors = new ArrayList<>();

        try {
            Class.forName(driver);
            con = DriverManager.getConnection(url, name, password);
            ps = con.prepareStatement(query);
            
            ps.setInt(1, dept_id);
            rs = ps.executeQuery();

            while (rs.next()) {
                sponsors.add(formSponsor(rs));
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            try { if (rs != null) rs.close(); } catch (Exception e) {}
            try { if (ps != null) ps.close(); } catch (Exception e) {}
            try { if (con != null) con.close(); } catch (Exception e) {}
        }
        return sponsors;
    }
    
    public List<Sponsor> getByDeptId(int dept_id) {
        String query = "select * from Sponsors where dept_id = ? and state = true";
        
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        List<Sponsor> sponsors = new ArrayList<>();

        try {
            Class.forName(driver);
            con = DriverManager.getConnection(url, name, password);
            ps = con.prepareStatement(query);
            
            ps.setInt(1, dept_id);
            rs = ps.executeQuery();

            while (rs.next()) {
                sponsors.add(formSponsor(rs));
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            try { if (rs != null) rs.close(); } catch (Exception e) {}
            try { if (ps != null) ps.close(); } catch (Exception e) {}
            try { if (con != null) con.close(); } catch (Exception e) {}
        }
        return sponsors;
    }
    
    public Sponsor getSponsorById(int sponsor_id) {
        String query = "select * from Sponsors where sponsor_id = ?";
        
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        Sponsor sponsor = null;

        try {
            Class.forName(driver);
            con = DriverManager.getConnection(url, name, password);
            ps = con.prepareStatement(query);
            
            ps.setInt(1, sponsor_id);
            rs = ps.executeQuery();
            
            if (rs.next()) {
                sponsor = formSponsor(rs);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            try { if (rs != null) rs.close(); } catch (Exception e) {}
            try { if (ps != null) ps.close(); } catch (Exception e) {}
            try { if (con != null) con.close(); } catch (Exception e) {}
        }
        return sponsor;
    }
    
    public List<Sponsor> sponsorsByKeyword(String keyword) {
        String query = "select * from Sponsors where name like ? or contact_info like ?";
        
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        List<Sponsor> sponsors = new ArrayList<>();
        
        try {
            Class.forName(driver);
            con = DriverManager.getConnection(url, name, password);
            ps = con.prepareStatement(query);
            
            String canKeyword = "%" + keyword + "%";
            ps.setString(1, canKeyword);
            ps.setString(2, canKeyword);
            
            rs = ps.executeQuery();
            
            while (rs.next()) {
                sponsors.add(formSponsor(rs));
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            try { if (rs != null) rs.close(); } catch (Exception e) {}
            try { if (ps != null) ps.close(); } catch (Exception e) {}
            try { if (con != null) con.close(); } catch (Exception e) {}
        }
        return sponsors;
    }


    public Sponsor addSponsor(Sponsor sponsor) {
        String query = "insert into Sponsors (name, contact_info) values (?, ?)";
        
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        try {
            Class.forName(driver);
            con = DriverManager.getConnection(url, name, password);
            ps = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

            ps.setString(1, sponsor.getName());
            ps.setString(2, sponsor.getContact_info());

            int row = ps.executeUpdate();
            if(row > 0) {
                rs = ps.getGeneratedKeys();
                if(rs.next()) {
                    sponsor.setSponsor_id(rs.getInt(1));
                }
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            try { if (rs != null) rs.close(); } catch (Exception e) {}
            try { if (ps != null) ps.close(); } catch (Exception e) {}
            try { if (con != null) con.close(); } catch (Exception e) {}
        }
        return sponsor;
    }

    public boolean updateSponsor(Sponsor sponsor) {
        String query = "update Sponsors set name = ?, contact_info = ? where sponsor_id = ?";
        
        Connection con = null;
        PreparedStatement ps = null;

        try {
            Class.forName(driver);
            con = DriverManager.getConnection(url, name, password);
            ps = con.prepareStatement(query);

            ps.setString(1, sponsor.getName());
            ps.setString(2, sponsor.getContact_info());
            ps.setInt(3, sponsor.getSponsor_id());

            int row = ps.executeUpdate();
            if (row > 0) return true;
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            try { if (ps != null) ps.close(); } catch (Exception e) {}
            try { if (con != null) con.close(); } catch (Exception e) {}
        }
        return false;
    }
    
    public boolean deactivateSponsor(int sponsor_id) {
        String query = "update sponsors set state = false where sponsor_id = ?";
        
        Connection con = null;
        PreparedStatement ps = null;

        try {
            Class.forName(driver);
            con = DriverManager.getConnection(url, name, password);
            ps = con.prepareStatement(query);

            ps.setInt(1, sponsor_id);

            int row = ps.executeUpdate();
            if (row > 0) return true;
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            try { if (ps != null) ps.close(); } catch (Exception e) {}
            try { if (con != null) con.close(); } catch (Exception e) {}
        }
        return false;
    }

    public boolean removeSponsor(int sponsor_id) {
        String query = "delete from Sponsors where sponsor_id = ?";
        
        Connection con = null;
        PreparedStatement ps = null;

        try {
            Class.forName(driver);
            con = DriverManager.getConnection(url, name, password);
            ps = con.prepareStatement(query);

            ps.setInt(1, sponsor_id);

            int row = ps.executeUpdate();
            if (row > 0) return true;
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            try { if (ps != null) ps.close(); } catch (Exception e) {}
            try { if (con != null) con.close(); } catch (Exception e) {}
        }
        return false;
    }
}
