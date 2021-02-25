package ca.sheridancollege.battalay.controllers;

import ca.sheridancollege.battalay.database.DatabaseAccess;
import ca.sheridancollege.battalay.model.Team;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@Controller
public class TeamController {

    @Autowired
    DatabaseAccess da;

    ModelAndView mv;

    @GetMapping("/")
    public String index(){

//        mv = new ModelAndView("Home", "teams",da.getTeams());
//        mv.addObject("team", new Team());

        return "/Home";
    }

    @RequestMapping("/addTeam") //make it PostMapping
    public ModelAndView processTeam(HttpSession session, @ModelAttribute Team team){

        if (team.getName() == null){
            System.out.println("null");
        }else {
            da.insertTeam(team.getName(),team.getContinent(),team.getGamesPlayed(),
                    team.getWins(),team.getDraws(),team.getLosses());
//        session.setAttribute("team",team);
        }

        return new ModelAndView("AddTeam", "team",team);
    }

}
