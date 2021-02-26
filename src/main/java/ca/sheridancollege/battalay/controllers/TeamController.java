package ca.sheridancollege.battalay.controllers;

import ca.sheridancollege.battalay.database.DatabaseAccess;
import ca.sheridancollege.battalay.model.Team;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class TeamController {

    @Autowired
    DatabaseAccess da;

    ModelAndView mv;

    @GetMapping("/")
    public String index(){
        return "/Home";
    }

    @GetMapping("/delete")
    public ModelAndView deletePage(){
        mv = new ModelAndView("DeleteTeam", "teams",da.getTeams());
        return mv;
    }

    @GetMapping("/add")
    public ModelAndView addPage(@ModelAttribute Team team){
        mv = new ModelAndView("AddTeam", "team",team);
        return mv;
    }

    @GetMapping("/edit")
    public ModelAndView editPage(){
        mv = new ModelAndView("EditTeam", "teams",da.getTeams());
        return mv;
    }

    @GetMapping("/results")
    public ModelAndView displayResultsPage(){
        mv = new ModelAndView("DisplayResults", "teams",da.getTeams());
        return mv;
    }

    @RequestMapping("/addTeam") //make it PostMapping
    public ModelAndView processTeam(@ModelAttribute Team team){

        da.insertTeam(team.getTeamName(),team.getContinent(),team.getPlayed(),
                    team.getWon(),team.getDrawn(),team.getLost());

        return new ModelAndView("AddTeam", "team",team);
    }

    @GetMapping("/deleteTeamById/{id}")
    public ModelAndView deleteTeam(@PathVariable int id){

        da.deleteTeamById(id);
        mv = new ModelAndView("redirect:/delete", "teams",da.getTeams());
        return mv;
    }

    @GetMapping("/editTeamById/{id}")
    public ModelAndView editTeam(@PathVariable int id){

        Team team;
        team = da.getTeamById(id).get(0);
        mv = new ModelAndView("EditTeamById", "teams",da.getTeams());
        mv.addObject("team",team);
        return mv;
    }

    @PostMapping("/applyChanges")
    public ModelAndView applyChanges(@ModelAttribute Team team){

        da.editTeamById(team);
        mv = new ModelAndView("redirect:/edit", "teams",da.getTeams());
        return mv;

    }

}
