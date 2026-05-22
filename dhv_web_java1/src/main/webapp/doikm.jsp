<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8" />
    <title>Chuyển đổi KM sang Miles</title>
    <link rel="stylesheet" href="css/doikm.css" />
</head>
<body>
    <h1>Chuyển đổi KM sang Miles</h1>
    
    <div id="result-box"></div>
    
    <form id="convertForm">
        <label>Nhập KM:</label><br>
        <input type="number" id="km" name="km" placeholder="Ví dụ: 100" step="0.01" min="0" required /><br>
        <button type="submit">Chuyển đổi</button>
    </form>
    
    <p><a href="index.jsp">← Trang chủ</a></p>
    
    <script>
        document.getElementById('convertForm').addEventListener('submit', function(e) {
            e.preventDefault();
            
            const km = document.getElementById('km').value;
            
            fetch('convert', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/x-www-form-urlencoded',
                    'X-Requested-With': 'XMLHttpRequest'
                },
                body: 'km=' + km
            })
            .then(response => response.json())
            .then(data => {
                document.getElementById('result-box').innerHTML = 
                    '<div class="result"><h2>✓ Kết quả: ' + data.km + ' KM = ' + data.miles + ' Miles</h2></div>';
            })
            .catch(error => console.error('Error:', error));
        });
    </script>
</body>
</html>
