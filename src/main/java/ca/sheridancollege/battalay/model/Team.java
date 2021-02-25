package ca.sheridancollege.battalay.model;

import lombok.*;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
public class Team implements Serializable {
    private int TeamID;  // auto increment to be generated by RDBMS
    @NonNull
    private  String TeamName;
    private String Continent;
    private int Played;
    private int Won;
    private int Drawn;
    private int Lost;
    private int points;

}
