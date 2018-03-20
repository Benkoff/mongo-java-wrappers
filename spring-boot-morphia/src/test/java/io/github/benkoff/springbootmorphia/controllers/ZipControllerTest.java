package io.github.benkoff.springbootmorphia.controllers;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.nio.charset.Charset;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by Ben Novikov on 2018 March 20
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ZipController.class)
@WebAppConfiguration
public class ZipControllerTest {
    private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(),
            Charset.forName("utf8"));

    private MockMvc mockMvc;

    @Autowired
    protected WebApplicationContext webApplicationContext;

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void findAllShoudReturnOk() throws Exception {
        mockMvc.perform(get("http://localhost:8083/zips/"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(contentType));
    }

    @Test
    public void urlNotFound() throws Exception {
        mockMvc.perform(get("http://localhost:8083/zips")
                .contentType(contentType))
                .andExpect(status().isNotFound());
    }

    @Test
    public void zipNotFound() throws Exception {
        mockMvc.perform(get("http://localhost:8083/zips/abrakadabra")
                .contentType(contentType))
                .andExpect(status().isNotFound());
    }

}
