package ca.sheridancollege.battalay.database;

import ca.sheridancollege.battalay.model.Team;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DatabaseAccess {
    @Autowired
    NamedParameterJdbcTemplate jdbc;

    public List<Team> getTeams(){

        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
        String query = "SELECT * FROM Teams";

        return jdbc.query(query,namedParameters,new BeanPropertyRowMapper<Team>(Team.class));
    }

    public void  insertTeam(String name,String continent,int gamesPlayed,int wins,int draws,int losses){

        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
        String query = "INSERT INTO Teams(TeamName,Continent,Played,Won,Drawn,Lost) " +
                "VALUES(:nam,:cont,:play,:win,:draw,:loss)";
        namedParameters.addValue("nam" , name);
        namedParameters.addValue("cont" , continent);
        namedParameters.addValue("play" , gamesPlayed);
        namedParameters.addValue("win" , wins);
        namedParameters.addValue("draw" , draws);
        namedParameters.addValue("loss" , losses);
        int rowsAffected = jdbc.update(query,namedParameters);
        if (rowsAffected > 0)
            System.out.println("Team record was added successfully!");

    }
}
