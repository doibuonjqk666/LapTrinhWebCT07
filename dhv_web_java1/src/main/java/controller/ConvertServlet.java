package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ConvertServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=UTF-8");
        
        try {
            String kmStr = request.getParameter("km");
            double km = Double.parseDouble(kmStr);
            double miles = km * 0.621371;
            
            PrintWriter out = response.getWriter();
            out.print("{\"km\":\"" + km + "\",\"miles\":" + String.format("%.5f", miles) + "}");
            out.close();
        } catch (Exception e) {
            response.setStatus(400);
            PrintWriter out = response.getWriter();
            out.print("{\"error\":\"Invalid input\"}");
            out.close();
        }
    }
}
