import com.github.budgettime.database.Database;
import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DatabaseConnectionTest {

    @Test
    public void testConnectToDatabaseWithoutException() throws SQLException {
        final Database   database   = new Database();
        final Connection connection = database.getConnection();
    }

    @Test
    public void testConnectToDatabaseWithoutException2() throws SQLException {
        String dbUri      = System.getenv("BT_DB_URI");
        String dbUsername = System.getenv("BT_DB_USERNAME");
        String dbPassword = System.getenv("BT_DB_PASSWORD");

        Connection con = DriverManager.getConnection(dbUri, dbUsername, dbPassword);
    }

    @Test
    public void testRunQueryGetData() throws SQLException {
        final Database   database   = new Database();
        final Connection connection = database.getConnection();

        final PreparedStatement preparedStatement = connection.prepareStatement("SELECT username, password FROM accounts");
        final ResultSet         rs                = preparedStatement.executeQuery();
        while (rs.next()) {
            String name = rs.getString("username");
            String pw   = rs.getString("password");
            System.out.println(name + "   " + pw);
        }
    }
}
