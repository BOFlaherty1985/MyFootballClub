package uk.co.myfootballclub.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Proof Of Concept (ClubDetails.class)
 *
 * @author Benjamin O'Flaherty
 * @date Created on: 04/12/2014
 * @project MyFootballClub
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ClubDetails {

    private String clubNickname;
    private String clubFounded;
    private String clubStadium;
    private String clubCapacity;
    private String clubWebsite;
    private String clubFacebook;

    public String getClubNickname() {
        return clubNickname;
    }

    public void setClubNickname(String clubNickname) {
        this.clubNickname = clubNickname;
    }

    public void setClubFounded(String clubFounded) {
        this.clubFounded = clubFounded;
    }

    public String getClubFounded() {
        return clubFounded;
    }

    public void setClubStadium(String clubStadium) {
        this.clubStadium = clubStadium;
    }

    public String getClubStadium() {
        return clubStadium;
    }

    public void setClubCapacity(String clubCapacity) {
        this.clubCapacity = clubCapacity;
    }

    public String getClubCapacity() {
        return clubCapacity;
    }

    public void setClubWebsite(String clubWebsite) {
        this.clubWebsite = clubWebsite;
    }

    public String getClubWebsite() {
        return clubWebsite;
    }

    public void setClubFacebook(String clubFacebook) {
        this.clubFacebook = clubFacebook;
    }

    public String getClubFacebook() {
        return clubFacebook;
    }

}
