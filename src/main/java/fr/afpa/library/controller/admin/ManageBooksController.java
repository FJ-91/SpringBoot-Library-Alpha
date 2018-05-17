package fr.afpa.library.controller.admin;

import fr.afpa.library.model.Author;
import fr.afpa.library.model.Book;
import fr.afpa.library.model.Copy;
import fr.afpa.library.model.Genre;
import fr.afpa.library.service.AuthorService;
import fr.afpa.library.service.BookService;
import fr.afpa.library.service.CopyService;
import fr.afpa.library.service.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author Fahd Jaouad
 */
@Controller
@PreAuthorize("hasRole('ADMIN')")
@RequestMapping("/manage")
public class ManageBooksController {

    @Autowired
    private GenreService genreService;
    @Autowired
    private AuthorService authorService;
    @Autowired
    private BookService bookService;
    @Autowired
    private CopyService copyService;
    /**
     * Creates and persists a new book along with its copies
     * @param params Map containing the form values
     * @param authorIds Array containing the author ids from the multiple select
     * @return String that redirects to the books list once the book has been added
     */
    @PostMapping("/book/new")
    public String createNewBook(@RequestParam Map<String, String> params,
                                @RequestParam("authors") Long[] authorIds) throws DataAccessException {
        //New book instance
        Book book = new Book();
        //Filling the book attributes retrieved from the form using classic setters
        book.setTitle(params.get("title"));
        //The subtitle is not "required" in the form so we check first if the String is empty
        if(!params.get("subtitle").isEmpty()) book.setSubtitle(params.get("subtitle"));
        book.setIsbn(params.get("isbn"));
        //We loop through the author ids we got from the authors multiple select in the form using a 1 line for loop
        List<Author> authors = new ArrayList<>();
        for (int i=0; i<authorIds.length; i++) authors.add(authorService.findOne(authorIds[i]));
        //We link the current book with its authors
        book.setAuthors(authors);
        //Setting the genre using the retrieved id
        book.setGenre(genreService.findOne(Integer.valueOf(params.get("genre"))));
        //We persist the brand new book in the database using our service layer
        bookService.save(book);
        //Now we need to create x number of copies depending on the "nbCopies" parameter we got from the form
        for(int i=0; i<Integer.valueOf(params.get("nbCopies")); i++){
            Copy copy = new Copy();
            copy.setBook(book);
            copyService.save(copy);
        }
        return "redirect:/manage/books/all";
    }

    /**
     * Method used to display the book form view. Authors and genres are sent by the model
     * @return ModelAndView object with the corresponding view name and two lists
     * containing the authors and the genres
     */
    @GetMapping("/book/new")
    public ModelAndView newBookForm(){
        ModelAndView modelAndView = new ModelAndView("books/newBook");
        modelAndView.addObject("authors", authorService.findAll());
        modelAndView.addObject("genres", genreService.findAll());
        return modelAndView;
    }



    /**
     * Method used to display the view containing the books details
     * @return ModelAndView object with the corresponding view name and a list containing
     *         the books retrieved from the database via the service layer
     */
    @GetMapping("/books/all")
    public ModelAndView allBooks(){
        ModelAndView modelAndView = new ModelAndView("books/allBooks");
        modelAndView.addObject("books", bookService.findAll());
        return modelAndView;
    }




    @PostMapping("/copies/new")
    public String createNewCopies(@RequestParam int nbCopies, @RequestParam("book") Long bookId){

        Book book = bookService.findOne(bookId);

        for(int i=0; i<nbCopies; i++){
            Copy copy = new Copy();
            copy.setBook(book);
            copyService.save(copy);
        }

        return "redirect:/manage/copies";
    }

    @GetMapping("copies")
    public ModelAndView copyFormAndList(){
        ModelAndView modelAndView = new ModelAndView("copies/createAndListCopies");
        modelAndView.addObject("books", bookService.findAll());
        modelAndView.addObject("copies", copyService.findAll());
        return modelAndView;
    }


    @GetMapping("/genres")
    public ModelAndView genreFormAndList(){
        ModelAndView modelAndView = new ModelAndView("genres/createAndListGenres");
        modelAndView.addObject("genres", genreService.findAll());
        return modelAndView;
    }

    @GetMapping("/genre/delete/{id}")
    public String deleteGenre(@PathVariable int id){
        if(genreService.exists(id)) genreService.delete(id);
        return "redirect:/manage/genres";
    }

    @PostMapping("/genre/new")
    @ExceptionHandler(DataAccessException.class)
    public String createGenre(@RequestParam String genreName, RedirectAttributes r) {

        if(genreService.findByName(genreName) != null){
            r.addFlashAttribute("genreAlreadyExists","Genre '"+genreName+"' already exists.");
        } else {
            genreService.save(new Genre(genreName));
        }
        return "redirect:/manage/genres";
    }

    @GetMapping("/authors")
    public ModelAndView authorFormAndList(){
        ModelAndView modelAndView = new ModelAndView("authors/createAndListAuthors");
        modelAndView.addObject("authors", authorService.findAll());
        return modelAndView;
    }

    @PostMapping("/author/new")
    public String createAuthors(@RequestParam("firstname") List<String> firstnames,
                                @RequestParam("lastname") List<String> lastnames,
                                @RequestParam("pseudonym") List<String> pseudonyms,
                                @RequestParam("dob") @DateTimeFormat(pattern="yyyy-MM-dd") List<LocalDate> dobs){

        System.out.println(firstnames+"\n"+lastnames+"\n"+pseudonyms+"\n"+dobs);

        for(int i = 0; i<firstnames.size(); i++){
            Author a = new Author();
            a.setFirstname(firstnames.get(i));
            a.setLastname(lastnames.get(i));
            try {
                a.setPseudonym(pseudonyms.get(i));
            } catch (IndexOutOfBoundsException error){
                System.out.print("OutofboundsCatched");
            }
            a.setDateOfBirth(dobs.get(i));
            authorService.save(a);
        }

        return "redirect:/manage/authors";
    }


}
