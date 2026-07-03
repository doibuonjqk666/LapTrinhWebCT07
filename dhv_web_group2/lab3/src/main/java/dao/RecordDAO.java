package dao;

import context.DBContext1;
import model.Record;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class RecordDAO {

    public RecordDAO() {
    }

    // ---------------- GET ALL ----------------
    public List<Record> getAllRecords() {

    List<Record> list = new ArrayList<>();

    String sql = """
            SELECT id,
                   stname,
                   course,
                   fee                   
            FROM records
            """;

    try (
            Connection conn = new DBContext1().getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery()
    ) {

        while (rs.next()) {

            Record p = new Record(
                    rs.getInt("id"),
                    rs.getString("stname"),
                    rs.getString("course"),
                    rs.getInt("fee")
                   
            );

            list.add(p);
        }

    } catch (Exception e) {
        e.printStackTrace();
    }

    return list;
}

    // ---------------- INSERT ----------------
    public boolean insertRecord(String stname, String course, int fee) {
        String sql = "INSERT INTO records(stname, course, fee) VALUES(?,?,?)";
        try (
                Connection conn = new DBContext1().getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)
        ) {
            ps.setString(1, stname);
            ps.setString(2, course);
            ps.setInt(3, fee);
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    // ---------------- DELETE ----------------
    public boolean deleteRecord(int id) {
        String sql = "DELETE FROM records WHERE id = ?";
        try (
                Connection conn = new DBContext1().getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)
        ) {
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    // ---------------- GET BY ID ----------------
    public Record getRecordById(int id) {
        String sql = "SELECT id, stname, course, fee FROM records WHERE id = ?";
        try (
                Connection conn = new DBContext1().getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)
        ) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Record(
                            rs.getInt("id"),
                            rs.getString("stname"),
                            rs.getString("course"),
                            rs.getInt("fee")
                    );
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    // ---------------- UPDATE ----------------
    public boolean updateRecord(int id, String stname, String course, int fee) {
        String sql = "UPDATE records SET stname = ?, course = ?, fee = ? WHERE id = ?";
        try (
                Connection conn = new DBContext1().getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)
        ) {
            ps.setString(1, stname);
            ps.setString(2, course);
            ps.setInt(3, fee);
            ps.setInt(4, id);
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

   
}