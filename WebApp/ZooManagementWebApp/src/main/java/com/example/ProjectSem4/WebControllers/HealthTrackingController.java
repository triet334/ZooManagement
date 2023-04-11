/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.ProjectSem4.WebControllers;

import com.example.ProjectSem4.IServices.IHealthTracking;
import com.example.ProjectSem4.Entities.*;
import com.example.ProjectSem4.IServices.IHealthDetail;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author Admin
 */
@Controller
@RequestMapping("health")
public class HealthTrackingController {
    
    @Autowired
    IHealthTracking healthTrackingservice;
    
    @Autowired
    IHealthDetail healthDetailService;

    public HealthTrackingController(IHealthTracking healthTrackingservice, IHealthDetail healthDetailService) {
        this.healthTrackingservice = healthTrackingservice;
        this.healthDetailService = healthDetailService;
    }

      
    
    @RequestMapping(value = "findAll",method = RequestMethod.GET)
    public String page(Model model) {
        List<Healthtracking> list =healthTrackingservice.findAll();
        Collections.reverse(list);
        model.addAttribute("list", list);
        
        return "health";
    }
    
    @RequestMapping(value = "create",method = RequestMethod.POST)
    public String create(Model model,@ModelAttribute Healthtracking healthtracking,HttpSession session,RedirectAttributes rs) {
        Employee ep= (Employee) session.getAttribute("employee");
        if ( ep!= null) {   
                       
            if (ep.getPositionCode().getPositionCode()==1) {                
                return "redirect:/";
            }
            else if(ep.getPositionCode().getPositionCode()==2){               
                healthtracking.setEmployeeCode(ep);
                if (healthTrackingservice.createHealth(healthtracking)) {
                    rs.addFlashAttribute("mes", "Success !!!!");
                    return "redirect:/job/findbyweek";
                } else {
                    return "error";
                }
            }
            else{
                return "redirect:/health/findFault";
            }            
        } else {
            return "redirect:/login";
        }
        
        
    }
    
    @RequestMapping(value = "findFault",method = RequestMethod.GET)
    public String findFault(Model model,HttpSession session) {
        Employee ep= (Employee) session.getAttribute("employee");
        if ( ep!= null) {   
                       
            if (ep.getPositionCode().getPositionCode()==1) {                
                return "redirect:/";
            }
            else if(ep.getPositionCode().getPositionCode()==2){               
                return "redirect:/job/findbyweek";
            }
            else{
                List<Healthtracking> list = healthTrackingservice.findStatus();
                model.addAttribute("list",list );
                return "doctor";
            }            
        } else {
            return "redirect:/login";
        }
        
    }
    
    @RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
    public String update(Model model,@PathVariable("id") int id,HttpSession session) {
        Employee ep= (Employee) session.getAttribute("employee");
        if ( ep!= null) {   
                       
            if (ep.getPositionCode().getPositionCode()==1) {                
                return "redirect:/";
            }
            else if(ep.getPositionCode().getPositionCode()==2){               
                return "redirect:/job/findbyweek";
            }
            else{
                Healthtracking list=healthTrackingservice.findOne(id);
                model.addAttribute("ht",list );            
                return "updateHealth";
            }            
        } else {
            return "redirect:/login";
        }
        
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String update(@ModelAttribute Healthtracking healthtracking,HttpSession session,RedirectAttributes redirectAttrs) {
        Employee emp = (Employee) session.getAttribute("employee");
        healthtracking.setDoctorCode(emp);
        if (healthTrackingservice.updateHealth(healthtracking)) {
            
            LocalDateTime now = LocalDateTime.now();

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

            String formatDateTime = now.format(formatter);
            
            Healthdetail htd= new Healthdetail();
            htd.setHealthid(healthtracking);
            htd.setEmployeeCode(healthtracking.getEmployeeCode().getEmployeeName());
            htd.setEmployeeDescription(healthtracking.getEmployeeDescription());
            htd.setDoctorCode(healthtracking.getDoctorCode().getEmployeeName());
            htd.setDoctorNote(healthtracking.getDoctorNote());
            htd.setTime(formatDateTime);
            
            if (healthDetailService.create(htd)) {
                redirectAttrs.addFlashAttribute("mes", "Success !!!!");
                return "redirect:/health/findFault";
            }
            else{
                return "error";
            }
            
            
        } else {
            return "error";
        }

    }
    
    @RequestMapping(value = "/detail/{id}", method = RequestMethod.GET)
    public String detail(Model model,@PathVariable("id") int id) { 
        
        List<Healthdetail> ht= healthDetailService.findByHealth(id);
        model.addAttribute("htdetail", ht);
        return "healthdetail";
    }
}
