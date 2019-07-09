package service;

import com.plivo.assignment.cache.service.impl.*;
import com.plivo.assignment.dto.*;
import com.plivo.assignment.entity.*;
import com.plivo.assignment.exception.*;
import com.plivo.assignment.repository.*;
import com.plivo.assignment.service.impl.*;

import org.junit.*;
import org.junit.runner.*;
import org.mockito.*;
import org.mockito.junit.*;
import org.springframework.security.core.*;
import org.springframework.security.core.context.*;

import static org.junit.Assert.fail;
import static org.mockito.Mockito.*;

public class ApplicationServiceImplTest {

    @Mock
    AccountRepository accountRepository;

    @Mock
    PhoneNumberRepository phoneNumberRepository;

    @Mock
    KeyCacheServiceImpl keyCacheService;

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @InjectMocks
    private static ApplicationServiceImpl applicationServiceImpl = new ApplicationServiceImpl();

    @BeforeClass
    public static void initialize() {
        Authentication authentication = mock(Authentication.class);
        SecurityContext securityContext = mock(SecurityContext.class);
        when(securityContext.getAuthentication()).thenReturn(authentication);
        SecurityContextHolder.setContext(securityContext);
        when(SecurityContextHolder.getContext().getAuthentication().getName()).thenReturn("user");
    }

    @Test
    public void inBoundToNotFound() {
        Account account = new Account();
        when(accountRepository.findByUserName(anyString())).thenReturn(account);

        try {
            ErrorResponse.AppResponse appResponse = applicationServiceImpl.inboundSms(new SmsDTO());
            Assert.assertEquals("to parameter not found", appResponse.getError());
        }
        catch (ApplicationException e) {
            fail("Exception should not occur");
        }
    }

    @Test
    public void inBoundStop() {

        SmsDTO smsDTO=new SmsDTO();
        smsDTO.setFrom("1234");
        smsDTO.setTo("5678");
        smsDTO.setText("STOP");

        Account account = new Account();
        when(accountRepository.findByUserName("user")).thenReturn(account);
        when(phoneNumberRepository.findNumberByLoggedInUser(account, smsDTO.getTo())).thenReturn(new String("5678"));

        try {
            ErrorResponse.AppResponse appResponse = applicationServiceImpl.inboundSms(smsDTO);
            Assert.assertEquals("inbound sms ok", appResponse.getMessage());
        }
        catch (ApplicationException e) {
            fail("Exception should not occur");
        }


    }

    @Test
    public void inBoundNotStop() {

        SmsDTO smsDTO=new SmsDTO();
        smsDTO.setFrom("1234");
        smsDTO.setTo("5678");
        smsDTO.setText("Hello");

        Account account = new Account();
        when(accountRepository.findByUserName("user")).thenReturn(account);
        when(phoneNumberRepository.findNumberByLoggedInUser(account, smsDTO.getTo())).thenReturn(new String("5678"));

        try {
            ErrorResponse.AppResponse appResponse = applicationServiceImpl.inboundSms(smsDTO);
            Assert.assertEquals("inbound sms ok", appResponse.getMessage());
        }
        catch (ApplicationException e) {
            fail("Exception should not occur");
        }
    }

    @Test
    public void outBoundFromNotFound() {
        Account account = new Account();
        when(accountRepository.findByUserName(anyString())).thenReturn(account);

        try {
            ErrorResponse.AppResponse appResponse = applicationServiceImpl.outboundSms(new SmsDTO());
            Assert.assertEquals("from parameter not found", appResponse.getError());
        }
        catch (ApplicationException e) {
            fail("Exception should not occur");
        }
    }

    @Test
    public void outBoundStopFound() {
        SmsDTO smsDTO=new SmsDTO();
        smsDTO.setFrom("1234");
        smsDTO.setTo("5678");
        smsDTO.setText("Hello");

        Account account = new Account();
        when(accountRepository.findByUserName("user")).thenReturn(account);
        when(phoneNumberRepository.findNumberByLoggedInUser(account, smsDTO.getFrom())).thenReturn("1234");
        when(keyCacheService.getValue(smsDTO.getFrom() + ":" + smsDTO.getTo())).thenReturn("STOP");

        try {
            ErrorResponse.AppResponse appResponse = applicationServiceImpl.outboundSms(smsDTO);
            Assert.assertEquals("sms from " + smsDTO.getFrom() + " to " + smsDTO.getTo() + " blocked by STOP request", appResponse.getError());
        }
        catch (ApplicationException e) {
            fail("Exception should not occur");
        }
    }

    @Test
    public void outBoundLimitReached() {
        SmsDTO smsDTO=new SmsDTO();
        smsDTO.setFrom("1234");
        smsDTO.setTo("5678");
        smsDTO.setText("Hello");

        Account account = new Account();
        when(accountRepository.findByUserName("user")).thenReturn(account);
        when(phoneNumberRepository.findNumberByLoggedInUser(account, smsDTO.getFrom())).thenReturn("1234");
        when(keyCacheService.getLimit(anyString(),anyInt(),anyInt())).thenReturn(false);

        try {
            ErrorResponse.AppResponse appResponse = applicationServiceImpl.outboundSms(smsDTO);
            Assert.assertEquals("limit reached for from " + smsDTO.getFrom(), appResponse.getError());
        }
        catch (ApplicationException e) {
            fail("Exception should not occur");
        }
    }

    @Test
    public void outBoundSuccess() {
        SmsDTO smsDTO=new SmsDTO();
        smsDTO.setFrom("1234");
        smsDTO.setTo("5678");
        smsDTO.setText("Hello");

        Account account = new Account();
        when(accountRepository.findByUserName("user")).thenReturn(account);
        when(phoneNumberRepository.findNumberByLoggedInUser(account, smsDTO.getFrom())).thenReturn("1234");
        when(keyCacheService.getLimit(anyString(),anyInt(),anyInt())).thenReturn(true);

        try {
            ErrorResponse.AppResponse appResponse = applicationServiceImpl.outboundSms(smsDTO);
            Assert.assertEquals("outbound sms ok", appResponse.getMessage());
        }
        catch (ApplicationException e) {
            fail("Exception should not occur");
        }
    }
}
