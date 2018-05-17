package fr.afpa.library.controller.admin;

import fr.afpa.library.model.Subscriber;
import fr.afpa.library.service.SubscriberService;
import io.florianlopes.spring.test.web.servlet.request.MockMvcRequestBuilderUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import java.time.LocalDate;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = ManageSubsController.class)
public class ManageSubsControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private SubscriberService subscriberService;

    private Subscriber subscriber;
    private Subscriber subscriber2;

    @Before
    public void setUp() {
        subscriber = new Subscriber();
        subscriber.setFirstname("Paul");
        subscriber.setLastname("Dupont");
        subscriber.setDateOfBirth(LocalDate.of(1990,10,10));
        subscriber.setEmail("dupont.p@gmail.com");
        subscriber.setPassword("000000");
        subscriber.setEnabled(true);
        subscriber.setTotalDelays(0);

        subscriber2 = new Subscriber();
        subscriber2.setFirstname("Paul");
        subscriber2.setLastname("Dupont");
        subscriber2.setDateOfBirth(LocalDate.of(1990,10,10));
        subscriber2.setEmail("dupont.p@gmail.com");
        subscriber2.setPassword("0000");
        subscriber2.setEnabled(true);
        subscriber2.setTotalDelays(0);
    }

    @Test
    public void testDisplayAllSubs() throws Exception {
        mockMvc.perform(get("/manage/subscribers/all"))
                .andExpect(status().isOk())
                .andExpect((view().name("admin/allSubscribers")))
                .andExpect(model()
                        .attribute("subscribers", subscriberService.findAll()));
    }

    @Test
    public void testNewSubPostForm_shouldBeSuccessStatus302() throws Exception {
                mockMvc.perform(MockMvcRequestBuilderUtils
                        .postForm("/manage/subscriber/new", subscriber))
                .andExpect(status().is(302))
                .andExpect(model().hasNoErrors())
                .andExpect(MockMvcResultMatchers
                        .redirectedUrl("/manage/subscribers/all"))
                .andReturn();
    }

    @Test
    public void testNewSubPostForm_shouldBeErrorStatus200() throws Exception {
        mockMvc.perform(MockMvcRequestBuilderUtils.postForm("/manage/subscriber/new", subscriber2))
                .andExpect(status().is(200))
                .andExpect(model().hasErrors())
                .andExpect(model().errorCount(1))
                .andExpect(MockMvcResultMatchers.view().name("admin/newSubscriber"))
                .andReturn();
    }


    @Test
    public void testDisplayNewSubForm() throws Exception {
        mockMvc.perform(get("/manage/subscriber/new"))
                .andExpect(status().isOk())
                .andExpect((view().name("admin/newSubscriber")));
    }

    @Test
    public void testDeleteSub() {
    }
}