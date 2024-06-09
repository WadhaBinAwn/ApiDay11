package org.example.controller;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;
import org.example.dao.EmployeeDAO;
import org.example.dao.JobDAO;
import org.example.dto.EmployeeFilterDto;
import org.example.dto.EmployeeIdDto;
import org.example.dto.EmployeesDto;
import org.example.dto.JobsDto;
import org.example.exceptions.DataNotFoundException;
import org.example.mappers.EmployeesMapper;
import org.example.mappers.JobsMapper;
import org.example.models.Employees;
import org.example.models.Jobs;

import java.net.URI;
import java.sql.SQLException;
import java.util.ArrayList;

@Path("/employees")
public class EmployeeController {

    EmployeeDAO dao = new EmployeeDAO();
     JobDAO jao = new JobDAO();


    @Context
    private UriInfo uriInfo;

    @Context
    private HttpHeaders headers;




//    @GET
//    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
//    public Response getAllEmployees(@BeanParam EmployeeFilterDto filterDto) throws SQLException, ClassNotFoundException {
//
//        GenericEntity<ArrayList<Employees>>  employee = new GenericEntity<ArrayList<Employees>> (dao.selectAllEmployees(filterDto)){};
//       try {
//           if (headers.getAcceptableMediaTypes().contains(MediaType.valueOf(MediaType.APPLICATION_XML))) {
//               return Response
//                       .ok(employee)
//                       .type(MediaType.APPLICATION_XML)
//                       .build();
//           }
//
//        return Response
//                .ok(employee, MediaType.APPLICATION_JSON)
//                .build();
//    }
//    catch (Exception e)
//    {
//        throw new RuntimeException(e);
//    }
//    }

@GET
@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
public Response getAllEmployees(@BeanParam EmployeeFilterDto filterDto) {
    try {
        // Check if hire year is specified
        if (filterDto.getHireYear() != null) {
            ArrayList<Employees> employeesByHireYear = dao.selectEmployeesByHireYear(filterDto.getHireYear());
            GenericEntity<ArrayList<Employees>> employee = new GenericEntity<ArrayList<Employees>>(employeesByHireYear) {};
            return Response.ok(employee).build();
        }
        // Check if job ID is specified
        else if (filterDto.getJobId() != null) {
            ArrayList<Employees> employeesByJobId = dao.selectEmployeesByJobId(filterDto.getJobId());
            GenericEntity<ArrayList<Employees>> employee = new GenericEntity<ArrayList<Employees>>(employeesByJobId) {};
            return Response.ok(employee).build();
        } else {
            // Retrieve all employees
            ArrayList<Employees> employees = dao.selectAllEmployees(filterDto);
            GenericEntity<ArrayList<Employees>> employee = new GenericEntity<ArrayList<Employees>>(employees) {};
            // Check acceptable media types
            if (headers.getAcceptableMediaTypes().contains(MediaType.valueOf(MediaType.APPLICATION_XML))) {
                return Response.ok(employee).type(MediaType.APPLICATION_XML).build();
            }
            return Response.ok(employee, MediaType.APPLICATION_JSON).build();
        }
    } catch (SQLException | ClassNotFoundException e) {
        throw new RuntimeException(e);
    }
}







//    @GET
//    @Path("{employees_id}")
//    public Response getEmployee(@PathParam("employees_id") int employees_id) {
//        try {
//            Employees employees = dao.selectEmployees(employees_id);
//
//            if (employees == null) {
//                throw new DataNotFoundException("employee with ID " + employees_id + " not found");
//            }
////            EmployeeFilterDto dto = new EmployeeFilterDto();
////            dto.setJobId(employees.getJob_id());
////            dto.setMaxSalary(employees.getMax_salary());
////            dto.setMinSalary(employees.getMin_salary());
////            dto.setJob_title(employees.getJob_title());
////            addLink(dto);
//
//            return Response.ok(employees).build();
//        } catch (ClassNotFoundException e) {
//            throw new RuntimeException(e);
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//    }


    @GET
    @Path("/{employee_id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON,"text/csv"})
    public Response getEmployee(@PathParam("employee_id") int employee_id, @Context UriInfo uriInfo) {
        try {
            Employees employees = dao.selectEmployees(employee_id);
            if (employees == null) {
                throw new DataNotFoundException("Employee with ID " + employee_id + " not found");
            }

            // Create self link
//            Link selfLink = Link.fromUri(uriInfo.getAbsolutePath())
//                    .rel("self")
//                    .build();


            Jobs j = JobDAO.selectJob(employees.getJob_id());
            EmployeesDto dto = EmployeesMapper.INSTANCE.toEmployeesDto(employees,j);
            // Add links to the response
            addLink(dto);

            return Response.ok(dto).build();
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void addLink(EmployeesDto dto) {
        URI selfUri = uriInfo.getAbsolutePath();
        URI jobUri = uriInfo.getAbsolutePathBuilder()
                            .path(JobController.class)
                .build();
        dto.addLink(jobUri.toString(),"Jobs");
        dto.addLink(selfUri.toString(), "self");

        // Add other links as needed
    }
    @DELETE
    @Path("{employees_id}")
    public void deleteEmployees(@PathParam("employees_id") int employees_id) {
        try {
            dao.deleteEmployees(employees_id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @POST
    public Response insertEmployees(Employees employees) {
        try {
            dao.insertEmployees(employees);
            NewCookie cookie = (new NewCookie.Builder("username")).value("OOOOO").build();
            URI uri = uriInfo.getAbsolutePathBuilder().path(employees.getJob_id()+"").build();
            return Response
                    .created(uri)
                    .cookie(cookie)
                    .header("Created by", "Wadha")
                    .build();
        } catch (Exception e) {
            throw new RuntimeException(e);
    }}

    @PUT
    @Path("{employees_id}")
    public void updateEmployees(@PathParam("employees_id") int employees_id, Employees employees) {



        try {
            employees.setEmployee_id(employees_id);
            dao.updateEmployees(employees);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

//HW
    @GET
    @Path("/hireYear/{year}")
    public Response getEmployeesByHireYear(@PathParam("year") int year) {
        try {
            ArrayList<Employees> employees = dao.selectEmployeesByHireYear(year);
            return Response.ok(employees).build();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    //HW
    @GET
    @Path("/jobId/{id}")
    public Response getEmployeesByJobId(@PathParam("id") int jobId) {
        try {
            ArrayList<Employees> employees = dao.selectEmployeesByJobId(jobId);
            return Response.ok(employees).build();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }




}
