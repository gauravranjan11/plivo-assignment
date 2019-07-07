package com.plivo.assignment.controller;

import com.plivo.assignment.dto.*;
import com.plivo.assignment.service.*;

import org.springframework.beans.factory.annotation.*;
import org.springframework.web.bind.annotation.*;

import javax.validation.*;


@RestController
public class WebController {

    @Autowired
    ApplicationService applicationService;

    @PostMapping(path = "/inbound/sms",
                 consumes = "application/json",
                 produces = "application/json")
    @ResponseBody
    public ErrorResponse.AppResponse inboundSms(@Valid @RequestBody SmsDTO smsDTO) throws Exception {
        return applicationService.inboundSms(smsDTO);
    }

    @PostMapping(path = "/outbound/sms",
                 consumes = "application/json",
                 produces = "application/json")
    @ResponseBody
    public ErrorResponse.AppResponse outboundSms(@Valid @RequestBody SmsDTO smsDTO) throws Exception {
        return applicationService.outboundSms(smsDTO);
    }


}
