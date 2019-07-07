package com.plivo.assignment.service;

import com.plivo.assignment.dto.*;
import com.plivo.assignment.exception.*;

public interface ApplicationService {
	ErrorResponse.AppResponse inboundSms(SmsDTO smsDTO) throws ApplicationException;
	ErrorResponse.AppResponse outboundSms(SmsDTO smsDTO) throws ApplicationException;
}
