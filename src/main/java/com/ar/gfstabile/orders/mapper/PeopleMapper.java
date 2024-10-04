package com.ar.gfstabile.orders.mapper;

import org.mapstruct.Mapper;

import com.ar.gfstabile.orders.dto.common.PersonDto;
import com.ar.gfstabile.orders.model.Person;

@Mapper(componentModel = "spring")
public interface PeopleMapper {

    Person map(PersonDto person);

}