#-----------------------------------------------------------------------------------------------------------------------
#-- Table 명 : order_master (주문)
#-----------------------------------------------------------------------------------------------------------------------
CREATE TABLE order_master (
	order_id           bigint              NOT NULL AUTO_INCREMENT COMMENT '주문 id',
	order_no           varchar(10)         NOT NULL                COMMENT '주문 번호',
	product_name	   varchar(100)        NOT NULL                COMMENT '제품명',
	member_id  	 	   bigint        	   NOT NULL                COMMENT '회원 id',
	payment_datetime   datetime        	   NOT NULL                COMMENT '결제일시',
	create_datetime   datetime        	   NOT NULL                COMMENT '생성일시',
    CONSTRAINT pk_order PRIMARY KEY (order_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='주문';

#-----------------------------------------------------------------------------------------------------------------------
#-- Table 명 : member (회원)
#-----------------------------------------------------------------------------------------------------------------------
CREATE TABLE member (
  member_id           bigint               NOT NULL AUTO_INCREMENT COMMENT '회원 id',
  name                varchar(20)          NOT NULL                COMMENT '이름',
  nickname            varchar(30)          NOT NULL                COMMENT '별명',
  password            varchar(100)         NOT NULL                COMMENT '패스워드',
  tel_number          varchar(20)          NOT NULL                COMMENT '전화번호',
  email               varchar(100)         NOT NULL                COMMENT '이메일',
  gender              varchar(1)           NOT NULL                COMMENT '성별',
  created_datetime    datetime             NOT NULL                COMMENT '등록일자',
  CONSTRAINT pk_member PRIMARY KEY (member_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='회원 정보'