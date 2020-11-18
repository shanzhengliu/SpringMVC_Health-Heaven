import com.sam.controller.FoodTrackerPage;
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
public class FoodControllerTest {
    @Autowired
    private FoodTrackerPage foodTrackerPage;
    MockMvc mock;

    @Before
    public void setUp() throws Exception {

        mock = MockMvcBuilders.standaloneSetup(foodTrackerPage).build();

    }

    @Test
    public void testQueryCalorieByName() {

        System.out.println("Begin test");
        String foodName = "Avocado Dip";

        try {
            mock.perform(MockMvcRequestBuilders.post("/FoodNameQuery").param("foodname",foodName))
                    .andExpect(status().is2xxSuccessful());
            System.out.println("Test Success");
        }
        catch (Exception e) {
            e.printStackTrace();
            System.out.println("Test Failed");
        }
    }

    @Test
    public void testQueryFoodRecords() {

        System.out.println("Begin test");

        try {
            mock.perform(MockMvcRequestBuilders.post("/GetAllFoodRecords"))
                    .andExpect(status().is2xxSuccessful());
            System.out.println("Test Success");
        }
        catch (Exception e) {
            e.printStackTrace();
            System.out.println("Test Failed");
        }
    }
}
