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

    public void  deleteTeamById(int id){

        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
        String query = "DELETE FROM Teams WHERE TeamID = :id";
        namedParameters.addValue("id" , id);
        int rowsAffected = jdbc.update(query,namedParameters);
        if (rowsAffected > 0)
            System.out.println("Team record was deleted successfully!");

    }

    public void  editTeamById(Team team){

        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
        String query = "UPDATE Teams SET TeamName =:nam,Continent=:cont,Played =:play,Won =:win,Drawn =:draw,Lost =:loss" +
                " WHERE TeamID = :id";
        namedParameters.addValue("id" , team.getTeamID());
        namedParameters.addValue("nam" , team.getTeamName());
        namedParameters.addValue("cont" , team.getContinent());
        namedParameters.addValue("play" , team.getPlayed());
        namedParameters.addValue("win" , team.getWon());
        namedParameters.addValue("draw" , team.getDrawn());
        namedParameters.addValue("loss" , team.getLost());
        int rowsAffected = jdbc.update(query,namedParameters);
        if (rowsAffected > 0)
            System.out.println("Team record was updated successfully!");

    }

    public List<Team> getTeamById(int id){

        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
        String query = "SELECT * FROM Teams WHERE TeamID = :id";
        namedParameters.addValue("id" , id);
        return jdbc.query(query,namedParameters,new BeanPropertyRowMapper<Team>(Team.class));

    }

    public List<Team> searchByString(String keyword){

        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
        String query = "SELECT * FROM Teams WHERE TeamName LIKE :keyword OR Continent LIKE :keyword";
        namedParameters.addValue("keyword","%"+keyword+"%");
        return jdbc.query(query,namedParameters,new BeanPropertyRowMapper<Team>(Team.class));

    }

    public List<Team> orderTeamsByName(){

        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
        String query = "SELECT * FROM Teams ORDER BY TeamName ASC";
        return jdbc.query(query,namedParameters,new BeanPropertyRowMapper<Team>(Team.class));
    }

    public List<Team> orderTeamsByContinent(){

        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
        String query = "SELECT * FROM Teams ORDER BY Continent ASC";
        return jdbc.query(query,namedParameters,new BeanPropertyRowMapper<Team>(Team.class));
    }

    public List<Team> orderTeamsByPoints(){

        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
        String query = "SELECT * FROM Teams ORDER BY (Won*3 + Drawn) DESC";
        return jdbc.query(query,namedParameters,new BeanPropertyRowMapper<Team>(Team.class));
    }
}
