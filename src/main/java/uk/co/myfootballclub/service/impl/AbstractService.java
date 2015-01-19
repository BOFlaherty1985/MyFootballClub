package uk.co.myfootballclub.service.impl;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;

/**
 * Abstract Service Implementation for generic/common Service behavior
 *
 * @author Benjamin O'Flaherty
 * @date Created on: 04/12/2014
 * @project MyFootballClub
 */
public abstract class AbstractService {

    protected HttpEntity generateRequestHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("auth-token", "9b849032b29941099df1bf60261e1c87");

        return new HttpEntity(headers);
    }

}
