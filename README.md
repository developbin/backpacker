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

# 참고사항
* 도커 mysql, redis 실행 및 정상동작 필수입니다.
* 회원가입 API 로 생성후에 해당 액세스토큰을 발급하여 테스트를 권고합니다.
   > 회원가입 > 액세스토큰 발급 > 회원 상세 정보 > 주문 정보 > 로그아웃
* 액세스토큰은 Authorization Bearer Token 으로 기입하여 api 통신합니다.
   > 회원정보, 단일회원주문, 여러회원주문, 로그아웃 API
* 스키마 샘플 테스트 sql 관련
   > V1_schema_sample_test.sql 로 데이터를 생성한 경우 test1@naver.com 에 비밀번호는 tt1132 입니다.
   > 패스워드 값의 제약 개발하기 이전에 만들어진 패스워드 입니다.