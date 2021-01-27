package com.api;

import com.api.baseDoc.CommonParams;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.api.baseDoc.CommonParams.intros;
import static com.api.baseDoc.CommonParams.notRequired;

public class JsonToApiDoc {

    private static HashMap<String, String> intros = CommonParams.intros;
    private static ArrayList<String> notRequired = CommonParams.notRequired;


    public static void main(String[] args) throws IOException {



        JsonToApiDoc jsonToApiDoc = new JsonToApiDoc();

        List<String> result = new ArrayList<>();
        try (Stream<Path> walk = Files.walk(Paths.get("src/main/java/com/api/baseDoc/thirdPartyReport/"))) {

            result = walk.filter(Files::isRegularFile)
                    .sorted()
                    .map(Path::toString).collect(Collectors.toList());

        } catch (IOException e) {
            e.printStackTrace();
        }

        for (String fileName : result) {
            String s = jsonToApiDoc.readToString(fileName);
            jsonToApiDoc.generateAPi(s);
        }
    }

    public String readToString(String fileName) throws IOException {

        File file = new File(fileName);
        FileInputStream fis = new FileInputStream(file);
        byte[] data = new byte[(int) file.length()];
        fis.read(data);
        fis.close();

        return new String(data, StandardCharsets.UTF_8);
    }


    public void generateAPi(String s) {

        String[] split = s.split("```");
        String pre = split[0].trim();
        String inputBody = split[1].trim();
        String outputBody = split[2].trim();
        System.out.println(pre);


        System.out.println("\n###入参\n");
        if ("?".equals(inputBody.substring(0, 1))) {
            processGetInput(inputBody);
        } else {
            processPost(inputBody);
        }


        System.out.println("\n###返回\n");
        processPost(outputBody);


        System.out.println("\n###实例\n");
        System.out.println("```json\n" + inputBody + "\n```");
        System.out.println("```json\n" + outputBody + "\n```");
    }

    private void processPost(String inputBody) {
        JSONObject jobject = new JSONObject(inputBody);
        System.out.printf("| %s | %s | %s | %s |\n", "字段", "类型", "说明", "是否必填");
        System.out.println("| ---------- | ------ | ------------------ | -------- |");
        process(jobject);
    }

    private static void processGetInput(String inputBody) {
        System.out.printf("| %s | %s | %s | %s |\n", "字段", "类型", "说明", "是否必填");
        System.out.println("| ---------- | ------ | ------------------ | -------- |");
        String substring = inputBody.trim().substring(1);
        for (String s1 : substring.split("&")) {
            String[] split1 = s1.split("=");
            String key = split1[0];
            printFormat(key, "String", "", "");
        }
    }

    public static void process(JSONObject jobject) {


        for (int i = 0; i < jobject.names().length(); i++) {
            String key = jobject.names().getString(i);
            Object value = jobject.get(jobject.names().getString(i));

            printFormat(key, value.getClass().getSimpleName(), "", "是");


            if (value instanceof JSONObject) {
                process((JSONObject) value);
            } else if (value instanceof Integer || value instanceof String) {
            } else {
                JSONArray valueArray = (JSONArray) value;
                Object o = valueArray.get(0);
                if (!(o instanceof Integer) && !(o instanceof String)) {
                    process((JSONObject) o);
                }
            }
        }
    }

    public static void printFormat(String key, String typeName, String intro, String isRequired) {
        typeName = typeName.replaceFirst("JSON", "");
        if (intro.isEmpty()) {
            intro = intros.getOrDefault(key, "");
        }
        if (notRequired.contains(key)) {
            isRequired = "否";
        }else{
            isRequired = "是";
        }
        if ("Object".equals(typeName) || "Array".equals(typeName)) {
            System.out.printf("| **%s** | **%s** | **%s** | **%s** |\n", key, typeName, intro, isRequired);
        } else {
            System.out.printf("| %s | %s | %s | %s |\n", key, typeName, intro, isRequired);
        }
    }
}
