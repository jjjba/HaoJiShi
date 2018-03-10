package com.haojishi.service;

import com.haojishi.util.BusinessMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class PositionService {

    public BusinessMessage getAllPosition(){
        BusinessMessage businessMessage =new BusinessMessage();
        return businessMessage;
    }


    public BusinessMessage getPositionByCompanyId(Integer companyId){
        BusinessMessage businessMessage =new BusinessMessage();
        return businessMessage;
    }


}
