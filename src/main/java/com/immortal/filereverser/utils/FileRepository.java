package com.immortal.filereverser.utils;


import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository

public interface FileRepository extends JpaRepository<UploadedFile,Long> {


    Optional<UploadedFile> findByFilename(String filename);

}
