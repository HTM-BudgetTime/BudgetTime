import com.github.budgettime.database.Database;
import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnectionTest {

    @Test
    public void doTest() throws SQLException {
        final Database   database   = new Database();
        final Connection connection = database.getConnection();
    }

    @Test
    public void doTest3() throws SQLException {
        String dbUri      = "jdbc:mysql://db-mysql-lon1-58063-do-user-2199254-0.db.ondigitalocean.com:25060/defaultdb?ssl-mode=REQUIRED";
        String dbUsername = System.getenv("BT_DB_USERNAME");
        String dbPassword = System.getenv("BT_DB_PASSWORD");

        Connection con = DriverManager.getConnection(dbUri, dbUsername, dbPassword);
    }
}
