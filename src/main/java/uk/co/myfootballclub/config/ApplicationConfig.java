package uk.co.myfootballclub.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * Java Application Configuration Class (servlet.xml)
 *
 * @author Benjamin O'Flaherty
 * @date Created on: 27/11/2014
 * @project MyFootballClub
 */
@Configuration      //Marks this class as configuration
@ComponentScan("com.javahash.spring")    //Specifies which package to scan
@EnableWebMvc   //Enables Spring's annotations
public class ApplicationConfig {


    // view resolver etc

}
