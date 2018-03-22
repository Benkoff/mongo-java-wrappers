package io.github.benkoff.springbootdatamongodb.controllers;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(ZipController.class)
public class ZipControllerUnitTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ZipController zipController;

    @Autowired
    protected WebApplicationContext webApplicationContext;

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void correctRequestShoudReturnOk() throws Exception {
        mockMvc.perform(get("/zips/"))
                .andExpect(status().isOk());
    }

    @Test
    public void incorrectRequestShouldReturnNotFound() throws Exception {
        mockMvc.perform(get("/zips"))
                .andExpect(status().isNotFound());
    }
}
