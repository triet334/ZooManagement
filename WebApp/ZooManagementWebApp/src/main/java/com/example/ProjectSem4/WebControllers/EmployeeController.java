/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.ProjectSem4.WebControllers;

import com.example.ProjectSem4.DTO.EmployeeDTO;
import com.example.ProjectSem4.Entities.*;
import com.example.ProjectSem4.IServices.IAnimalFamily;
import com.example.ProjectSem4.IServices.IClassAnimal;
import com.example.ProjectSem4.IServices.IEmployee;
import com.example.ProjectSem4.IServices.IEmployeePosition;
import com.example.ProjectSem4.IServices.IHealthTracking;
import com.example.ProjectSem4.IServices.IReport;
import com.example.ProjectSem4.IServices.IShift;
import com.google.gson.Gson;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import javax.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author Admin
 */
@Controller
public class EmployeeController {

    @Autowired
    IAnimalFamily animalFamilyService;
    @Autowired
    IEmployee employeeService;
    @Autowired
    IEmployeePosition employeePositionService;
    @Autowired
    IShift shiftChangeService;
    @Autowired
    IClassAnimal classAnimalService;

    @Autowired
    IReport reportService;
    
    @Autowired
    IHealthTracking healthTrackingService;
    
    @ResponseBody
    @RequestMapping(value = "/employee/loadSpesForEmployee/{employeeCode}", method = RequestMethod.GET)
    public String loadSpesForEmployee(@PathVariable("employeeCode") String employeeCode) {
        Employee emp = employeeService.findOneEmp(employeeCode);
        Gson gson = new Gson();
        return gson.toJson(animalFamilyService.findAnimalFamilyByClass(emp.getClassCode().getClassCode()));
    }

    public EmployeeController(IEmployee employeeService, IEmployeePosition employeePositionService, IShift shiftChangeService, IAnimalFamily animalFamilyService, IClassAnimal classAnimalService) {
        this.employeeService = employeeService;
        this.employeePositionService = employeePositionService;
        this.shiftChangeService = shiftChangeService;
        this.animalFamilyService = animalFamilyService;
        this.classAnimalService = classAnimalService;
    }

    @RequestMapping(value = "/createemployee", method = RequestMethod.GET)
    public String createemployee(Model model, HttpSession session) {
        Employee ep = (Employee) session.getAttribute("employee");
        if (ep != null) {

            if (ep.getPositionCode().getPositionCode() == 1) {
                model.addAttribute("newEmp", new EmployeeDTO());
                model.addAttribute("classcode", classAnimalService.getAllClassAnimals());
                model.addAttribute("pst", employeePositionService.find());
                return "createemployee";
            } else if (ep.getPositionCode().getPositionCode() == 2) {
                return "redirect:/job/findbyweek";
            } else {
                return "redirect:/health/findFault";
            }
        } else {
            return "redirect:/login";
        }

    }

    @RequestMapping(value = "/createemployee", method = RequestMethod.POST)
    public String createemployee(Model model, @Valid @ModelAttribute("newEmp") EmployeeDTO employeeDTO, BindingResult bindingResult) {
        if (employeeService.codeExist(employeeDTO.getEmployeeCode())) {
            bindingResult.addError(new FieldError("employeeDTO", "employeeCode", "Employee Code existed ! Please enter another code !!"));
        }
        if (bindingResult.hasErrors()) {
            model.addAttribute("classcode", classAnimalService.getAllClassAnimals());
            model.addAttribute("pst", employeePositionService.findAll());
            model.addAttribute("canimal", classAnimalService.getAllClassAnimals());
            model.addAttribute("sc", shiftChangeService.getShiftList());
            return "createemployee";
        }
        if (employeeService.createEmployee(employeeDTO)) {
            return "redirect:/";
        } else {
            return "error";
        }

    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(Model model) {
        model.addAttribute("newEmp", new Employee());

        return "login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(@ModelAttribute Employee employee, HttpSession session,Model model,RedirectAttributes redirectAttrs) {
        Employee emp = employeeService.checkLogin(employee.getEmployeeCode(), employee.getPassword());
        if (emp != null) {
            session.setAttribute("employee", employeeService.findOneEmp(employee.getEmployeeCode()));

            Employee ep = (Employee) session.getAttribute("employee");
            if (ep.getPositionCode().getPositionCode() == 1) {
                return "redirect:/";
            } else if (ep.getPositionCode().getPositionCode() == 2) {
                return "redirect:/job/findbyweek";
            } else {
                return "redirect:/health/findFault";
            }
        } else {
            redirectAttrs.addFlashAttribute("mes", "Login Fail!!!!!");
            
            return "redirect:/login";
        }
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(Model model, HttpSession session) {
        session.removeAttribute("employee");
        return "redirect:/login";
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(Model model,HttpSession session) {
        Employee ep= (Employee) session.getAttribute("employee");
        if ( ep!= null) {                         
            if (ep.getPositionCode().getPositionCode()==1) {   
                String date= LocalDate.now().toString();
                
                List<Report> rp = reportService.findF(date);
                List<Report> arp = reportService.findByDate(date);
                List<Healthtracking> ht = healthTrackingService.findStatus();
                
                model.addAttribute("countall", arp.size());
                model.addAttribute("countrp",arp.size()- rp.size());
                model.addAttribute("countht",ht.size());
                return "dashboard";
            } else if (ep.getPositionCode().getPositionCode() == 2) {
                return "redirect:/job/findbyweek";
            } else {
                return "redirect:/health/findFault";
            }
        } else {
            return "redirect:/login";
        }

    }

    @RequestMapping(value = "/show", method = RequestMethod.GET)
    public String show(Model model, HttpSession session) {
        Employee ep = (Employee) session.getAttribute("employee");
        if (ep != null) {
            if (ep.getPositionCode().getPositionCode() == 1) {
                List<Employee> list = new ArrayList<>();
                List<Employee> elist = employeeService.findAll();

                for (Employee e : elist) {
                    if (e.getPositionCode().getPositionCode() != 1) {
                        list.add(e);
                    }
                }
                model.addAttribute("emp", list);
                return "index";
            } else if (ep.getPositionCode().getPositionCode() == 2) {
                return "redirect:/job/findbyweek";
            } else {
                return "redirect:/health/findFault";
            }
        } else {
            return "redirect:/login";
        }

    }
    // search
//    @RequestMapping(value = "/search", method = RequestMethod.GET)
//    public String search(Model model, @PathParam("code") String code) {
//        model.addAttribute("emp", employeeService.searchCode(code));
//        return "index";
//    }

    //edit
    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String edit(Model model, @PathVariable("id") String id, HttpSession session) {
        Employee ep = (Employee) session.getAttribute("employee");
        if (ep != null) {

            if (ep.getPositionCode().getPositionCode() == 1) {
                model.addAttribute("oldEmp", employeeService.findOneEmp(id));
                model.addAttribute("pst", employeePositionService.findAll());
                model.addAttribute("canimal", classAnimalService.getAllClassAnimals());
                model.addAttribute("sc", shiftChangeService.getShiftList());
                return "edit";
            } else if (ep.getPositionCode().getPositionCode() == 2) {
                return "redirect:/job/findbyweek";
            } else {
                return "redirect:/health/findFault";
            }
        } else {
            return "redirect:/login";
        }

    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String edit(Model model, @Valid @ModelAttribute("oldEmp") EmployeeDTO employeeDTO, BindingResult bindingResult,RedirectAttributes rs) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("pst", employeePositionService.findAll());
            model.addAttribute("canimal", classAnimalService.getAllClassAnimals());
            model.addAttribute("sc", shiftChangeService.getShiftList());
            return "edit";
        }
        if (employeeService.updateEmployee(employeeDTO)) {
            rs.addFlashAttribute("mes", "Success !!!!");
            return "redirect:/";
        } else {
            return "error";
        }
    }

    //edit for employee
    @RequestMapping(value = "/editEmp/{id}", method = RequestMethod.GET)
    public String editEmp(Model model, @PathVariable("id") String id, HttpSession session) {
        Employee ep = (Employee) session.getAttribute("employee");
        if (ep != null) {

            if (ep.getPositionCode().getPositionCode() == 1) {
                return "redirect:/";
            } else if (ep.getPositionCode().getPositionCode() == 2) {
                model.addAttribute("oldEmp", employeeService.findOneEmp(id));
                return "editemployee";
            } else {
                model.addAttribute("oldEmp", employeeService.findOneEmp(id));
                return "editemployee";
            }
        } else {
            return "redirect:/login";
        }

    }

    @RequestMapping(value = "/editEmp", method = RequestMethod.POST)
    public String editEmp(@ModelAttribute EmployeeDTO employeeDTO,HttpSession session,RedirectAttributes rs) {
        if (employeeService.updateEmployee(employeeDTO)) {
            session.setAttribute("employee", employeeService.findOneEmp(employeeDTO.getEmployeeCode()));
            rs.addFlashAttribute("mes", "Success !!!!");
            return "redirect:/job/findbyweek";
        } else {
            return "error";
        }
    }

    @RequestMapping(value = "/editPass", method = RequestMethod.POST)
    public String editPass(@RequestParam("pw") String pass, HttpSession session) {
        Employee emp = (Employee) session.getAttribute("employee");

        if (employeeService.updatePass(emp.getEmployeeCode(), pass)) {
            return "redirect:/job/findbyweek";
        } else {
            return "error";
        }
    }

    //delete
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String delete(Model model, @PathVariable("id") String id) {
        if (employeeService.deleteEmployee(id)) {
            return "redirect:/";
        } else {
            return "error";
        }
    }

    @RequestMapping(value = "/reset/{id}", method = RequestMethod.GET)
    public String reset(Model model, @PathVariable("id") String id,RedirectAttributes rs) {
        if (employeeService.resetPassword(id)) {
            rs.addFlashAttribute("mes", "Success !!!!");
            return "redirect:/";
        } else {
            return "error";
        }
    }

}
