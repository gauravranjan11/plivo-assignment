package Controller;

import com.fasterxml.jackson.databind.*;
import com.plivo.assignment.controller.*;
import com.plivo.assignment.dto.*;
import com.plivo.assignment.service.impl.*;

import org.junit.*;
import org.mockito.*;
import org.mockito.junit.*;
import org.springframework.http.*;
import org.springframework.test.web.servlet.*;
import org.springframework.test.web.servlet.request.*;
import org.springframework.test.web.servlet.setup.*;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class WebControllerTest {

    @Mock
    ApplicationServiceImpl applicationService;

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @InjectMocks
    private static WebController webController = new WebController();

    private MockMvc mockMvc;

    @Before
    public void setup() {

        MockitoAnnotations.initMocks(this);

        this.mockMvc = MockMvcBuilders.standaloneSetup(webController).build();

    }

    @Test
    public void inBoundSMS() {
        SmsDTO smsDTO = new SmsDTO();
        smsDTO.setTo("12347777");
        smsDTO.setFrom("567888888");
        smsDTO.setText("Hello");

        try {
            mockMvc.perform(MockMvcRequestBuilders
                                    .post("/inbound/sms")
                                    .content(asJsonString(smsDTO))
                                    .contentType(MediaType.APPLICATION_JSON)
                                    .accept(MediaType.APPLICATION_JSON))
                   .andExpect(status().is(200));
        }
        catch (Exception e) {
            fail("Exception should not occur");
        }
    }

    @Test
    public void outBoundSMS() {
        SmsDTO smsDTO = new SmsDTO();
        smsDTO.setTo("12347777");
        smsDTO.setFrom("567888888");
        smsDTO.setText("Hello");

        try {
            mockMvc.perform(MockMvcRequestBuilders
                                    .post("/outbound/sms")
                                    .content(asJsonString(smsDTO))
                                    .contentType(MediaType.APPLICATION_JSON)
                                    .accept(MediaType.APPLICATION_JSON))
                   .andExpect(status().is(200));
        }
        catch (Exception e) {
            fail("Exception should not occur");
        }
    }

    @Test
    public void inValidToParam() {
        SmsDTO smsDTO = new SmsDTO();
        smsDTO.setTo("12347777ff");
        smsDTO.setFrom("567888888");
        smsDTO.setText("Hello");

        try {
            mockMvc.perform(MockMvcRequestBuilders
                                    .post("/inbound/sms")
                                    .content(asJsonString(smsDTO))
                                    .contentType(MediaType.APPLICATION_JSON)
                                    .accept(MediaType.APPLICATION_JSON))
                   .andExpect(status().is(400));
        }
        catch (Exception e) {
            fail("Exception should not occur");
        }
    }

    @Test
    public void inValidFromParam() {
        SmsDTO smsDTO = new SmsDTO();
        smsDTO.setTo("12347777ff");
        smsDTO.setFrom("5678xx88888");
        smsDTO.setText("Hello");

        try {
            mockMvc.perform(MockMvcRequestBuilders
                                    .post("/inbound/sms")
                                    .content(asJsonString(smsDTO))
                                    .contentType(MediaType.APPLICATION_JSON)
                                    .accept(MediaType.APPLICATION_JSON))
                   .andExpect(status().is(400));
        }
        catch (Exception e) {
            fail("Exception should not occur");
        }
    }

    @Test
    public void inValidTextParam() {
        SmsDTO smsDTO = new SmsDTO();
        smsDTO.setTo("12347777ff");
        smsDTO.setFrom("5678xx88888");

        try {
            mockMvc.perform(MockMvcRequestBuilders
                                    .post("/inbound/sms")
                                    .content(asJsonString(smsDTO))
                                    .contentType(MediaType.APPLICATION_JSON)
                                    .accept(MediaType.APPLICATION_JSON))
                   .andExpect(status().is(400));
        }
        catch (Exception e) {
            fail("Exception should not occur");
        }
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
