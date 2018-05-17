package fr.afpa.library.service;

import fr.afpa.library.model.Author;
import fr.afpa.library.repository.AuthorRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
public class AuthorServiceImplTest {

    @TestConfiguration
    static class AuthorServiceImplTestContextConfiguration{
        @Bean
        public AuthorService authorService(){
            return new AuthorServiceImpl();
        }
    }

    @Autowired
    private AuthorService authorService;
    @MockBean
    private AuthorRepository authorRepository;
    private Author author;

    @Before
    public void setUp() {
        author = new Author();
        author.setLastname("Hugo");
        author.setFirstname("Victor");
        author.setDateOfBirth(LocalDate.of(1905,10,10));
        Mockito.when(authorRepository.findByFirstnameAndLastname("Victor", "Hugo")).thenReturn(author);
    }

    @Test
    public void testSaveOne() {
        String firstname = "Victor";
        authorService.save(author);
        assertEquals(firstname, author.getFirstname());
        assertNotNull(author);
    }

    @Test
    public void testFindByFirstnameAndLastname() {
        String firstname = "Victor";
        String lastname = "Hugo";
        Author found = authorService.findByFirstnameAndLastname(firstname, lastname);
        assertEquals(firstname, found.getFirstname());
    }

    @Test
    public void testDeleteOne() {
    }

    @Test
    public void testFindOne() {
    }

    @Test
    public void testFindAll() {
        List<Author> authors = authorService.findAll();
        Mockito.when(authorService.findAll()).thenReturn(authors);
    }



}