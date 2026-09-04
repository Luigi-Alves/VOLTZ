import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

    private static final String URL = "jdbc:oracle:thin:@oracle.fiap.com.br:1521:orcl";
    // É necessario colocar o RM e senha do aluno
    // Foi utilizado o DBrever e o proprio IDEA para os testes com o banco de dados
    private static final String USER = "RM565639";
    private static final String PASSWORD = "859674";

    public static Connection getConnection() throws SQLException {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
        } catch (ClassNotFoundException e) {
            throw new SQLException("Driver Oracle JDBC (ojdbc) nao encontrado no classpath.", e);
        }
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
