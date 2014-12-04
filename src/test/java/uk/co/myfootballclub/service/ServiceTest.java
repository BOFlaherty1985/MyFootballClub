package uk.co.myfootballclub.service;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;

/**
 * Common Service Test Setup
 *
 * @author Benjamin O'Flaherty
 * @date Created on: 04/12/2014
 * @project MyFootballClub
 */
public class ServiceTest {

    protected HttpEntity mockRequestHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("auth-token", "9b849032b29941099df1bf60261e1c87");

        return new HttpEntity(headers);
    }

}
