package io.github.benkoff.springbootmorphia.controllers;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.nio.charset.Charset;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by Ben Novikov on 2018 March 20
 */
@RunWith(SpringRunner.class)
@WebMvcTest(MorphiaZipController.class)
public class MorphiaZipControllerTest {
    private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(),
            Charset.forName("utf8"));

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MorphiaZipController zipController;

    @Autowired
    protected WebApplicationContext webApplicationContext;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
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
