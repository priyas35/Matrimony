package com.matrimony.cassini.dto;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InterestRequestDto {
	
	private Integer userId;
	private String occupation;
	private String religion;
	private LocalDate dateOfBirth;

}
