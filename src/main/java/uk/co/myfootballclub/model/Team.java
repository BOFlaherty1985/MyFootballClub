package uk.co.myfootballclub.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


/**
 * Team Model Object
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Team {

    private String name;

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Team{" +
                "name='" + name + '\'' +
                '}';
    }
}
