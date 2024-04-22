package org.dnyanyog.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.dnyanyog.dto.LoginRequest;
import org.dnyanyog.dto.LoginResponse;
import org.dnyanyog.service.LoginServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.annotation.Order;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@SpringBootTest
@AutoConfigureMockMvc
public class LoginTestControllerXML {

  @Autowired private MockMvc mockMvc;

  @Mock private LoginServiceImpl loginService;

  @InjectMocks private LoginController loginController;

  @BeforeEach
  public void setup() {
    MockitoAnnotations.initMocks(this);
    mockMvc = MockMvcBuilders.standaloneSetup(loginController).build();
  }

  @Test
  public void loginJSONTestCase_Success() throws Exception {

    LoginRequest loginRequest = new LoginRequest();
    loginRequest.setUsername("samruddhi");
    loginRequest.setPassword("spatil");

    LoginResponse loginResponse = new LoginResponse();
    loginResponse.setStatus("Success");
    loginResponse.setMessage("Login successful");

    when(loginService.validateUser(any(LoginRequest.class))).thenReturn(loginResponse);

    mockMvc
        .perform(
            post("/auth/validate")
                .contentType(MediaType.APPLICATION_XML)
                .content(
                    "<loginRequest>\n"
                        + " <username>samruddhi</username>\n"
                        + " <password>spatil</password>\n"
                        + " </loginRequest>"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.status").value("Success"))
        .andExpect(jsonPath("$.message").value("Login successful"));
  }

  @Test
  @Order(4)
  public void loginJSONTest_Fail() throws Exception {

    LoginResponse loginResponse = new LoginResponse();
    loginResponse.setStatus("Fail");
    loginResponse.setMessage("Login Failed");

    when(loginService.validateUser(any(LoginRequest.class))).thenReturn(loginResponse);
    mockMvc
        .perform(
            post("/auth/validate")
                .contentType(MediaType.APPLICATION_XML) // Set content type to XML
                .content(
                    "<loginRequest>\n"
                        + "    <username>nonExisting</username>\n"
                        + "    <password>nonExisting</password>\n"
                        + "</loginRequest>"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.status").value("Fail"))
        .andExpect(jsonPath("$.message").value("Login Failed"));
  }
}
