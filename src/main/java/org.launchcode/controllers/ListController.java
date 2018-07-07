package org.launchcode.controllers;

import org.launchcode.models.Job;
import org.launchcode.models.JobData;
import org.launchcode.models.JobField;
import org.launchcode.models.JobFieldType;
import org.launchcode.models.data.OOJobData;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.HashMap;

@Controller
@RequestMapping(value="list")
public class ListController {

    private OOJobData OOjobData = OOJobData.getInstance();

    static HashMap<String, String> columnChoices = new HashMap<>();

    public ListController () {
        columnChoices.put("core competency", "Skill");
        columnChoices.put("employer", "Employer");
        columnChoices.put("location", "Location");
        columnChoices.put("position type", "Position Type");
        columnChoices.put("all", "All");
    }

    @RequestMapping(value = "")
    public String list(Model model) {
        JobFieldType[] fields = JobFieldType.values();
        model.addAttribute("fields", fields);
        model.addAttribute("columns", columnChoices);
        return "list";
    }

    @RequestMapping(value = "values")
    public String listColumnValues(Model model,
                                   // @RequestParam String column,
                                   @RequestParam JobFieldType OOcolumn) {

        if(OOcolumn.equals(JobFieldType.ALL)) {
            return "redirect:/list/all";
        }

        ArrayList<? extends JobField> items;

        switch(OOcolumn) {
            case EMPLOYER:
                items = OOjobData.getEmployers().findAll();
                break;

            case LOCATION:
                items = OOjobData.getLocations().findAll();
                break;

            case CORE_COMPETENCY:
                items = OOjobData.getCoreCompetencies().findAll();
                break;

            case POSITION_TYPE:
            default:
                items = OOjobData.getPositionTypes().findAll();
        }

        model.addAttribute("title", "All " + OOcolumn.getName() + " Values");
        model.addAttribute("OOcolumn", OOcolumn);
        model.addAttribute("items", items);

        /* if(column.equals("all")) {
            ArrayList<HashMap<String, String>> jobs = JobData.findAll();
            model.addAttribute("title", "All Jobs");
            model.addAttribute("jobs", jobs);
            return "list-jobs";
        } else { */
            //ArrayList<String> items = JobData.findAll(column);
            //model.addAttribute("title", "All " + columnChoices.get(column) + " Values");
            //model.addAttribute("column", column);
            //model.addAttribute("items", items);
            return "list-column";
        //}

        //return "list-column";
    }

    @RequestMapping(value = "jobs")
    public String listByColumnAndValue(Model model,
                                       //@RequestParam String column,
                                       @RequestParam JobFieldType OOcolumn, @RequestParam String value, @RequestParam String name) {

        ArrayList<Job> OOjobs = OOjobData.findByColumnAndValue(OOcolumn, name);
        model.addAttribute("title", "Jobs with " + OOcolumn.getName() + ": " + name);
        model.addAttribute("OOjobs", OOjobs);


        //ArrayList<HashMap<String, String>> jobs = JobData.findByColumnAndValue(column, value);
        //model.addAttribute("title", "Jobs with " + columnChoices.get(column) + ": " + value);
        //model.addAttribute("jobs", jobs);
        return "list-jobs";
    }

    @RequestMapping(value = "all")
    public String listAllJobs(Model model) {

        ArrayList<Job> OOjobs = OOjobData.findAll();
        model.addAttribute("title", "All Jobs");
        model.addAttribute("OOjobs", OOjobs); // TODO - determine whether this should be OOjobs ...
                                            // ... and figure out effect of changed model parameter names on HTML files;

        return "list-jobs";
    }
}
