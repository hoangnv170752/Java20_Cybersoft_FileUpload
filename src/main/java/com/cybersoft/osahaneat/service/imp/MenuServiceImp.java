package com.cybersoft.osahaneat.service.imp;

import org.springframework.web.multipart.MultipartFile;

public interface MenuServiceImp {
    boolean insertFood(
            MultipartFile file,
            String name,
            String description,
            double price,
            String instruction,
            int cate_res_id
    );
}