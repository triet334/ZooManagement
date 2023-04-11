/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.ProjectSem4.WebControllers;

import com.example.ProjectSem4.Entities.*;
import com.example.ProjectSem4.IServices.*;
import com.google.gson.Gson;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Collections;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
@RequestMapping("report")
public class ReportController {
    
    @Autowired
   IReport reprotService;
    
    @Autowired
   IReportDetail reportDetailService;
    
    @Autowired
    ICage cageService;

    public ReportController(IReport reprotService) {
        this.reprotService = reprotService;
    }   
    
    @ResponseBody
    @RequestMapping(value = "loadfindByCage/{code}" ,method = RequestMethod.GET)
    public List<Report> loadfindByCage(@PathVariable("code") int code) {
        List<Report> lst = reprotService.findByCage(code);
//        Collections.reverse(lst);
        return lst;
    }
    
//    @RequestMapping(value = "/findbyweek",method = RequestMethod.GET)
//    public String findbyweek(Model model,HttpSession session) {
//        Employee ep= (Employee) session.getAttribute("employee");
//        if ( ep!= null) {   
//                       
//            if (ep.getPositionCode().getPositionCode()==1) {                
//                return "redirect:/";
//            }
//            else if(ep.getPositionCode().getPositionCode()==2){               
//                List<Report> list = reprotService.findAll();
//                List<Report> ls = new ArrayList<>();
//        
//                for (int i = 0; i < 6; i++) {
//                ls.add(list.get(i));
//                }
//                model.addAttribute("list",ls );
//                return "report";
//            }
//            else{
//                return "redirect:/health/findFault";
//            }            
//        } else {
//            return "redirect:/login";
//        }
//        
//    }
    
    @RequestMapping(value = "/findAll",method = RequestMethod.GET)
    public String findAll(Model model) {
        
        model.addAttribute("cage", cageService.getCageList());
        List<Report> list = reprotService.findAll();
        
        model.addAttribute("list",list );
        return "indexReport";
    }
    
    @RequestMapping(value = "/findByCgae/{code}",method = RequestMethod.GET)
    public String findByCage(Model model,@PathVariable("code") int code) {
        model.addAttribute("list", reprotService.findByCage(code));
        return "indexreport";
    }
    
    @RequestMapping(value = "/findByDate/{date}",method = RequestMethod.GET)
    public String findByDate(Model model,@PathVariable("date") String date) {
        model.addAttribute("list", reprotService.findByDate(date));
        return "indexreport";
    }
    
    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String create(Model model) {      
        
        return "createReport";
    }
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String create(Model model,@RequestParam("date1") String date1,RedirectAttributes redirectAttrs) throws ParseException {
        
            String dat = date1; //lấy tham số bên create qua, do th:field ko nhận kiểu dữ liệu date dưới database
            SimpleDateFormat sdff = new SimpleDateFormat("yyyy-MM-dd"); 
            Calendar cc = Calendar.getInstance();
            cc.setTime(sdff.parse(dat)); 
            dat = sdff.format(cc.getTime()); 
            
            LocalDate localdate= LocalDate.now();
            LocalDate datess = LocalDate.parse(dat);
            if (localdate.compareTo(datess) <= 0) {
            List<Cage> listc = cageService.getCageList();
            for (Cage cage : listc) {
                int i = 0;
                String dt = date1; //lấy tham số bên create qua, do th:field ko nhận kiểu dữ liệu date dưới database
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                Calendar c = Calendar.getInstance();
                c.setTime(sdf.parse(dt));
                dt = sdf.format(c.getTime());
                String newdate = dt;
                do {
                    Report rp = new Report();
                    rp.setCageCode(cage);
                    rp.setDate(newdate);
                    rp.setCheckCage(false);
                    rp.setFeeding(false);
                    rp.setClean(false);

                    reprotService.createReport(rp);

                    i++;

                    dt = sdf.format(c.getTime());
                    sdf = new SimpleDateFormat("yyyy-MM-dd");
                    c = Calendar.getInstance();
                    c.setTime(sdf.parse(dt));
                    c.add(Calendar.DATE, 1);
                    dt = sdf.format(c.getTime());
                    newdate = dt;
                } while (i < 7);
            }
            return "redirect:/";
        } else{
                redirectAttrs.addFlashAttribute("mes", "Cannot create a work schedule for a date in the past ");
                
                return "redirect:/report/create";
            }
        

    }
    
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String update(Model model,HttpSession session,@RequestParam("id") int id,@Param("checkc") boolean checkc,@Param("fc") boolean fc,@Param("cleanc") boolean cleanc,RedirectAttributes rs) { 
        
        Employee emp = (Employee) session.getAttribute("employee");
        
        Report rep= reprotService.findOne(id);
        Report rp = new Report();
        
        LocalDateTime now = LocalDateTime.now();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        String formatDateTime = now.format(formatter);
        
        rp.setReportId(id);
        rp.setCageCode(rep.getCageCode());
        rp.setDate(rep.getDate());
        rp.setCheckCage(checkc);
        rp.setFeeding(fc);
        rp.setClean(cleanc);
        reprotService.updateReport(rp);
        
        Reportdetail reportdetail= new Reportdetail();
        reportdetail.setReportid(rp);
        reportdetail.setCheckCage(rp.getCheckCage());
        reportdetail.setFeeding(rp.getFeeding());
        reportdetail.setClean(rp.getClean());
        reportdetail.setEmployeeCode(emp.getEmployeeName());
        reportdetail.setTime(formatDateTime);
        reportDetailService.create(reportdetail);
        
        rs.addFlashAttribute("mes", "Success !!!!");
        return "redirect:/job/findbyweek";
    }
    
    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String edit(Model model,@PathVariable("id") int id) { 
        
        Report rep= reprotService.findOne(id);
        model.addAttribute("rp", rep);
        return "editReport";
    }
    
    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String edit(Model model,@ModelAttribute Report report) { 
        
        if (reprotService.updateReport(report)) {
            return "redirect:/report/findAll";
        } else {
            return "error";
        }
    }
    
    @RequestMapping(value = "/detail/{id}", method = RequestMethod.GET)
    public String detail(Model model,@PathVariable("id") int id) { 
        
        List<Reportdetail> rep= reportDetailService.findByReport(id);
        model.addAttribute("rpdetail", rep);
        return "reportdetail";
    }
    
    @RequestMapping(value = "/search",method = RequestMethod.GET)
    public String search(Model model,@Param("combo") boolean combo,@Param("date") String date,@Param("cag") String cag) {
        List<Report> list = new ArrayList<>();
        List<Report> ls = reprotService.findAll();
        
        if (!cag.isEmpty() && date.isEmpty() && combo==false) {
            for (Report l : ls) {
                if (l.getCageCode().getCageCode() == Integer.valueOf(cag)) {
                    list.add(l);
                }
            }
        }
        else if (cag.isEmpty() && !date.isEmpty() && combo==false) {
            for (Report l : ls) {
                if (l.getDate().equals(date)) {
                    list.add(l);
                }
            }
        }
        else if (cag.isEmpty() && date.isEmpty() && combo==true) {
            for (Report l : ls) {
                if (!l.getCheckCage() || !l.getClean() || !l.getFeeding()) {
                    list.add(l);
                }
            }
        }
        else if (!cag.isEmpty() && !date.isEmpty() && combo==false) {
            for (Report l : ls) {
                if (l.getDate().equals(date) && l.getCageCode().getCageCode()==Integer.valueOf(cag)) {
                    list.add(l);
                }
            }
        }
        else if (!cag.isEmpty() && date.isEmpty() && combo==true) {
            for (Report l : ls) {
                if (l.getCageCode().getCageCode()==Integer.valueOf(cag) && (!l.getCheckCage() || !l.getClean() || !l.getFeeding())) {
                    list.add(l);
                }
            }
        }
        else if (cag.isEmpty() && !date.isEmpty() && combo==true) {
            for (Report l : ls) {
                if (l.getDate().equals(date) && (!l.getCheckCage() || !l.getClean() || !l.getFeeding())) {
                    list.add(l);
                }
            }
        }
        else if (!cag.isEmpty() && !date.isEmpty() && combo==true) {
            for (Report l : ls) {
                if (l.getCageCode().getCageCode() == Integer.valueOf(cag) &&l.getDate().equals(date) && (l.getCheckCage()==false || l.getClean()==false || l.getFeeding()==false)) {
                    list.add(l);
                }
            }
        }
        else if (cag.isEmpty() && date.isEmpty() && combo==false) {
            for (Report l : ls) {
                list.add(l);
            }
        }
        
        model.addAttribute("cage", cageService.getCageList());
        model.addAttribute("list",list );
        return "indexReport";
    }
}
