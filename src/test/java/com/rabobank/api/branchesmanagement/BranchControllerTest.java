package com.rabobank.api.branchesmanagement;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabobank.api.branchesmanagement.dto.OpenBranchRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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

    private OpenBranchRequest getOpenBranchRequest() {
        var code = "APLCNT";
        var name = "Rabobank Apeldoorn Central";
        var address = "Eendrachtstraat 133 7336 AC Apeldoorn";
        var phone = "0887226600";
        return new OpenBranchRequest(code, name, address, phone);
    }
}