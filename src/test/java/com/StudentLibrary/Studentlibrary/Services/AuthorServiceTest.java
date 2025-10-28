package com.StudentLibrary.Studentlibrary.Services;

import com.StudentLibrary.Studentlibrary.Model.Author;
import com.StudentLibrary.Studentlibrary.Repositories.AuthorRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AuthorServiceTest {

    @Mock
    private AuthorRepository authorRepository;

    @InjectMocks
    private AuthorService authorService;

    @Test
    @DisplayName("Should call repository.save with provided Author when creating")
    void createAuthor_callsRepositorySave() {
        Author author = new Author();
        author.setName("John Doe");
        author.setEmail("john@example.com");

        when(authorRepository.save(any(Author.class))).thenReturn(author);

        authorService.createAuthor(author);

        verify(authorRepository).save(eq(author));
    }


    @Test
    @DisplayName("Should propagate exceptions thrown by repository.save during create")
    void createAuthor_propagatesRepositoryException() {
        Author author = new Author();
        author.setName("Error");
        author.setEmail("error@example.com");

        doThrow(new RuntimeException("db error")).when(authorRepository).save(any(Author.class));

        assertThrows(RuntimeException.class, () -> authorService.createAuthor(author));
    }

    @Test
    @DisplayName("Should call repository.updateAuthorDetails with provided Author when updating")
    void updateAuthor_callsRepositoryUpdateDetails() {
        Author author = new Author();
        author.setId(5);
        author.setName("Updated");
        author.setEmail("updated@example.com");

        when(authorRepository.updateAuthorDetails(any(Author.class))).thenReturn(1);

        authorService.updateAuthor(author);

        verify(authorRepository).updateAuthorDetails(eq(author));
    }

    @Test
    @DisplayName("Should propagate exceptions thrown by repository.updateAuthorDetails during update")
    void updateAuthor_propagatesRepositoryException() {
        Author author = new Author();
        author.setId(10);
        author.setName("Break");
        author.setEmail("break@example.com");

        doThrow(new RuntimeException("update failure")).when(authorRepository).updateAuthorDetails(any(Author.class));

        assertThrows(RuntimeException.class, () -> authorService.updateAuthor(author));
    }

    @Test
    @DisplayName("Should call repository.deleteCustom with provided id when deleting")
    void deleteAuthor_callsRepositoryDeleteCustom() {
        int id = 42;
        when(authorRepository.deleteCustom(eq(id))).thenReturn(1);

        authorService.deleteAuthor(id);

        verify(authorRepository).deleteCustom(eq(id));
    }
}
