/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Context.DBContext;
import Entity.Digital;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author nguye
 */
public class DigitalDAO {

    public Digital getTop1() throws Exception {
        DBContext db = new DBContext();
        // connect sql server
        Connection conn = null;
        // mmove statement to sql server
        PreparedStatement ps = null;
        // receive result return
        ResultSet rs = null;
        try {
            //Select a post is newest
            String query = "select * from Article where [time] = (\n"
                    + "select max([time]) from Article)";
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            // = excute in sql
            rs = ps.executeQuery();
            // take data every collumn in table Article
            while (rs.next()) {
                Digital d = new Digital(
                        rs.getInt("id"),
                        rs.getString("title"),
                        rs.getString("content"),
                        rs.getString("description"),
                        rs.getString("image"),
                        rs.getTimestamp("time"),
                        rs.getString("author"));
                return d;
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        } // close database connection to sql
        finally {
            db.closeConnection(rs, ps, conn);
        }
        return null;
    }

    public List<Digital> getTop5() throws Exception {
        DBContext db = new DBContext();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            /*select top 5 news are with time decrease except Latest news */
            String query = "select top 5 * from Article where [time] not in(\n"
                    + "select max([time]) from Article) \n"
                    + "order by [time] desc";
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            List<Digital> list = new ArrayList<>();
            // take data every collumn in table Article
            while (rs.next()) {
                Digital d = new Digital(
                        rs.getInt("id"),
                        rs.getString("title"),
                        rs.getString("content"),
                        rs.getString("description"),
                        rs.getString("image"),
                        rs.getTimestamp("time"),
                        rs.getString("author"));
                list.add(d);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        } // close database connection to sql
        finally {
            db.closeConnection(rs, ps, conn);
        }
    }

    public int count(String txtSearch) throws Exception {
        DBContext db = new DBContext();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            /*Count all of news have title is likes inputed*/
            String query = "select COUNT(*) from Article where title like ?";
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1, "%" + txtSearch + "%");
            rs = ps.executeQuery();
            //take data every collumn in table Article
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        } // close database connection to sql
        finally {
            db.closeConnection(rs, ps, conn);
        }
        return 0;
    }

    public Digital getOne(int id) throws Exception {
        DBContext db = new DBContext();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            /*Select all of news have id inputed*/
            String query = "select * from Article where id = ?";
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            //take data every collumn in table Article
            while (rs.next()) {
                return new Digital(
                        rs.getInt("id"),
                        rs.getString("title"),
                        rs.getString("content"),
                        rs.getString("description"),
                        rs.getString("image"),
                        rs.getTimestamp("time"),
                        rs.getString("author"));
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        } // close database connection to sql
        finally {
            db.closeConnection(rs, ps, conn);
        }
        return null;
    }

    public List<Digital> search(String txtSearch, int size, int index) throws Exception {
        DBContext db = new DBContext();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            /*create a collumn named r index increase 1 to end select with all of
            collumn in Article, search follow title, select one-time 3 news as 
            one page 3 news condition time decrease*/
            String query = "select * from\n"
                    + "(select ROW_NUMBER() OVER (order by [time] desc) as row,\n"
                    + "* from Article where title like ?) as result\n"
                    + "where row between ? * ? - 2 and ? * ?";
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1, "%" + txtSearch + "%");
            ps.setInt(2, index);
            ps.setInt(3, size);
            ps.setInt(4, index);
            ps.setInt(5, size);
            rs = ps.executeQuery();
            List<Digital> list = new ArrayList<>();
            //take data every collumn in table Article
            while (rs.next()) {
                Digital d = new Digital(
                        rs.getInt("id"),
                        rs.getString("title"),
                        rs.getString("content"),
                        rs.getString("description"),
                        rs.getString("image"),
                        rs.getTimestamp("time"),
                        rs.getString("author"));
                list.add(d);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        } // close database connection to sql
        finally {
            db.closeConnection(rs, ps, conn);
        }
    }
}
