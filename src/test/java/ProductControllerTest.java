import com.sam.controller.LoginPage;
import com.sam.controller.ProductPage;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = "classpath*:*.xml")
public class ProductControllerTest {

    @Autowired
    private ProductPage productPage;
    MockMvc mock;


    @Before
    public void setUp() throws Exception {
        mock = MockMvcBuilders.standaloneSetup(productPage).build();
    }

    @Test
    public void testQueryAll(){
        System.out.println("Begin test");
        String firstLevelFilter = "Swisse";
        String secondLevelFilter = "Liver";
        try {
            mock.perform(MockMvcRequestBuilders.post("/product/searchDatabase")
                    .param("firstLevelFilter",firstLevelFilter)
                    .param("secondLevelFilter",secondLevelFilter))
                    .andExpect(status().is2xxSuccessful());
            System.out.println("Test Success");

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Test Failed");
        }
    }
}
