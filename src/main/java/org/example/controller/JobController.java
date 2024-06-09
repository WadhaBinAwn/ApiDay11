package org.example.controller;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;
import org.example.dao.JobDAO;
import org.example.dto.JobsDto;
import org.example.exceptions.DataNotFoundException;
import org.example.mappers.JobsMapper;
import org.example.models.Jobs;
import org.example.dto.JobsFilterDto;

import java.net.URI;
import java.sql.SQLException;
import java.util.ArrayList;

@Path("jobs")
public class JobController {


    JobDAO dao = new JobDAO();

    @Context
    private UriInfo uriInfo;

    @Context
    private HttpHeaders headers;

    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, "text/csv"})
    public Response getAllJobs(@BeanParam JobsFilterDto filterDto) {
        try {
            GenericEntity<ArrayList<Jobs>> jobs = new GenericEntity<ArrayList<Jobs>>(dao.selectAllJobs(filterDto)) {};
            if (headers.getAcceptableMediaTypes().contains(MediaType.valueOf(MediaType.APPLICATION_XML))) {
                return Response
                        .ok(jobs)
                        .type(MediaType.APPLICATION_XML)
                        .build();
            } else if (headers.getAcceptableMediaTypes().contains(MediaType.valueOf("text/csv"))) {
                return Response
                        .ok(jobs)
                        .type("text/csv")
                        .build();
            }

            return Response
                    .ok(jobs, MediaType.APPLICATION_JSON)
                    .build();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @GET
    @Path("{jobId}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, "text/csv"})
    public Response getJob(@PathParam("jobId") int jobId) throws SQLException {
        try {
            Jobs job = dao.selectJob(jobId);
            if (job == null) {
                throw new DataNotFoundException("Job with ID " + jobId + " not found");
            }
            JobsDto dto = JobsMapper.INSTANCE.toJobsDto(job);
            addLink(dto);
            return Response.ok(dto).build();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private  void addLink(JobsDto dto){
        URI selfUri = uriInfo.getAbsolutePath();
        dto.addLink(selfUri.toString(), "self");
    }

    @POST
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response insertJob(JobsDto dto) {
        try {
            Jobs job = JobsMapper.INSTANCE.toModel(dto);
            System.out.println(job);
            dao.insertJob(job);
            NewCookie cookie = (new NewCookie.Builder("username")).value("Wadha").build();
            URI uri = uriInfo.getAbsolutePathBuilder().path(String.valueOf(job.getJob_id())).build();
            return Response
                    .created(uri)
                    .cookie(cookie)
                    .header("Created by", "Wadha")
                    .build();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @POST
    public Response insertJobFromForm(@BeanParam Jobs job) {
        try {
            dao.insertJob(job);
            NewCookie cookie = (new NewCookie.Builder("username")).value("OOOOO").build();
            URI uri = uriInfo.getAbsolutePathBuilder().path(String.valueOf(job.getJob_id())).build();
            return Response
                    .created(uri)
                    .cookie(cookie)
                    .header("Created by", "Wadha")
                    .build();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @PUT
    @Path("{jobId}")
    public void updateJob(@PathParam("jobId") int jobId, Jobs job) {
        try {
            job.setJob_id(jobId);
            dao.updateJob(job);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
