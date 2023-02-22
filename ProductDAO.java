import java.sql.Statement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO implements Repository<Product, Integer> {

    public static ProductDAO getInstance() {
        return new ProductDAO();
    }

    @Override
    public Integer add(Product item) {
        try {
            // Bước 1: Tạo kết nối
            Connection connection = MySQLconnUtils.getConnection();
            // Bước 2: Tạo đối tượng statement
            Statement st = connection.createStatement();
            // Bước 3: Thực thi câu lênh SQL
            String sql = "INSERT INTO product(id, name, price)"
                    + "VALUES ('" + item.getId() + "' , '" + item.getName() + "' , " + item.getPrice() + ")";

            int result = st.executeUpdate(sql);

            // Bước 4: xử lý kết quả
            System.out.println("Bạn đã thực thi : " + sql);
            System.out.println("Số dòng vừa thêm: " + result);

            // Bước 5 ngắt kết nối
            MySQLconnUtils.closeConnection(connection);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();

        }
        return 0;
    }

    @Override
    public List<Product> readAll() {
        List<Product> list = new ArrayList<Product>();
        try {
            Connection connection = MySQLconnUtils.getConnection();
            Statement st = connection.createStatement();
            String sql = "SELECT * FROM product";
            st.executeQuery(sql);
            while (st.getResultSet().next()) {
                Product pro = new Product();
                pro.setId(st.getResultSet().getInt("id"));
                pro.setName(st.getResultSet().getString("name"));
                pro.setPrice(st.getResultSet().getInt("price"));
                list.add(pro);
            }

            MySQLconnUtils.closeConnection(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public Product read(Integer id) {
        Product pro = new Product();
        try {

            Connection connection = MySQLconnUtils.getConnection();
            Statement st = connection.createStatement();
            String sql = "SELECT * FROM product WHERE id = " + id;
            st.executeQuery(sql);

            while (st.getResultSet().next()) {

                pro.setId(st.getResultSet().getInt("id"));
                pro.setName(st.getResultSet().getString("name"));
                pro.setPrice(st.getResultSet().getInt("price"));
            }
            MySQLconnUtils.closeConnection(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return pro;
    }

    @Override
    public boolean update(Product item) {
        try {
            Connection connection = MySQLconnUtils.getConnection();
            Statement st = connection.createStatement();
            // check id
            if (read(item.getId()) == null) {
                return false;
            }
            String sql = "UPDATE product SET name = '" + item.getName() + "', price = " + item.getPrice()
                    + " WHERE id = " + item.getId();
            st.executeUpdate(sql);
            MySQLconnUtils.closeConnection(connection);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean delete(Integer id) {
        try {
            if (read(id) == null) {
                return false;
            }
            // check id
            Connection connection = MySQLconnUtils.getConnection();
            Statement st = connection.createStatement();
            String sql = "DELETE FROM product WHERE id = " + id;
            st.executeUpdate(sql);
            MySQLconnUtils.closeConnection(connection);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
