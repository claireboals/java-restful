package challenge.javarestful;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;

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

	@Test
	public void getAllTest() throws Exception {
		this.mockMvc.perform(get("/products")
            .accept(org.springframework.http.MediaType.APPLICATION_JSON))
            .andDo(print())
            .andExpect(status().isOk())
			.andExpect(content().string(containsString("[]")));
	}

    @Test
    public void postTest() throws Exception {
        this.mockMvc.perform((post("/products"))
            .content(asJsonString(new Product("test-name", "test-description")))
            .contentType(org.springframework.http.MediaType.APPLICATION_JSON)
            .accept(org.springframework.http.MediaType.APPLICATION_JSON))
            .andExpect(status().isOk());
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}