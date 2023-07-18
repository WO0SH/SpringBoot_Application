package com.immortal.filereverser.utils;

import org.springframework.core.io.ClassPathResource;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.yaml.snakeyaml.DumperOptions;

import java.io.File;
import java.io.IOException;

@RestController
@RequestMapping
public class FileController {

    @GetMapping(value = "/file",produces = {"text/plain"})

    public String getFileContent() {

        String input = "output.txt";

        try {


            File file = new File(input);

            String fileContent = new String(FileCopyUtils.copyToByteArray(file));
            return (fileContent);

        } catch (IOException e) {
            System.out.println("BIpipbipbiib... err...or");
            return "An error occurred: " + e.getMessage();
        }

    }

    private String formatString(String stringer) {
        String formattedContent = stringer.replace(System.lineSeparator(), "<br>");
        formattedContent = "<p>" + formattedContent + "</p>";
        return formattedContent;

    }
}



