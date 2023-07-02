package com.idle.osmas.seller.service;

import com.idle.osmas.member.dto.MemberDTO;
import com.idle.osmas.seller.dto.*;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RegistProjectService {


    int selectMemberNoById(String userId);

}
