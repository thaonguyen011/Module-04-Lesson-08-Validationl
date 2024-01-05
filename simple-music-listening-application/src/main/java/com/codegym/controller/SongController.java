package com.codegym.controller;


import com.codegym.model.Song;
import com.codegym.model.SongForm;
import com.codegym.service.ISongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/songs")
public class SongController {

    @Autowired
    private ISongService songService;

    @Value("${upload-file}")
    private String upload;

    @GetMapping("")
    public ModelAndView index() {
        ModelAndView modelAndView = new ModelAndView("/index");
        List<Song> songs = songService.findAll();
        modelAndView.addObject("songs", songs);
        return modelAndView;
    }

    @GetMapping("/add")
    public ModelAndView showAddForm() {
        ModelAndView modelAndView = new ModelAndView("/create");
        modelAndView.addObject("songForm", new SongForm());
        return modelAndView;
    }

    @PostMapping("/add")
    public String add(@Valid @ModelAttribute("songForm") SongForm songForm, RedirectAttributes redirect) {
        MultipartFile multipartFile = songForm.getFileURL();
        String fileName = multipartFile.getOriginalFilename();

        try {
            FileCopyUtils.copy(songForm.getFileURL().getBytes(), new File(upload + fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }

        Song song = new Song();
        song.setName(songForm.getName());
        song.setSinger(songForm.getSinger());
        song.setGenres(songForm.getGenres());
        song.setFileURL(fileName);

        songService.save(song);

        redirect.addFlashAttribute("success", "Add song successfully");
        return "redirect:/songs";
    }

    @GetMapping("/{id}/edit")
    public ModelAndView showEditForm(@PathVariable("id") Long id) {
        Song song = songService.findById(id);
        ModelAndView modelAndView = new ModelAndView("/update");
        modelAndView.addObject("song", song);
        return modelAndView;
    }

    @PostMapping("/update")
    public String update(@Valid @ModelAttribute("songForm") SongForm songForm, RedirectAttributes redirect) {
        MultipartFile multipartFile = songForm.getFileURL();
        String fileName = multipartFile.getOriginalFilename();

        try {
            FileCopyUtils.copy(songForm.getFileURL().getBytes(), new File(upload + fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }

        Song song = new Song();
        song.setName(songForm.getName());
        song.setSinger(songForm.getSinger());
        song.setGenres(songForm.getGenres());
        song.setFileURL(fileName);

        songService.save(song);

        redirect.addFlashAttribute("success", "Update song successfully");
        return "redirect:/songs";

    }

    @GetMapping("/{id}/delete")
    public String delete(@PathVariable("id") Long id, RedirectAttributes redirect) {
        songService.remove(id);
        redirect.addFlashAttribute("success", "Delete song successfully");
        return "redirect:/songs";
    }
}
