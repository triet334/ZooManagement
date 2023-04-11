/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.ProjectSem4.APIControllers;

import com.example.ProjectSem4.Entities.Report;
import com.example.ProjectSem4.IServices.IReport;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Admin
 */
@RestController
//@RequestMapping("/url")
public class APIReportController {
    @Autowired
    IReport reportService;

    public APIReportController(IReport reportService) {
        this.reportService = reportService;
    }
    
    @PostMapping("/create")
    public ResponseEntity<Boolean> create(@RequestBody Report report){
        try {
            reportService.createReport(report);
            return new ResponseEntity<>(true,HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(false,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/findAll")
    public ResponseEntity<List<Report>> findAll(){
        try {
            List<Report> list = reportService.findAll();
            if (list.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            else{
                return new ResponseEntity<>(list,HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/findOne/{id}")
    public ResponseEntity<Report> findOne(@PathVariable("id") int id){
        try {
            Report report = reportService.findOne(id);           
            return new ResponseEntity<>(report,HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
