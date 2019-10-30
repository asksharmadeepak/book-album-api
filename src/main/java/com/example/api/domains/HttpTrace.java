package com.example.api.domains;


public class HttpTrace {

    private int totalNumberOfRequest;
    private int totalNumberOfOkRequest;
    private int totalNumberOfNotFoundRequest;
    private int totalNumberOfServerErrorRequest;
    private String averageResponseTimeRequest;
    private String minResponseTimeRequest;
    private String maxResponseTimeRequest;


    public HttpTrace(int totalNumberOfRequest, int totalNumberOfOkRequest, int totalNumberOfNotFoundRequest,
                     int totalNumberOfServerErrorRequest, String averageResponseTimeRequest, String minResponseTimeRequest,
                     String maxResponseTimeRequest) {
        this.totalNumberOfRequest = totalNumberOfRequest;
        this.totalNumberOfOkRequest = totalNumberOfOkRequest;
        this.totalNumberOfNotFoundRequest = totalNumberOfNotFoundRequest;
        this.totalNumberOfServerErrorRequest = totalNumberOfServerErrorRequest;
        this.averageResponseTimeRequest = averageResponseTimeRequest;
        this.minResponseTimeRequest = minResponseTimeRequest;
        this.maxResponseTimeRequest = maxResponseTimeRequest;
    }

    public HttpTrace() {
    }

    public int getTotalNumberOfRequest() {
        return totalNumberOfRequest;
    }

    public void setTotalNumberOfRequest(int totalNumberOfRequest) {
        this.totalNumberOfRequest = totalNumberOfRequest;
    }

    public int getTotalNumberOfOkRequest() {
        return totalNumberOfOkRequest;
    }

    public void setTotalNumberOfOkRequest(int totalNumberOfOkRequest) {
        this.totalNumberOfOkRequest = totalNumberOfOkRequest;
    }

    public int getTotalNumberOfNotFoundRequest() {
        return totalNumberOfNotFoundRequest;
    }

    public void setTotalNumberOfNotFoundRequest(int totalNumberOfNotFoundRequest) {
        this.totalNumberOfNotFoundRequest = totalNumberOfNotFoundRequest;
    }

    public int getTotalNumberOfServerErrorRequest() {
        return totalNumberOfServerErrorRequest;
    }

    public void setTotalNumberOfServerErrorRequest(int totalNumberOfServerErrorRequest) {
        this.totalNumberOfServerErrorRequest = totalNumberOfServerErrorRequest;
    }

    public String getAverageResponseTimeRequest() {
        return averageResponseTimeRequest;
    }

    public void setAverageResponseTimeRequest(String averageResponseTimeRequest) {
        this.averageResponseTimeRequest = averageResponseTimeRequest;
    }

    public String getMinResponseTimeRequest() {
        return minResponseTimeRequest;
    }

    public void setMinResponseTimeRequest(String minResponseTimeRequest) {
        this.minResponseTimeRequest = minResponseTimeRequest;
    }

    public String getMaxResponseTimeRequest() {
        return maxResponseTimeRequest;
    }

    public void setMaxResponseTimeRequest(String maxResponseTimeRequest) {
        this.maxResponseTimeRequest = maxResponseTimeRequest;
    }

    @Override
    public String toString() {
        return "HttpTrace{" +
                "totalNumberOfRequest=" + totalNumberOfRequest +
                ", totalNumberOfOkRequest=" + totalNumberOfOkRequest +
                ", totalNumberOfNotFoundRequest=" + totalNumberOfNotFoundRequest +
                ", totalNumberOfServerErrorRequest=" + totalNumberOfServerErrorRequest +
                ", averageResponseTimeRequest='" + averageResponseTimeRequest + '\'' +
                ", minResponseTimeRequest='" + minResponseTimeRequest + '\'' +
                ", maxResponseTimeRequest='" + maxResponseTimeRequest + '\'' +
                '}';
    }
}
