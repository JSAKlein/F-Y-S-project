import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class Welcome extends HttpServlet {

    
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        String contextPath = "http://192.168.42.1:8080/process.php";
        response.sendRedirect(response.encodeRedirectURL(contextPath));

    }

}
