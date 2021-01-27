package com.api.example;

import com.api.JsonToApiDoc;
import com.api.baseDoc.CommonParams;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Test {
    public static void main(String[] args) throws IOException {

        CommonParams.notRequired= ExampleCommonParams.notRequiredExample;
        CommonParams.intros= ExampleCommonParams.introsExample;

        JsonToApiDoc jsonToApiDoc = new JsonToApiDoc();

        List<String> result = new ArrayList<>();
        try (Stream<Path> walk = Files.walk(Paths.get("src/main/java/com/api/example/exampleApi/"))) {

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
}
