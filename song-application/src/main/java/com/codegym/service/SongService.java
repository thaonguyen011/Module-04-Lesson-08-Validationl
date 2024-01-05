package com.codegym.service;

import com.codegym.model.Song;
import com.codegym.repository.ISongRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SongService implements ISongService{
    @Autowired
    private ISongRepository iSongRepository;

    @Override
    public List<Song> findAll() {
        return iSongRepository.findAll();
    }

    @Override
    public Song findById(Long id) {
        return iSongRepository.getReferenceById(id);
    }

    @Override
    public void save(Song song) {
        iSongRepository.save(song);
    }

    @Override
    public void remove(Long id) {
        iSongRepository.deleteById(id);
    }
}
