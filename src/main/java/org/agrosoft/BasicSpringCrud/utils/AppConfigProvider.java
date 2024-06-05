package org.agrosoft.BasicSpringCrud.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
public class AppConfigProvider {
	
	@Value("${randomuser.fake.api.base.url}")
	private String baseUrl;
	
	@Value("${randomuser.fake.api.nationality}")
	private String nat;
	
	@Value("${randomuser.fake.api.include}")
	private String inc;
}
