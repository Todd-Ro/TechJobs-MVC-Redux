package org.launchcode.controllers;

import org.launchcode.models.JobData;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.HashMap;

@Controller
@RequestMapping("search")
public class SearchController {

    @RequestMapping(value = "")
    public String search(Model model) {
        model.addAttribute("columns", ListController.columnChoices);
        model.addAttribute("checkedSearch", "all");
        return "search";
    }

    // TODO #1 - Create handler to process search request and display results

    @RequestMapping(value="results")
    public String displayResults(Model model, @RequestParam String searchTerm, @RequestParam String searchType) {
        ArrayList<HashMap<String, String>> results = new ArrayList<>();
        if (searchType.equals("all")) {
            results = JobData.findByValue(searchTerm);
        }
        else {
            results = JobData.findByColumnAndValue(searchType, searchTerm);
        }
        model.addAttribute("jobs", results);
        model.addAttribute("columns", ListController.columnChoices);
        model.addAttribute("checkedSearch", searchType);
        return "search";
    }

}
