
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

/**
 *
 * @author Jeremia Klein
 */

    public class Login extends HttpServlet{
    
  
    public void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        PrintWriter out = response.getWriter();
        String ticketNumber = request.getParameter("ticketNumber");
        String lastName = request.getParameter("lastName");
        String pid = json.findPid(ticketNumber);
    
    
        //final user check.    
        if (MySQL.checkUser(ticketNumber, lastName) && json.checkLastname(lastName, pid) && json.checkTicket(ticketNumber)) {
            RequestDispatcher rs = request.getRequestDispatcher("Welcome.java");
            rs.forward(request, response);

        } else {
            out.println("<link rel='stylesheet' href='Captiveportal.css' type='text/css'>");
            out.println("<div class=\"error\">Inlog credentials are not correct, or the ticketnumber has already been used.</div>");
            RequestDispatcher rs = request.getRequestDispatcher("index.html");
            rs.include(request, response);
     }
    }
    }
