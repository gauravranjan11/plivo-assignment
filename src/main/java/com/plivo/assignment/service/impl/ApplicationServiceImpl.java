package com.plivo.assignment.service.impl;

import com.plivo.assignment.cache.service.impl.*;
import com.plivo.assignment.dto.*;
import com.plivo.assignment.entity.*;
import com.plivo.assignment.exception.*;
import com.plivo.assignment.repository.*;
import com.plivo.assignment.service.*;

import org.springframework.beans.factory.annotation.*;
import org.springframework.security.core.*;
import org.springframework.security.core.context.*;
import org.springframework.stereotype.*;

@Service
public class ApplicationServiceImpl implements ApplicationService {


    @Autowired
    AccountRepository accountRepository;

    @Autowired
    PhoneNumberRepository phoneNumberRepository;

    @Autowired
    KeyCacheServiceImpl keyCacheService;

    private static final int stopTTL      = 4*60*60;
    private static final int rateLimitTTL = 24*60*60;
    private static final int rateLimit    = 50;

    @Override
    public ErrorResponse.AppResponse inboundSms(SmsDTO smsDTO) throws ApplicationException {
        ErrorResponse.AppResponse appResponse = new ErrorResponse.AppResponse();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        Account account = accountRepository.findByUserName(auth.getName());
        String number = phoneNumberRepository.findNumberByLoggedInUser(account, smsDTO.getTo());
        if (number == null) {
            appResponse.setMessage("");
            appResponse.setError("to parameter not found");
        }
        else {

            switch (smsDTO.getText()) {
                case "STOP":
                case "STOP\n":
                case "STOP\r":
                case "STOP\r\n": {
                    System.out.println("i am here");
                    keyCacheService.put(smsDTO.getFrom() + ":" + smsDTO.getTo(), "STOP", stopTTL);
                    break;
                }

            }
            appResponse.setMessage("inbound sms ok");
            appResponse.setError("");
        }


        return appResponse;

    }

    @Override
    public ErrorResponse.AppResponse outboundSms(SmsDTO smsDTO) throws ApplicationException {
        ErrorResponse.AppResponse appResponse = new ErrorResponse.AppResponse();

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        Account account = accountRepository.findByUserName(auth.getName());
        String number = phoneNumberRepository.findNumberByLoggedInUser(account, smsDTO.getFrom());
        if (number == null) {
            appResponse.setMessage("");
            appResponse.setError("from parameter not found");
        }
        else if (keyCacheService.getValue(smsDTO.getFrom() + ":" + smsDTO.getTo()) != null) {
            appResponse.setMessage("");
            appResponse.setError("sms from " + smsDTO.getFrom() + " to " + smsDTO.getTo() + " blocked by STOP request");
        }
        else if (!keyCacheService.getLimit(smsDTO.getFrom(), rateLimit, rateLimitTTL)) {
            appResponse.setMessage("");
            appResponse.setError("limit reached for from " + smsDTO.getFrom());
        }
        else {
            appResponse.setMessage("outbound sms ok");
            appResponse.setError("");
        }
        return appResponse;
    }

}
