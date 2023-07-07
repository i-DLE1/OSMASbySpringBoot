package com.idle.osmas.admin.dao;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TermsMapper {
    int termsInputGo(String title, String content);
}
