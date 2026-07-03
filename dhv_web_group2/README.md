# Hướng dẫn chạy dự án `dhv_web_group2`

## Mục tiêu
Hướng dẫn chạy project Java web trong `dhv_web_group2/lab3` cùng MySQL bằng Docker.

## Yêu cầu trước
- Java 21 đã cài
- Maven đã cài
- Docker đã cài và đang chạy
- Port `8080` và `3306` chưa bị chiếm

## Bước 1: Chuẩn bị MySQL bằng Docker
1. Mở terminal và vào thư mục project:
```bash
cd /workspaces/LapTrinhWebCT07/dhv_web_group2/lab3
```

2. Tải image MySQL:
```bash
docker pull mysql:8.0
```

3. Tạo và chạy container MySQL:
```bash
docker run -d \
  --name lab3-mysql \
  -e MYSQL_ROOT_PASSWORD=Vietnam@123 \
  -e MYSQL_DATABASE=northwind \
  -p 3306:3306 \
  mysql:8.0
```

4. Kiểm tra container đã chạy:
```bash
docker ps
```

Nếu bạn đã từng tạo container `lab3-mysql`, dùng:
```bash
docker start lab3-mysql
```

## Bước 2: Import dữ liệu MySQL
Project `lab3` sử dụng database `northwind` với cấu hình sau trong `src/main/java/context/DBContext.java`:
- SERVER: `127.0.0.1`
- PORT: `3306`
- DATABASE: `northwind`
- USER: `root`
- PASSWORD: `Vietnam@123`

Import file SQL vào MySQL:
```bash
docker exec -i lab3-mysql mysql -u root -pVietnam@123 < my_sql_northwind.sql
```

Nếu muốn tạo bảng đơn giản cho lab khác, bạn có thể kết nối MySQL và thực hiện câu lệnh SQL manual.

## Bước 3: Chạy dự án Java Web
1. Vào thư mục `lab3` nếu chưa vào:
```bash
cd /workspaces/LapTrinhWebCT07/dhv_web_group2/lab3
```

2. Chạy server Tomcat bằng Maven:
```bash
mvn tomcat7:run
```

3. Mở trình duyệt và truy cập:
```
http://localhost:8080/
```

> Nếu port `8080` bị chiếm, dừng process đang dùng port hoặc sửa lại cổng trong `pom.xml`.

## Kiểm tra lỗi kết nối MySQL
Nếu project không kết nối được MySQL, kiểm tra:
- Container `lab3-mysql` đang chạy
- Port `3306` đã map đúng
- Cấu hình database `northwind`, user `root`, password `Vietnam@123`
- `my_sql_northwind.sql` đã được import

## Các lệnh hữu ích
- Xem port 8080 đang dùng bởi process nào:
```bash
ss -ltnp | grep :8080
```

- Nếu cần tìm và kill process đang chiếm port 8080 một dòng:
```bash
sudo kill -9 $(ss -ltnp | awk '/:8080/ { sub(/.*pid=/, "", $NF); sub(/,.*/, "", $NF); print $NF }')
```

- Xem container Docker:
```bash
docker ps -a
```

- Dừng container:
```bash
docker stop lab3-mysql
```

- Xóa container nếu cần:
```bash
docker rm lab3-mysql
```

- Xóa image MySQL nếu muốn làm lại từ đầu:
```bash
docker rmi mysql:8.0
```

## Ghi chú
- Dự án `lab3` dùng plugin `tomcat7-maven-plugin` trong `pom.xml`.
- Nếu bạn cần chạy project khác trong `dhv_web_group2`, hãy vào thư mục tương ứng và kiểm tra cấu hình riêng của project đó.
