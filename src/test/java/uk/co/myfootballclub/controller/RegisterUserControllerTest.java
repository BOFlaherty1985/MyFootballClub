package uk.co.myfootballclub.controller;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import uk.co.myfootballclub.config.TestConfig;
import uk.co.myfootballclub.config.WebInitializer;
import uk.co.myfootballclub.persistence.dao.UserRepository;
import uk.co.myfootballclub.persistence.domain.User;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Register User Controller Test
 *
 * @author Benjamin O'Flaherty
 * @date Created on: 09/12/2014
 * @project MyFootballClub
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {TestConfig.class, WebInitializer.class})
@WebAppConfiguration
public class RegisterUserControllerTest {

    private MockMvc mockMvc;

    @Autowired
    @InjectMocks
    private RegisterUserController controller;

    @Mock
    private UserRepository repository;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/WEB-INF/views/");
        viewResolver.setSuffix(".jsp");

        mockMvc = MockMvcBuilders.standaloneSetup(controller)
                .setViewResolvers(viewResolver)
                .build();
    }

    @Test
    public void registerUser_validRequestMapping_statusIsFound302() throws Exception {

        mockMvc.perform(post("/registerUser"))
                .andExpect(status().isFound());

    }

    @Test
    public void registerUser_userPassedInAsParameter_notNull() throws Exception {

        mockMvc.perform(post("/registerUser"))
                .andExpect(status().isFound());

    }

    @Test
    public void registerUser_firstNameParameterNull_firstNameNotNullFieldError() throws Exception {

        mockMvc.perform(post("/registerUser")
                .param("lastName", generateRandomString(8, true, false)))
                .andDo(print())
                .andExpect(status().isFound())
                .andExpect(model().attributeHasFieldErrors("user", "firstName"));

    }

    @Test
    public void registerUser_firstNameParameterIsOfLength2_firstNameLengthIsBelowMinimumFieldError() throws Exception {

        mockMvc.perform(post("/registerUser")
                .param("firstName", generateRandomString(2, true, false)))
                .andDo(print())
                .andExpect(status().isFound())
                .andExpect(model().attributeHasFieldErrors("user", "firstName"));

    }

    @Test
    public void registerUser_firstNameParameterIsValid_statusIsFound302() throws Exception {

        mockMvc.perform(post("/registerUser")
                .param("firstName", generateRandomString(5, true, false)))
                .andDo(print())
                .andExpect(status().isFound());

    }

    @Test
    public void registerUser_lastNameParameterNull_lastNameNotNullFieldError() throws Exception {

        mockMvc.perform(post("/registerUser")
                .param("firstName", generateRandomString(7, true, false)))
                .andDo(print())
                .andExpect(model().attributeHasFieldErrors("user", "lastName"))
                .andExpect(status().isFound());

    }

    @Test
    public void registerUser_lastNameParameterIsOfLength2_lastNameLengthIsBelowMinimumFieldError() throws Exception {

        mockMvc.perform(post("/registerUser")
                .param("lastName", generateRandomString(1, true, false)))
                .andDo(print())
                .andExpect(model().attributeHasFieldErrors("user", "lastName"))
                .andExpect(status().isFound());

    }

    @Test
    public void registerUser_lastNameParameterIsValid_statusIsFound302() throws Exception {

        mockMvc.perform(post("/registerUser")
                .param("lastName", generateRandomString(12, true, false)))
                .andDo(print())
                .andExpect(status().isFound());

    }

    @Test
    public void registerUser_usernameParameterNull_usernameNotNullFieldError() throws Exception {

        mockMvc.perform(post("/registerUser")
                .param("firstName", generateRandomString(6, true, false))
                .param("lastName", generateRandomString(10, true, false)))
                .andDo(print())
                .andExpect(model().attributeHasFieldErrors("user", "username"))
                .andExpect(status().isFound());

    }

    @Test
    public void registerUser_usernameParameterHasLengthOf5_usernameLengthIsBelowMinimumFieldError() throws Exception {

        mockMvc.perform(post("/registerUser")
                .param("firstName", generateRandomString(6, true, false))
                .param("lastName", generateRandomString(10, true, false))
                .param("username", generateRandomString(5, true, true)))
                .andDo(print())
                .andExpect(model().attributeHasFieldErrors("user", "username"));

    }

    @Test
    public void registerUser_usernameParameterHasLengthOf22_usernameLengthIsAboveMaximumFieldError() throws Exception {

        mockMvc.perform(post("/registerUser")
                .param("firstName", generateRandomString(6, true, false))
                .param("lastName", generateRandomString(10, true, false))
                .param("username", generateRandomString(22, true, true)))
                .andDo(print())
                .andExpect(model().attributeHasFieldErrors("user", "username"));

    }

    @Test
    public void registerUser_userNameIsValid_statusIsFound302() throws Exception {

        mockMvc.perform(post("/registerUser")
                .param("firstName", generateRandomString(6, true, false))
                .param("lastName", generateRandomString(10, true, false))
                .param("username", generateRandomString(15, true, true)))
                .andDo(print())
                .andExpect(status().isFound());

    }

    @Test
    public void registerUser_emailParameterNull_emailParameterIsInvalidFieldError() throws Exception {

        mockMvc.perform(post("/registerUser")
                .param("email", generateRandomString(10, true, false))
                .param("firstName", generateRandomString(6, true, false))
                .param("lastName", generateRandomString(10, true, false))
                .param("username", generateRandomString(15, true, true)))
                .andDo(print())
                .andExpect(model().attributeHasFieldErrors("user", "email"))
                .andExpect(status().isFound());

    }

    @Test
    public void registerUser_emailParameterIsValid_statusIsFound302() throws Exception {

        mockMvc.perform(post("/registerUser")
                .param("firstName", generateRandomString(6, true, false))
                .param("lastName", generateRandomString(10, true, false))
                .param("username", generateRandomString(15, true, true))
                .param("email", "test@test.com"))
                .andDo(print())
                .andExpect(status().isFound());

    }

    @Test
    public void registerUser_saveUserEntity_saveUserEntityToUserRepository() throws Exception {

        mockMvc.perform(post("/registerUser")
                .param("firstName", generateRandomString(6, true, false))
                .param("lastName", generateRandomString(10, true, false))
                .param("username", generateRandomString(15, true, true))
                .param("email", "test@test.com"))
                .andDo(print())
                .andExpect(status().isFound());

        verify(repository, times(1)).save(Mockito.<User> any());

    }

    @Test
    public void registerUser_doNotSaveUserEntityIfInvalid_userRepositoryNotCalled() throws Exception {

        mockMvc.perform(post("/registerUser")
                .param("firstName", generateRandomString(2, true, false))
                .param("lastName", generateRandomString(10, true, false))
                .param("username", generateRandomString(15, true, true))
                .param("email", "test@test.com"))
                .andDo(print())
                .andExpect(model().attributeHasFieldErrors("user", "firstName"))
                .andExpect(status().isFound());

        verify(repository, times(0)).save(Mockito.<User> any());

    }

    @Test
    public void registerUser_usernameAlreadyExists_doNotSaveUserEntity() {


    }


    private String generateRandomString(int length, boolean letters, boolean numbers) {
        return RandomStringUtils.random(length, letters, numbers);
    }

}
