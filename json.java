
import java.io.*;
import java.net.*;


/**
 *
 * @author Jeremia Klein
 *
 */
public class json {

    protected static boolean checkTicket(String ticketNumber) throws IOException {
        try {
            final URL url = new URL("http://fys.securidoc.nl/Ticket");
            final String request = "{\n" + "\"function\":\"List\",  \n"
                    +"\"teamId\": \"IN104-5\", \n" + " \"teamKey\": \"26b2fd690864f66eb5e7d38d71f9f9d5\","
                    + "\"resquestId\" : \"42\"  \n" + "}";

            //maakt een connectie
            URLConnection urlc = url.openConnection();

            //voer post uit
            urlc.setDoOutput(true);
            urlc.setAllowUserInteraction(false);

            //Verstuurd de query
            PrintStream ps = new PrintStream(urlc.getOutputStream());
            ps.print(request);
            ps.close();

            //krijg resultaat
            BufferedReader br = new BufferedReader(new InputStreamReader(urlc
                    .getInputStream()));

            String list = null;
            String[] parts = null;
            String Check = ("\"ticketNumber\":\"" + ticketNumber + "\"");

            //splitsen van string in de array 
            while ((list = br.readLine()) != null) {
                parts = list.split(",");
            }

            for (int i = 0; i < parts.length; i++) {
                if (Check.equals(parts[i])) {

                    return true;
                }
            }
        } catch (IOException e) {
        }

        return false;
    }

    protected static String findPid(String ticketnumber) throws IOException {

        try {
            final URL url = new URL("http://fys.securidoc.nl/Ticket");
            final String request = "{\n" + "\"function\":\"List\",  \n"
                    + "\"teamId\": \"IN104-5\", \n" + " \"teamKey\": \"26b2fd690864f66eb5e7d38d71f9f9d5\","
                    + "\"resquestId\" : \"42\"  \n" + "}";

            //maakt een connectie
            URLConnection urlc = url.openConnection();

            //voer post uit
            urlc.setDoOutput(true);
            urlc.setAllowUserInteraction(false);

            //Verstuurd de query
            PrintStream ps = new PrintStream(urlc.getOutputStream());
            ps.print(request);
            ps.close();

            //krijg resultaat
            BufferedReader br = new BufferedReader(new InputStreamReader(urlc
                    .getInputStream()));

            String list = null;
            String[] parts = null;
            String Check = ("\"ticketNumber\":\"" + ticketnumber + "\"");
            String Pid[]=null;

            //splitsen van string in de array 
            while ((list = br.readLine()) != null) {
                parts = list.split(",");
            }

            //Test of het ticketnummer al in de lijst staat
            for (int i = 0; i < parts.length; i++) {
                if (Check.equals(parts[i])) {
                    ticketnumber = parts[i + 1];
                    Pid = ticketnumber.split("\"pid\":");
                    ticketnumber = Pid[1];                    
                    return ticketnumber;
                }
            }
        } catch (IOException e) {
        }
        String error = "Er is iets misgegaan.";
        return error;
    }

    protected static boolean checkLastname(String lastName, String pid) throws IOException {
        try {
            final URL url = new URL("http://fys.securidoc.nl/Passenger");
            final String request = "{\n" + "\"function\":\"List\",  \n"
                    + "\"teamId\": \"IN104-5\", \n" + " \"teamKey\": \"26b2fd690864f66eb5e7d38d71f9f9d5\","
                    + "\"resquestId\" : \"42\"  \n" + "}";

            //maakt een connectie
            URLConnection urlc = url.openConnection();

            //voer post uit
            urlc.setDoOutput(true);
            urlc.setAllowUserInteraction(false);

            //Verstuurd de query
            PrintStream ps = new PrintStream(urlc.getOutputStream());
            ps.print(request);
            ps.close();

            //krijg resultaat
            BufferedReader br = new BufferedReader(new InputStreamReader(urlc.getInputStream()));

            String list = null;
            String[] parts = null;
            String CheckLastName = ("\"lastName\":\"" + lastName + "\"");
            String CheckPid = (pid+":[\"pid\":"+pid);

            //splitsen van string in de array 
            while ((list = br.readLine()) != null) {
                parts = list.split(",");
            }
            
            for (int i = 0; i < parts.length; i++) {
                
                if (CheckPid.equals(parts[i])) {
                    
                    if (CheckLastName.equalsIgnoreCase(parts[i+2])) {
                        return true;
                    }
                }
            }
        }
        catch (IOException e) {
        }

        return false;
    }

}
