package com.example.api.services;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.IntSummaryStatistics;
import java.util.List;

@Component
public class TrackCounterService {

    private static Integer REQUEST_COUNT = 0;
    private static Integer REQUEST_OK = 0;
    private static Integer REQUEST_NOT_FOUND = 0;
    private static Integer REQUEST_SERVER_ERROR = 0;
    List<Long> responseTimeOfRequests = new ArrayList<>();

    public void startTracking() {
        REQUEST_COUNT++;
    }

    public void stopTracking(long currentTimeMillis, HttpStatus statusCode) {
        computeStatusCodeCount(statusCode);
        responseTimeOfRequests(System.currentTimeMillis() - currentTimeMillis);
        generateReport();
    }

    private void generateReport() {
        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter("service_tracker.txt");
            PrintWriter printWriter = new PrintWriter(fileWriter);
            printWriter.append("TotalNumberOfRequest::" + REQUEST_COUNT);
            printWriter.append("\nTotalNumberOfOkRequest::" + REQUEST_OK);
            printWriter.append("\nTotalNumberOfNotFoundRequest::" + REQUEST_NOT_FOUND);
            printWriter.append("\nTotalNumberOfServerErrorRequest::" + REQUEST_SERVER_ERROR);
            printWriter.append("\nAverageResponseTimeRequest::" + getStatistics().getAverage());
            printWriter.append("\nMinResponseTimeRequest::" + getStatistics().getMin());
            printWriter.append("\nMaxResponseTimeRequest::" + getStatistics().getMax());
            printWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void computeStatusCodeCount(HttpStatus statusCode) {
        switch (statusCode.value()) {
            case 200:
                REQUEST_OK++;
                break;
            case 400:
                REQUEST_NOT_FOUND++;
                break;
            case 500:
                REQUEST_SERVER_ERROR++;
                break;
        }
    }

    private void responseTimeOfRequests(long currentTimeMillis) {
        responseTimeOfRequests.add(currentTimeMillis);
    }

    private IntSummaryStatistics getStatistics() {
        IntSummaryStatistics stats = responseTimeOfRequests.stream().mapToInt(Long::intValue).summaryStatistics();
        return stats;
    }

}
