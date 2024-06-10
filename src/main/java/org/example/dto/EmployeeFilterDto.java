package org.example.dto;

import jakarta.ws.rs.QueryParam;

public class EmployeeFilterDto {
    @QueryParam("employeeId")
    private Integer employeeId;

    @QueryParam("limit")
    private Integer limit;

    @QueryParam("offset")
    private int offset;

    @QueryParam("hireYear") // Query parameter for hire year
    private Integer hireYear;

    @QueryParam("jobId") // Query parameter for job ID
    private Integer jobId;


    @QueryParam("first_name") // Query parameter for job ID
    private Integer first_name;


    public Integer getFirst_name() {
        return first_name;
    }

    public void setFirst_name(Integer first_name) {
        this.first_name = first_name;
    }

    public Integer getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public Integer getHireYear() {
        return hireYear;
    }

    public void setHireYear(Integer hireYear) {
        this.hireYear = hireYear;
    }

    public Integer getJobId() {
        return jobId;
    }

    public void setJobId(Integer jobId) {
        this.jobId = jobId;
    }
}
