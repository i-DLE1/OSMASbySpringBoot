package com.idle.osmas.seller.service;

import com.idle.osmas.seller.dto.OptionDTO;
import com.idle.osmas.seller.dto.SalesDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
public interface SalesService {

    public List<SalesDTO> selectAllProject();
    public SalesDTO selectProjectByNo(int no);

    public List<OptionDTO> selectAllOption();


}
