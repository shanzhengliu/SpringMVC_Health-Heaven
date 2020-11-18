import com.sam.controller.ExerciseTrackerPage;
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
public class ExerciseControllerTest {

    @Autowired
    private ExerciseTrackerPage exerciseTrackerPage;
    MockMvc mock;


    @Before
    public void setUp() throws Exception {

        mock = MockMvcBuilders.standaloneSetup(exerciseTrackerPage).build();

    }

    @Test
    public void testQueryCalorieByName() {

        System.out.println("Begin test");
        String sportName = "Football";

        try {
            mock.perform(MockMvcRequestBuilders.post("/ExerciseNameQuery").param("sportName",sportName))
                    .andExpect(status().is2xxSuccessful());
            System.out.println("Test Success");
        }
        catch (Exception e) {
            e.printStackTrace();
            System.out.println("Test Failed");
        }
    }

    @Test
    public void testQueryExerciseRecords() {

        System.out.println("Begin test");

        try {
            mock.perform(MockMvcRequestBuilders.post("/GetAllExerciseRecords"))
                    .andExpect(status().is2xxSuccessful());
            System.out.println("Test Success");
        }
        catch (Exception e) {
            e.printStackTrace();
            System.out.println("Test Failed");
        }
    }

}
