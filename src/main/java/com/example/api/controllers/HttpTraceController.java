package com.example.api.controllers;

import com.example.api.domains.HttpTrace;
import com.example.api.services.HttpTraceService;
import com.example.api.utils.ServiceEndPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.UUID;
import java.util.concurrent.ExecutionException;

@Controller
public class HttpTraceController {

    @Autowired
    HttpTraceService httpTraceService;

    protected static final Logger LOGGER = LoggerFactory.getLogger(HttpTraceController.class);

    @GetMapping(ServiceEndPoint.HTTP_TRACE)
    @ResponseBody
    public HttpTrace getHttpTraceReport() throws ExecutionException, InterruptedException {
        LOGGER.debug("getHttpTraceReport: START");
        MDC.put("getHttpTraceReport:ID", UUID.randomUUID().toString());
        HttpTrace trace = httpTraceService.getHttpTraceReport();
        LOGGER.debug("getHttpTraceReport: END");
        MDC.clear();
        return trace;
    }

}
