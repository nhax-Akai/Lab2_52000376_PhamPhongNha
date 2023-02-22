import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQLconnUtils {

    public static Connection getConnection() {
        Connection conn=null;

        try {
            //Đăng ký MySQL
            DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());

            //Các thông số
            String url="jdbc:mysql://localhost:3306/product_management";
            String username="root";
            String password="";

            //Tạo kết nối
            conn=DriverManager.getConnection(url, username, password);


        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return conn;

    }
    public static void closeConnection(Connection conn) {
        try {
            if(conn!=null) {
                conn.close();
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
