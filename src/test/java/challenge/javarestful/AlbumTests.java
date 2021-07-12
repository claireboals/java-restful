package challenge.javarestful;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
public class AlbumTests {

	@Autowired
	private MockMvc mockMvc;

    /*** Success test: GET all albums ***/
	@Test
	public void getAllTest() throws Exception {
	    this.mockMvc.perform(get("/albums")
            .accept(org.springframework.http.MediaType.APPLICATION_JSON))
            .andDo(print())
            .andExpect(status().isOk());
	}


    /*** Failure test: GET with bad request ***/
    @Test
	public void getAllTestFailure() throws Exception {
	    this.mockMvc.perform(get("/album")
            .accept(org.springframework.http.MediaType.APPLICATION_JSON))
            .andDo(print())
            .andExpect(status().isNotFound());
	}


    /*** Success test: GET album by id ***/
    @Test
    public void getByIdSuccess() throws Exception {
            // POST the album to be searched by id
        Album album = new Album("test-my-id");
        this.mockMvc.perform((post("/albums"))
            .content(asJsonString(album))
            .contentType(org.springframework.http.MediaType.APPLICATION_JSON)
            .accept(org.springframework.http.MediaType.APPLICATION_JSON));
        
            // Get the id and perform GET request
        Long id = album.getId();
        this.mockMvc.perform((get("/albums"))
            .content(asJsonString(id))
            .contentType(org.springframework.http.MediaType.APPLICATION_JSON)
            .accept(org.springframework.http.MediaType.APPLICATION_JSON))
            .andDo(print())
            .andExpect(status().isOk());
    }


    /*** Failure test: GET album with bad id ***/
    @Test
    public void getByIdFailure() throws Exception {
            // Assert that searching for nonexistant id 67 throws an exception
        assertThrows(Exception.class, () ->
        this.mockMvc.perform((get("/albums/67"))
            .contentType(org.springframework.http.MediaType.APPLICATION_JSON)
            .accept(org.springframework.http.MediaType.APPLICATION_JSON)));
    }


    /*** Success test: POST album ***/
    @Test
    public void postTestSuccess() throws Exception {
        this.mockMvc.perform((post("/albums"))
            .content(asJsonString(new Album("test-album-description")))
            .contentType(org.springframework.http.MediaType.APPLICATION_JSON)
            .accept(org.springframework.http.MediaType.APPLICATION_JSON))
            .andDo(print())
            .andExpect(status().isOk());
    }


    /*** Helper to convert Objects to JSON ***/
    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}