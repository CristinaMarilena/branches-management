package com.rabobank.api.branchesmanagement;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabobank.api.branchesmanagement.dto.OpenBranchRequest;
import com.rabobank.api.branchesmanagement.dto.UpdateBranchRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import java.net.URL;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class BranchControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void givenValidRequest_whenOpenBranch_thenReturnResponseStatusAsCreated() throws Exception {
        var request = getOpenBranchRequest();

        mockMvc.perform(post("/branches")
                   .contentType(APPLICATION_JSON)
                   .content(objectMapper.writeValueAsString(request)))
               .andExpect(status().isCreated());
    }

    @Test
    void givenValidRequest_whenUpdateBranch_thenReturnResponseStatusAsNoContent() throws Exception {
        var openBranchRequest = getOpenBranchRequest();
        var result = mockMvc.perform(post("/branches")
                                          .contentType(APPLICATION_JSON)
                                          .content(objectMapper.writeValueAsString(openBranchRequest)))
                                      .andExpect(status().isCreated())
                                      .andReturn();

        var resourceLocation = result.getResponse().getHeader("Location");
        var resourceURL = new URL(resourceLocation);
        var updateBranchRequest = getUpdateBrunchRequest();

        mockMvc.perform(put(resourceURL.getPath())
                   .contentType(APPLICATION_JSON)
                   .content(objectMapper.writeValueAsString(updateBranchRequest)))
               .andExpect(status().isNoContent());
    }

    private OpenBranchRequest getOpenBranchRequest() {
        var code = "APLCNT";
        var name = "Rabobank Apeldoorn Central";
        var address = "Eendrachtstraat 133 7336 AC Apeldoorn";
        var phone = "0887226600";
        return new OpenBranchRequest(code, name, address, phone);
    }

    private UpdateBranchRequest getUpdateBrunchRequest() {
        var code = "DVNCNT";
        var name = "Deventer Rabobank Central - Updated";
        var address = "Eendrachtstraat 133 7336 AC Deventer - Updated";
        var phone = "0887226628";
        return new UpdateBranchRequest(code, name, address, phone);
    }
}