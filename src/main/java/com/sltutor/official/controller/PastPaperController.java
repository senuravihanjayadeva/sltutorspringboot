package com.sltutor.official.controller;

import com.sltutor.official.model.Pastpaper;
import com.sltutor.official.repository.PastPapersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;

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

    @GetMapping("/{id}")
    public ResponseEntity<?> getSinglePaper(@PathVariable("id") String id){
       Optional<Pastpaper> paperOptional =  pastPapersRepo.findById(id);

       if(paperOptional.isPresent()){
           return new ResponseEntity<>(paperOptional.get(),HttpStatus.OK);
       }else{
           return new ResponseEntity<>("Paper not found",HttpStatus.NOT_FOUND);
       }
    }


    @PutMapping("/{id}")
    public ResponseEntity<?> updatePaperById(@PathVariable("id") String id, @RequestBody Pastpaper paper){
        Optional<Pastpaper> paperOptional =  pastPapersRepo.findById(id);
        if(paperOptional.isPresent()){
            Pastpaper updatedPaper = paperOptional.get();
            updatedPaper.setSchool(paper.getSchool());
            updatedPaper.setSubject(paper.getSubject());
            updatedPaper.setTerm(paper.getTerm());
            updatedPaper.setYear(paper.getYear());
            updatedPaper.setMedium(paper.getMedium());
            updatedPaper.setPaperLink(paper.getPaperLink());
            updatedPaper.setImage(paper.getImage());
            updatedPaper.setUpdatedAt(new Date(System.currentTimeMillis()));
            return new ResponseEntity<>(pastPapersRepo.save(updatedPaper), HttpStatus.OK);
        }else{
            return new ResponseEntity<>("Paper Update Failed",HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePaperById(@PathVariable("id") String id){
        try{
            pastPapersRepo.deleteById(id);
            return new ResponseEntity<>("Deleted Successfuly",HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
        }
    }



}
