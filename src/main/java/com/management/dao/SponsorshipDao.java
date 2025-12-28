package com.management.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.management.model.Sponsorship;
import com.management.model.Symposium;

public class SponsorshipDao {
	static String driver = "com.mysql.jdbc.Driver";
    static String url = "jdbc:mysql://localhost:3306/symposium_management";
    static String name = "root";
    static String password = "Mk11MYSQL@2004";

    private Sponsorship formSponsorship(ResultSet rs) throws SQLException {
        Sponsorship sponsorship = new Sponsorship(
        		rs.getInt("sponsor_id"),
        	    rs.getDouble("amount"),
        	    rs.getDate("date"),
        	    rs.getInt("symp_id")
        );
        
        sponsorship.setSponsorship_id(rs.getInt("sponsorship_id"));
        return sponsorship;
    }
    
    public double totalSponsorshipsPerSymposium(int  symp_id) {
        String query = "select sum(amount) as total_sponsors from Sponsorships where symp_id = ?";

        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        double total = 0;

        try {
            Class.forName(driver);
            con = DriverManager.getConnection(url, name, password);
            ps = con.prepareStatement(query);
            
            ps.setInt(1, symp_id);

            rs = ps.executeQuery();
            if (rs.next()) {
                total = rs.getDouble("total_sponsors");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        finally {
            try { if (rs != null) rs.close(); } catch (Exception e) {}
            try { if (ps != null) ps.close(); } catch (Exception e) {}
            try { if (con != null) con.close(); } catch (Exception e) {}
        }

        return total;
    }

    public List<Sponsorship> getAllSponsorship() {
        String query = "select * from Sponsorships";

        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        List<Sponsorship> sponsorships = new ArrayList<>();

        try {
            Class.forName(driver);
            con = DriverManager.getConnection(url, name, password);
            ps = con.prepareStatement(query);
            
            rs = ps.executeQuery();

            while (rs.next()) {
                sponsorships.add(formSponsorship(rs));
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        finally {
            try { if (rs != null) rs.close(); } catch (Exception e) {}
            try { if (ps != null) ps.close(); } catch (Exception e) {}
            try { if (con != null) con.close(); } catch (Exception e) {}
        }

        return sponsorships;
    }
    
    public List<Sponsorship> getBySponsorId(int sponsor_id) {
        String query = "select * from Sponsorships where sponsor_id = ?";

        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        List<Sponsorship> sponsorships = new ArrayList<>();

        try {
            Class.forName(driver);
            con = DriverManager.getConnection(url, name, password);
            ps = con.prepareStatement(query);
            
            ps.setInt(1, sponsor_id);
            rs = ps.executeQuery();

            while (rs.next()) {
                sponsorships.add(formSponsorship(rs));
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        finally {
            try { if (rs != null) rs.close(); } catch (Exception e) {}
            try { if (ps != null) ps.close(); } catch (Exception e) {}
            try { if (con != null) con.close(); } catch (Exception e) {}
        }

        return sponsorships;
    }
    
    public List<Sponsorship> getBySympId(int symp_id) {
        String query = "select * from Sponsorships where symp_id = ?";

        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        List<Sponsorship> sponsorships = new ArrayList<>();

        try {
            Class.forName(driver);
            con = DriverManager.getConnection(url, name, password);
            ps = con.prepareStatement(query);
            
            ps.setInt(1, symp_id);
            rs = ps.executeQuery();

            while (rs.next()) {
                sponsorships.add(formSponsorship(rs));
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        finally {
            try { if (rs != null) rs.close(); } catch (Exception e) {}
            try { if (ps != null) ps.close(); } catch (Exception e) {}
            try { if (con != null) con.close(); } catch (Exception e) {}
        }

        return sponsorships;
    }
    
    public List<Sponsorship> getSponsorshipsByDept(int deptId) {
	    String query = "select * from Sponsorships";

	    Connection con = null;
	    PreparedStatement ps = null;
	    ResultSet rs = null;
	    List<Sponsorship> sponsorships = new ArrayList<>();

	    SymposiumDao symposiumDao = new SymposiumDao();

	    try {
	        Class.forName(driver);
	        con = DriverManager.getConnection(url, name, password);
	        ps = con.prepareStatement(query);

	        rs = ps.executeQuery();
	        while (rs.next()) {
	            int sympId = rs.getInt("symp_id");

	            Symposium symp = symposiumDao.getSymposiumById(sympId);
	            if (symp != null && symp.getDept_id() == deptId) {
	                Sponsorship s = new Sponsorship(rs.getInt("sponsor_id"), rs.getDouble("amount"), rs.getDate("sponsorship_date"), sympId);
	                s.setSponsorship_id(rs.getInt("sponsorship_id"));
	                sponsorships.add(s);
	            }
	        }

	    } catch (Exception e) {
	        e.printStackTrace();
	    } finally {
	        try { if (rs != null) rs.close(); } catch (Exception e) {}
	        try { if (ps != null) ps.close(); } catch (Exception e) {}
	        try { if (con != null) con.close(); } catch (Exception e) {}
	    }
	    
	    return sponsorships;
	}

    public Sponsorship getSponsorshipById(int sponsorship_id) {
        String query = "select * from Sponsorships where sponsorship_id = ?";

        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        Sponsorship sponsorship = null;

        try {
            Class.forName(driver);
            con = DriverManager.getConnection(url, name, password);
            ps = con.prepareStatement(query);
            
            ps.setInt(1, sponsorship_id);
            rs = ps.executeQuery();

            if (rs.next()) {
                sponsorship = formSponsorship(rs);
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        finally {
            try { if (rs != null) rs.close(); } catch (Exception e) {}
            try { if (ps != null) ps.close(); } catch (Exception e) {}
            try { if (con != null) con.close(); } catch (Exception e) {}
        }

        return sponsorship;
    }

    public Sponsorship addSponsorship(Sponsorship sponsorship) {
        String query = "insert into Sponsorships (sponsor_id, symp_id, amount, date) values (?, ?, ?, ?)";

        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            Class.forName(driver);
            con = DriverManager.getConnection(url, name, password);
            ps = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

            ps.setInt(1, sponsorship.getSponsor_id());
            ps.setInt(2, sponsorship.getSymp_id());
            ps.setDouble(3, sponsorship.getAmount());
            ps.setDate(4, new java.sql.Date(sponsorship.getSponsorship_date().getTime()));

            int row = ps.executeUpdate();
            
            if (row > 0) {
                rs = ps.getGeneratedKeys();
                if (rs.next()) {
                    sponsorship.setSponsorship_id(rs.getInt(1));
                }
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            try { if (rs != null) rs.close(); } catch (Exception e) {}
            try { if (ps != null) ps.close(); } catch (Exception e) {}
            try { if (con != null) con.close(); } catch (Exception e) {}
        }

        return sponsorship;
    }

    public boolean updateSponsorship(Sponsorship sponsorship) {
        String query = "update Sponsorships set sponsor_id = ?, symp_id = ?, amount = ?, date = ? where sponsorship_id = ?";

        Connection con = null;
        PreparedStatement ps = null;

        try {
            Class.forName(driver);
            con = DriverManager.getConnection(url, name, password);
            ps = con.prepareStatement(query);

            ps.setInt(1, sponsorship.getSponsor_id());
            ps.setInt(2, sponsorship.getSymp_id());
            ps.setDouble(3, sponsorship.getAmount());
            ps.setDate(4, new java.sql.Date(sponsorship.getSponsorship_date().getTime()));
            ps.setInt(5, sponsorship.getSponsorship_id());

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

    public boolean removeSponsorship(int sponsorship_id) {
        String query = "delete from Sponsorships where sponsorship_id = ?";

        Connection con = null;
        PreparedStatement ps = null;

        try {
            Class.forName(driver);
            con = DriverManager.getConnection(url, name, password);
            ps = con.prepareStatement(query);
            
            ps.setInt(1, sponsorship_id);

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
