package Config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

public class config {
    public Connection connect;

    // Constructor to connect to the database
    public config(){
        try{
            connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/brg_blot", "root", "");
        }catch(SQLException ex){
            System.out.println("Can't connect to database: " + ex.getMessage());
        }
    }

    // Function to save data
    public int insertData(String sql){
        int result;
        try{
            PreparedStatement pst = connect.prepareStatement(sql);
            pst.executeUpdate();
            System.out.println("Inserted Successfully!");
            pst.close();
            result = 1;
        }catch(SQLException ex){
            System.out.println("Connection Error: " + ex);
            result = 0;
        }
        return result;
    }

    // Function to retrieve data
    public ResultSet getData(String sql) throws SQLException{
        Statement stmt = connect.createStatement();
        ResultSet rst = stmt.executeQuery(sql);
        return rst;
    }

    // Function to update data
    public void updateData(String sql){
        try{
            PreparedStatement pst = connect.prepareStatement(sql);
            int rowsUpdated = pst.executeUpdate();
            if(rowsUpdated > 0){
                JOptionPane.showMessageDialog(null, "Data Updated Successfully!");
            }else{
                System.out.println("Data Update Failed!");
            }
            pst.close();
        }catch(SQLException ex){
            System.out.println("Connection Error: " + ex);
        }
    }

    // Function to delete data
    public void deleteData(String sql){
        try{
            PreparedStatement pst = connect.prepareStatement(sql);
            int rowsDeleted = pst.executeUpdate();
            if(rowsDeleted > 0){
                JOptionPane.showMessageDialog(null, "Data Deleted Successfully!");
            }else{
                System.out.println("Data Deletion Failed!");
            }
            pst.close();
        }catch(SQLException ex){
            System.out.println("Connection Error: " + ex);
        }
    }
}


