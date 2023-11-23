package com.rabobank.api.branchesmanagement.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabobank.api.branchesmanagement.dto.GetBranchResponse;
import com.rabobank.api.branchesmanagement.dto.OpenBranchRequest;
import com.rabobank.api.branchesmanagement.dto.OpenBranchResponse;
import com.rabobank.api.branchesmanagement.service.BranchService;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.mockito.Mockito.when;

@WebMvcTest(BranchesController.class)
class BranchesControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private BranchService branchService;

    @Test
    void testGetBranchSuccess() throws Exception {
        // Arrange
        Long branchId = 1L;
        GetBranchResponse mockResponse = createSampleGetBranchResponse(1L);

        when(branchService.getBranchById(branchId))
                .thenReturn(Optional.of(mockResponse));

        // Act & Assert
        mockMvc.perform(MockMvcRequestBuilders.get("/branches/{branchId}", branchId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(objectMapper.writeValueAsString(mockResponse)));
    }

    @Test
    void testGetBranchNotFound() throws Exception {
        // Arrange
        Long branchId = 1L;

        when(branchService.getBranchById(branchId))
                .thenReturn(Optional.empty());

        // Act & Assert
        mockMvc.perform(MockMvcRequestBuilders.get("/branches/{branchId}", branchId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    void testOpenBranchSuccess() throws Exception {
        // Arrange
        OpenBranchRequest request = new OpenBranchRequest(); // Set your request data
        OpenBranchResponse mockResponse = new OpenBranchResponse(); // Set your mock response data

        when(branchService.createBranch(ArgumentMatchers.any(OpenBranchRequest.class)))
                .thenReturn(mockResponse);

        // Act & Assert
        mockMvc.perform(MockMvcRequestBuilders.post("/branches")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.header().exists("Location"));
    }

    private GetBranchResponse createSampleGetBranchResponse(Long id) {
        GetBranchResponse response = new GetBranchResponse();
        response.setId(id);
        response.setCode("NL-UTR-CRS");
        response.setName("Utrecht Croeselaan");
        response.setAddress("Croeselaan 18, 3521 CB Utrecht");
        response.setPhone("088 722 6600");
        response.setCreatedAt(LocalDateTime.now());
        response.setUpdatedAt(LocalDateTime.now());
        return response;
    }
}
