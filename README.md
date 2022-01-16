# 개발환경 디비/레디스 설치 디비 스키마
* docker 설치 후 mysql 설치
```
1. docker pull mysql:latest
2. docker run --name mysql-container -e MYSQL_ROOT_PASSWORD=root -d -p 53307:3306 mysql:latest 
```

* mysql client에 접속한 후 backpacker 디비 생성
```
1. docker exec -it mysql-container bash
2. mysql -uroot -proot
mysql> CREATE DATABASE backpacker CHARACTER SET utf8mb4;
```

* docker 설치 후 레디스 설치
```
1. docker pull redis:alpine
2. docker run -i -t --name redis -p 63790:6379 redis:alpine
```

# backpacker 디비 테이블
* 생성
```
V1_schema.sql 파일 참조
```
* 회원, 주문 테이블 샘플 데이터
```
V1_schema_sample_test.sql 파일 참조
```