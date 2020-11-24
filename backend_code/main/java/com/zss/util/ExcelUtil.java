package com.zss.util;

import com.zss.po.Instore;
import com.zss.po.Outstore;
import com.zss.po.Singlesale;
import com.zss.po.Store;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;

import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class ExcelUtil {

    //月报表
    public void writeExcelMonthSale(List<Singlesale>list, HttpServletResponse response){
        try{
            //标题列
            String titleRow[] = {"商品名称","出售数量","商品价格","出售时间","利润"};
            //创建Excel文件
            Workbook workbook = new HSSFWorkbook();
            //创建sheet页
            Sheet sheet = workbook.createSheet("月销售表");

            //添加内容到Exel
            //第一行
            Row row = sheet.createRow(0);
            Cell cell = row.createCell(0);//第一行第一列
            cell.setCellValue("月销售表");//给单元格赋值
            //给单元格添加样式
            CellStyle style=workbook.createCellStyle();
            style.setVerticalAlignment(VerticalAlignment.CENTER);//垂直居中
            style.setAlignment(HorizontalAlignment.CENTER);//水平居中
            style.setWrapText(true);//自动换行

            //应用
            cell.setCellStyle(style);

            //单元格字体
            Font font=workbook.createFont();

            font.setFontName("微软雅黑");
            //如果希望设置高度14,则填参数14*20=280！！！！！
            font.setFontHeight((short) 280);//单位是提那

            style.setFont(font);

            //合并单元格
            //CellRangeAddress方法有4个参数，起始行，结束行，起始列，结束列
            sheet.addMergedRegion(new CellRangeAddress(0,0,0,4));

            //第二行
            row = sheet.createRow(1);
            for (int i=0;i<titleRow.length;i++){
                cell = row.createCell(i);
                cell.setCellValue(titleRow[i]);
                cell.setCellStyle(style);
                //如果希望列宽是20,则写20*256
                sheet.setColumnWidth(i,20*256);//差0.62到20宽
            }
            //如果希望设置高度28,则填参数28*20=540！！！！！
            row.setHeight((short)540);

            //单利润
            float profite = 0;
            //总利润
            float sumProfite = 0;
            //单笔总价
            float sale = 0;
            //总售价
            float sumSale = 0;

            //循环写入数据
            for (int i=0;i<list.size();i++){
                row = sheet.createRow(i+2);
                row.setHeight((short)500);
                row.createCell(0).setCellValue(list.get(i).getSalename());
                row.createCell(1).setCellValue(list.get(i).getSaleamount());
                row.createCell(2).setCellValue(list.get(i).getProduct().getPromoteprice());
                sale = list.get(i).getSaleamount() * list.get(i).getProduct().getPromoteprice();
                sumSale += sale;

                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                String date = sdf.format(list.get(i).getSaletime());

                row.createCell(3).setCellValue(date);
                profite = list.get(i).getProduct().getPromoteprice()-list.get(i).getProduct().getOrignalprice();
                sumProfite += profite;
                row.createCell(4).setCellValue(profite);
            }

            //统计总售价、利润
            row = sheet.createRow(list.size()+2);
            row.setHeight((short)500);
            row.createCell(0).setCellValue("总计：");
            row.createCell(1).setCellValue("总售价：");
            row.createCell(2).setCellValue(sumSale);
            row.createCell(3).setCellValue("总利润：");
            row.createCell(4).setCellValue(sumProfite);

            //写文件
            OutputStream outputStream = response.getOutputStream();
            workbook.write(outputStream);
            outputStream.flush();
            outputStream.close();

        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    //年报表
    public void writeExcelYearSale(List<Singlesale>list, HttpServletResponse response){
        try{
            //标题列
            String titleRow[] = {"商品名称","出售数量","商品价格","出售时间","利润"};
            //创建Excel文件
            Workbook workbook = new HSSFWorkbook();
            //创建sheet页
            Sheet sheet = workbook.createSheet("年销售表");

            //添加内容到Exel
            //第一行
            Row row = sheet.createRow(0);
            Cell cell = row.createCell(0);//第一行第一列
            cell.setCellValue("年销售表");//给单元格赋值
            //给单元格添加样式
            CellStyle style=workbook.createCellStyle();
            style.setVerticalAlignment(VerticalAlignment.CENTER);//垂直居中
            style.setAlignment(HorizontalAlignment.CENTER);//水平居中
            style.setWrapText(true);//自动换行

            //应用
            cell.setCellStyle(style);

            //单元格字体
            Font font=workbook.createFont();

            font.setFontName("微软雅黑");
            //如果希望设置高度14,则填参数14*20=280！！！！！
            font.setFontHeight((short) 280);//单位是提那

            style.setFont(font);

            //合并单元格
            //CellRangeAddress方法有4个参数，起始行，结束行，起始列，结束列
            sheet.addMergedRegion(new CellRangeAddress(0,0,0,4));

            //第二行
            row = sheet.createRow(1);
            for (int i=0;i<titleRow.length;i++){
                cell = row.createCell(i);
                cell.setCellValue(titleRow[i]);
                cell.setCellStyle(style);
                //如果希望列宽是20,则写20*256
                sheet.setColumnWidth(i,20*256);//差0.62到20宽
            }
            //如果希望设置高度28,则填参数28*20=540！！！！！
            row.setHeight((short)540);

            //单利润
            float profite = 0;
            //总利润
            float sumProfite = 0;
            //单笔总价
            float sale = 0;
            //总售价
            float sumSale = 0;

            //循环写入数据
            for (int i=0;i<list.size();i++){
                row = sheet.createRow(i+2);
                row.setHeight((short)500);
                row.createCell(0).setCellValue(list.get(i).getSalename());
                row.createCell(1).setCellValue(list.get(i).getSaleamount());
                row.createCell(2).setCellValue(list.get(i).getProduct().getPromoteprice());
                sale = list.get(i).getSaleamount() * list.get(i).getProduct().getPromoteprice();
                sumSale += sale;

                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                String date = sdf.format(list.get(i).getSaletime());

                row.createCell(3).setCellValue(date);
                profite = list.get(i).getProduct().getPromoteprice()-list.get(i).getProduct().getOrignalprice();
                sumProfite += profite;
                row.createCell(4).setCellValue(profite);
            }

            //统计总售价、利润
            row = sheet.createRow(list.size()+2);
            row.setHeight((short)500);
            row.createCell(0).setCellValue("总计：");
            row.createCell(1).setCellValue("总售价：");
            row.createCell(2).setCellValue(sumSale);
            row.createCell(3).setCellValue("总利润：");
            row.createCell(4).setCellValue(sumProfite);

            //写文件
            OutputStream outputStream = response.getOutputStream();
            workbook.write(outputStream);
            outputStream.flush();
            outputStream.close();

        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    //进货报表
    public void writeExcelInstore(List<Instore>list, HttpServletResponse response){
        try{
            //标题列
            String titleRow[] = {"商品名称","进货数量","商品价格","进货时间","进货厂商"};
            //创建Excel文件
            Workbook workbook = new HSSFWorkbook();
            //创建sheet页
            Sheet sheet = workbook.createSheet("进货报表");

            //添加内容到Exel
            //第一行
            Row row = sheet.createRow(0);
            Cell cell = row.createCell(0);//第一行第一列
            cell.setCellValue("进货报表");//给单元格赋值
            //给单元格添加样式
            CellStyle style=workbook.createCellStyle();
            style.setVerticalAlignment(VerticalAlignment.CENTER);//垂直居中
            style.setAlignment(HorizontalAlignment.CENTER);//水平居中
            style.setWrapText(true);//自动换行

            //应用
            cell.setCellStyle(style);

            //单元格字体
            Font font=workbook.createFont();

            font.setFontName("微软雅黑");
            //如果希望设置高度14,则填参数14*20=280！！！！！
            font.setFontHeight((short) 280);//单位是提那

            style.setFont(font);

            //合并单元格
            //CellRangeAddress方法有4个参数，起始行，结束行，起始列，结束列
            sheet.addMergedRegion(new CellRangeAddress(0,0,0,4));

            //第二行
            row = sheet.createRow(1);
            for (int i=0;i<titleRow.length;i++){
                cell = row.createCell(i);
                cell.setCellValue(titleRow[i]);
                cell.setCellStyle(style);
                //如果希望列宽是20,则写20*256
                sheet.setColumnWidth(i,20*256);//差0.62到20宽
            }
            //如果希望设置高度28,则填参数28*20=540！！！！！
            row.setHeight((short)540);

            //循环写入数据
            for (int i=0;i<list.size();i++){
                row = sheet.createRow(i+2);
                row.setHeight((short)500);
                row.createCell(0).setCellValue(list.get(i).getInname());
                row.createCell(1).setCellValue(list.get(i).getInamount());
                row.createCell(2).setCellValue(list.get(i).getInprice());

                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                String date = sdf.format(list.get(i).getIntime());

                row.createCell(3).setCellValue(date);
                row.createCell(4).setCellValue(list.get(i).getInfactory());
            }

            //写文件
            OutputStream outputStream = response.getOutputStream();
            workbook.write(outputStream);
            outputStream.flush();
            outputStream.close();

        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    //退货报表
    public void writeExcelOutstore(List<Outstore>list, HttpServletResponse response){
        try{
            //标题列
            String titleRow[] = {"商品名称","退货数量","商品价格","退货时间","退货厂商","退货原因"};
            //创建Excel文件
            Workbook workbook = new HSSFWorkbook();
            //创建sheet页
            Sheet sheet = workbook.createSheet("退货报表");

            //添加内容到Exel
            //第一行
            Row row = sheet.createRow(0);
            Cell cell = row.createCell(0);//第一行第一列
            cell.setCellValue("退货报表");//给单元格赋值
            //给单元格添加样式
            CellStyle style=workbook.createCellStyle();
            style.setVerticalAlignment(VerticalAlignment.CENTER);//垂直居中
            style.setAlignment(HorizontalAlignment.CENTER);//水平居中
            style.setWrapText(true);//自动换行

            //应用
            cell.setCellStyle(style);

            //单元格字体
            Font font=workbook.createFont();

            font.setFontName("微软雅黑");
            //如果希望设置高度14,则填参数14*20=280！！！！！
            font.setFontHeight((short) 280);//单位是提那

            style.setFont(font);

            //合并单元格
            //CellRangeAddress方法有4个参数，起始行，结束行，起始列，结束列
            sheet.addMergedRegion(new CellRangeAddress(0,0,0,5));

            //第二行
            row = sheet.createRow(1);
            for (int i=0;i<titleRow.length;i++){
                cell = row.createCell(i);
                cell.setCellValue(titleRow[i]);
                cell.setCellStyle(style);
                //如果希望列宽是20,则写20*256
                sheet.setColumnWidth(i,20*256);//差0.62到20宽
            }
            //如果希望设置高度28,则填参数28*20=540！！！！！
            row.setHeight((short)540);

            //循环写入数据
            for (int i=0;i<list.size();i++){
                row = sheet.createRow(i+2);
                row.setHeight((short)500);
                row.createCell(0).setCellValue(list.get(i).getOutname());
                row.createCell(1).setCellValue(list.get(i).getOutamount());
                row.createCell(2).setCellValue(list.get(i).getOutprice());
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                String date = sdf.format(list.get(i).getOuttime());
                row.createCell(3).setCellValue(date);
                row.createCell(4).setCellValue(list.get(i).getOutfactory());
                row.createCell(5).setCellValue(list.get(i).getOutreason());
            }

            //写文件
            OutputStream outputStream = response.getOutputStream();
            workbook.write(outputStream);
            outputStream.flush();
            outputStream.close();

        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    //库存报表
    public void writeExcelStore(List<Store>list, HttpServletResponse response){
        try{
            //标题列
            String titleRow[] = {"商品名称","入库时间","库存数量","损坏数量","是否上架"};
            //创建Excel文件
            Workbook workbook = new HSSFWorkbook();
            //创建sheet页
            Sheet sheet = workbook.createSheet("库存报表");

            //添加内容到Exel
            //第一行
            Row row = sheet.createRow(0);
            Cell cell = row.createCell(0);//第一行第一列
            cell.setCellValue("库存报表");//给单元格赋值
            //给单元格添加样式
            CellStyle style=workbook.createCellStyle();
            style.setVerticalAlignment(VerticalAlignment.CENTER);//垂直居中
            style.setAlignment(HorizontalAlignment.CENTER);//水平居中
            style.setWrapText(true);//自动换行

            //应用
            cell.setCellStyle(style);

            //单元格字体
            Font font=workbook.createFont();

            font.setFontName("微软雅黑");
            //如果希望设置高度14,则填参数14*20=280！！！！！
            font.setFontHeight((short) 280);//单位是提那

            style.setFont(font);

            //合并单元格
            //CellRangeAddress方法有4个参数，起始行，结束行，起始列，结束列
            sheet.addMergedRegion(new CellRangeAddress(0,0,0,4));

            //第二行
            row = sheet.createRow(1);
            for (int i=0;i<titleRow.length;i++){
                cell = row.createCell(i);
                cell.setCellValue(titleRow[i]);
                cell.setCellStyle(style);
                //如果希望列宽是20,则写20*256
                sheet.setColumnWidth(i,20*256);//差0.62到20宽
            }
            //如果希望设置高度28,则填参数28*20=540！！！！！
            row.setHeight((short)540);

            //循环写入数据
            for (int i=0;i<list.size();i++){
                row = sheet.createRow(i+2);
                row.setHeight((short)500);
                row.createCell(0).setCellValue(list.get(i).getStorename());
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                String date = sdf.format(list.get(i).getStoretime());
                row.createCell(1).setCellValue(date);
                row.createCell(2).setCellValue(list.get(i).getAmount());
                row.createCell(3).setCellValue(list.get(i).getBadamount());
                int flag1 = list.get(i).getIsup();
                if(flag1 == 1) row.createCell(4).setCellValue("是");
                else row.createCell(4).setCellValue("否");
            }

            //写文件
            OutputStream outputStream = response.getOutputStream();
            workbook.write(outputStream);
            outputStream.flush();
            outputStream.close();

        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    //积压/缺货报表
    public void writeExcelupperlower(List<Store>upper,List<Store>lower, HttpServletResponse response){
        try{
            //标题列
            String titleRow[] = {"商品名称","是否积压","超标量","是否缺货","缺少量"};
            //创建Excel文件
            Workbook workbook = new HSSFWorkbook();
            //创建sheet页
            Sheet sheet = workbook.createSheet("积压-缺货报表");

            //添加内容到Exel
            //第一行
            Row row = sheet.createRow(0);
            Cell cell = row.createCell(0);//第一行第一列
            cell.setCellValue("积压-缺货报表");//给单元格赋值
            //给单元格添加样式
            CellStyle style=workbook.createCellStyle();
            style.setVerticalAlignment(VerticalAlignment.CENTER);//垂直居中
            style.setAlignment(HorizontalAlignment.CENTER);//水平居中
            style.setWrapText(true);//自动换行

            //应用
            cell.setCellStyle(style);

            //单元格字体
            Font font=workbook.createFont();

            font.setFontName("微软雅黑");
            //如果希望设置高度14,则填参数14*20=280！！！！！
            font.setFontHeight((short) 280);//单位是提那

            style.setFont(font);

            //合并单元格
            //CellRangeAddress方法有4个参数，起始行，结束行，起始列，结束列
            sheet.addMergedRegion(new CellRangeAddress(0,0,0,4));

            //第二行
            row = sheet.createRow(1);
            for (int i=0;i<titleRow.length;i++){
                cell = row.createCell(i);
                cell.setCellValue(titleRow[i]);
                cell.setCellStyle(style);
                //如果希望列宽是20,则写20*256
                sheet.setColumnWidth(i,20*256);//差0.62到20宽
            }
            //如果希望设置高度28,则填参数28*20=540！！！！！
            row.setHeight((short)540);

            //循环写入数据

            for (int i=0;i<upper.size();i++){
                row = sheet.createRow(i+2);
                row.setHeight((short)500);
                row.createCell(0).setCellValue(upper.get(i).getStorename());
                row.createCell(1).setCellValue("是");
                row.createCell(2).setCellValue(upper.get(i).getAmount()-upper.get(i).getUpperlimit());
                row.createCell(3).setCellValue("否");
                row.createCell(4).setCellValue("");
            }
            for (int i=0;i<lower.size();i++){
                row = sheet.createRow(i+2+upper.size());
                row.setHeight((short)500);
                row.createCell(0).setCellValue(lower.get(i).getStorename());
                row.createCell(1).setCellValue("否");
                row.createCell(2).setCellValue("");
                row.createCell(3).setCellValue("是");
                row.createCell(4).setCellValue(lower.get(i).getLowerlimit()-lower.get(i).getAmount());
            }

            //写文件
            OutputStream outputStream = response.getOutputStream();
            workbook.write(outputStream);
            outputStream.flush();
            outputStream.close();

        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

}
