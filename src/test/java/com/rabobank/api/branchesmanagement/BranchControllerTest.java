package com.rabobank.api.branchesmanagement;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.JsonPath;
import com.rabobank.api.branchesmanagement.dto.OpenBranchRequest;
import com.rabobank.api.branchesmanagement.dto.UpdateBranchRequest;
import com.rabobank.api.branchesmanagement.service.StoreService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class BranchControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void givenValidRequest_whenOpenBranch_thenReturnOpenBranchResponse() throws Exception {
        var request = getOpenBranchRequest();

        mockMvc.perform(post("/branches")
                   .contentType(APPLICATION_JSON)
                   .content(objectMapper.writeValueAsString(request)))
               .andExpect(status().isCreated())
               .andExpect(jsonPath("$.id").exists())
               .andExpect(jsonPath("$.code").value(request.code()))
               .andExpect(jsonPath("$.name").value(request.name()))
               .andExpect(jsonPath("$.address").value(request.address()))
               .andExpect(jsonPath("$.phone").value(request.phone()));
    }

    @Test
    void givenValidRequest_whenUpdateBranch_thenReturnUpdateBranchResponse() throws Exception {

        var request = getOpenBranchRequest();

        MvcResult openResult = mockMvc.perform(post("/branches")
                        .contentType(APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andReturn();

        // Extract the ID from the response
        String branchId = JsonPath.read(openResult.getResponse().getContentAsString(), "$.id");

        var updateBranchRequest = getUpdateBrunchRequest();

        mockMvc.perform(put("/branches/{id}", branchId)
                        .contentType(APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updateBranchRequest)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.code").value(updateBranchRequest.code()))
                .andExpect(jsonPath("$.name").value(updateBranchRequest.name()))
                .andExpect(jsonPath("$.address").value(updateBranchRequest.address()))
                .andExpect(jsonPath("$.phone").value(updateBranchRequest.phone()));
    }

    private OpenBranchRequest getOpenBranchRequest() {
        var code = "APLCNT";
        var name = "Rabobank Apeldoorn Central";
        var address = "Eendrachtstraat 133 7336 AC Apeldoorn";
        var phone = "0887226600";
        return new OpenBranchRequest(code, name, address, phone);
    }

    private UpdateBranchRequest getUpdateBrunchRequest() {
        var code = "APLCNT";
        var name = "Updated Rabobank Branch";
        var address = "123 Utrecht Street";
        var phone = "0887226600";
        return new UpdateBranchRequest(code, name, address, phone);
    }
}