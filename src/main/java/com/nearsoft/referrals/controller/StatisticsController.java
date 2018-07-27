package com.nearsoft.referrals.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StatisticsController {

    @RequestMapping(value = "mostreferred")
    public String mostReferred() {
        return "";
    }
}
