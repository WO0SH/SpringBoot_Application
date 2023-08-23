package com.immortal.filereverser.utils;

import jakarta.persistence.*;

import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Blob;
import java.sql.SQLException;


@Entity
public class UploadedFile {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name="filename")
    private String filename;




    @Column(name="content",length = 4000)
    private String content;

    public UploadedFile() {
    }

    public UploadedFile(Long id, String filename, String content) {
        this.id = id;
        this.filename = filename;
        this.content = content;
    }

    public UploadedFile(String filename, String content) {

        this.filename = filename;
        this.content = content;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}

