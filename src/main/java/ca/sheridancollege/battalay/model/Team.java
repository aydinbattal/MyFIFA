package ca.sheridancollege.battalay.model;

import lombok.*;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
public class Team implements Serializable {
    private int id;  // auto increment to be generated by RDBMS
    @NonNull
    private  String name;
    private String continent;
    private int gamesPlayed;
    private int wins;
    private int draws;
    private int losses;
    private int points;
}
