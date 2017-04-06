/*
 * @author Jeremia Klein
 */

import java.io.*;
import java.net.*;

public class json {

    protected static boolean checkTicket(String ticketNumber) throws IOException {
try{
        final URL url = new URL("http://XXXXXXX.XXX");
        final String query = "{\n" + "\"function\":\"List\",	\n" + "	\"teamId\": \"XXXXX\", \n" + "	\"teamKey\": \"XXXXXXXXXXXXXXX\"," + "\"requestId\" : \"1\"	\n" + "}";

        //make connection
        URLConnection urlc = url.openConnection();

        //use post mode
        urlc.setDoOutput(true);
        urlc.setAllowUserInteraction(false);

        //send query
        PrintStream ps = new PrintStream(urlc.getOutputStream());
        ps.print(query);
        ps.close();

        //get result
        BufferedReader br = new BufferedReader(new InputStreamReader(urlc
                .getInputStream()));
        
        String list = null;
        String[] parts = null;
        String Check = ("\"ticketNumber\":\"" + ticketNumber + "\"");

        //splitting String in Array.
        while ((list = br.readLine()) != null) {
            parts = list.split(",");
        }

        //Real check if ticketnumber is in list.
        for (int i = 0; i < parts.length; i++) {

            if (Check.equals(parts[i])) {
                return true;
            }

        }
        
        }catch(IOException e)
      {
      }
        return false;
    }
   
    
    protected static String findPid(String ticketnumber) throws IOException {
        try {

            final URL url = new URL("http://fys.securidoc.nl/Ticket");
            final String query = "{\n" + "\"function\":\"List\",	\n" + "	\"teamId\": \”XXXXXXX\”, \n" + "	\"teamKey\": \”XXXXXXXXXXXXX\”,” + "\"requestId\" : \"1\"	\n" + "}";

            //make connection
            URLConnection urlc = url.openConnection();

            //use post mode
            urlc.setDoOutput(true);
            urlc.setAllowUserInteraction(false);

            //send query
            PrintStream ps = new PrintStream(urlc.getOutputStream());
            ps.print(query);
            ps.close();

            //get result
            BufferedReader br = new BufferedReader(new InputStreamReader(urlc
                    .getInputStream()));

            String list = null;
            String[] parts = null;
            String Check = ("\"ticketNumber\":\"" + ticketnumber + "\"");
            String Pid[]=null;

            //splitting String in Array.
            while ((list = br.readLine()) != null) {
                parts = list.split(",");
            }

            //Real check if ticketnumber is in list and return pid.
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
        String error = "Something went wrong.";
        return error;

    }
    
     protected static boolean checkLastname(String Lastname, String pid) throws IOException {
        try {
            final URL url = new URL("http://fys.securidoc.nl/Passenger");
            final String query = "{\n" + "\"function\":\"List\",	\n" + "	\"teamId\": \”XXXXXX\”, \n" + "	\"teamKey\": \”XXXXXXXXXXXXXX\”,” + "\"requestId\" : \"1\"	\n" + "}";

            //make connection
            URLConnection urlc = url.openConnection();

            //use post mode
            urlc.setDoOutput(true);
            urlc.setAllowUserInteraction(false);

            //send query
            PrintStream ps = new PrintStream(urlc.getOutputStream());
            ps.print(query);
            ps.close();

            //get result
            BufferedReader br = new BufferedReader(new InputStreamReader(urlc
                    .getInputStream()));

            String list = null;
            String[] parts = null;
            String CheckL = ("\"lastName\":\"" + Lastname + "\"");
            String CheckP = (pid+":{\"pid\":"+pid);
        

            //splitting String in Array.
            while ((list = br.readLine()) != null) {
                parts = list.split(",");
            }

            //Real check if lastname is in list.
            for (int i = 0; i < parts.length; i++) {

                if (CheckP.equals(parts[i])) {
                  
                    if(CheckL.equalsIgnoreCase(parts[i+2])){
                       return true; 
                   }
                   
                }

            }

        } catch (IOException e) {
        }
        return false;
     }   
    
}
