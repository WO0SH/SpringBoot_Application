package com.immortal.filereverser.utils;


import com.immortal.filereverser.validation.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.yaml.snakeyaml.DumperOptions;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping
public class FileController {

    @Autowired
    private FileRepository fileRepository;
    @Autowired
    private Validator validator;

    @GetMapping("/")
    public String listFiles(Model model) {
        List<UploadedFile> files = fileRepository.findAll();
        model.addAttribute("files", files);
        return "file-list";
    }

    @PostMapping("/upload")
    public String uploadFile(@RequestParam("file") MultipartFile file) {
        String content = new String();


        if (!file.isEmpty()&&!validator.compareNames(file)) {
            try {
                content = new String(file.getBytes());
                UploadedFile uploadedFile = new UploadedFile();
                uploadedFile.setFilename(file.getOriginalFilename());
                uploadedFile.setContent(content);

                UploadedFile saveResult=fileRepository.save(uploadedFile);
                System.out.println("File uploaded successfuly "+ saveResult.getId());

            } catch (IOException e) {
                e.printStackTrace();
            }
        }


        return formatString(content);
    }

    @GetMapping("/view/{id}")
    public String viewFile(@PathVariable Long id, Model model) {
        Optional<UploadedFile> optionalFile = fileRepository.findById(id);
        if (optionalFile.isPresent()) {
            UploadedFile file = optionalFile.get();
            model.addAttribute("file", file);
            return "file-view";
        } else {
            return "redirect:/";
        }
    }

    @GetMapping("/reversed/{id}")
    public String reverseFileContent(@PathVariable Long id) {
        Optional<UploadedFile> optionalFile = fileRepository.findById(id);
        String reversedContent=new String();
        if (optionalFile.isPresent()) {
            UploadedFile file = optionalFile.get();
            reversedContent = reverseString(file.getContent());

            try {
                // Save the reversed content to a new file
                File outputFile = new File("reversed_" + file.getFilename());
                FileWriter writer = new FileWriter(outputFile);
                writer.write(reversedContent);
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return formatString(reversedContent);
    }

    @GetMapping("/delete/{id}")
    public String deleteFile(@PathVariable Long id) {
        fileRepository.deleteById(id);
        return "redirect:/";
    }

/*
    @GetMapping(value = "/file", produces = {"text/plain"})

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

 */

    private String reverseString(String text) {
        StringBuilder reversed = new StringBuilder();
        for (int i = text.length() - 1; i >= 0; i--) {
            reversed.append(text.charAt(i));
        }
        return reversed.toString();
    }

    private String formatString(String stringer) {
        String formattedContent = stringer.replace(System.lineSeparator(), "<br>");
        formattedContent = "<p>" + formattedContent + "</p>";
        return formattedContent;

    }
}



