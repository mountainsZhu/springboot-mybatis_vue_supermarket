package com.zss.controller;

import com.zss.po.Instore;
import com.zss.service.InStoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class InStoreController {

    @Autowired
    private InStoreService inStoreService;

    @RequestMapping("selectInstore")
    @ResponseBody
    List<Instore> selectInstore(String inname,String intime,String infactory){
        return inStoreService.selectInstore(inname,intime,infactory);
    }

    @RequestMapping("inStore")
    @ResponseBody
    int inStore(Instore instore,float promoteprice,int guarantee,String providername,String phone,String email){
        return inStoreService.inStore(instore,promoteprice,guarantee,providername,phone,email);
    }

    @RequestMapping("inStore2")
    @ResponseBody
    int inStore2(Instore instore){
        return inStoreService.inStore2(instore);
    }

}
