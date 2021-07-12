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
public class ProductTests {

	@Autowired
	private MockMvc mockMvc;

    /*** Success test: GET all products ***/
	@Test
	public void getAllTestSuccess() throws Exception {
	    this.mockMvc.perform(get("/products")
            .accept(org.springframework.http.MediaType.APPLICATION_JSON))
            .andDo(print())
            .andExpect(status().isOk());
	}


    /*** Failure test: GET with bad request ***/
    @Test
	public void getAllTestFailure() throws Exception {
		this.mockMvc.perform(get("/product")
            .accept(org.springframework.http.MediaType.APPLICATION_JSON))
            .andDo(print())
            .andExpect(status().isNotFound());
	}


    /*** Success test: GET product by id ***/
    @Test
    public void getByIdSuccess() throws Exception {
            // POST the product to be searched by id
        Product product = new Product("test-my-id", "test-id-description");
        this.mockMvc.perform((post("/products"))
            .content(asJsonString(product))
            .contentType(org.springframework.http.MediaType.APPLICATION_JSON)
            .accept(org.springframework.http.MediaType.APPLICATION_JSON));
        
            // Get the id and perform GET request
        Long id = product.getId();
        this.mockMvc.perform((get("/products"))
            .content(asJsonString(id))
            .contentType(org.springframework.http.MediaType.APPLICATION_JSON)
            .accept(org.springframework.http.MediaType.APPLICATION_JSON))
            .andDo(print())
            .andExpect(status().isOk());
    }


    /*** Failure test: GET product with bad id ***/
    @Test
    public void getByIdFailure() throws Exception {
            // Assert that searching for nonexistant id 45 throws an exception
        assertThrows(Exception.class, () ->
        this.mockMvc.perform((get("/products/45"))
            .contentType(org.springframework.http.MediaType.APPLICATION_JSON)
            .accept(org.springframework.http.MediaType.APPLICATION_JSON)));
    }


    /*** Success test: POST product ***/
    @Test
    public void postTestSuccess() throws Exception {
        this.mockMvc.perform((post("/products"))
            .content(asJsonString(new Product("test-product-name", "test-product-description")))
            .contentType(org.springframework.http.MediaType.APPLICATION_JSON)
            .accept(org.springframework.http.MediaType.APPLICATION_JSON))
            .andDo(print())
            .andExpect(status().isOk());
    }


    /*** Failure test: POST product with malformed JSON ***/
    @Test
    public void postTestFailure() throws Exception {
        this.mockMvc.perform((post("/products"))
            .content("i am malformed JSON")
            .contentType(org.springframework.http.MediaType.APPLICATION_JSON)
            .accept(org.springframework.http.MediaType.APPLICATION_JSON))
            .andDo(print())
            .andExpect(status().isBadRequest());
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