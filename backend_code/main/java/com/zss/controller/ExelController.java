package com.zss.controller;

import com.zss.dao.StoreDao;
import com.zss.po.Instore;
import com.zss.po.Outstore;
import com.zss.po.Singlesale;
import com.zss.po.Store;
import com.zss.service.InStoreService;
import com.zss.service.OutStoreService;
import com.zss.service.SinglesaleService;
import com.zss.util.ExcelUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URLEncoder;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

@Controller
public class ExelController {

    @Autowired
    private SinglesaleService singlesaleService;
    @Autowired
    private InStoreService inStoreService;
    @Autowired
    private OutStoreService outStoreService;
    @Autowired
    private StoreDao storeDao;

    ExcelUtil excelUtil = new ExcelUtil();

    //月报表
    @RequestMapping(value = "monthSaleExcel")
    @ResponseBody
    public void monthSaleExcel(HttpServletResponse response, String year, String month) throws Exception {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/vnd.ms-excel;charset=UTF-8");
        response.setHeader("content-dispostion", "attachemnt;filename=" + URLEncoder.encode(year + "年" + month + "月销售报表.xls", "UTF-8"));

        List<Singlesale> list = singlesaleService.selectSaleByMonth(year, month);
        excelUtil.writeExcelMonthSale(list, response);
    }

    //年报表
    @RequestMapping(value = "yearSaleExcel")
    @ResponseBody
    public void yearSaleExcel(HttpServletResponse response, String year) throws Exception {
        System.out.println(year);
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/vnd.ms-excel;charset=UTF-8");
        response.setHeader("content-dispostion", "attachemnt;filename=" + URLEncoder.encode(year + "年月销售报表.xls", "UTF-8"));

        List<Singlesale> list = singlesaleService.selectSaleByYear(year);

        excelUtil.writeExcelYearSale(list, response);
    }

    //进货报表
    @RequestMapping(value = "inSaleExcel")
    @ResponseBody
    public void inSaleExcel(HttpServletResponse response ,String inname,String intime,String infactory) throws Exception {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/vnd.ms-excel;charset=UTF-8");
        response.setHeader("content-dispostion", "attachemnt;filename=" + URLEncoder.encode("进货报表.xls", "UTF-8"));

        if(intime!=""){
            Date date = new SimpleDateFormat("EEE MMM dd yyyy HH:mm:ss z", Locale.ENGLISH).parse(intime);
            intime = new SimpleDateFormat("yyyy-MM-dd").format(date);
        }

        List<Instore> list = inStoreService.selectInstore(inname,intime,infactory);
        System.out.println(list);
        excelUtil.writeExcelInstore(list,response);
    }

    //退货报表
    @RequestMapping(value = "outSaleExcel")
    @ResponseBody
    public void outSaleExcel(HttpServletResponse response , String outname,String outtime,String outfactory) throws Exception {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/vnd.ms-excel;charset=UTF-8");
        response.setHeader("content-dispostion", "attachemnt;filename=" + URLEncoder.encode("退货报表.xls", "UTF-8"));

        if(outtime!=""){
            Date date = new SimpleDateFormat("EEE MMM dd yyyy HH:mm:ss z", Locale.ENGLISH).parse(outtime);
            outtime = new SimpleDateFormat("yyyy-MM-dd").format(date);
        }

        List<Outstore> list = outStoreService.selectOutstore(outname,outtime,outfactory);
        excelUtil.writeExcelOutstore(list,response);
    }

    //库存报表(可查看货物损坏情况)
    @RequestMapping(value = "storeExcel")
    @ResponseBody
    public void StoreExcel(HttpServletResponse response , String storetime,String storename) throws Exception {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/vnd.ms-excel;charset=UTF-8");
        response.setHeader("content-dispostion", "attachemnt;filename=" + URLEncoder.encode("退货报表.xls", "UTF-8"));

        if(storetime!=""){
            Date date = new SimpleDateFormat("EEE MMM dd yyyy HH:mm:ss z", Locale.ENGLISH).parse(storetime);
            storetime = new SimpleDateFormat("yyyy-MM-dd").format(date);
        }

        List<Store> list = storeDao.selectAllStore(storetime,storename);
        excelUtil.writeExcelStore(list,response);
    }

    //当前库存积压/缺货
    @RequestMapping(value = "upperlowerExcel")
    @ResponseBody
    public void upperlowerExcel(HttpServletResponse response) throws Exception {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/vnd.ms-excel;charset=UTF-8");
        response.setHeader("content-dispostion", "attachemnt;filename=" + URLEncoder.encode("退货报表.xls", "UTF-8"));

        List<Store> upper = storeDao.isUpper();
        List<Store> lower = storeDao.isLower();
        excelUtil.writeExcelupperlower(upper,lower,response);
    }
}
