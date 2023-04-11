/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.ProjectSem4.Repositories;

import com.example.ProjectSem4.Entities.Reportdetail;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.bind.annotation.PathVariable;

/**
 *
 * @author ruava
 */
public interface ReportDetailRepository extends JpaRepository<Reportdetail, Integer> {
    @Query(value = "SELECT * FROM Reportdetail WHERE reportid = :reportid ORDER BY id DESC",nativeQuery = true)
    List<Reportdetail> findByReport(@PathVariable("reportid") int reportid);
}
