package uk.co.myfootballclub.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.net.InetSocketAddress;
import java.net.Proxy;
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
@ComponentScan("uk.co.myfootballclub")    //Specifies which package to scan
public class ApplicationConfig {

    @Autowired
    private ObjectMapper objectMapper;

    private static final String PROXY_HOST = "127.0.0.1";
    private static final int PROXY_PORT = 5865;

    /**
     * Bean Definition for RestTemplate with Proxy setup.
     * @return restTemplate
     */
    @Bean
    public RestTemplate restTemplate() {

        SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();

        Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(PROXY_HOST, PROXY_PORT));
        requestFactory.setProxy(proxy);

        List<HttpMessageConverter<?>> converters = new ArrayList<HttpMessageConverter<?>>();

        RestTemplate restTemplate = new RestTemplate(requestFactory);

        MappingJackson2HttpMessageConverter jsonMessageConverter = new MappingJackson2HttpMessageConverter();
        jsonMessageConverter.setObjectMapper(objectMapper);
        converters.add(jsonMessageConverter);
        restTemplate.setMessageConverters(converters);

        return restTemplate;
    }

    /**
     * Bean definition for Jackson ObjectMapper.
     * @return objectMapper
     */
    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper();
    }

}
