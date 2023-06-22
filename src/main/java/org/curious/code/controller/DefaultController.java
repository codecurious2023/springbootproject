package org.curious.code.controller;

import org.curious.code.config.NewPropConfig;
import org.curious.code.config.PropertyConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/default")
@PropertySource("classpath:/startup.properties")
//@PropertySources({
//		@PropertySource(""),@PropertySource("")
//})
public class DefaultController {
	@Value("${spring.datasource.url}")
	private String url;
	@Autowired
	private PropertyConfig config;
	@Autowired
	private NewPropConfig newConfig;

	@Value("${code.curious.ownerName}")
	private String owner;

	@RequestMapping(value="/test",method = RequestMethod.GET)
	public String get(){
		return "Hello CodeCurious Group in test project";
	}
	
	@GetMapping("/")
	public String def(){
		return owner+"Hello CodeCurious Group url-"+config.getDriverClassName() +" - newConfig-"+newConfig.getDatabasePlatform();
	}

}
