package org.example.mappers;

import org.example.dto.EmployeeIdDto;
import org.example.dto.EmployeesDto;
import org.example.models.Employees;
import org.example.models.Jobs;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface EmployeesMapper {

    EmployeesMapper INSTANCE = Mappers.getMapper(EmployeesMapper.class);


    Employees toModel(EmployeesDto dto);

    @Mapping(source = "e.job_id", target = "job_id")
    EmployeesDto toEmployeesDto(Employees e, Jobs j);

    EmployeesDto toEmployeesDto(Employees e);
}
