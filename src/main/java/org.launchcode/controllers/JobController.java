package org.launchcode.controllers;

import org.launchcode.models.data.OOJobData;
import org.launchcode.models.forms.JobForm;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

@Controller
@RequestMapping(value="job")
public class JobController {

    private OOJobData OOjobData = OOJobData.getInstance();

    // The detail display for a given Job at URLs like /job?id=28
    @RequestMapping(value = "", method=RequestMethod.GET)
    public String index(Model model, int id) {

        // TODO - get the Job with the given ID and pass it into the view

        return "job-detail";
    }

    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String add(Model model) {
        model.addAttribute(new JobForm());
        return "new-job";
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String add(Model model, @Valid JobForm jobForm, Errors errors) {
        /* TODO - Validate the JobForm model, and if valid, create a
        *  new Job and add it to the jobData data store. Then
        *  redirect tot he job detail view for the new Job.
         */

        return "";
    }

}
