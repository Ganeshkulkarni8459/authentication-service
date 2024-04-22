package org.dnyanyog.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.annotation.Order;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@SpringBootTest
@AutoConfigureMockMvc
public class UserManagementTestControllerXML {

  @Autowired private MockMvc mockMvc;

  @Test
  public void testAddUpdateUser() throws Exception {
    String requestBody =
        "<UserRequest>\n"
            + "  <username>priya</username>\n"
            + "  <password>pGaikwad</password>\n"
            + "  <email>test@example.com</email>\n"
            + "  <age>22</age>\n"
            + "</UserRequest>";

    mockMvc
        .perform(
            MockMvcRequestBuilders.post("/api/v1/auth/user")
                .contentType(MediaType.APPLICATION_XML_VALUE)
                .accept(MediaType.APPLICATION_XML_VALUE)
                .content(requestBody))
        .andExpect(status().isOk())
        .andExpect(xpath("/UserResponse/status").string("Success"))
        .andExpect(xpath("/UserResponse/message").string("User added successfully"));
  }

  @Test
  @Order(4)
  public void testGetSingleUser() throws Exception {
    long userId = 1; // Assuming this user ID exists in the system

    String responseContent =
        mockMvc
            .perform(
                MockMvcRequestBuilders.get("/api/v1/auth/user/{userId}", userId)
                    .contentType(MediaType.APPLICATION_XML_VALUE)
                    .accept(MediaType.APPLICATION_XML_VALUE))
            .andExpect(status().isOk())
            .andReturn()
            .getResponse()
            .getContentAsString();

    System.out.println("XML Response: " + responseContent);

    mockMvc
        .perform(
            MockMvcRequestBuilders.get("/api/v1/auth/user/{userId}", userId)
                .contentType(MediaType.APPLICATION_XML_VALUE)
                .accept(MediaType.APPLICATION_XML_VALUE))
        .andExpect(status().isOk())
        .andExpect(xpath("/UserResponse/status").string("Success"))
        .andExpect(xpath("/UserResponse/message").string("User found successfully"))
        .andExpect(xpath("/UserResponse/username").string("priya"))
        .andReturn();
  }
}
