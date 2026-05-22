<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Locale" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>DHV Java Web</title>
    <link rel="stylesheet" href="css/index.css" />
</head>
<body>
    <div class="container">
        <header>
            <div>
                <h1>DHV Web Java</h1>
                <p>Mẫu layout web cơ bản với JSP và Servlet</p>
            </div>
            <nav>
                <a href="#form">Giao diện</a>
                <a href="doikm.jsp">Chuyển đổi KM</a>
                <a href="#info">Thông tin</a>
            </nav>
        </header>

        <section class="hero">
            <h1>Chào mừng đến với ứng dụng Java Web</h1>
            <p>Đây là trang chủ của dự án Maven Webapp. Ứng dụng sử dụng Servlet để nhận dữ liệu và JSP để hiển thị giao diện.</p>
            <p>Thời gian hiện tại: <strong><%= new SimpleDateFormat("EEEE, dd/MM/yyyy HH:mm:ss", new Locale("vi", "VN")).format(new java.util.Date()) %></strong></p>
        </section>

        <div class="grid">
            <article class="card" id="form">
                <h2>Gửi dữ liệu đến Servlet</h2>
                <form action="hello" method="get">
                    <label for="name">Họ tên</label>
                    <input type="text" id="name" name="name" value="Tên Sinh Viên" />
                    
                    <label for="mssv">Mã số sinh viên</label>
                    <input type="text" id="mssv" name="mssv" value="Mã Sinh Viên" />
                    
                    <button type="submit">Gửi</button>
                </form>
            </article>

            <article class="card" id="info">
                <h2>Giới thiệu</h2>
                <p>Trang web này sử dụng:</p>
                <ul>
                    <li>Java Servlet</li>
                    <li>JSP</li>
                    <li>Maven Webapp</li>
                    <li>Tomcat Maven Plugin</li>
                </ul>
                <p>Nhấn gửi để xem Servlet xử lý dữ liệu và trả về trang kết quả.</p>
            </article>
        </div>

        <footer>
            &copy; 2026 vn.edu.dhv - Bài tập Java Web cơ bản
        </footer>
    </div>
</body>
</html>
