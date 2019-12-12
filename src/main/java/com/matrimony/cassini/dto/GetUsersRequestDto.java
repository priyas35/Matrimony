package com.matrimony.cassini.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GetUsersRequestDto {
	
	private Integer userId;
	private String occupation;
	private String religion;
	private Integer age;

}
