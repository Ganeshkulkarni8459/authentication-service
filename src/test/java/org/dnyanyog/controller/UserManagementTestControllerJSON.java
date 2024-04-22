package org.dnyanyog.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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
public class UserManagementTestControllerJSON {

  @Autowired private MockMvc mockMvc;

  @Test
  public void testAddUpdateUser() throws Exception {
    String requestBody =
        "{\n"
            + "  \"username\": \"samruddhi\",\n"
            + "  \"password\": \"spatil\",\n"
            + "  \"email\": \"test@example.com\",\n"
            + "  \"age\": 22\n"
            + "}";

    mockMvc
        .perform(
            post("/api/v1/auth/user").contentType(MediaType.APPLICATION_JSON).content(requestBody))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.status").value("Success"))
        .andExpect(
            jsonPath("$.message").value("User added successfully")); // Corrected expected message
  }

  @Test
  @Order(4)
  public void testGetSingleUser() throws Exception {
    long userId = 1; // Assuming this user ID exists in the system

    mockMvc
        .perform(
            MockMvcRequestBuilders.get("/api/v1/auth/user/{userId}", userId)
                .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.status").value("Success"))
        .andExpect(jsonPath("$.message").value("User found successfully"))
        .andExpect(jsonPath("$.username").value("priya"))
        .andReturn();
  }
}
