package org.example.dto;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlElementWrapper;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

@XmlRootElement
public class JobsDto {

    private int job_id;
private String job_title;
    private double min_salary;
    private double max_salary;

    private ArrayList<LinkDto> links = new ArrayList<>();

    public JobsDto() {
    }


    public JobsDto(int job_id, double min_salary, double max_salary, String job_title) {
        this.job_id = job_id;
       this.job_title=job_title;
        this.min_salary = min_salary;
        this.max_salary = max_salary;
    }

    public String getJob_title() {
        return job_title;
    }

    public void setJob_title(String job_title) {
        this.job_title = job_title;
    }

    public JobsDto(ResultSet rs) throws SQLException {
        this.job_id = rs.getInt("job_id");

        this.min_salary = rs.getDouble("min_salary");
        this.max_salary = rs.getDouble("max_salary");
    }


    public int getJob_id() {
        return job_id;
    }

    public void setJob_id(int job_id) {
        this.job_id = job_id;
    }

    public double getMinSalary() {
        return min_salary;
    }

    public void setMinSalary(double min_salary) {
        this.min_salary = min_salary;
    }

    public double getMaxSalary() {
        return max_salary;
    }

    public void setMaxSalary(double max_salary) {
        this.max_salary = max_salary;
    }

    @XmlElementWrapper
    @XmlElement(name = "link")
    public ArrayList<LinkDto> getLinks() {
        return links;
    }

    public void addLink(String url, String rel) {
        LinkDto link = new LinkDto();
        link.setLink(url);
        link.setRel(rel);
        links.add(link);
    }

    @Override
    public String toString() {
        return "JobsDto{" +
                "maxSalary=" + max_salary +
                ", minSalary=" + min_salary +
                ", job_title='" + job_title + '\'' +
                ", jobId=" + job_id +
                '}';
    }
}
