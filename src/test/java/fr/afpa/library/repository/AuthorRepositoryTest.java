package fr.afpa.library.repository;

import fr.afpa.library.model.Author;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.collection.IsIterableContainingInAnyOrder.containsInAnyOrder;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@DataJpaTest
public class AuthorRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;
    @Autowired
    private AuthorRepository authorRepository;
    private Author author;
    private Author author2;

    @Before
    public void setUp() {
        author = new Author();
        author.setLastname("Hugo");
        author.setFirstname("Victor");
        author.setDateOfBirth(LocalDate.of(1905,10,10));
        entityManager.persist(author);

        author2 = new Author();
        author2.setLastname("Dupont");
        author2.setFirstname("Jean");
        author2.setDateOfBirth(LocalDate.of(1945,2,20));
        entityManager.persist(author2);

        entityManager.flush();
    }

    @Test
    public void testDeleteOne(){
        AuthorRepository mock = Mockito.mock(AuthorRepository.class);
        when(mock.findOne(author.getId())).thenReturn(author);
        mock.delete(author.getId());
        verify(mock, times(1)).delete(author.getId());
    }

    @Test
    public void testFindAll(){
        Iterable<Author> authors = authorRepository.findAll();
        assertThat(authors, containsInAnyOrder(author, author2));
        assertThat(authors, containsInAnyOrder(
                hasProperty("firstname", is("Victor")),
                hasProperty("firstname", is("Jean"))
                )
        );
    }

    @Test
    public void testFindByLastnameAndFirstname(){
        Author found = authorRepository.findByFirstnameAndLastname("Victor", "Hugo");
        assertEquals(found.getFirstname(), author.getFirstname());
    }



    @Test
    public void testSaveOne(){
        Author a = new Author();
        a.setLastname("Arouet");
        a.setFirstname("François-Marie");
        a.setPseudonym("Voltaire");
        a.setDateOfBirth(LocalDate.of(1694,11,21));
        entityManager.persist(a);

        assertThat(authorRepository.findOne(a.getId())).hasFieldOrPropertyWithValue("pseudonym", "Voltaire");
    }



    @Test
    public void testFindOne(){
        Author foundAuthor = authorRepository.findOne(author.getId());
        assertEquals(foundAuthor, author);
    }




}