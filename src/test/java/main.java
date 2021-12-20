import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class main {
    public static void main(String[] args) throws SQLException {
        Connection connection=null;

        String url = "jdbc:postgresql://34.116.212.97:5432/postgres";
        String username = "postgres";
        String password = "redteamdbpass";

        connection= DriverManager.getConnection(url,username,password);


        if(connection!=null){
            System.out.println("успех");
            System.out.println(connection.toString());
        }else{
            System.out.println("пили еще");
        }
    }
}
