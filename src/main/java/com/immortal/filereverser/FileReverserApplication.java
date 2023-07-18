package com.immortal.filereverser;

import ch.qos.logback.core.util.FileUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.util.FileCopyUtils;

import java.io.*;

@SpringBootApplication
public class FileReverserApplication {

    public static void main(String[] args) {
        SpringApplication.run(FileReverserApplication.class, args);


        // Input Output Files
        String inputFile = "C:\\Users\\a883123\\OneDrive - Atos\\Desktop\\borodino.txt";
        String outputFile = "output.txt";

        // Kinda checking if already reversed
        File existingOutputFile = new File(outputFile);
        if (existingOutputFile.exists()) {
            System.out.println("Already reversed");
            return;
        }


        {
            try {


                File inFile = new File(inputFile);
                String fileContent = new String(FileCopyUtils.copyToByteArray(inFile));

                // reversing file
                String reversedFile = reverseString(fileContent);

                // writing the reversed Text into the output File
                File outFile = new File(outputFile);
                BufferedWriter bw = new BufferedWriter(new FileWriter(outFile));
                bw.write(reversedFile);
                bw.close();

                System.out.println("File reversed. File: output.txt");


            } catch (IOException e) {
                System.out.println("The unseen happend");
                throw new RuntimeException(e);
            }
        }


    }

    private static String reverseString(String input) {
        StringBuilder reversed = new StringBuilder();
        for (int i = input.length() - 1; i >= 0; i--) {
            reversed.append(input.charAt(i));
        }
        return reversed.toString();
    }


}
