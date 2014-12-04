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

    private String club_nickname;

    public String getClub_nickname() {
        return club_nickname;
    }

    public void setClub_nickname(String club_nickname) {
        this.club_nickname = club_nickname;
    }

    @Override
    public String toString() {
        return "ClubDetails{" +
                "club_nickname='" + club_nickname + '\'' +
                '}';
    }
}
