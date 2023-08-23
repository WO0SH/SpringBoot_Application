package com.immortal.filereverser.validation;

import com.immortal.filereverser.utils.FileRepository;
import com.immortal.filereverser.utils.UploadedFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

@Component
public class Validator {
    @Autowired
    FileRepository fileRepository;




    public boolean compareNames(MultipartFile file) {
        Optional<UploadedFile> optionalFile = fileRepository.findByFilename(file.getOriginalFilename());
        return optionalFile.isPresent();
    }


}
