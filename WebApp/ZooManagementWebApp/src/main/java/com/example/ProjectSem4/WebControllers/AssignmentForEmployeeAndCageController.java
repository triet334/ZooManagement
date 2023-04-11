/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.ProjectSem4.WebControllers;

import com.example.ProjectSem4.Entities.*;
import com.example.ProjectSem4.IServices.IAnimal;
import com.example.ProjectSem4.IServices.IAssignmentForEmployeeAndCage;
import com.example.ProjectSem4.IServices.ICage;
import com.example.ProjectSem4.IServices.IEmployee;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @Quan
 */
@Controller
@RequestMapping("assignmentCage")
public class AssignmentForEmployeeAndCageController {

    @Autowired
    IAssignmentForEmployeeAndCage service;
    
    @Autowired
    ICage Cservice;
    
    @Autowired
    IEmployee Eservice;
    @Autowired
    IAnimal animalService;

    @RequestMapping("assignmentIndex")
    public String index(Model model,HttpSession session) {
        Employee ep= (Employee) session.getAttribute("employee");
        if ( ep!= null) {   
                       
            if (ep.getPositionCode().getPositionCode()==1) {                
                model.addAttribute("list", service.findAll());
                model.addAttribute("clist", Cservice.getCageList());
                return "indexAssignmentForEmployeeAndCage";
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

    @RequestMapping(value = "createAssignment", method = RequestMethod.GET)
    public String Create(Model model,HttpSession session) {
        Employee ep= (Employee) session.getAttribute("employee");
        if ( ep!= null) {   
                       
            if (ep.getPositionCode().getPositionCode()==1) {                
                model.addAttribute("clist", Cservice.getCageList());
                model.addAttribute("elist", Eservice.findAllExcept());
                model.addAttribute("newAssignment", new Assignmentforemployeeandcage());
                return "createAssignmentForEmployeeAndCage";
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

    @RequestMapping(value = "createAssignment", method = RequestMethod.POST)
    public String Create(Model model, @Valid @ModelAttribute("newAssignment") Assignmentforemployeeandcage assign, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("clist", Cservice.getCageList());
            model.addAttribute("elist", Eservice.findAllExcept());
            model.addAttribute("newAssignment", new Assignmentforemployeeandcage());
            return "createAssignmentForEmployeeAndCage";
        }

        Assignmentforemployeeandcage checkCageAndEmployee = service.checkCageAndEmployee(assign.getCageCode().getCageCode(), assign.getEmployeeCode().getEmployeeCode());
        int employeeFullCages = service.checkEmployeeFullCages(assign.getEmployeeCode().getEmployeeCode());

        if (employeeFullCages >= 4) {
            model.addAttribute("clist", Cservice.getCageList());
            model.addAttribute("elist", Eservice.findAllExcept());
            model.addAttribute("newAssignment", new Assignmentforemployeeandcage());
            model.addAttribute("msg", "Employee has full cage !!!");
            return "createAssignmentForEmployeeAndCage";
        } else {
            if (checkCageAndEmployee != null) {
                model.addAttribute("clist", Cservice.getCageList());
                model.addAttribute("elist", Eservice.findAllExcept());
                model.addAttribute("newAssignment", new Assignmentforemployeeandcage());
                model.addAttribute("msg", "Employee already assigned for that cage !!!");
                return "createAssignmentForEmployeeAndCage";
            } else {
                int employeeForCage = service.countEmployeeForCage(assign.getCageCode().getCageCode());
                int maxEmployee = Cservice.findCageByCode(assign.getCageCode().getCageCode()).getMaxEmployee();
                if (employeeForCage < maxEmployee) {
                    service.saveAssignment(assign);
                    return "redirect:/assignmentCage/assignmentIndex";
                } else {
                    model.addAttribute("clist", Cservice.getCageList());
                    model.addAttribute("elist", Eservice.findAllExcept());
                    model.addAttribute("newAssignment", new Assignmentforemployeeandcage());
                    model.addAttribute("msg", "That Cage already reach max employees !!!");
                    return "createAssignmentForEmployeeAndCage";
                }
            }
        }
    }
    //delete

    @RequestMapping(value = "deleteAssignment/{assignmentCode}")
    public String delete(Model model, @PathVariable("assignmentCode") int assignmentCode) {
        Assignmentforemployeeandcage oneAss = service.getOne(assignmentCode);
        if (oneAss != null) {
            service.removeAssignment(assignmentCode);
        }

        return "redirect:/assignmentCage/assignmentIndex";
    }

    //detail
    @RequestMapping(value = "detailsAssignment/{assignmentCode}", method = RequestMethod.GET)
    public String detail(Model model, @PathVariable("assignmentCode") int assignmentCode,HttpSession session) {
        Employee ep= (Employee) session.getAttribute("employee");
        if ( ep!= null) {   
                       
            if (ep.getPositionCode().getPositionCode()==1) {                
                Assignmentforemployeeandcage oneAss = service.getOne(assignmentCode);
                if (oneAss != null) {
                model.addAttribute("details", oneAss);
//                    model.addAttribute("employeeList", service.findEmpByCagecode(oneAss.getCageCode()));
//                    model.addAttribute("animalList", animalService.findbyCage(oneAss.getCageCode()));
                }
                return "detailsAssignmentForEmployeeAndCage";
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

    //update
    @RequestMapping(value = "updateAssignment/{assignmentCode}", method = RequestMethod.GET)
    public String update(Model model, @PathVariable("assignmentCode") int assignmentCode,HttpSession session) {
        Employee ep= (Employee) session.getAttribute("employee");
        if ( ep!= null) {   
                       
            if (ep.getPositionCode().getPositionCode()==1) {                
                Assignmentforemployeeandcage oneAss = service.getOne(assignmentCode);
                    if (oneAss != null) {

                    model.addAttribute("clist", Cservice.getCageList());
                    model.addAttribute("elist", Eservice.findAllExcept());
                    model.addAttribute("oneAss", oneAss);
                }
                return "updateAssignmentForEmployeeAndCage";
            } else if (ep.getPositionCode().getPositionCode() == 2) {
                return "redirect:/job/findbyweek";
            }
            else{
                return "redirect:/health/findFault";
            }            
        } else {
            return "redirect:/login";
        }
        
        
    }

    @RequestMapping(value = "updateAssignment", method = RequestMethod.POST)
    public String update(Model model, @Valid @ModelAttribute("oneAss") Assignmentforemployeeandcage assignmentforemployeeandcage, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("clist", Cservice.getCageList());
            model.addAttribute("elist", Eservice.findAllExcept());
            return "updateAssignmentForEmployeeAndCage";
        } else {
            service.saveAssignment(assignmentforemployeeandcage);
//            service.updateAssignment(assignmentforemployeeandcage);
            return "redirect:/assignmentCage/assignmentIndex";
        }

    }

}
