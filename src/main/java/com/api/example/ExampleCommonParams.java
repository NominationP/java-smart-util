package com.api.example;

import java.util.ArrayList;
import java.util.HashMap;

public class ExampleCommonParams {

    public static ArrayList<String> notRequiredExample = new ArrayList<String>() {{
        add("birthday");
    }};

    public static HashMap<String, String> introsExample = new HashMap<String, String>() {{
        put("resultCode", "请求结果 10000:成功");
        put("resultMessage", "请求结果说明");
        put("items", "请求内容");
        put("birthday", "生日信息");
        put("year", "年份");
        put("teachers", "教师信息数组");
        put("code", "编码");
        put("name", "教师名称");
    }};
}
