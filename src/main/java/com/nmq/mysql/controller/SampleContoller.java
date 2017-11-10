package com.nmq.mysql.controller;

import com.nmq.mysql.entity.TbCart;
import com.nmq.mysql.service.ITbCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by niemengquan on 2017/11/9.
 */
@Controller
public class SampleContoller {

    @RequestMapping(value = "/")
    @ResponseBody
    String home(){
       return "Hello world!";
    }


}
