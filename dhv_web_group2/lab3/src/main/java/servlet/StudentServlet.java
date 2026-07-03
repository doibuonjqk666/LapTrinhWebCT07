package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import dao.RecordDAO;
import model.Record;

@WebServlet("/student")
public class StudentServlet extends HttpServlet {

    private String buildRedirectUrl(HttpServletRequest request) {
        String proto = request.getHeader("X-Forwarded-Proto");
        String host = request.getHeader("X-Forwarded-Host");
        if (host == null || host.isEmpty()) {
            host = request.getHeader("Host");
        }
        if (proto == null || proto.isEmpty()) {
            proto = request.getScheme();
        }
        if (host != null && !host.isEmpty()) {
            return proto + "://" + host + request.getContextPath() + "/";
        }
        return request.getContextPath() + "/";
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        RecordDAO dao = new RecordDAO();
        if ("delete".equalsIgnoreCase(action)) {
            String idS = request.getParameter("id");
            try {
                int id = Integer.parseInt(idS);
                dao.deleteRecord(id);
            } catch (Exception ignored) {
            }
            response.sendRedirect(buildRedirectUrl(request));
            return;
        }

        if ("edit".equalsIgnoreCase(action)) {
            String idS = request.getParameter("id");
            try {
                int id = Integer.parseInt(idS);
                Record r = dao.getRecordById(id);
                request.setAttribute("record", r);
            } catch (Exception ignored) {
            }
            request.getRequestDispatcher("/index.jsp").forward(request, response);
            return;
        }

        // default: redirect to index
        response.sendRedirect(request.getContextPath() + "/");
    }

    @Override
    protected void doPost(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        String action = request.getParameter("action");
        RecordDAO dao = new RecordDAO();

        if ("update".equalsIgnoreCase(action)) {
            try {
                int id = Integer.parseInt(request.getParameter("id"));
                String stname = request.getParameter("stname");
                String course = request.getParameter("course");
                int fee = Integer.parseInt(request.getParameter("fee"));
                dao.updateRecord(id, stname, course, fee);
            } catch (Exception ignored) {
            }
            response.sendRedirect(buildRedirectUrl(request));
            return;
        }

        // create
        String stname = request.getParameter("stname");
        String course = request.getParameter("course");
        String feeS = request.getParameter("fee");
        int fee = 0;
        try {
            fee = Integer.parseInt(feeS);
        } catch (Exception ignored) {
        }
        dao.insertRecord(stname, course, fee);
        response.sendRedirect(buildRedirectUrl(request));
    }
}