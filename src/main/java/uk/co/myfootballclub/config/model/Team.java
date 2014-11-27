package uk.co.myfootballclub.config.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by BO034731 on 27/11/14.
        */

@JsonIgnoreProperties(ignoreUnknown = true)
public class Team {

    private int id;
    private String name;
    private String shortName;
    private String crestUrl;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getShortName() {
        return shortName;
    }

    public String getCrestUrl() {
        return crestUrl;
    }

    @Override
    public String toString() {
        return "Team{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", shortName='" + shortName + '\'' +
                ", crestUrl='" + crestUrl + '\'' +
                '}';
    }
}
