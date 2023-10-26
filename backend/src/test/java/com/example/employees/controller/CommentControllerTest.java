
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;

import com.example.employees.controller.CommentController;
import com.example.employees.model.Comment;
import com.example.employees.repository.CommentRepository;

@SpringBootTest
@AutoConfigureMockMvc
public class CommentControllerTest {

    @Mock
    private CommentRepository commentRepository;

    @InjectMocks
    private CommentController commentController;

    public CommentControllerTest() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testCreateComment() {
        Comment comment = new Comment(1, "John", "http://user.image.com", new added_user("1", "John"), "Nice post!", "2023-04-20T12:00:00Z");

        when(commentRepository.save(comment)).thenReturn(comment);

        given().
                contentType(MediaType.APPLICATION_JSON_VALUE).
                body(comment).
                when().
                post("/api/comments").
                then().
                statusCode(201).
                assertThat().
                body("postId", equalTo(1),
                        "username", is(equalTo("John")),
                        "userImage", is(equalTo("http://user.image.com")),
                        "added_user.userId", is(equalTo("1")),
                        "added_user.userName", is(equalTo("John")),
                        "comment", is(equalTo("Nice post!")),
                        "time", is(equalTo("2023-04-20T12:00:00Z")));
    }

    @Test
    public void testGetAllComments() {
        Comment comment1 = new Comment(1, "John", "http://user.image.com", new added_user("1", "John"), "Nice post!", "2023-04-20T12:00:00Z");
        Comment comment2 = new Comment(2, "Jane", "http://user.image.com", new added_user("2", "Jane"), "Great article!", "2023-04-21T10:00:00Z");

        List<Comment> comments = new ArrayList<>();
        comments.add(comment1);
        comments.add(comment2);

        when(commentRepository.findAll()).thenReturn(comments);

        given().
                when().
                get("/api/comments").
                then().
                statusCode(200).
                assertThat().
                body("postId", hasItems(1, 2),
                        "username", hasItems("John", "Jane"),
                        "userImage", hasItems("http://user.image.com", "http://user.image.com"),
                        "added_user.userId", hasItems("1", "2"),
                        "added_user.userName", hasItems("John", "Jane"),
                        "comment", hasItems("Nice post!", "Great article!"),
                        "time", hasItems("2023-04-20T12:00:00Z", "2023-04-21T10:00:00Z"),
                        "size()", equalTo(2));
    }