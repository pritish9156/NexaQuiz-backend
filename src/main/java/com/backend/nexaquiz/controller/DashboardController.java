package com.backend.nexaquiz.controller;

import com.backend.nexaquiz.dto.response.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.backend.nexaquiz.service.DashboardService;

import lombok.RequiredArgsConstructor;

import java.util.List;

@RestController
@RequestMapping("/api/dashboard")
@RequiredArgsConstructor
public class DashboardController {

    private final DashboardService
            dashboardService;

    @GetMapping
    public ResponseEntity<
            DashboardResponse>
    getDashboardData(){

        return ResponseEntity.ok(

                dashboardService
                        .getDashboardData()

        );

    }

    @GetMapping(
            "/recent-attempts"
    )
    public ResponseEntity<
            List<RecentAttemptResponse>>
    getRecentAttempts(){

        return ResponseEntity.ok(

                dashboardService
                        .getRecentAttempts()

        );
    }

    @GetMapping(
            "/upcoming-quizzes"
    )
    public ResponseEntity<
            List<UpcomingQuizResponse>>
    getUpcomingQuizzes(){

        return ResponseEntity.ok(

                dashboardService
                        .getUpcomingQuizzes()

        );
    }

    @GetMapping(
            "/top-students"
    )
    public ResponseEntity<
            List<TopStudentResponse>>
    getTopStudents(){

        return ResponseEntity.ok(

                dashboardService
                        .getTopStudents()

        );
    }

    @GetMapping(
            "/monthly-performance"
    )
    public ResponseEntity<
            List<MonthlyPerformanceResponse>>
    getMonthlyPerformance(){

        return ResponseEntity.ok(

                dashboardService
                        .getMonthlyPerformance()

        );

    }
}