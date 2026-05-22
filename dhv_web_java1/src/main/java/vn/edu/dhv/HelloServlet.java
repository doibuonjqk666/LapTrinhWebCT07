package vn.edu.dhv;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Locale;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HelloServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html; charset=UTF-8");
        request.setCharacterEncoding("UTF-8");

        String name = request.getParameter("name");
        if (name == null || name.isBlank()) {
            name = "Bạn chưa nhập tên";
        }
        String mssv = request.getParameter("mssv");
        if (mssv == null || mssv.isBlank()) {
            mssv = "MSSV chưa được nhập";
        }
        try (PrintWriter out = response.getWriter()) {
            String context = request.getContextPath();
            out.println("<!DOCTYPE html>");
            out.println("<html lang=\"vi\">");
            out.println("<head>");
            out.println("  <meta charset=\"UTF-8\"> ");
            out.println("  <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\"> ");
            out.println("  <title>Servlet Hello</title>");
            out.println("  <link rel=\"stylesheet\" href=\"" + context + "/css/style.css\">\n");
            out.println("</head>");
            out.println("<body>");
            out.println("  <div class=\"site-container\">");
            out.println("    <header class=\"header\">");
            out.println("      <div>");
            out.println("        <h1 class=\"site-title\">Kết quả gửi dữ liệu</h1>");
            out.println("        <p class=\"site-subtitle\">Servlet đã xử lý thông tin của bạn</p>");
            out.println("      </div>");
            out.println("      <nav class=\"nav-links\">");
            out.println("        <a href=\"" + context + "/index.jsp\">Trang chủ</a>");
            out.println("      </nav>");
            out.println("    </header>");
            out.println("    <section class=\"hero\">");
            out.println("      <h1>Xin chào, " + escapeHtml(name) + "!</h1>");
            out.println("      <p class=\"site-subtitle\">MSSV của bạn là <strong>" + escapeHtml(mssv) + "</strong></p>");
            out.println("      <p>Thời gian xử lý: " + new SimpleDateFormat("EEEE, dd/MM/yyyy HH:mm:ss", new Locale("vi", "VN")).format(new java.util.Date()) + "</p>");
            out.println("    </section>");
            out.println("    <footer class=\"footer\">&copy; 2026 vn.edu.dhv - Java Web Basic</footer>");
            out.println("  </div>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    private String escapeHtml(String input) {
        return input.replace("&", "&amp;")
                .replace("<", "&lt;")
                .replace(">", "&gt;")
                .replace("\"", "&quot;")
                .replace("'", "&#39;");
    }
}
