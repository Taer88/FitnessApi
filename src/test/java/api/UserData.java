package api;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserData {

    private Integer id;
    private String firstName;
    private String lastName;
    private Integer fitCash;
    private Boolean isMyFriend;


    public UserData(Integer id, String firstName, String lastName, Integer fitCash, Boolean isMyFriend) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.fitCash = fitCash;
        this.isMyFriend = isMyFriend;
    }

    public Integer getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Integer getFitCash() {
        return fitCash;
    }

    public Boolean getMyFriend() {
        return isMyFriend;
    }


}