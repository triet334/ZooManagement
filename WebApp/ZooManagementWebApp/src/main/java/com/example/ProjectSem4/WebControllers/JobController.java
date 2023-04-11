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
import com.example.ProjectSem4.IServices.IHealthTracking;
import com.example.ProjectSem4.IServices.IJob;
import com.example.ProjectSem4.IServices.IReport;
import com.example.ProjectSem4.IServices.IShift;

import com.google.gson.Gson;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
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
@RequestMapping("job")
public class JobController {

    @Autowired
    IEmployee employeeService;

    @Autowired
    ICage cageService;

    @Autowired
    IShift shiftService;

    @Autowired
    IJob jobService;

    @Autowired
    IAnimal animalService;

    @Autowired
    IAssignmentForEmployeeAndCage assignmentService;

    @Autowired
    IReport reportService;

    @Autowired
    IHealthTracking healthTrackingService;

    public JobController(IJob _jobService, IEmployee _employeeService, IShift _shiftService) {
        this.jobService = _jobService;
        this.employeeService = _employeeService;
        this.shiftService = _shiftService;
    }

    //load Job theo Employee trong combobox
    @ResponseBody
    @RequestMapping(value = "loadfindByEmployee/{code}", method = RequestMethod.GET)
    public List<Jobsforemployee> loadfindByEmployee(@PathVariable("code") String code) {
        List<Jobsforemployee> lst = jobService.findAllSort(code);
        return lst;
    }

    //load animal theo cage
    @ResponseBody
    @RequestMapping(value = "loadAnimalsByCage/{code}", method = RequestMethod.GET)
    public String loadAnimalsByCage(@PathVariable("code") int code) {
        Gson gson = new Gson();
        return gson.toJson(animalService.findbyCage(code));
    }
    @RequestMapping(value = "/findByEmployee/{code}",method = RequestMethod.GET)
    public String findByCage(Model model,@PathVariable("code") String code) {
        model.addAttribute("list", jobService.findAllSort(code));
        return "indexreport";
    }
    
    @RequestMapping(value = "/findByDate/{date}",method = RequestMethod.GET)
    public String findByDate(Model model,@PathVariable("date") String date) {
        model.addAttribute("list", jobService.findByDate(date));
        return "indexreport";
    }
    
    @RequestMapping(value = "/findByShift/{code}",method = RequestMethod.GET)
    public String findByShift(Model model,@PathVariable("code") String code) {
        model.addAttribute("list", jobService.findByDate(code));
        return "indexreport";
    }

    //index
    @RequestMapping("indexJob")
    public String index(Model model, HttpSession session) {
        Employee ep = (Employee) session.getAttribute("employee");
        if (ep != null) {

            if (ep.getPositionCode().getPositionCode() == 1) {
                model.addAttribute("employees", employeeService.findAllExcept());
                model.addAttribute("jobList", jobService.getJobList());
                model.addAttribute("shiftList", shiftService.getShiftList());
                return "indexJob";
            } else if (ep.getPositionCode().getPositionCode() == 2) {
                return "redirect:/job/findbyweek";
            } else {
                return "redirect:/health/findFault";
            }
        } else {
            return "redirect:/login";
        }

    }

    //create job
    @RequestMapping(value = "createJob", method = RequestMethod.GET)
    public String create(Model model, HttpSession session) {
        Employee ep = (Employee) session.getAttribute("employee");
        if (ep != null) {

            if (ep.getPositionCode().getPositionCode() == 1) {
                model.addAttribute("newjob", new Jobsforemployee());
                model.addAttribute("employeeList", employeeService.findAllExcept());
                model.addAttribute("shiftList", shiftService.getShiftList());
                return "createJob";
            } else if (ep.getPositionCode().getPositionCode() == 2) {
                return "redirect:/job/findbyweek";
            } else {
                return "redirect:/health/findFault";
            }
        } else {
            return "redirect:/login";
        }

    }

    @RequestMapping(value = "createJob", method = RequestMethod.POST)
    public String create(Model model, @Valid @ModelAttribute("newJob") Jobsforemployee newJob, BindingResult bindingResult, @RequestParam("date1") String date1, RedirectAttributes redirectAttrs) throws ParseException {
        if (bindingResult.hasErrors()) {
            model.addAttribute("newjob", newJob);
            return "createJob";
        }
        String dat = date1; //lấy tham số bên create qua, do th:field ko nhận kiểu dữ liệu date dưới database
        SimpleDateFormat sdff = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cc = Calendar.getInstance();
        cc.setTime(sdff.parse(dat));
        dat = sdff.format(cc.getTime());

        LocalDate localdate = LocalDate.now();
        LocalDate datess = LocalDate.parse(dat);
        if (localdate.compareTo(datess) <= 0) {
            String dt = date1; //lấy tham số bên create qua, do th:field ko nhận kiểu dữ liệu date dưới database
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Calendar c = Calendar.getInstance();
            c.setTime(sdf.parse(dt));
            dt = sdf.format(c.getTime());
            String newdate = dt;
            int i = 0;

            do {
                Jobsforemployee bb = new Jobsforemployee();
                bb.setEmployeeCode(newJob.getEmployeeCode());
                bb.setShiftCode(newJob.getShiftCode());
                bb.setWorkDate(newdate);

                jobService.saveJob(bb);
                i++;

                dt = sdf.format(c.getTime());
                sdf = new SimpleDateFormat("yyyy-MM-dd");
                c = Calendar.getInstance();
                c.setTime(sdf.parse(dt));
                c.add(Calendar.DATE, 1);
                dt = sdf.format(c.getTime());
                newdate = dt;

            } while (i < 7);
            return "redirect:/job/indexJob";
        } //        List<Jobsforemployee> listJ= jobService.findByDT(date1, newJob.getShiftCode().getShiftCode());
        //        List<Assignmentforemployeeandcage> listA= assignmentService.checkCageAndEmployee(0, date1)
        //        int count = 0;
        //        for (Assignmentforemployeeandcage as : listA) {
        //            for (Jobsforemployee js : listJ) {
        //                if (as.getEmployeeCode().getEmployeeCode().equals(js.getEmployeeCode().getEmployeeCode())) {
        //                    count++;
        //                }
        //            }
        //            
        //        }
        //        int a = count;
        //        return "error";
        //        List<Jobsforemployee> sortShift = jobService.findByEmpCode(newJob.getEmployeeCode().getEmployeeCode(), newJob.getShiftCode().getShiftCode());
        //        if (!sortShift.isEmpty()) {
        //            if (jobService.dateExist(date1)) {
        //                model.addAttribute("newJob", newJob);
        //                model.addAttribute("msg", "Employee was assigned work !");
        //                return "createJob";
        ////                bindingResult.addError(new FieldError("newJob", "workDate", "Employee was assigned work !"));
        //            } else {
        //            }
        //        } else {
        //            if (jobService.dateExist(date1)) {
        //                bindingResult.addError(new FieldError("newJob", "workDate", "Employee was assign for this Shift !"));
        //            } else {
        //                String dt = date1; //lấy tham số bên create qua, do th:field ko nhận kiểu dữ liệu date dưới database
        //                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        //                Calendar c = Calendar.getInstance();
        //                c.setTime(sdf.parse(dt));
        //                dt = sdf.format(c.getTime());
        //                String newdate = dt;
        //                int i = 0;
        //
        //                do {
        //                    Jobsforemployee bb = new Jobsforemployee();
        //                    bb.setEmployeeCode(newJob.getEmployeeCode());
        //                    bb.setShiftCode(newJob.getShiftCode());
        //                    bb.setWorkDate(newdate);
        //
        //                    jobService.saveJob(bb);
        //                    i++;
        //
        //                    dt = sdf.format(c.getTime());
        //                    sdf = new SimpleDateFormat("yyyy-MM-dd");
        //                    c = Calendar.getInstance();
        //                    c.setTime(sdf.parse(dt));
        //                    c.add(Calendar.DATE, 1);
        //                    dt = sdf.format(c.getTime());
        //                    newdate = dt;
        //
        //                } while (i < 7);
        //            }
        //        }
        //        String emp = newJob.getEmployeeCode().getEmployeeCode();
        //        List<Assignmentforemployeeandcage> assignment = assignmentService.findEmpByCode(emp);
        //        int cageCode = assignment.get(0).getCageCode().getCageCode();
        //        List<Assignmentforemployeeandcage> list = assignmentService.findEmployee(cageCode);
        //
        //        int shift = newJob.getShiftCode().getShiftCode();
        //        int count = 0;
        //        //String empCode1 = list.get(0).getEmployeeCode().getEmployeeCode();
        //
        //        for (int z = 0; z < list.size(); z++) {
        //            List<Jobsforemployee> job = jobService.findByEmpCode(list.get(z).getEmployeeCode().getEmployeeCode(), shift);
        //            if (!job.isEmpty()) {
        //                count++;   
        //                
        //            }                      
        //        }
        //        
        //        if (count > 2) {
        //            model.addAttribute("msg", "Employee for Shift is full, can not add more Employe in this Shift !!!");
        //        } else {
        //        String dt = date1; //lấy tham số bên create qua, do th:field ko nhận kiểu dữ liệu date dưới database
        //        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        //        Calendar c = Calendar.getInstance();
        //        c.setTime(sdf.parse(dt));
        //        dt = sdf.format(c.getTime());
        //        String newdate = dt;
        //        int i = 0;
        //
        //        do {
        //            Jobsforemployee bb = new Jobsforemployee();
        //            bb.setEmployeeCode(newJob.getEmployeeCode());
        //            bb.setShiftCode(newJob.getShiftCode());
        //            bb.setWorkDate(newdate);
        //
        //            jobService.saveJob(bb);
        //            i++;
        //
        //            dt = sdf.format(c.getTime());
        //            sdf = new SimpleDateFormat("yyyy-MM-dd");
        //            c = Calendar.getInstance();
        //            c.setTime(sdf.parse(dt));
        //            c.add(Calendar.DATE, 1);
        //            dt = sdf.format(c.getTime());
        //            newdate = dt;
        //
        //        } while (i < 7);
        //        }
        else {
            redirectAttrs.addFlashAttribute("mes", "Cannot create a work schedule for a date in the past ");
            return "redirect:/job/createJob";
        }
    }

    //update
    @RequestMapping(value = "editJob/{id}", method = RequestMethod.GET)
    public String edit(Model model, @PathVariable("id") int id, HttpSession session) {
        Employee ep = (Employee) session.getAttribute("employee");
        if (ep != null) {

            if (ep.getPositionCode().getPositionCode() == 1) {
                Jobsforemployee job = jobService.findOne(id);
                if (job != null) {
                    model.addAttribute("employeeList", employeeService.findAllExcept());
                    model.addAttribute("shiftList", shiftService.getShiftList());
                    model.addAttribute("cageList", cageService.getCageList());
                    model.addAttribute("editJob", job);
                }
                return "editJob";
            } else if (ep.getPositionCode().getPositionCode() == 2) {
                return "redirect:/job/findbyweek";
            } else {
                return "redirect:/health/findFault";
            }
        } else {
            return "redirect:/login";
        }

    }

    @RequestMapping(value = "editJob", method = RequestMethod.POST)
    public String edit(Model model, @Validated Jobsforemployee job, @RequestParam("date1") String date1, BindingResult bindingResult) throws ParseException {
        if (bindingResult.hasErrors()) {
            return "editJob";
        }
        String dt = date1; //lấy tham số bên create qua, do th:field ko nhận kiểu dữ liệu date dưới database
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();
        c.setTime(sdf.parse(dt));
        dt = sdf.format(c.getTime());
        String newdate = dt;

        Jobsforemployee bb = new Jobsforemployee();
        bb.setFeedTableId(job.getFeedTableId());
        bb.setEmployeeCode(job.getEmployeeCode());
        bb.setShiftCode(job.getShiftCode());
        bb.setWorkDate(newdate);

        jobService.saveJob(bb);
        return "redirect:/job/indexJob";
    }

    @RequestMapping(value = "deleteJob/{id}", method = RequestMethod.GET)
    public String delete(Model model, @PathVariable("id") int id) {
        Jobsforemployee job = jobService.findOne(id);
        if (job != null) {
            jobService.deleteJob(job);
        } else {
            return "error";
        }
        return "redirect:/job/indexJob";
    }

    @RequestMapping(value = "qq/{cage}/{date}/{scode}", method = RequestMethod.GET)
    public String qq(Model model, @PathVariable("cage") int cage, @PathVariable("date") String date, @PathVariable("scode") int scode) {
        List<Jobsforemployee> listJ = jobService.findByDT(date, scode);
        List<Assignmentforemployeeandcage> listA = assignmentService.findByCage(cage);
        int count = 0;
        for (Assignmentforemployeeandcage as : listA) {
            for (Jobsforemployee js : listJ) {
                if (as.getEmployeeCode().getEmployeeCode().equals(js.getEmployeeCode().getEmployeeCode())) {
                    count++;
                }
            }

        }
        int a = count;
        return "error";
    }

    @RequestMapping(value = "/findbyweek", method = RequestMethod.GET)
    public String findW(Model model, HttpSession session) {
        Employee ep = (Employee) session.getAttribute("employee");
        if (ep != null) {

            if (ep.getPositionCode().getPositionCode() == 1) {
                return "redirect:/";
            } else if (ep.getPositionCode().getPositionCode() == 2) {
                Healthtracking healthtracking = new Healthtracking();
                model.addAttribute("health", healthtracking);

                model.addAttribute("cages", assignmentService.findByEmp(ep.getEmployeeCode()));
                model.addAttribute("rpd", reportService.findAll());
                model.addAttribute("orp", new Report());
                List<Jobsforemployee> list = jobService.findAllSort(ep.getEmployeeCode());
                List<Jobsforemployee> ls = new ArrayList<>();

                for (int i = 0; i < 6; i++) {
                    ls.add(list.get(i));
                }
                // đảo list
                Collections.reverse(ls);
                model.addAttribute("list", ls);

                //load health
                List<Healthtracking> ht = healthTrackingService.findStatus();

                List<Assignmentforemployeeandcage> asm = assignmentService.findByEmp(ep.getEmployeeCode());
                List<Healthtracking> htk = new ArrayList<>();
                for (Healthtracking he : ht) {
                    for (Assignmentforemployeeandcage as : asm) {
                        if (as.getCageCode().getCageCode() == he.getAnimalCode().getCageCode().getCageCode()) {
                            htk.add(he);
                        }
                    }
                }
                model.addAttribute("listhealth", htk);
                return "report";
            } else {
                return "redirect:/health/findFault";
            }
        } else {
            return "redirect:/login";
        }

    }

    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public String search(Model model, @Param("cag") String cag, @Param("combo") int combo, @Param("date") String date) {
        List<Jobsforemployee> list = new ArrayList<>();
        List<Jobsforemployee> ls = jobService.getJobList();

        if (!cag.isEmpty() && date.isEmpty() && combo == 0) {
            for (Jobsforemployee l : ls) {
                if (l.getEmployeeCode().getEmployeeCode().equals(cag)) {
                    list.add(l);
                }
            }
        } else if (!cag.isEmpty() && date.isEmpty() && combo != 0) {
             for (Jobsforemployee l : ls) {
                if (l.getEmployeeCode().getEmployeeCode().equals(cag) && l.getShiftCode().getShiftCode().equals(combo)) {
                    list.add(l);
                }
            }
        } else if (!cag.isEmpty() && !date.isEmpty() && combo == 0) {
            for (Jobsforemployee l : ls) {
                if (l.getWorkDate().equals(date) && l.getEmployeeCode().getEmployeeCode().equals(cag)) {
                    list.add(l);
                }
            }
        } else if (!cag.isEmpty() && !date.isEmpty() && combo != 0) {
            for (Jobsforemployee l : ls) {
                if (l.getWorkDate().equals(date) && l.getEmployeeCode().getEmployeeCode().equals(cag) && l.getShiftCode().getShiftCode().equals(combo)) {
                    list.add(l);
                }
            }
        } else if (cag.isEmpty() && date.isEmpty() && combo == 0) {
            for (Jobsforemployee l : ls) {
                
                    list.add(l);
                

            }
        } else if (cag.isEmpty() && date.isEmpty() && combo != 0) {
            for (Jobsforemployee l : ls) {
                if (l.getShiftCode().getShiftCode()==combo) {
                    list.add(l);
                }
            }
        } else if (cag.isEmpty() && !date.isEmpty() && combo == 0) {
            for (Jobsforemployee l : ls) {
                if (l.getWorkDate().equals(date)) {
                    list.add(l);
                }
            }
        } else if (cag.isEmpty() && !date.isEmpty() && combo != 0) {
            for (Jobsforemployee l : ls) {
                if (l.getWorkDate().equals(date) && l.getShiftCode().getShiftCode().equals(combo)) {
                    list.add(l);
                }
            }
        }
          model.addAttribute("employees", employeeService.findAllExcept());
                model.addAttribute("jobList", list);
                model.addAttribute("shiftList", shiftService.getShiftList());
//        model.addAttribute("cage", cageService.getCageList());
//        model.addAttribute("list", list);
        return "indexJob";
    }
}
