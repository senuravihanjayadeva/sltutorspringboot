package com.sltutor.official.controller;

import com.sltutor.official.model.Pastpaper;
import com.sltutor.official.repository.PastPapersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PastPaperController {

    @Autowired
    private PastPapersRepository pastPapersRepo;

    @GetMapping("/papers")
    public ResponseEntity<?> getAllPastPapers(){

        List<Pastpaper> pastpapersList = pastPapersRepo.findAll();

        if(pastpapersList.size() > 0){
            return new ResponseEntity<List<Pastpaper>>(pastpapersList, HttpStatus.OK);
        }else{
            return new ResponseEntity<>("No Papers Available",HttpStatus.NOT_FOUND);
        }

    }

}
