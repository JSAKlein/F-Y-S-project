
import java.io.IOException;
import java.sql.*;

/**
 *
 * @author Jeremia Klein
 */
public class MySQL {

   protected static boolean checkUser(String ticketNumber, String lastName){
       
     
        try {
            
            String pid = json.findPid(ticketNumber);
            //laden van driver
            Class.forName("com.mysql.jdbc.Driver");
            
            //maakt een connectie met de database
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ticket", "root", "root");
            
            
            PreparedStatement ps = con.prepareStatement("select * from ticketlist where ticketNumber=? and lastName=?");
            ps.setString(1, ticketNumber);
            ps.setString(2, lastName);
            ResultSet rs = ps.executeQuery();
            
            //checkt of het ticketnummer en de achternaam niet in de database staan
            if(!rs.next() && json.checkTicket(ticketNumber) && json.checkLastname(lastName, pid)) {
                ps = con.prepareStatement("insert into ticketlist(ticketNumber,lastName) values(?,?)");
                
                ps.setString(1, ticketNumber);
                ps.setString(2, lastName);
                ps.executeUpdate();
                con.close();
                return true;
            }
            
        } catch (IOException | ClassNotFoundException | SQLException e) {
        }
       return false;
    
}
}
