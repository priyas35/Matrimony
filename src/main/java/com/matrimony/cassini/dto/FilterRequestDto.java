package com.matrimony.cassini.dto;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;

@Setter@Getter
public class FilterRequestDto {
	
	private Integer userId;
	private String occupation;
	private String religion;
	private LocalDate dateOfBirth;

}
