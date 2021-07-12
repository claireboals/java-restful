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
public class ImageTests {

	@Autowired
	private MockMvc mockMvc;

    /*** Success test: GET all images ***/
	@Test
	public void getAllTest() throws Exception {
        this.mockMvc.perform(get("/images")
            .accept(org.springframework.http.MediaType.APPLICATION_JSON))
            .andDo(print())
            .andExpect(status().isOk());
	}


    /*** Failure test: GET with bad request ***/
    @Test
	public void getAllTestFailure() throws Exception {
	    this.mockMvc.perform(get("/image")
            .accept(org.springframework.http.MediaType.APPLICATION_JSON))
            .andDo(print())
            .andExpect(status().isNotFound());
	}


    /*** Success test: GET image by id ***/
    @Test
    public void getByIdSuccess() throws Exception {
            // POST the image to be searched by id
        Image image = new Image("test-my-id", "test-description");
        this.mockMvc.perform((post("/images"))
            .content(asJsonString(image))
            .contentType(org.springframework.http.MediaType.APPLICATION_JSON)
            .accept(org.springframework.http.MediaType.APPLICATION_JSON));
        
            // Get the id and perform GET request
        Long id = image.getId();
        this.mockMvc.perform((get("/images"))
            .content(asJsonString(id))
            .contentType(org.springframework.http.MediaType.APPLICATION_JSON)
            .accept(org.springframework.http.MediaType.APPLICATION_JSON))
            .andDo(print())
            .andExpect(status().isOk());
    }


    /*** Failure test: GET image with bad id ***/
    @Test
    public void getByIdFailure() throws Exception {
            // Assert that searching for nonexistant id 88 throws an exception
        assertThrows(Exception.class, () ->
        this.mockMvc.perform((get("/images/88"))
            .contentType(org.springframework.http.MediaType.APPLICATION_JSON)
            .accept(org.springframework.http.MediaType.APPLICATION_JSON)));
    }


    /*** Success test: POST image ***/
    @Test
    public void postTest() throws Exception {
        this.mockMvc.perform((post("/images"))
            .content(asJsonString(new Image("test-image-title", "test-image-description")))
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