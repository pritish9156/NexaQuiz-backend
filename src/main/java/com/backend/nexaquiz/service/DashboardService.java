package com.backend.nexaquiz.service;

import com.backend.nexaquiz.dto.response.*;

import java.util.List;

public interface DashboardService {

    DashboardResponse getDashboardData();

    List<RecentAttemptResponse> getRecentAttempts();

    List<UpcomingQuizResponse> getUpcomingQuizzes();

    List<TopStudentResponse> getTopStudents();

    List<MonthlyPerformanceResponse> getMonthlyPerformance();
}