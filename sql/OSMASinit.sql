-- 파일 변경 이력
-- 20230622
--  1.  TBL_PRJ_NEW_INFO_FILE 속성추가 (ORIGINAL VARCHAR2(512), CHANGED VARCHAR2(512)), 프로젝트파일 속성제거(경로)

-- 20230623
--  1, TBL_PRJ_NEW_INFO CONTENT TYPE VARCHAR(4000) -> CLOB 변경
--  2. TBL_PRJ ATTRIUBE START_DATE 추가 LAST_DATE -> END_DATE 변경

-- 20230624
--  1. TBL_MEMBER ROLE - 삭제
--  2. COMMENT ON COLUMN TBL_PRJ.START_DATE IS '프로젝트 시작날짜'; 추가
--  3. COMMENT ON COLUMN TBL_PRJ.END_DATE IS '프로젝트 종료날짜'; LAST_DATE 수정
--  4. FOREIGN KEY 조건 추가
--  5. TBL_PRJ_FILE PATH 속성 삭체

-- 20230626
-- 1. TBL_ADDRESS SHIP_NO 삭제
-- Drop table

-- 20230703
-- 1. ALTER TABLE TBL_REVIEW RENAME COLUMN TITLE TO REVIEW_TITLE;

-- 20230704
-- 1. ALTER TABLE TBL_SUGGEST ADD DETELE_YN CHAR(1);  삭제여부 추가


DROP TABLE TBL_ADDRESS CASCADE CONSTRAINTS;
DROP TABLE TBL_ADMIN_BOARD CASCADE CONSTRAINTS;
DROP TABLE TBL_ADMIN_BOARD_FILE CASCADE CONSTRAINTS;
DROP TABLE TBL_ALERT CASCADE CONSTRAINTS;
DROP TABLE TBL_CATEGORY CASCADE CONSTRAINTS;
DROP TABLE TBL_DELIVERY_STATUS CASCADE CONSTRAINTS;
DROP TABLE TBL_MEMBER CASCADE CONSTRAINTS;
DROP TABLE TBL_MEMBER_ROLE CASCADE CONSTRAINTS;
DROP TABLE TBL_PAYMENT CASCADE CONSTRAINTS;
DROP TABLE TBL_PAYMENT_HISTORY CASCADE CONSTRAINTS;
DROP TABLE TBL_PERMISSION_ROLE CASCADE CONSTRAINTS;
DROP TABLE TBL_PRJ CASCADE CONSTRAINTS;
DROP TABLE TBL_PRJ_FAQ CASCADE CONSTRAINTS;
DROP TABLE TBL_PRJ_FILE CASCADE CONSTRAINTS;
DROP TABLE TBL_PRJ_NEW_INFO CASCADE CONSTRAINTS;
-- DROP TABLE TBL_PRJ_NEW_INFO_FILE CASCADE CONSTRAINTS;
DROP TABLE TBL_PRJ_PRODUCT_LIST CASCADE CONSTRAINTS;
DROP TABLE TBL_PRJ_PROGRESS CASCADE CONSTRAINTS;
DROP TABLE TBL_PRJ_QNA CASCADE CONSTRAINTS;
DROP TABLE TBL_PRJ_QNA_ANSWER CASCADE CONSTRAINTS;
DROP TABLE TBL_PRJ_WISH CASCADE CONSTRAINTS;
DROP TABLE TBL_PRODUCT CASCADE CONSTRAINTS;
DROP TABLE TBL_REVIEW CASCADE CONSTRAINTS;
DROP TABLE TBL_ROLE_LIST CASCADE CONSTRAINTS;
DROP TABLE TBL_SELLER_ROLE CASCADE CONSTRAINTS;
DROP TABLE TBL_SELLER_ROLE_FILE CASCADE CONSTRAINTS;
DROP TABLE TBL_SELLER_ROLE_REQ CASCADE CONSTRAINTS;
DROP TABLE TBL_SHIPPING_TRACK_INFO CASCADE CONSTRAINTS;
DROP TABLE TBL_SPONSORED_PRJ CASCADE CONSTRAINTS;
DROP TABLE TBL_SUGGEST CASCADE CONSTRAINTS;
DROP TABLE TBL_TERMS CASCADE CONSTRAINTS;
DROP TABLE TBL_TERMS_HISTORY CASCADE CONSTRAINTS;


COMMIT;

-- Create Table

CREATE TABLE TBL_ADDRESS
(
    GENERAL    VARCHAR2(1000),
    DETAIL    VARCHAR2(1000),
    POSTAL_CODE    VARCHAR2(10),
--     SHIP_NO    NUMBER,
    NO    NUMBER NOT NULL,
    REF_MEMBER_NO    NUMBER
);

COMMENT ON COLUMN TBL_ADDRESS.GENERAL IS '일반주소';

COMMENT ON COLUMN TBL_ADDRESS.DETAIL IS '상세주소';

COMMENT ON COLUMN TBL_ADDRESS.POSTAL_CODE IS '우편번호';

COMMENT ON COLUMN TBL_ADDRESS.SHIP_NO IS '배송번호';

COMMENT ON COLUMN TBL_ADDRESS.NO IS '주소코드';

COMMENT ON COLUMN TBL_ADDRESS.REF_MEMBER_NO IS '회원번호';

COMMENT ON TABLE TBL_ADDRESS IS '주소';

CREATE UNIQUE INDEX TBL_ADDRESS_PK ON TBL_ADDRESS
    ( NO );

ALTER TABLE TBL_ADDRESS
    ADD CONSTRAINT TBL_ADDRESS_PK PRIMARY KEY ( NO )
        USING INDEX TBL_ADDRESS_PK;

COMMENT ON TABLE TBL_ADDRESS IS '주소';

CREATE TABLE TBL_ADMIN_BOARD
(
    NO    NUMBER NOT NULL,
    TITLE    VARCHAR2(150),
    CONTENT    VARCHAR2(4000),
    REGIST_DATE    DATE,
    MODIFY_DATE    DATE,
    CLASSIFY_CODE    VARCHAR2(20),
    REF_MEMBER_NO    NUMBER NOT NULL,
    DETELE_YN    CHAR(1)
);

COMMENT ON COLUMN TBL_ADMIN_BOARD.NO IS '게시판 번호';

COMMENT ON COLUMN TBL_ADMIN_BOARD.TITLE IS '제목';

COMMENT ON COLUMN TBL_ADMIN_BOARD.CONTENT IS '내용';

COMMENT ON COLUMN TBL_ADMIN_BOARD.REGIST_DATE IS '생성날짜';

COMMENT ON COLUMN TBL_ADMIN_BOARD.MODIFY_DATE IS '수정날짜';

COMMENT ON COLUMN TBL_ADMIN_BOARD.CLASSIFY_CODE IS '게시판 구분코드';

COMMENT ON COLUMN TBL_ADMIN_BOARD.REF_MEMBER_NO IS '작성자';

COMMENT ON COLUMN TBL_ADMIN_BOARD.DETELE_YN IS '삭제여부';

COMMENT ON TABLE TBL_ADMIN_BOARD IS '관리자 게시판';

CREATE UNIQUE INDEX TBL_ADMIN_BOARD_PK ON TBL_ADMIN_BOARD
    ( NO );

ALTER TABLE TBL_ADMIN_BOARD
    ADD CONSTRAINT TBL_ADMIN_BOARD_PK PRIMARY KEY ( NO )
        USING INDEX TBL_ADMIN_BOARD_PK;

COMMENT ON TABLE TBL_ADMIN_BOARD IS '관리자 게시판';

CREATE TABLE TBL_ADMIN_BOARD_FILE
(
    NO    NUMBER NOT NULL,
    ORIGIN_NAME    VARCHAR2(600),
    CHANGE_NAME    VARCHAR2(600),
    REGIST_DATE    DATE,
    DETELE_YN    CHAR(1),
    REF_ADMIN_BOARD_NO    NUMBER NOT NULL
);

COMMENT ON COLUMN TBL_ADMIN_BOARD_FILE.NO IS '관리자게시판파일번호';

COMMENT ON COLUMN TBL_ADMIN_BOARD_FILE.ORIGIN_NAME IS '원본파일명';

COMMENT ON COLUMN TBL_ADMIN_BOARD_FILE.CHANGE_NAME IS '변경된파일명';

COMMENT ON COLUMN TBL_ADMIN_BOARD_FILE.REGIST_DATE IS '등록일';

COMMENT ON COLUMN TBL_ADMIN_BOARD_FILE.DETELE_YN IS '삭제여부';

COMMENT ON COLUMN TBL_ADMIN_BOARD_FILE.REF_ADMIN_BOARD_NO IS '게시판 번호';

COMMENT ON TABLE TBL_ADMIN_BOARD_FILE IS '관리자게시판파일이력';

CREATE UNIQUE INDEX TBL_ADMIN_BOARD_FILE_PK ON TBL_ADMIN_BOARD_FILE
    ( NO );

ALTER TABLE TBL_ADMIN_BOARD_FILE
    ADD CONSTRAINT TBL_ADMIN_BOARD_FILE_PK PRIMARY KEY ( NO )
        USING INDEX TBL_ADMIN_BOARD_FILE_PK;

COMMENT ON TABLE TBL_ADMIN_BOARD_FILE IS '관리자게시판파일이력';


CREATE TABLE TBL_ALERT
(
    CONTENT    VARCHAR2(4000),
    REGIST_DATE    DATE,
    REF_MEMBER_NO    NUMBER NOT NULL,
    NO    NUMBER NOT NULL,
    REF_SUGGEST_NO    NUMBER NOT NULL
);

COMMENT ON COLUMN TBL_ALERT.CONTENT IS '답변내용';

COMMENT ON COLUMN TBL_ALERT.REGIST_DATE IS '답변날짜';

COMMENT ON COLUMN TBL_ALERT.REF_MEMBER_NO IS '관리자 번호';

COMMENT ON COLUMN TBL_ALERT.NO IS '알림코드';

COMMENT ON COLUMN TBL_ALERT.REF_SUGGEST_NO IS '제안하기게시판번호';

COMMENT ON TABLE TBL_ALERT IS '알림';

CREATE UNIQUE INDEX TBL_ALERT_PK ON TBL_ALERT
    ( NO );

ALTER TABLE TBL_ALERT
    ADD CONSTRAINT TBL_ALERT_PK PRIMARY KEY ( NO )
        USING INDEX TBL_ALERT_PK;

COMMENT ON TABLE TBL_ALERT IS '알림';


CREATE TABLE TBL_CATEGORY
(
    NO    NUMBER NOT NULL,
    SUB_NO    NUMBER,
    NAME    VARCHAR2(50)
);

COMMENT ON COLUMN TBL_CATEGORY.NO IS '프로젝트카테고리코드';

COMMENT ON COLUMN TBL_CATEGORY.SUB_NO IS '하위카테고리코드';

COMMENT ON COLUMN TBL_CATEGORY.NAME IS '카테고리명';

COMMENT ON TABLE TBL_CATEGORY IS '카테고리';

CREATE UNIQUE INDEX TBL_CATEGORY_PK ON TBL_CATEGORY
    ( NO );

ALTER TABLE TBL_CATEGORY
    ADD CONSTRAINT TBL_CATEGORY_PK PRIMARY KEY ( NO )
        USING INDEX TBL_CATEGORY_PK;

COMMENT ON TABLE TBL_CATEGORY IS '카테고리';


CREATE TABLE TBL_DELIVERY_STATUS
(
    STATUS    VARCHAR2(20),
    REGIST_DATE    DATE,
    REF_SHIPPING_TRACK_INFO_NO    NUMBER NOT NULL,
    NO    NUMBER NOT NULL
);

COMMENT ON COLUMN TBL_DELIVERY_STATUS.STATUS IS '배송상태';

COMMENT ON COLUMN TBL_DELIVERY_STATUS.REGIST_DATE IS '상태일';

COMMENT ON COLUMN TBL_DELIVERY_STATUS.REF_SHIPPING_TRACK_INFO_NO IS '배송코드';

COMMENT ON COLUMN TBL_DELIVERY_STATUS.NO IS '배송이력코드';

COMMENT ON TABLE TBL_DELIVERY_STATUS IS '배송상태이력';

CREATE UNIQUE INDEX TBL_DELIVERY_STATUS_PK ON TBL_DELIVERY_STATUS
    ( NO );

ALTER TABLE TBL_DELIVERY_STATUS
    ADD CONSTRAINT TBL_DELIVERY_STATUS_PK PRIMARY KEY ( NO )
        USING INDEX TBL_DELIVERY_STATUS_PK;

COMMENT ON TABLE TBL_DELIVERY_STATUS IS '배송상태이력';


CREATE TABLE TBL_MEMBER
(
    ID    VARCHAR2(50),
    PWD    VARCHAR2(4000),
    NAME    VARCHAR2(50),
    PHONE    VARCHAR2(20),
    BIRTH    DATE,
    EMAIL    VARCHAR2(200),
    REGIST_DATE    DATE,
    NICKNAME    VARCHAR2(50),
    NO    NUMBER NOT NULL,
    STATUS    VARCHAR2(20),
    INTRODUCTION    VARCHAR2(4000),
    DROP_REASON    VARCHAR2(4000)
);

COMMENT ON COLUMN TBL_MEMBER.ID IS '아이디';

COMMENT ON COLUMN TBL_MEMBER.PWD IS '비밀번호';

COMMENT ON COLUMN TBL_MEMBER.NAME IS '이름';

COMMENT ON COLUMN TBL_MEMBER.PHONE IS '핸드폰 번호';

COMMENT ON COLUMN TBL_MEMBER.BIRTH IS '생년월일';

COMMENT ON COLUMN TBL_MEMBER.EMAIL IS '이메일';

COMMENT ON COLUMN TBL_MEMBER.REGIST_DATE IS '가입날짜';

COMMENT ON COLUMN TBL_MEMBER.NICKNAME IS '닉네임';

COMMENT ON COLUMN TBL_MEMBER.NO IS '회원번호';

COMMENT ON COLUMN TBL_MEMBER.STATUS IS '회원상태';

COMMENT ON COLUMN TBL_MEMBER.INTRODUCTION IS '소개';

COMMENT ON COLUMN TBL_MEMBER.DROP_REASON IS '탈퇴사유';

COMMENT ON TABLE TBL_MEMBER IS '회원';

CREATE UNIQUE INDEX TBL_MEMBER_PK ON TBL_MEMBER
    ( NO );

ALTER TABLE TBL_MEMBER
    ADD CONSTRAINT TBL_MEMBER_PK PRIMARY KEY ( NO )
        USING INDEX TBL_MEMBER_PK;

COMMENT ON TABLE TBL_MEMBER IS '회원';

CREATE TABLE TBL_MEMBER_ROLE
(
    ROLE_CODE    NUMBER NOT NULL,
    ROLE_NAME    VARCHAR2(20)
);

COMMENT ON COLUMN TBL_MEMBER_ROLE.ROLE_CODE IS '권한코드';

COMMENT ON COLUMN TBL_MEMBER_ROLE.ROLE_NAME IS '권한명';

COMMENT ON TABLE TBL_MEMBER_ROLE IS '권한';

CREATE UNIQUE INDEX TBL_MEMBER_ROLE_PK ON TBL_MEMBER_ROLE
    ( ROLE_CODE );

ALTER TABLE TBL_MEMBER_ROLE
    ADD CONSTRAINT TBL_MEMBER_ROLE_PK PRIMARY KEY ( ROLE_CODE )
        USING INDEX TBL_MEMBER_ROLE_PK;

COMMENT ON TABLE TBL_MEMBER_ROLE IS '권한';


CREATE TABLE TBL_PAYMENT
(
    CODE    NUMBER NOT NULL,
    PAYMENT_DATE    DATE,
    STATUS    VARCHAR2(20),
    AMOUNT    NUMBER,
    PAYMENT_TYPE    VARCHAR2(20)
);

COMMENT ON COLUMN TBL_PAYMENT.CODE IS '결제코드';

COMMENT ON COLUMN TBL_PAYMENT.PAYMENT_DATE IS '승인날짜';

COMMENT ON COLUMN TBL_PAYMENT.STATUS IS '승인상태';

COMMENT ON COLUMN TBL_PAYMENT.AMOUNT IS '결제금액';

COMMENT ON COLUMN TBL_PAYMENT.PAYMENT_TYPE IS '결제종류';

COMMENT ON TABLE TBL_PAYMENT IS '결제';

CREATE UNIQUE INDEX TBL_PAYMENT_PK ON TBL_PAYMENT
    ( CODE );

ALTER TABLE TBL_PAYMENT
    ADD CONSTRAINT TBL_PAYMENT_PK PRIMARY KEY ( CODE )
        USING INDEX TBL_PAYMENT_PK;

COMMENT ON TABLE TBL_PAYMENT IS '결제';


CREATE TABLE TBL_PAYMENT_HISTORY
(
    REF_PAYMENT_NO    NUMBER NOT NULL,
    REF_SPONSORED_PRJ_NO    NUMBER NOT NULL
);

COMMENT ON COLUMN TBL_PAYMENT_HISTORY.REF_PAYMENT_NO IS '결제코드';

COMMENT ON COLUMN TBL_PAYMENT_HISTORY.REF_SPONSORED_PRJ_NO IS '후원한프로젝트코드';

COMMENT ON TABLE TBL_PAYMENT_HISTORY IS '결제내역';

CREATE UNIQUE INDEX TBL_PAYMENT_HISTORY_PK ON TBL_PAYMENT_HISTORY
    ( REF_PAYMENT_NO,REF_SPONSORED_PRJ_NO );

ALTER TABLE TBL_PAYMENT_HISTORY
    ADD CONSTRAINT TBL_PAYMENT_HISTORY_PK PRIMARY KEY ( REF_PAYMENT_NO,REF_SPONSORED_PRJ_NO )
        USING INDEX TBL_PAYMENT_HISTORY_PK;

COMMENT ON TABLE TBL_PAYMENT_HISTORY IS '결제내역';


CREATE TABLE TBL_PERMISSION_ROLE
(
    NO    NUMBER NOT NULL,
    REJECT_REASON    VARCHAR2(4000),
    PERMISSION_STATUS    VARCHAR2(20),
    LAST_DATE    DATE,
    REF_SELLER_ROLE_REQ_NO    NUMBER NOT NULL
);

COMMENT ON COLUMN TBL_PERMISSION_ROLE.NO IS '승인내역코드';

COMMENT ON COLUMN TBL_PERMISSION_ROLE.REJECT_REASON IS '사유';

COMMENT ON COLUMN TBL_PERMISSION_ROLE.PERMISSION_STATUS IS '상태값';

COMMENT ON COLUMN TBL_PERMISSION_ROLE.LAST_DATE IS '처리일자';

COMMENT ON COLUMN TBL_PERMISSION_ROLE.REF_SELLER_ROLE_REQ_NO IS '판매자권한신청코드';

COMMENT ON TABLE TBL_PERMISSION_ROLE IS '권한승인내역';

CREATE UNIQUE INDEX TBL_PERMISSION_ROLE_PK ON TBL_PERMISSION_ROLE
    ( NO );

ALTER TABLE TBL_PERMISSION_ROLE
    ADD CONSTRAINT TBL_PERMISSION_ROLE_PK PRIMARY KEY ( NO )
        USING INDEX TBL_PERMISSION_ROLE_PK;

COMMENT ON TABLE TBL_PERMISSION_ROLE IS '권한승인내역';


CREATE TABLE TBL_PRJ
(
    NO    NUMBER NOT NULL,
    TITLE    VARCHAR2(150),
    CONTENT    CLOB,
    REGIST_DATE    DATE NOT NULL,
    START_DATE    DATE,
    END_DATE    DATE,
    TARGET_AMOUNT    NUMBER,
    CURRENT_AMOUNT    NUMBER,
    VIEWS    INT,
    REF_MEMBER_NO    NUMBER NOT NULL,
    REF_CATEGORY_NO    NUMBER
);

COMMENT ON COLUMN TBL_PRJ.NO IS '프로젝트번호';

COMMENT ON COLUMN TBL_PRJ.TITLE IS '제목';

COMMENT ON COLUMN TBL_PRJ.CONTENT IS '프로젝트 내용';

COMMENT ON COLUMN TBL_PRJ.REGIST_DATE IS '생성날짜';

COMMENT ON COLUMN TBL_PRJ.START_DATE IS '프로젝트 시작날짜';

COMMENT ON COLUMN TBL_PRJ.END_DATE IS '프로젝트 종료날짜';

COMMENT ON COLUMN TBL_PRJ.TARGET_AMOUNT IS '목표액';

COMMENT ON COLUMN TBL_PRJ.CURRENT_AMOUNT IS '모금액';

COMMENT ON COLUMN TBL_PRJ.VIEWS IS '조회수';

COMMENT ON COLUMN TBL_PRJ.REF_MEMBER_NO IS '회원번호';

COMMENT ON COLUMN TBL_PRJ.REF_CATEGORY_NO IS '프로젝트카테고리코드';

COMMENT ON TABLE TBL_PRJ IS '프로젝트';

CREATE UNIQUE INDEX TBL_PRJ_PK ON TBL_PRJ
    ( NO );

ALTER TABLE TBL_PRJ
    ADD CONSTRAINT TBL_PRJ_PK PRIMARY KEY ( NO )
        USING INDEX TBL_PRJ_PK;

COMMENT ON TABLE TBL_PRJ IS '프로젝트';


CREATE TABLE TBL_PRJ_FAQ
(
    NO    NUMBER NOT NULL,
    REF_PRJ_NO    NUMBER NOT NULL,
    TITLE    VARCHAR2(150) NOT NULL,
    CONTENT    VARCHAR2(4000) NOT NULL,
    REGIST_DATE    DATE NOT NULL
);

COMMENT ON COLUMN TBL_PRJ_FAQ.NO IS '번호';

COMMENT ON COLUMN TBL_PRJ_FAQ.REF_PRJ_NO IS '프로젝트번호';

COMMENT ON COLUMN TBL_PRJ_FAQ.TITLE IS '제목';

COMMENT ON COLUMN TBL_PRJ_FAQ.CONTENT IS '내용';

COMMENT ON COLUMN TBL_PRJ_FAQ.REGIST_DATE IS '생성날짜';

COMMENT ON TABLE TBL_PRJ_FAQ IS '프로젝트FAQ';

CREATE UNIQUE INDEX TBL_PRJ_FAQ_PK ON TBL_PRJ_FAQ
    ( NO );

ALTER TABLE TBL_PRJ_FAQ
    ADD CONSTRAINT TBL_PRJ_FAQ_PK PRIMARY KEY ( NO )
        USING INDEX TBL_PRJ_FAQ_PK;

COMMENT ON TABLE TBL_PRJ_FAQ IS '프로젝트FAQ';


CREATE TABLE TBL_PRJ_FILE
(
    TYPE    VARCHAR2(20),
    ORIGIN_NAME    VARCHAR2(600),
    NO    NUMBER NOT NULL,
    DELETE_YN    CHAR(1),
    CHANGE_NAME    VARCHAR2(600),
    REGIST_DATE    DATE,
    REF_PRJ_NO    NUMBER NOT NULL
);

COMMENT ON COLUMN TBL_PRJ_FILE.TYPE IS '유형';

COMMENT ON COLUMN TBL_PRJ_FILE.ORIGIN_NAME IS '원본파일명';

COMMENT ON COLUMN TBL_PRJ_FILE.NO IS '프로젝트파일번호';

COMMENT ON COLUMN TBL_PRJ_FILE.DELETE_YN IS '삭제여부';

COMMENT ON COLUMN TBL_PRJ_FILE.CHANGE_NAME IS '변경된파일명';

COMMENT ON COLUMN TBL_PRJ_FILE.REGIST_DATE IS '등록일';

COMMENT ON COLUMN TBL_PRJ_FILE.REF_PRJ_NO IS '프로젝트번호';

COMMENT ON TABLE TBL_PRJ_FILE IS '프로젝트 파일';

CREATE UNIQUE INDEX TBL_PRJ_FILE_PK ON TBL_PRJ_FILE
    ( NO );

ALTER TABLE TBL_PRJ_FILE
    ADD CONSTRAINT TBL_PRJ_FILE_PK PRIMARY KEY ( NO )
        USING INDEX TBL_PRJ_FILE_PK;

COMMENT ON TABLE TBL_PRJ_FILE IS '프로젝트 파일';


CREATE TABLE TBL_PRJ_NEW_INFO
(
    NO    NUMBER NOT NULL,
    TITLE    VARCHAR2(150) NOT NULL,
    CONTENT    CLOB NOT NULL,
    REGIST_DATE    DATE NOT NULL,
    MODIFY_DATE    DATE,
    DELETE_YN    CHAR(1),
    REF_MEMBER_NO    NUMBER,
    REF_PRJ_NO    NUMBER NOT NULL
);

COMMENT ON COLUMN TBL_PRJ_NEW_INFO.NO IS '프로젝트새소식번호';

COMMENT ON COLUMN TBL_PRJ_NEW_INFO.TITLE IS '제목';

COMMENT ON COLUMN TBL_PRJ_NEW_INFO.CONTENT IS '내용';

COMMENT ON COLUMN TBL_PRJ_NEW_INFO.REGIST_DATE IS '생성날짜';

COMMENT ON COLUMN TBL_PRJ_NEW_INFO.MODIFY_DATE IS '수정날짜';

COMMENT ON COLUMN TBL_PRJ_NEW_INFO.DELETE_YN IS '삭제여부';

COMMENT ON COLUMN TBL_PRJ_NEW_INFO.REF_MEMBER_NO IS '작성자';

COMMENT ON COLUMN TBL_PRJ_NEW_INFO.REF_PRJ_NO IS '프로젝트번호';

COMMENT ON TABLE TBL_PRJ_NEW_INFO IS '프로젝트 새소식';

CREATE UNIQUE INDEX TBL_PRJ_NEW_INFO_PK ON TBL_PRJ_NEW_INFO
    ( NO );

ALTER TABLE TBL_PRJ_NEW_INFO
    ADD CONSTRAINT TBL_PRJ_NEW_INFO_PK PRIMARY KEY ( NO )
        USING INDEX TBL_PRJ_NEW_INFO_PK;

COMMENT ON TABLE TBL_PRJ_NEW_INFO IS '프로젝트 새소식';


-- CREATE TABLE TBL_PRJ_NEW_INFO_FILE
-- (
--     NO    NUMBER NOT NULL,
--     ORIGINAL    VARCHAR2(512),
--     CHANGED     VARCHAR2(512),
--     REF_PRJ_NEW_INFO_NO    NUMBER NOT NULL
-- );
--
-- COMMENT ON COLUMN TBL_PRJ_NEW_INFO_FILE.NO IS '새소식파일번호';
--
-- COMMENT ON COLUMN TBL_PRJ_NEW_INFO_FILE.ORIGINAL IS '새소식 원본파일';
-- COMMENT ON COLUMN TBL_PRJ_NEW_INFO_FILE.CHANGED IS '새소식 변경파일';
--
-- COMMENT ON COLUMN TBL_PRJ_NEW_INFO_FILE.REF_PRJ_NEW_INFO_NO IS '프로젝트새소식번호';
--
-- COMMENT ON TABLE TBL_PRJ_NEW_INFO_FILE IS '프로젝트새소식파일';
--
-- CREATE UNIQUE INDEX TBL_PRJ_NEW_INFO_FILE_PK ON TBL_PRJ_NEW_INFO_FILE
--     ( NO );
--
-- ALTER TABLE TBL_PRJ_NEW_INFO_FILE
--     ADD CONSTRAINT TBL_PRJ_NEW_INFO_FILE_PK PRIMARY KEY ( NO )
--         USING INDEX TBL_PRJ_NEW_INFO_FILE_PK;
--
-- COMMENT ON TABLE TBL_PRJ_NEW_INFO_FILE IS '프로젝트새소식파일';


CREATE TABLE TBL_PRJ_PRODUCT_LIST
(
    NO    NUMBER NOT NULL,
    REF_PRJ_NO    NUMBER NOT NULL,
    REF_PRODUCT_NO    NUMBER NOT NULL
);

COMMENT ON COLUMN TBL_PRJ_PRODUCT_LIST.NO IS '상품리스트코드';

COMMENT ON COLUMN TBL_PRJ_PRODUCT_LIST.REF_PRJ_NO IS '프로젝트번호';

COMMENT ON COLUMN TBL_PRJ_PRODUCT_LIST.REF_PRODUCT_NO IS '상품번호';

COMMENT ON TABLE TBL_PRJ_PRODUCT_LIST IS '프로젝트상품리스트';

CREATE UNIQUE INDEX TBL_PRJ_PRODUCT_LIST_PK ON TBL_PRJ_PRODUCT_LIST
    ( NO );

ALTER TABLE TBL_PRJ_PRODUCT_LIST
    ADD CONSTRAINT TBL_PRJ_PRODUCT_LIST_PK PRIMARY KEY ( NO )
        USING INDEX TBL_PRJ_PRODUCT_LIST_PK;

COMMENT ON TABLE TBL_PRJ_PRODUCT_LIST IS '프로젝트상품리스트';


CREATE TABLE TBL_PRJ_PROGRESS
(
    NO    NUMBER NOT NULL,
    REGIST_DATE    DATE,
    STATUS    VARCHAR2(20) NOT NULL,
    CONTENT    VARCHAR2(500),
    REF_PRJ_NO    NUMBER NOT NULL
);

COMMENT ON COLUMN TBL_PRJ_PROGRESS.NO IS '진행코드';

COMMENT ON COLUMN TBL_PRJ_PROGRESS.REGIST_DATE IS '등록일';

COMMENT ON COLUMN TBL_PRJ_PROGRESS.STATUS IS '등록상태';

COMMENT ON COLUMN TBL_PRJ_PROGRESS.CONTENT IS '승인내용';

COMMENT ON COLUMN TBL_PRJ_PROGRESS.REF_PRJ_NO IS '프로젝트번호';

COMMENT ON TABLE TBL_PRJ_PROGRESS IS '프로젝트진행내역';

CREATE UNIQUE INDEX TBL_PRJ_PROGRESS_PK ON TBL_PRJ_PROGRESS
    ( NO );

ALTER TABLE TBL_PRJ_PROGRESS
    ADD CONSTRAINT TBL_PRJ_PROGRESS_PK PRIMARY KEY ( NO )
        USING INDEX TBL_PRJ_PROGRESS_PK;

COMMENT ON TABLE TBL_PRJ_PROGRESS IS '프로젝트진행내역';


CREATE TABLE TBL_PRJ_QNA
(
    CONTENT    VARCHAR2(4000) NOT NULL,
    REF_MEMBER_NO    NUMBER NOT NULL,
    REF_PRJ_NO    NUMBER NOT NULL,
    REGIST_DATE   DATE,
    NO    NUMBER NOT NULL
);

COMMENT ON COLUMN TBL_PRJ_QNA.CONTENT IS '내용';

COMMENT ON COLUMN TBL_PRJ_QNA.REGIST_DATE IS '등록일자';

COMMENT ON COLUMN TBL_PRJ_QNA.REF_MEMBER_NO IS '회원번호';

COMMENT ON COLUMN TBL_PRJ_QNA.REF_PRJ_NO IS '프로젝트번호';

COMMENT ON COLUMN TBL_PRJ_QNA.NO IS '프로젝트QnA코드';

COMMENT ON TABLE TBL_PRJ_QNA IS '프로젝트 Q/A';

CREATE UNIQUE INDEX TBL_PRJ_QNA_PK ON TBL_PRJ_QNA
    ( NO );

ALTER TABLE TBL_PRJ_QNA
    ADD CONSTRAINT TBL_PRJ_QNA_PK PRIMARY KEY ( NO )
        USING INDEX TBL_PRJ_QNA_PK;

COMMENT ON TABLE TBL_PRJ_QNA IS '프로젝트 Q/A';

CREATE TABLE TBL_PRJ_QNA_ANSWER
(
    NO    NUMBER NOT NULL,
    CONTENT    VARCHAR2(4000),
    REGIST_DATE    DATE,
    PRJ_QNA_CODE    NUMBER NOT NULL,
    REF_MEMBER_NO    NUMBER NOT NULL
);

COMMENT ON COLUMN TBL_PRJ_QNA_ANSWER.NO IS '답변코드';

COMMENT ON COLUMN TBL_PRJ_QNA_ANSWER.CONTENT IS '답변내용';

COMMENT ON COLUMN TBL_PRJ_QNA_ANSWER.REGIST_DATE IS '답변일';

COMMENT ON COLUMN TBL_PRJ_QNA_ANSWER.PRJ_QNA_CODE IS '프로젝트QnA코드';

COMMENT ON COLUMN TBL_PRJ_QNA_ANSWER.REF_MEMBER_NO IS '판매자(답변자)';

COMMENT ON TABLE TBL_PRJ_QNA_ANSWER IS '프로젝트QnA답변';

CREATE UNIQUE INDEX TBL_PRJ_QNA_ANSWER_PK ON TBL_PRJ_QNA_ANSWER
    ( NO );

ALTER TABLE TBL_PRJ_QNA_ANSWER
    ADD CONSTRAINT TBL_PRJ_QNA_ANSWER_PK PRIMARY KEY ( NO )
        USING INDEX TBL_PRJ_QNA_ANSWER_PK;

COMMENT ON TABLE TBL_PRJ_QNA_ANSWER IS '프로젝트QnA답변';


CREATE TABLE TBL_PRJ_WISH
(
    REF_MEMBER_NO    NUMBER NOT NULL,
    REF_PRJ_NO    NUMBER NOT NULL
);

COMMENT ON COLUMN TBL_PRJ_WISH.REF_MEMBER_NO IS '회원번호';

COMMENT ON COLUMN TBL_PRJ_WISH.REF_PRJ_NO IS '프로젝트번호';

COMMENT ON TABLE TBL_PRJ_WISH IS '프로젝트 찜하기';

CREATE UNIQUE INDEX TBL_PRJ_WISH_PK ON TBL_PRJ_WISH
    ( REF_MEMBER_NO,REF_PRJ_NO );

ALTER TABLE TBL_PRJ_WISH
    ADD CONSTRAINT TBL_PRJ_WISH_PK PRIMARY KEY ( REF_MEMBER_NO,REF_PRJ_NO )
        USING INDEX TBL_PRJ_WISH_PK;

COMMENT ON TABLE TBL_PRJ_WISH IS '프로젝트 찜하기';


CREATE TABLE TBL_PRODUCT
(
    NO    NUMBER NOT NULL,
    NAME    VARCHAR2(500) NOT NULL,
    INTRODUCTION    VARCHAR2(4000),
    PRICE    NUMBER NOT NULL,
    MAX_QUANTITY    NUMBER NOT NULL,
    STATUS    VARCHAR2(20) NOT NULL,
    PRODUCT_SIZE    VARCHAR2(200)
);

COMMENT ON COLUMN TBL_PRODUCT.NO IS '상품번호';

COMMENT ON COLUMN TBL_PRODUCT.NAME IS '상품명';

COMMENT ON COLUMN TBL_PRODUCT.INTRODUCTION IS '상품소개';

COMMENT ON COLUMN TBL_PRODUCT.PRICE IS '상품가격';

COMMENT ON COLUMN TBL_PRODUCT.MAX_QUANTITY IS '최대수량';

COMMENT ON COLUMN TBL_PRODUCT.STATUS IS '상태';

COMMENT ON COLUMN TBL_PRODUCT.PRODUCT_SIZE IS '사이즈';

COMMENT ON TABLE TBL_PRODUCT IS '상품';

CREATE UNIQUE INDEX TBL_PRODUCT_PK ON TBL_PRODUCT
    ( NO );

ALTER TABLE TBL_PRODUCT
    ADD CONSTRAINT TBL_PRODUCT_PK PRIMARY KEY ( NO )
        USING INDEX TBL_PRODUCT_PK;

COMMENT ON TABLE TBL_PRODUCT IS '상품';


CREATE TABLE TBL_REVIEW
(
    REGIST_DATE    DATE,
    REF_MEMBER_NO    NUMBER NOT NULL,
    CONTENT    VARCHAR2(4000),
    TITLE    VARCHAR2(150),
    DETELE_YN    CHAR(1),
    MODIFY_DATE    DATE,
    REF_DELIVERY_STATUS_CODE    NUMBER NOT NULL
);

COMMENT ON COLUMN TBL_REVIEW.REGIST_DATE IS '등록날짜';

COMMENT ON COLUMN TBL_REVIEW.REF_MEMBER_NO IS '회원번호';

COMMENT ON COLUMN TBL_REVIEW.CONTENT IS '내용';

COMMENT ON COLUMN TBL_REVIEW.TITLE IS '제목';

COMMENT ON COLUMN TBL_REVIEW.DETELE_YN IS '삭제여부';

COMMENT ON COLUMN TBL_REVIEW.MODIFY_DATE IS '수정날짜';

COMMENT ON COLUMN TBL_REVIEW.REF_DELIVERY_STATUS_CODE IS '배송이력코드';

COMMENT ON TABLE TBL_REVIEW IS '펀딩후기';

CREATE UNIQUE INDEX TBL_REVIEW_PK ON TBL_REVIEW
    ( REF_DELIVERY_STATUS_CODE );

ALTER TABLE TBL_REVIEW
    ADD CONSTRAINT TBL_REVIEW_PK PRIMARY KEY ( REF_DELIVERY_STATUS_CODE )
        USING INDEX TBL_REVIEW_PK;

COMMENT ON TABLE TBL_REVIEW IS '펀딩후기';


CREATE TABLE TBL_ROLE_LIST
(
    REF_MEMBER_ROLE_CODE    NUMBER NOT NULL,
    REF_MEMBER_NO    NUMBER NOT NULL
);

COMMENT ON COLUMN TBL_ROLE_LIST.REF_MEMBER_ROLE_CODE IS '권한코드';

COMMENT ON COLUMN TBL_ROLE_LIST.REF_MEMBER_NO IS '회원번호';

COMMENT ON TABLE TBL_ROLE_LIST IS '권한목록';

CREATE UNIQUE INDEX TBL_ROLE_LIST_PK ON TBL_ROLE_LIST
    ( REF_MEMBER_ROLE_CODE,REF_MEMBER_NO );

ALTER TABLE TBL_ROLE_LIST
    ADD CONSTRAINT TBL_ROLE_LIST_PK PRIMARY KEY ( REF_MEMBER_ROLE_CODE,REF_MEMBER_NO )
        USING INDEX TBL_ROLE_LIST_PK;

COMMENT ON TABLE TBL_ROLE_LIST IS '권한목록';


CREATE TABLE TBL_SELLER_ROLE
(
    ACCOUNT_NO    VARCHAR2(20),
    REGIST_NO    VARCHAR2(20),
    NAME    VARCHAR2(50),
    CALL_NUMBER    VARCHAR2(20),
    RPRSN    VARCHAR2(50),
    ADDRESS    VARCHAR2(1000),
    REF_MEMBER_NO    NUMBER NOT NULL,
    BANK    VARCHAR2(20),
    REPORT_NO    VARCHAR2(50)
);

COMMENT ON COLUMN TBL_SELLER_ROLE.ACCOUNT_NO IS '계좌번호';

COMMENT ON COLUMN TBL_SELLER_ROLE.REGIST_NO IS '사업자 등록번호';

COMMENT ON COLUMN TBL_SELLER_ROLE.NAME IS '회사명';

COMMENT ON COLUMN TBL_SELLER_ROLE.CALL_NUMBER IS '대표번호';

COMMENT ON COLUMN TBL_SELLER_ROLE.RPRSN IS '대표자명';

COMMENT ON COLUMN TBL_SELLER_ROLE.ADDRESS IS '주소';

COMMENT ON COLUMN TBL_SELLER_ROLE.REF_MEMBER_NO IS '회원번호';

COMMENT ON COLUMN TBL_SELLER_ROLE.BANK IS '은행';

COMMENT ON COLUMN TBL_SELLER_ROLE.REPORT_NO IS '통신판매업신고번호';

COMMENT ON TABLE TBL_SELLER_ROLE IS '판매자 권한';

CREATE UNIQUE INDEX TBL_SELLER_ROLE_PK ON TBL_SELLER_ROLE
    ( REF_MEMBER_NO );

ALTER TABLE TBL_SELLER_ROLE
    ADD CONSTRAINT TBL_SELLER_ROLE_PK PRIMARY KEY ( REF_MEMBER_NO )
        USING INDEX TBL_SELLER_ROLE_PK;

COMMENT ON TABLE TBL_SELLER_ROLE IS '판매자 권한';


CREATE TABLE TBL_SELLER_ROLE_FILE
(
    NO    NUMBER NOT NULL,
    ID_CODE    VARCHAR2(50),
    ORIGIN_NAME    VARCHAR2(600),
    CHANGE_NAME    VARCHAR2(600),
    DETELE_YN    CHAR(1),
    REF_MEMBER_NO    NUMBER NOT NULL
);

COMMENT ON COLUMN TBL_SELLER_ROLE_FILE.NO IS '판매자정보파일번호';

COMMENT ON COLUMN TBL_SELLER_ROLE_FILE.ID_CODE IS '구분 코드';

COMMENT ON COLUMN TBL_SELLER_ROLE_FILE.ORIGIN_NAME IS '원본파일명';

COMMENT ON COLUMN TBL_SELLER_ROLE_FILE.CHANGE_NAME IS '변경된파일명';

COMMENT ON COLUMN TBL_SELLER_ROLE_FILE.DETELE_YN IS '사용가능';

COMMENT ON COLUMN TBL_SELLER_ROLE_FILE.REF_MEMBER_NO IS '회원번호';

COMMENT ON TABLE TBL_SELLER_ROLE_FILE IS '판매자 신청 파일';

CREATE UNIQUE INDEX TBL_SELLER_ROLE_FILE_PK ON TBL_SELLER_ROLE_FILE
    ( NO );

ALTER TABLE TBL_SELLER_ROLE_FILE
    ADD CONSTRAINT TBL_SELLER_ROLE_FILE_PK PRIMARY KEY ( NO )
        USING INDEX TBL_SELLER_ROLE_FILE_PK;

COMMENT ON TABLE TBL_SELLER_ROLE_FILE IS '판매자 신청 파일';


CREATE TABLE TBL_SELLER_ROLE_REQ
(
    NO    NUMBER NOT NULL,
    REF_MEMBER_NO    NUMBER NOT NULL,
    REGIST_DATE    DATE
);

COMMENT ON COLUMN TBL_SELLER_ROLE_REQ.NO IS '판매자권한신청코드';

COMMENT ON COLUMN TBL_SELLER_ROLE_REQ.REF_MEMBER_NO IS '판매자번호';

COMMENT ON COLUMN TBL_SELLER_ROLE_REQ.REGIST_DATE IS '신청일자';

COMMENT ON TABLE TBL_SELLER_ROLE_REQ IS '판매자권한신청';

CREATE UNIQUE INDEX TBL_SELLER_ROLE_REQ_PK ON TBL_SELLER_ROLE_REQ
    ( NO );

ALTER TABLE TBL_SELLER_ROLE_REQ
    ADD CONSTRAINT TBL_SELLER_ROLE_REQ_PK PRIMARY KEY ( NO )
        USING INDEX TBL_SELLER_ROLE_REQ_PK;

COMMENT ON TABLE TBL_SELLER_ROLE_REQ IS '판매자권한신청';


CREATE TABLE TBL_SHIPPING_TRACK_INFO
(
    NO    NUMBER NOT NULL,
    REF_MEMBER_NO    NUMBER NOT NULL,
    CORP    VARCHAR2(20),
    DOC_NO    VARCHAR2(20),
    REQUEST    VARCHAR2(1000),
    REF_PAYMENT_NO    NUMBER NOT NULL
);

COMMENT ON COLUMN TBL_SHIPPING_TRACK_INFO.NO IS '배송코드';

COMMENT ON COLUMN TBL_SHIPPING_TRACK_INFO.REF_MEMBER_NO IS '회원번호';

COMMENT ON COLUMN TBL_SHIPPING_TRACK_INFO.CORP IS '운송 회사';

COMMENT ON COLUMN TBL_SHIPPING_TRACK_INFO.DOC_NO IS '운송장 번호';

COMMENT ON COLUMN TBL_SHIPPING_TRACK_INFO.REQUEST IS '요청사항';

COMMENT ON COLUMN TBL_SHIPPING_TRACK_INFO.REF_PAYMENT_NO IS '결제코드';

COMMENT ON TABLE TBL_SHIPPING_TRACK_INFO IS '배송정보';

CREATE UNIQUE INDEX TBL_SHIPPING_TRACK_INFO_PK ON TBL_SHIPPING_TRACK_INFO
    ( NO );

ALTER TABLE TBL_SHIPPING_TRACK_INFO
    ADD CONSTRAINT TBL_SHIPPING_TRACK_INFO_PK PRIMARY KEY ( NO )
        USING INDEX TBL_SHIPPING_TRACK_INFO_PK;

COMMENT ON TABLE TBL_SHIPPING_TRACK_INFO IS '배송정보';


CREATE TABLE TBL_SPONSORED_PRJ
(
    REGIST_DATE    DATE,
    AMOUNT    NUMBER,
    NO    NUMBER NOT NULL,
    REF_PRJ_PRODUCT_LIST_NO    NUMBER NOT NULL,
    REF_MEMBER_NO    NUMBER NOT NULL
);

COMMENT ON COLUMN TBL_SPONSORED_PRJ.REGIST_DATE IS '참여 날짜';

COMMENT ON COLUMN TBL_SPONSORED_PRJ.AMOUNT IS '수량';

COMMENT ON COLUMN TBL_SPONSORED_PRJ.NO IS '후원한프로젝트코드';

COMMENT ON COLUMN TBL_SPONSORED_PRJ.REF_PRJ_PRODUCT_LIST_NO IS '상품리스트코드';

COMMENT ON COLUMN TBL_SPONSORED_PRJ.REF_MEMBER_NO IS '회원번호';

COMMENT ON TABLE TBL_SPONSORED_PRJ IS '후원한 프로젝트';

CREATE UNIQUE INDEX TBL_SPONSORED_PRJ_PK ON TBL_SPONSORED_PRJ
    ( NO );

ALTER TABLE TBL_SPONSORED_PRJ
    ADD CONSTRAINT TBL_SPONSORED_PRJ_PK PRIMARY KEY ( NO )
        USING INDEX TBL_SPONSORED_PRJ_PK;

COMMENT ON TABLE TBL_SPONSORED_PRJ IS '후원한 프로젝트';


CREATE TABLE TBL_SUGGEST
(
    NO    NUMBER NOT NULL,
    REGIST_DATE    DATE,
    TITLE    VARCHAR2(150),
    CONTENT    VARCHAR2(4000),
    REF_MEMBER_NO    NUMBER NOT NULL,
    STATUS    VARCHAR2(50),
    MODIFY_DATE    DATE,
    REF_CATEGORY_NO    NUMBER NOT NULL
);

COMMENT ON COLUMN TBL_SUGGEST.NO IS '제안하기게시판번호';

COMMENT ON COLUMN TBL_SUGGEST.REGIST_DATE IS '날짜';

COMMENT ON COLUMN TBL_SUGGEST.TITLE IS '제목';

COMMENT ON COLUMN TBL_SUGGEST.CONTENT IS '내용';

COMMENT ON COLUMN TBL_SUGGEST.REF_MEMBER_NO IS '회원번호';

COMMENT ON COLUMN TBL_SUGGEST.STATUS IS '상태';

COMMENT ON COLUMN TBL_SUGGEST.MODIFY_DATE IS '수정날짜';

COMMENT ON COLUMN TBL_SUGGEST.REF_CATEGORY_NO IS '프로젝트카테고리코드';

COMMENT ON TABLE TBL_SUGGEST IS '제안하기';

CREATE UNIQUE INDEX TBL_SUGGEST_PK ON TBL_SUGGEST
    ( NO );

ALTER TABLE TBL_SUGGEST
    ADD CONSTRAINT TBL_SUGGEST_PK PRIMARY KEY ( NO )
        USING INDEX TBL_SUGGEST_PK;

COMMENT ON TABLE TBL_SUGGEST IS '제안하기';


CREATE TABLE TBL_TERMS
(
    NO    NUMBER NOT NULL,
    CONTENT    VARCHAR2(4000),
    REGIST_DATE    DATE,
    CLASSIFY_CODE    VARCHAR2(20),
    TERMS_INDEX    NUMBER
);

COMMENT ON COLUMN TBL_TERMS.NO IS '약관번호';

COMMENT ON COLUMN TBL_TERMS.CONTENT IS '내용';

COMMENT ON COLUMN TBL_TERMS.REGIST_DATE IS '생성날짜';

COMMENT ON COLUMN TBL_TERMS.CLASSIFY_CODE IS '구분코드';

COMMENT ON COLUMN TBL_TERMS.TERMS_INDEX IS '순번';

COMMENT ON TABLE TBL_TERMS IS '약관';

CREATE UNIQUE INDEX TBL_TERMS_PK ON TBL_TERMS
    ( NO );

ALTER TABLE TBL_TERMS
    ADD CONSTRAINT TBL_TERMS_PK PRIMARY KEY ( NO )
        USING INDEX TBL_TERMS_PK;

COMMENT ON TABLE TBL_TERMS IS '약관';


CREATE TABLE TBL_TERMS_HISTORY
(
    NO    NUMBER NOT NULL,
    CONTENT    VARCHAR2(4000),
    LAST_DATE    DATE,
    REF_TERMS_NO    NUMBER NOT NULL
);

COMMENT ON COLUMN TBL_TERMS_HISTORY.NO IS '변경이력코드';

COMMENT ON COLUMN TBL_TERMS_HISTORY.CONTENT IS '변경내용';

COMMENT ON COLUMN TBL_TERMS_HISTORY.LAST_DATE IS '변경날짜';

COMMENT ON COLUMN TBL_TERMS_HISTORY.REF_TERMS_NO IS '약관번호';

COMMENT ON TABLE TBL_TERMS_HISTORY IS '약관변경이력';

CREATE UNIQUE INDEX TBL_TERMS_HISTORY_PK ON TBL_TERMS_HISTORY
    ( NO );

ALTER TABLE TBL_TERMS_HISTORY
    ADD CONSTRAINT TBL_TERMS_HISTORY_PK PRIMARY KEY ( NO )
        USING INDEX TBL_TERMS_HISTORY_PK;

COMMENT ON TABLE TBL_TERMS_HISTORY IS '약관변경이력';

COMMIT;
