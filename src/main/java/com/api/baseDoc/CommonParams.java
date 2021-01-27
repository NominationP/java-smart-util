package com.api.baseDoc;

import java.util.ArrayList;
import java.util.HashMap;

public class CommonParams {
    public static ArrayList<String> notRequired = new ArrayList<String>() {{
        add("timeLength");
        add("freq");
        add("quantity");
        add("productTypeCode");
        add("productTypeName");
        add("buildingRate");
        add("searchStr");
        add("pageNum");
        add("pageSize");

    }};
//    public static HashMap<String, Intros> introsNew = new HashMap<String, Intros>() {{
//
//        String s = "searchStr 搜索字段 否\n" +
//                "pageNum 页数 否\n" +
//                "pageSize 页数据数量 否\n" +
//                "totalSize 数据总量 是\n" +
//                "totalPages 总页数 是\n" +
//                ""
//
//
//        String name = "isRequired";
//        String desc = "搜索字段";
//        String isRequired = "否";
//        put(name, new Intros().setName(name).setDes(desc).setIsRequired(isRequired));
//
//        name = "isRequired";
//        desc = "搜索字段";
//        isRequired = "否";
//        put(name, new Intros().setName(name).setDes(desc).setIsRequired(isRequired));
//
//
//    }};

    public static HashMap<String, String> intros = new HashMap<String, String>() {{
        put("resultCode", "请求结果 10000:成功");
        put("resultMessage", "请求结果说明");
        put("items", "请求内容");

        put("publishType", "销售类型");
        put("code", "编码");
        put("name", "名称");

        put("contractNo", "合同号");
        put("mediaType", "媒体类型");
        put("beginDate", "上刊时间");
        put("endDate", "下刊时间");
        put("fcsCityId", "发布地编码");
        put("fcsCityIds", "发布地编码Id数组");
        put("fcsCityName", "发布地名称");
        put("publishId", "发布明细ID");
        put("publishIds", "发布明细ID数组");
        put("superviseBrandCode", "销售类型编码");
        put("superviseBrandName", "销售类型名称");
        put("standardBeginDate", "上刊日期");
        put("standardEndDate", "下刊日期");
        put("timeLength", "时长");
        put("freq", "频次");
        put("quantity", "发布数量");
        put("productTypeCode", "套装编码");
        put("productTypeName", "套装名称");
        put("buildingRate", "挑楼系数");

        put("publishDetails", "发布明细");
        put("saleCities", "销售城市名称");
        put("reports", "申请报告数组");




        put("searchStr", "搜索字段");
        put("pageNum", "当前页数 默认为1");
        put("pageSize", "当前页数据总量 默认为6");
        put("totalSize", "数据总量");
        put("totalPages", "总共页数");
        put("countCheckStateReport", "待确认核对版报告");
        put("countStampStateReport", "待确认盖章版报告");
        put("countAllReport", "申请报告总数");
        put("newFlagStr", "new标识,申请当天显示");
        put("brandName", "报告品牌");
        put("remark", "备注");
        put("saleCity", "销售城市");
        put("operatorName", "操作人名称");
        put("createdAt", "创建日期");
        put("updatedAt", "更新日期");
        put("reportId", "报告ID");
        put("reportState", "报告状态");
        put("operatorId", "操作人ID");


    }};
}
