
import com.sam.controller.LoginPage;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.DefaultMockMvcBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.MultiValueMap;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionContext;

import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = "classpath*:*.xml")
public class LoginControllerTest {


    @Autowired
    private LoginPage loginpage;
    MockMvc mock;

    @Before
    public void setUp() throws Exception {

        mock = MockMvcBuilders.standaloneSetup(loginpage).build();

    }

    @Test
    public void testLogin() {

        System.out.println("begin test");
        String Email="8888888@88.com";
        String Password="88888888";

        try {
            mock.perform(MockMvcRequestBuilders.get("/login").param("Email",Email).param("Password",Password))
                    .andExpect(status().is3xxRedirection());
            System.out.println("Test Success");

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Test Failed");
        }
    }

    @Test
    public void testIndex() {

        System.out.println("begin test");


        try {
            mock.perform(MockMvcRequestBuilders.post("/"))
                    .andExpect(status().isOk());
            System.out.println("Test Success");

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Test Failed");
        }
    }



}
