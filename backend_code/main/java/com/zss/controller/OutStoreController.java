package com.zss.controller;

import com.zss.po.Outstore;
import com.zss.service.OutStoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class OutStoreController {

    @Autowired
    private OutStoreService outStoreService;

    @RequestMapping("selectOutstore")
    @ResponseBody
    List<Outstore> selectOutstore(String outname,String outtime,String outfactory){
        return outStoreService.selectOutstore(outname,outtime,outfactory);
    }

    @RequestMapping("outStore")
    @ResponseBody
    int outStore(Outstore outstore){
        return outStoreService.outStore(outstore);
    }

}
