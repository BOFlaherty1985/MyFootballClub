package uk.co.myfootballclub.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;


import java.util.ArrayList;
import java.util.List;

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

    @Autowired
    private ObjectMapper objectMapper;

    @Bean
    public RestTemplate restTemplate() {
        RestTemplate restTemplate = new RestTemplate();
        List<HttpMessageConverter<?>> converters = new ArrayList<HttpMessageConverter<?>>();

        MappingJackson2HttpMessageConverter jsonMessageConverter = new MappingJackson2HttpMessageConverter();
        jsonMessageConverter.setObjectMapper(objectMapper);
        converters.add(jsonMessageConverter);
        restTemplate.setMessageConverters(converters);

        return restTemplate;
    }

}
