import com.sam.controller.CommunityPage;
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
public class CommunityControllerTest {
    @Autowired
    private CommunityPage communityPage;
    MockMvc mock;

    @Before
    public void setUp() throws Exception {

        mock = MockMvcBuilders.standaloneSetup(communityPage).build();

    }
    @Test
    public void testUpdatePosts(){
        System.out.println("Begin test");


        try{
            mock.perform(MockMvcRequestBuilders.post("/updatePosts"))
                    .andExpect(status().is2xxSuccessful());
            System.out.println("Test Success");
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("Test Failed");
        }
    }

    @Test
    public void testPublishPosts() {

        System.out.println("Begin test");
        String title = "I like Football";
        String tag="Sports";
        String content="Football is my favorite sports!";

        try {
            mock.perform(MockMvcRequestBuilders.post("/publishPosts").param("Title",title).param("Tag",tag).param("Content",content))
                    .andExpect(status().is2xxSuccessful());
            System.out.println("Test Success");
        }
        catch (Exception e) {
            e.printStackTrace();
            System.out.println("Test Failed");
        }
    }
}
