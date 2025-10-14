````markdown
# Hướng dẫn Docker-Compose cho Infrastructure

## 1. Các lệnh Docker-Compose cơ bản

```bash
# Khởi động Zookeeper
docker-compose -f common.yml -f zookeeper.yml up

# Khởi động Kafka Cluster
docker-compose -f common.yml -f kafka_cluster.yml up

# Khởi tạo / reset các topic Kafka
docker-compose -f common.yml -f init_kafka.yml up
```
````

> Lưu ý: Bạn nên chạy theo thứ tự Zookeeper → Kafka Cluster → Init Kafka để tránh lỗi kết nối.

---

## 2. Mô tả vai trò từng file/thư mục

### Root của infrastructure

- **pom.xml**
  Vai trò: Cấu hình Maven cho module `infrastructure`. Chứa dependencies, plugin, và cấu hình build liên quan tới phần hạ tầng (ví dụ: đóng gói các file cấu hình, docker-compose, scripts, v.v.).

### Thư mục `docker-compose/`

Chứa các file để khởi tạo stack Docker cho hệ thống, chủ yếu Kafka & Zookeeper.

- **.env**
  Vai trò: Biến môi trường cho docker-compose (ví dụ: `KAFKA_VERSION`, `GLOBAL_NETWORK`, ports, tên network, v.v.).

- **common.yml**
  Vai trò: Compose file chung chứa cấu hình network, volumes, và các service dùng chung. Dùng kết hợp với các file compose khác thông qua:

  ```bash
  docker-compose -f common.yml -f <other>.yml
  ```

- **init_kafka.yml**
  Vai trò: Service tạm thời để (re)khởi tạo các topic Kafka.
  Nội dung:

  - Chờ Kafka hoạt động
  - Xóa các topic cũ nếu tồn tại (`payment-request`, `payment-response`, `restaurant-approval-request`, `restaurant-approval-response`, `customer`)
  - Tạo lại các topic với replication-factor 3 và partitions 3
  - Liệt kê các topic đã tạo
    Ý nghĩa: hữu ích để reset môi trường dev/test trước khi service bắt đầu gửi/nhận message.

- **kafka_cluster.yml**
  Vai trò: Định nghĩa cluster Kafka gồm nhiều broker (thường 3 brokers) và cấu hình liên quan như listeners, broker ids, zookeeper connect, replicas, volumes.

- **zookeeper.yml**
  Vai trò: Service Zookeeper dùng bởi Kafka để lưu metadata về brokers và topics. Cần thiết cho Kafka phiên bản dùng Zookeeper (non-KRaft).

- **readme.md**
  Vai trò: Hướng dẫn sử dụng docker-compose trong thư mục này, bao gồm cách khởi động cluster, lệnh, biến môi trường, và lưu ý về volumes.

- **volumes/**
  Vai trò: Chứa dữ liệu persistent cho container (host mounts) nhằm giữ state giữa các lần khởi động Docker Compose.

  Cấu trúc:

  - `kafka/`

    - `broker-1/`, `broker-2/`, `broker-3/`: dữ liệu log của mỗi broker Kafka (segment files, index, \_\_consumer_offsets, v.v.).

  - `zookeeper/`

    - `data/`, `transactions/`: dữ liệu trạng thái Zookeeper, session, transaction logs.

---

## 3. Lưu ý và gợi ý

- **Reset topic mà không khởi chạy toàn bộ container:**

  ```bash
  docker-compose -f common.yml -f kafka_cluster.yml -f init_kafka.yml up
  ```

  > Chú ý: container `init-kafka` cần truy cập `kafka-broker-1:9092` nên phải cùng network.

- **Đảm bảo `.env` có biến `KAFKA_VERSION` tương ứng với image Confluent Kafka.**

- **Xóa dữ liệu persistent nếu muốn khởi tạo lại hoàn toàn:**

  ```bash
  docker-compose down -v
  ```

  hoặc xóa trực tiếp folder `broker-*` (cẩn thận: mất dữ liệu).

- **Nếu dùng Kafka KRaft (no Zookeeper):** cấu trúc sẽ khác, repo này hiện dùng Zookeeper.

---

## 4. Tiếp theo

Bạn có thể mở rộng README bằng:

- Trích dẫn nội dung YAML và giải thích từng service / option
- Kiểm tra compose files chạy thành công trên máy
- Tạo script helper (bash) để dễ khởi động / reset topics
