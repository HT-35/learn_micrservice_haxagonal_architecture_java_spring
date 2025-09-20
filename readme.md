# Lern Microservice - Hexagonal Architecture (Java, Spring)

## Giới thiệu

Dự án này là một hệ thống microservice quản lý đơn hàng, được xây dựng theo kiến trúc Hexagonal (Ports & Adapters) sử dụng Java và Spring. Mỗi module đảm nhận một vai trò riêng biệt trong hệ thống, giúp dễ dàng mở rộng, bảo trì và kiểm thử.

## Cấu trúc dự án

```
pom.xml
order-service/
  ├── order-application/
  ├── order-container/
  ├── order-dataaccess/
  ├── order-domain/
      ├── order-application-service/
      └── order-domain-core/
  └── order-messaging/
```

### Mô tả các module

- **order-application**: Xử lý logic nghiệp vụ liên quan đến đơn hàng.
- **order-container**: Đóng vai trò khởi tạo và cấu hình các thành phần của service.
- **order-dataaccess**: Quản lý truy xuất dữ liệu, kết nối với database.
- **order-domain**: Định nghĩa các thực thể, giá trị, và logic nghiệp vụ cốt lõi.
  - **order-application-service**: Các service nghiệp vụ cho domain.
  - **order-domain-core**: Các thực thể, giá trị, và logic nghiệp vụ cốt lõi.
- **order-messaging**: Quản lý giao tiếp giữa các service qua messaging (ví dụ: Kafka, RabbitMQ).

## Kiến trúc Hexagonal

- **Domain**: Chứa logic nghiệp vụ cốt lõi, không phụ thuộc vào framework hay công nghệ bên ngoài.
- **Application**: Xử lý các trường hợp sử dụng (use case), điều phối luồng dữ liệu giữa domain và các adapter.
- **Adapters**: Kết nối với các hệ thống bên ngoài như database, messaging, API.

## Yêu cầu hệ thống

- Java 11+
- Maven 3.6+
- Spring Boot

## Hướng dẫn cài đặt

1. Clone repository:
   ```bash
   git clone <repo-url>
   ```
2. Di chuyển vào thư mục dự án:
   ```bash
   cd Lern_microservice
   ```
3. Build toàn bộ project:
   ```bash
   mvn clean install
   ```
4. Chạy service:
   ```bash
   cd order-service/order-container
   mvn spring-boot:run
   ```

## Đóng góp

Vui lòng tạo pull request hoặc issue nếu bạn muốn đóng góp hoặc báo lỗi.

## License

MIT
