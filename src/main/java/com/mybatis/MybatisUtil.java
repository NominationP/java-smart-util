package com.mybatis;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.google.common.base.CaseFormat;
import org.apache.commons.lang.StringUtils;

public class MybatisUtil {

    public static MybatisUtil mybatisUtil = new MybatisUtil();

    public static String tableName = "third_monitor_report";
    public static String shortName = "tmr";
    public static String str = "id,contract_no,publish_type,brand_name,supervise_brand_name,sale_city,media_type,standard_begin_date,standard_end_date,supervise_brand_code,remark,mark_type,created_at,updated_at,operator_id,operator_name,";
    public static String type = "cn.focusmedia.entity.ThirdMonitorReport";
    private String shortPrefixWithOutIdColumns;
    private String withOutIdColumns;
    private ArrayList<String> underlineColumnsWithoutIdArray;
    private ArrayList<String> underlineColumnsArray;
    /**
     * [userName,notId]
     */
    private ArrayList<String> camelCaseWithOutIdArray;
    private ArrayList<String> camelCaseArray;

    /**
     * <sql id="columns">
     * contract_no,publish_type,brand_name,supervise_brand_name,sale_city,media_type,standard_begin_date,standard_end_date,supervise_brand_code,remark,mark_type,created_at,updated_at
     * </sql>
     * <p>
     * <!--    除了 创建时间和更新时间-->
     * <sql id="batch_insert_values">
     * #{item.contractNo},#{item.publishType},#{item.brandName},#{item.superviseBrandName},#{item.saleCity},#{item.mediaType},#{item.standardBeginDate},#{item.standardEndDate},#{item.superviseBrandCode},#{item.remark},#{item.markType}
     * </sql>
     */
    public static void main(String[] args) throws IOException {

//        Scanner sc = new Scanner(System.in);
//        System.out.print("Enter a string: ");
//        String str = sc.nextLine();


        mybatisUtil.setShortPrefixOrNotWithOutId(str);

        System.out.println("======columns=====================\n");
        mybatisUtil.printBasic();
        mybatisUtil.printColumns();
        mybatisUtil.printColumnsWithShortName();
        mybatisUtil.printInsertValues();
        mybatisUtil.printBatchInsertValues();
        mybatisUtil.printResultMap();


    }

    private void printColumnsWithShortName() {
        StringBuilder stringBuilder = new StringBuilder();
        for (String s : underlineColumnsArray) {
            stringBuilder.append(shortName).append(".").append(s)
                    .append(" ").append(shortName)
                    .append(StringUtils.capitalize(underlineToCamelCase(s)))
                    .append(", ");
        }

        String columns = "<sql id=\"columnsWithShortName\">" + stringBuilder.toString().trim().replaceAll(",$", "") + "</sql>";
        System.out.println(columns);
    }

    private void printBasic() {
        System.out.printf("<sql id=\"%s\"> %s </sql>\n", "table_name", tableName);
        System.out.printf("<sql id=\"%s\"> %s </sql>\n", "short_name", shortName);
    }

    private void printResultMap() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("<resultMap id=\"result\" type=\"").append(type).append("\"> \n");
        System.out.println();
        for (String s : camelCaseArray) {
            stringBuilder.append("\t<result property=\"").append(s)
                    .append("\" column=\"").append(shortName)
                    .append(StringUtils.capitalize(s)).append("\"/>\n");
        }
        stringBuilder.append("</resultMap>\n");

        System.out.println(stringBuilder.toString());
    }

    /**
     * 去掉 <sql id="columns">
     */
    private void setShortPrefixOrNotWithOutId(String str) {
        camelCaseWithOutIdArray = Arrays.stream(mybatisUtil.underlineToCamelCase(str).split(","))
                .filter(i -> !"id".equals(i)).collect(Collectors.toCollection(ArrayList::new));
        camelCaseArray = Arrays.stream(mybatisUtil.underlineToCamelCase(str).split(","))
                .collect(Collectors.toCollection(ArrayList::new));


        StringBuilder newS = new StringBuilder();
        StringBuilder newS2 = new StringBuilder();
        underlineColumnsWithoutIdArray = new ArrayList<>();
        underlineColumnsArray = new ArrayList<>();
        for (String s : str.split(",")) {
            if (!"id".equalsIgnoreCase(s)) {
                newS.append(shortName).append(".").append(s).append(",");
                newS2.append(s).append(",");
                underlineColumnsWithoutIdArray.add(s);
            }
            underlineColumnsArray.add(s);
        }
        shortPrefixWithOutIdColumns = newS.toString().replaceAll(",$", "");
        withOutIdColumns = newS2.toString().replaceAll(",$", "");
    }

    private void printBatchInsertValues() {
        String s2 = mybatisUtil.underlineToCamelCase(withOutIdColumns);
        String s3 = mybatisUtil.camelCaseToInsertValues(s2);
        System.out.println("<sql id=\"batch_insert_values\">" + s3 + "</sql>");
    }

    private void printInsertValues() {
        String s = mybatisUtil.underlineToCamelCase(withOutIdColumns);
        String s1 = mybatisUtil.camelCaseToInsertValues1(s);
        s1 = "<sql id=\"insert_values\">" + s1 + "</sql>";
        System.out.println(s1);
    }

    private void printColumns() {
        String columns = "<sql id=\"columns\">" + withOutIdColumns + "</sql>";
        System.out.println(columns);
    }

    public String underlineToCamelCase(String underLine) {
        String to = CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, underLine);
        return toLowerCaseFirstOne(to);
    }

    private String toLowerCaseFirstOne(String s) {
        if (Character.isLowerCase(s.charAt(0))) {
            return s;
        } else {
            return Character.toLowerCase(s.charAt(0)) + s.substring(1);
        }
    }

    public String camelCaseToInsertValues1(String camelCase) {
        StringBuilder res = new StringBuilder();
        for (String s : camelCase.split(",")) {
            if ("createdAt".equalsIgnoreCase(s) || "updatedAt".equalsIgnoreCase(s)) {
                res.append("CURRENT_TIMESTAMP,");
            } else {
                res.append("#{").append(s).append("},");
            }
        }
        return StringUtils.strip(String.valueOf(res), ",");
    }

    public String camelCaseToInsertValues(String camelCase) {
        StringBuilder res = new StringBuilder();
        for (String s : camelCase.split(",")) {
            if ("createdAt".equalsIgnoreCase(s) || "updatedAt".equalsIgnoreCase(s)) {
                res.append("CURRENT_TIMESTAMP,");
            } else {
                res.append("#{item.").append(s).append("},");
            }
        }
        return StringUtils.strip(String.valueOf(res), ",");
    }
}

