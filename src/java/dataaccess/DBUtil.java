package dataaccess;

import java.sql.*;

public class DBUtil {
    public static void closePreparedStatement(Statement ps) {
        try {
            if (ps != null) {
                ps.close();
            }
        } 
        catch (SQLException e) {
           
        }
    }

    public static void closeResultSet(ResultSet rs) {
        try {
            if (rs != null) {
                rs.close();
            }
        } 
        catch (SQLException e) {
         
        }
    }
}
