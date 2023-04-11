/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.ProjectSem4.WebControllers;

import com.example.ProjectSem4.DTO.AnimalDTO;
import com.example.ProjectSem4.Entities.*;
import com.example.ProjectSem4.IServices.*;
import com.google.gson.Gson;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 *
 * @Triet
 */
@Controller
@RequestMapping("animal")
public class AnimalController {
    
    @Autowired
    IAnimal animalService;
    
    @Autowired
    IAnimalFamily animalFamilyService;
    
    @Autowired
    ICage cageService;   
    
    @Autowired
    IClassAnimal classAnimalService;
             
    @ResponseBody
    @RequestMapping(value = "loadAnimalFamilyByClass/{code}" ,method = RequestMethod.GET)
    public String loadAnimalFamilyByClass(@PathVariable("code") int code) {
        Gson gson = new Gson();        
        return gson.toJson(animalFamilyService.findAnimalFamilyByClass(code));
    }
        
    @ResponseBody
    @RequestMapping(value = "loadCagesByAnimalFamily/{code}" ,method = RequestMethod.GET)
    public String loadCagesByAnimalFamily(@PathVariable("code") int code) {
        Gson gson = new Gson();        
        return gson.toJson(cageService.findCagesByAnimalFamily(code));
    }
    
    @RequestMapping(value = "detailsAnimal/{animalCode}" ,method = RequestMethod.GET)
    public String detail(Model model, @PathVariable("animalCode") String animalCode,HttpSession session) {  
        
        Employee ep= (Employee) session.getAttribute("employee");
        if ( ep!= null) {   
                       
            if (ep.getPositionCode().getPositionCode()==1) {                
                Animal oneAnimal = animalService.getOne(animalCode);  
                if(oneAnimal != null){
                model.addAttribute("details", animalService.getOne(animalCode));            
                }
                return "detailsAnimal";
            }
            else if(ep.getPositionCode().getPositionCode()==2){               
                return "redirect:/job/findbyweek";
            }
            else{
                return "redirect:/health/findFault";
            }            
        } else {
            return "redirect:/login";
        }
        
                       
    }
    
    @RequestMapping(value = "deleteAnimal/{animalCode}")
    public String delete(Model model, @PathVariable("animalCode") String animalCode) {
        Animal oneAnimal = animalService.getOne(animalCode);
        if (oneAnimal != null) {    
            animalService.deleteAnimal(animalCode);
        }     
        return "redirect:/animal/animalIndex";
    }
     
    @RequestMapping(value = "updateAnimal/{animalCode}", method = RequestMethod.GET)
    public String updateAnimal(Model model, @PathVariable("animalCode") String animalCode,HttpSession session) { 
        Employee ep= (Employee) session.getAttribute("employee");
        if ( ep!= null) {   
                       
            if (ep.getPositionCode().getPositionCode()==1) {                
                Animal oneAnimal = animalService.getOne(animalCode);        
                if (oneAnimal != null) {
                model.addAttribute("classList", classAnimalService.getAllClassAnimals()); 
                model.addAttribute("oneAnimal", oneAnimal);
                }        
                return "updateAnimal";
            }
            else if(ep.getPositionCode().getPositionCode()==2){               
                return "redirect:/job/findbyweek";
            }
            else{
                return "redirect:/health/findFault";
            }            
        } else {
            return "redirect:/login";
        }
        
    }
    
    @RequestMapping(value = "updateAnimal", method = RequestMethod.POST)
    public String updateAnimal(Model model, @Valid @ModelAttribute("oneAnimal") AnimalDTO dto, BindingResult bindingResult) {        
        if(bindingResult.hasErrors()){
            model.addAttribute("classList", classAnimalService.getAllClassAnimals()); 
            return "updateAnimal";
        }                           
        else{
            animalService.createAnimal(dto);
            return "redirect:/animal/animalIndex";
        }
        
        
    }        
           
    @RequestMapping(value = "createAnimal", method = RequestMethod.GET)
    public String createAnimal(Model model,HttpSession session) {  
        Employee ep= (Employee) session.getAttribute("employee");
        if ( ep!= null) {   
                       
            if (ep.getPositionCode().getPositionCode()==1) {                
                model.addAttribute("classList", classAnimalService.getAllClassAnimals()); 
                model.addAttribute("newAnimal", new AnimalDTO());        
                return "createAnimal";
            }
            else if(ep.getPositionCode().getPositionCode()==2){               
                return "redirect:/job/findbyweek";
            }
            else{
                return "redirect:/health/findFault";
            }            
        } else {
            return "redirect:/login";
        }
        
                             
    }
    
    @RequestMapping(value = "createAnimal", method = RequestMethod.POST)
    public String createAnimal(Model model,@Valid @ModelAttribute("newAnimal") AnimalDTO dto, BindingResult bindingResult) {        
        
        if (animalService.codeExist(dto.getAnimalCode())) {
            bindingResult.addError(new FieldError("dto","animalCode","Animal Code existed ! Please enter another code !!"));
        }
        if(bindingResult.hasErrors()){
            model.addAttribute("classList", classAnimalService.getAllClassAnimals()); 
            return "createAnimal";
        }      
        
        int animalInCage = animalService.countAnimalInCage(dto.getCageCode());
        int maxAnimal = cageService.findCageByCode(dto.getCageCode()).getMaxAnimal();
        if(animalInCage < maxAnimal){
            animalService.createAnimal(dto);
            return "redirect:/animal/animalIndex";        
        }               
        else {
            model.addAttribute("newAnimal", new AnimalDTO()); 
            model.addAttribute("classList", classAnimalService.getAllClassAnimals()); 
            model.addAttribute("msg", "Cage is full, can not add more animal !!!");     
            return "createAnimal";
        }
        
    }        
    
    
    public AnimalController(IAnimal _animalService, IAnimalFamily _animalFamilyService, ICage _cageService) {
        animalService = _animalService;
        animalFamilyService = _animalFamilyService;
        cageService = _cageService;
    }
    
    @RequestMapping(value = "animalIndex" ,method = RequestMethod.GET)
    public String indexAnimal(Model model, HttpSession session) {
        Employee ep= (Employee) session.getAttribute("employee");
        if ( ep!= null) {   
                       
            if (ep.getPositionCode().getPositionCode()==1) {                
                model.addAttribute("animalList", animalService.getAnimalList());       
             return "indexAnimal";
            }
            else if(ep.getPositionCode().getPositionCode()==2){               
                return "redirect:/job/findbyweek";
            }
            else{
                return "redirect:/health/findFault";
            }            
        } else {
            return "redirect:/login";
        }
        
                   
    }
    
}
