package com.shenhesoft.logistics.manage.web.realm;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
/**
 * @description:无权处理
 * 
 * @author shilvfei
 * 
 * @date 2017年12月12日
 */
@Controller
public class ErrorController {
    @RequestMapping(value = "/error/unauthorized")
    public String unauthorized(){
        return "/error/jsp/unauthorized";
    }
}
