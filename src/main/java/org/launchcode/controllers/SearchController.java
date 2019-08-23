package org.launchcode.controllers;

import org.launchcode.models.JobData;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by LaunchCode
 */
@Controller
@RequestMapping("search")
public class SearchController {

    @RequestMapping(value = "")
    public String search(Model model) {
        model.addAttribute("columns", ListController.columnChoices);
        model.addAttribute("searchType", "all");
        return "search";
    }

    @RequestMapping(value = "results")
    public String results(@RequestParam() String searchType, @RequestParam() String searchTerm, Model model) {

        ArrayList<HashMap<String, String>> results;
        if (searchType.equals("all")) {
            results = JobData.findByValue(searchTerm);
        } else {
            results = JobData.findByColumnAndValue(searchType, searchTerm);

        }

        model.addAttribute("selectedColumn", searchType);
        model.addAttribute("columns", ListController.columnChoices);
        model.addAttribute("results", results);

        return "results";

        // TODO #1 - Create handler to process search request and display results
    }
}