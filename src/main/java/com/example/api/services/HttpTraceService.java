package com.example.api.services;

import com.example.api.domains.HttpTrace;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

@Service
public class HttpTraceService {

    public HttpTrace getHttpTraceReport() {
        String fileName = "service_tracker.txt";
        List<String> httpTraceResponse = new ArrayList<>();
        try (Stream<String> stream = Files.lines(Paths.get(fileName))) {
            stream.forEach(line -> httpTraceResponse.add(line.split("::")[1]));
        } catch (IOException e) {
            e.printStackTrace();
        }
        HttpTrace httpTrace= new HttpTrace(Integer.parseInt(httpTraceResponse.get(0)), Integer.parseInt(httpTraceResponse.get(1)),
                Integer.parseInt(httpTraceResponse.get(2)), Integer.parseInt(httpTraceResponse.get(3)), httpTraceResponse.get(4),
                httpTraceResponse.get(5), httpTraceResponse.get(6));
        return httpTrace;
    }
}
