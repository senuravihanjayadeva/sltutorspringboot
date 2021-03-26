package com.sltutor.official.controller;

import com.sltutor.official.model.Pastpaper;
import com.sltutor.official.repository.PastPapersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RequestMapping(value = "/api/papers")
@RestController
public class PastPaperController {

    @Autowired
    private PastPapersRepository pastPapersRepo;

    @GetMapping
    public ResponseEntity<?> getAllPastPapers(){
        List<Pastpaper> pastpapersList = pastPapersRepo.findAll();
        if(pastpapersList.size() > 0){
            return new ResponseEntity<List<Pastpaper>>(pastpapersList, HttpStatus.OK);
        }else{
            return new ResponseEntity<>("No Papers Available",HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public  ResponseEntity<?> insertPastPapers(@RequestBody Pastpaper paper){

        try{
            paper.setCreatedAt(new Date(System.currentTimeMillis()));
            pastPapersRepo.save(paper);
            return new ResponseEntity<Pastpaper>(paper,HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
        }


    }



}
