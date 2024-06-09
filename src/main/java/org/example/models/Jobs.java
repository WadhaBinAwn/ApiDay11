package org.example.models;

import jakarta.ws.rs.FormParam;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.sql.ResultSet;
import java.sql.SQLException;
@XmlRootElement
public class Jobs {


//    @FormParam("job_id")
//    private int job_id;
//    @FormParam("job_title")
//    private String job_title;
//    @FormParam("min_salary")
//    private double min_salary;
//    @FormParam("max_salary")
//    private double max_salary;
private int job_id;
    private String job_title;
    private double min_salary;
    private double max_salary;

    public Jobs() {
    }

    public Jobs(int job_id, double max_salary, double min_salary, String job_title) {
        this.job_id = job_id;
        this.max_salary = max_salary;
        this.min_salary = min_salary;
        this.job_title = job_title;
    }

    public Jobs(ResultSet rs) throws SQLException {
        job_id=rs.getInt(1);
        job_title=rs.getString(2);
        max_salary=rs.getDouble(3);
        min_salary=rs.getDouble(4);
    }

    @Override
    public String toString() {
        return "Jobs{" +
                "job_id=" + job_id +
                ", job_title='" + job_title + '\'' +
                ", min_salary=" + min_salary +
                ", max_salary=" + max_salary +
                '}';
    }

    public int getJob_id() {
        return job_id;
    }

    public void setJob_id(int job_id) {
        this.job_id = job_id;
    }

    public double getMax_salary() {
        return max_salary;
    }

    public void setMax_salary(double max_salary) {
        this.max_salary = max_salary;
    }

    public double getMin_salary() {
        return min_salary;
    }

    public void setMin_salary(double min_salary) {
        this.min_salary = min_salary;
    }

    public String getJob_title() {
        return job_title;
    }

    public void setJob_title(String job_title) {
        this.job_title = job_title;
    }
}
