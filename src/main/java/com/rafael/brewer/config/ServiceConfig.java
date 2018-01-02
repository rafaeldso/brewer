package com.rafael.brewer.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.rafael.brewer.service.CadastroCervejaService;

@Configuration
@ComponentScan(basePackageClasses = {CadastroCervejaService.class})
public class ServiceConfig {

}
