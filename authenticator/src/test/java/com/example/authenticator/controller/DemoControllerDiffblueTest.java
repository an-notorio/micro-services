package com.example.authenticator.controller;

import static org.mockito.Mockito.when;

import com.example.authenticator.dto.UserDto;
import com.example.authenticator.service.AuthenticationService;
import jakarta.servlet.http.HttpServletRequest;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {DemoController.class})
@ExtendWith(SpringExtension.class)
class DemoControllerDiffblueTest {
    @MockBean
    private AuthenticationService authenticationService;

    @Autowired
    private DemoController demoController;

    /**
     * Method under test:
     * {@link DemoController#getUser(Integer, HttpServletRequest)}
     */
    @Test
    void testGetUser() throws Exception {
        // Arrange
        UserDto.UserDtoBuilder lastNameResult = UserDto.builder()
                .email("jane.doe@example.org")
                .firstName("Jane")
                .lastName("Doe");
        UserDto buildResult = lastNameResult.role(new ArrayList<>()).build();
        when(authenticationService.getUser(Mockito.<Integer>any())).thenReturn(buildResult);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/demo/user/{userId}", 1);

        // Act and Assert
        MockMvcBuilders.standaloneSetup(demoController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string("{\"firstName\":\"Jane\",\"lastName\":\"Doe\",\"email\":\"jane.doe@example.org\",\"role\":[]}"));
    }

    /**
     * Method under test: {@link DemoController#sayHello()}
     */
    @Test
    void testSayHello() throws Exception {
        // Arrange
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/demo/trial");

        // Act and Assert
        MockMvcBuilders.standaloneSetup(demoController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("Hello from secured endpoint"));
    }

    /**
     * Method under test: {@link DemoController#sayHello()}
     */
    @Test
    void testSayHello2() throws Exception {
        // Arrange
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/demo/trial");
        requestBuilder.contentType("https://example.org/example");

        // Act and Assert
        MockMvcBuilders.standaloneSetup(demoController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("Hello from secured endpoint"));
    }
}
