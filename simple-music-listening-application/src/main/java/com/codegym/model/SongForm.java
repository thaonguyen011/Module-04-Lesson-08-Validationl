package com.codegym.model;

import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class SongForm {
    @NotEmpty
    @NotNull
    @Max(800)
    @Pattern(regexp = "^\\w+$")
    private String name;

    @NotEmpty
    @Max(300)
    @Pattern(regexp = "^\\w+$")
    private String singer;

    @NotEmpty
    @Max(1000)
    @Pattern(regexp = "^[\\w |,]+$")
    private String genres;
    private MultipartFile fileURL;

    public SongForm() {
    }

    public SongForm(String name, String singer, String genres, MultipartFile fileURL) {
        this.name = name;
        this.singer = singer;
        this.genres = genres;
        this.fileURL = fileURL;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSinger() {
        return singer;
    }

    public void setSinger(String singer) {
        this.singer = singer;
    }

    public String getGenres() {
        return genres;
    }

    public void setGenres(String genres) {
        this.genres = genres;
    }

    public MultipartFile getFileURL() {
        return fileURL;
    }

    public void setFileURL(MultipartFile fileURL) {
        this.fileURL = fileURL;
    }
}
