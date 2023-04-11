/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.ProjectSem4.APIControllers;

import com.example.ProjectSem4.Entities.Animal;
import com.example.ProjectSem4.Entities.Assignmentforemployeeandcage;
import com.example.ProjectSem4.Entities.Employee;
import com.example.ProjectSem4.Entities.Healthdetail;
import com.example.ProjectSem4.Entities.Healthtracking;
import com.example.ProjectSem4.Entities.Jobsforemployee;
import com.example.ProjectSem4.Entities.Report;
import com.example.ProjectSem4.Entities.Reportdetail;
import com.example.ProjectSem4.IServices.IAnimal;
import com.example.ProjectSem4.IServices.IAssignmentForEmployeeAndCage;
import com.example.ProjectSem4.IServices.IEmployee;
import com.example.ProjectSem4.IServices.IHealthDetail;
import com.example.ProjectSem4.IServices.IHealthTracking;
import com.example.ProjectSem4.IServices.IJob;
import com.example.ProjectSem4.IServices.IReport;
import com.example.ProjectSem4.IServices.IReportDetail;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Admin
 */
@RestController
public class APIEmployeeController {

    @Autowired
    IEmployee employeeService;

    @Autowired
    IJob jobService;

    @Autowired
    IAssignmentForEmployeeAndCage assignmentService;

    @Autowired
    IAnimal animalService;

    @Autowired
    IHealthTracking healthService;

    @Autowired
    IHealthDetail healthDetailService;

    @Autowired
    IReportDetail reportDetailService;

    @Autowired
    IReport reportService;

    public APIEmployeeController(IEmployee _employeeService) {
        employeeService = _employeeService;
    }

    @RequestMapping(value = "/getReport/{code}/{date}", method = RequestMethod.GET)
    public ResponseEntity<List<Report>> getReport(@PathVariable("code") String code, @PathVariable("date") String date) {
        try {
            List<Report> list = new ArrayList<>();
            List<Report> lsr = reportService.findByDate(date);

            List<Assignmentforemployeeandcage> lsa = assignmentService.findByEmp(code);

            for (Assignmentforemployeeandcage la : lsa) {
                for (Report lr : lsr) {
                    if (la.getCageCode().getCageCode().equals(lr.getCageCode().getCageCode())) {
                        list.add(lr);
                    }
                }
            }

            if (list.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } else {
                return new ResponseEntity<>(list, HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/editHealthtracking/{id}")
    public ResponseEntity<Boolean> editHealthtracking(@PathVariable("id") int id, @RequestBody Healthtracking health) {
        try {
            boolean i = healthService.updateHealth(health);
            Healthdetail he = new Healthdetail();

            LocalDateTime now = LocalDateTime.now();

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

            String formatDateTime = now.format(formatter);

            he.setHealthid(health);
            he.setTime(formatDateTime);

            he.setDoctorCode(health.getDoctorCode().getEmployeeName());
            he.setDoctorNote(health.getDoctorNote());
            he.setEmployeeDescription(health.getEmployeeDescription());

            healthDetailService.create(he);
            return new ResponseEntity<>(i, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(false, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/find1Health/{id}")
    public ResponseEntity<Healthtracking> find1Health(@PathVariable("id") int id) {
        try {
            Healthtracking health = healthService.findOne(id);
            return new ResponseEntity<>(health, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/getHealthtracking/{code}", method = RequestMethod.GET)
    public ResponseEntity<List<Healthtracking>> getHealthtracking(@PathVariable("code") String code) {
        try {
            Employee emp = employeeService.findOneEmp(code);
            if (emp != null) {
                if (emp.getPositionCode().getPositionCode() == 3) {
                    List<Healthtracking> list = healthService.findStatus();
                    if (list.isEmpty()) {
                        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
                    } else {
                        return new ResponseEntity<>(list, HttpStatus.OK);
                    }
                } else {
                    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
                }
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/createHealthtracking", method = RequestMethod.POST)
    public ResponseEntity<Healthtracking> createHealthtracking(@RequestBody Healthtracking health) {
        try {
            healthService.createHealth(health);
            return new ResponseEntity<>(health, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/getAnimals/{code}", method = RequestMethod.GET)
    public ResponseEntity<List<Animal>> getAnimals(@PathVariable("code") int code) {
        try {
            List<Animal> list = animalService.findbyCage(code);
            if (list.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } else {
                return new ResponseEntity<>(list, HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/getCages/{code}", method = RequestMethod.GET)
    public ResponseEntity<List<Assignmentforemployeeandcage>> getCages(@PathVariable("code") String code) {
        try {
            List<Assignmentforemployeeandcage> list = assignmentService.findByEmp(code);
            if (list.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } else {
                return new ResponseEntity<>(list, HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/getTimeTable/{code}", method = RequestMethod.GET)
    public ResponseEntity<List<Jobsforemployee>> getTimeTable(@PathVariable("code") String code) {
        try {
            List<Jobsforemployee> list = jobService.findAllSort(code);
            List<Jobsforemployee> ls = new ArrayList<>();

            for (int i = 0; i < 7; i++) {
                ls.add(list.get(i));
            }
            Collections.reverse(ls);
            if (ls.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } else {
                return new ResponseEntity<>(ls, HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/findByCode/{code}")
    public ResponseEntity<Employee> findByCode(@PathVariable("code") String code) {
        try {
            Employee employee = employeeService.findOneEmp(code);
            return new ResponseEntity<>(employee, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/login/{code}/{password}")
    public ResponseEntity<Employee> checkLogin(@PathVariable("code") String code, @PathVariable("password") String password) {
        try {
            Employee employee = employeeService.checkLogin(code, password);
            return new ResponseEntity<>(employee, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/editEmployee/{code}")
    public ResponseEntity<Boolean> editEmployee(@PathVariable("code") String code, @RequestBody Employee employee) {
        try {
            Employee emp = employeeService.findOneEmp(code);
            boolean i = employeeService.updateAPI(employee);
            return new ResponseEntity<>(i, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(false, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @PutMapping("/editReport/{name}")
    public ResponseEntity<Boolean> editReport(@PathVariable("name") String name, @RequestBody Report report) {
        try {
            boolean i = reportService.updateReport(report);
            LocalDateTime now = LocalDateTime.now();

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

            String formatDateTime = now.format(formatter);
            Reportdetail rp = new Reportdetail();
            rp.setReportid(report);
            rp.setCheckCage(report.getCheckCage());
            rp.setClean(report.getClean());
            rp.setFeeding(report.getFeeding());
            rp.setEmployeeCode(name);
            rp.setTime(formatDateTime);

            reportDetailService.create(rp);
            return new ResponseEntity<>(i, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(false, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
