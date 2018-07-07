package org.launchcode.controllers;

import org.launchcode.models.Job;
import org.launchcode.models.JobData;
import org.launchcode.models.JobFieldType;
import org.launchcode.models.data.OOJobData;
import org.launchcode.models.forms.SearchForm;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.HashMap;

@Controller
@RequestMapping("search")
public class SearchController {

    private OOJobData OOjobData = OOJobData.getInstance();

    @RequestMapping(value = "")
    public String search(Model model) {
        model.addAttribute("columns", ListController.columnChoices);
        model.addAttribute("checkedSearch", "all");
        model.addAttribute(new SearchForm());
        return "search";
    }

    // TODO #1 - Create handler to process search request and display results

    @RequestMapping(value="results")
    public String displayResults(Model model, @RequestParam String keyword,
                                 // @RequestParam String searchType,
                                 @ModelAttribute SearchForm searchForm) {
        ArrayList<HashMap<String, String>> results = new ArrayList<>();
        ArrayList<Job> OOjobs;

        if (searchForm.getSearchField().equals(JobFieldType.ALL)) {
            OOjobs = OOjobData.findByValue(searchForm.getKeyword());
        } else {
            OOjobs = OOjobData.findByColumnAndValue(searchForm.getSearchField(), searchForm.getKeyword());
        }
        model.addAttribute("OOjobs", OOjobs);


        //if (searchType.equals("all")) {
        if (searchForm.getSearchField().equals(JobFieldType.ALL)) {
            results = JobData.findByValue(keyword);
        }
        else {
            //results = JobData.findByColumnAndValue(searchType, keyword);
            results = JobData.findByColumnAndValue(searchForm.getSearchField().toString(), keyword);
        }
        model.addAttribute("jobs", results);
        model.addAttribute("columns", ListController.columnChoices);
        //model.addAttribute("checkedSearch", searchType);
        model.addAttribute("checkedSearch", searchForm.getSearchField().toString());
        return "search";
    }

}
