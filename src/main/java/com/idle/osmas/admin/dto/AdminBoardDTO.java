    package com.idle.osmas.admin.dto;

    import com.idle.osmas.admin.dto.AdminBoardDTO;
    import com.idle.osmas.member.dto.MemberDTO;
    import lombok.*;

    import java.time.LocalDate;
    import java.util.List;

    @Getter
    @Setter
    @ToString
    @AllArgsConstructor
    @NoArgsConstructor
    public class AdminBoardDTO {

        private int no;  // index

        private String title; // 제목

        private String content; // 컨텐츠

        private LocalDate registDate; // 게시판 등록일

        private LocalDate modifyDate; // 게시판 수정일

        private String classifyCode; //구분코드

        private int refmemberno;  //작성자

        private char deleteYN; //삭제여부

        private List<AdminBoardFileDTO> fileList;

        private String category;

    }
