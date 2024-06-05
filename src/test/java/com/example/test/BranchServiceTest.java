package com.example.test;
 
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
 
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
 
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.example.controller.BranController;
import com.example.entities.Branch;
import com.example.entities.Branches;
import com.example.repo.BranRepository;
import com.example.service.BranService;
import com.fasterxml.jackson.databind.ObjectMapper;
 
@ExtendWith(MockitoExtension.class)
@WebMvcTest(BranController.class)
public class BranchServiceTest {
	@Autowired
	private MockMvc mockMvc;
	@MockBean
	private BranRepository branRepository;
	@MockBean
	private BranService branService;
	@InjectMocks
	private BranController branController;
	private Branch branch1;
	private Branch branch2;
	@BeforeEach
	void setUp() {
		branch1 = new Branch();
		branch1.setBranch_id(0);
		branch1.setBranch_location("ABC Street");
		branch1.setBranch_name("ABC Bank");
		branch1.setBranch_contact(123456789);
		
		branch2 = new Branch();
		branch2.setBranch_id(1);
		branch2.setBranch_location("DEF Street");
		branch2.setBranch_name("DEF Bank");
		branch2.setBranch_contact(282828282);
	}
	@Test
	void testRead() throws Exception{
		Branches brans =new Branches(Arrays.asList(branch1,branch2));
		when(branService.getAll()).thenReturn(brans);
		mockMvc.perform(get("/branchservice/Bgetall"))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.branchList[0].branch_name").value("ABC Bank"))
			.andExpect(jsonPath("$.branchList[1].branch_name").value("DEF Bank"));
	}
//	@Test
//	void testAdd() throws Exception{
//		ObjectMapper objectMapper = new ObjectMapper();
//		String teamJson = objectMapper.writeValueAsString(customer1);
//		mockMvc.perform(post("/customer/add")
//			.contentType(MediaType.APPLICATION_JSON)
//			.content(teamJson))
//			.andExpect(status().isOk());
//    		verify(customerService).add(customer1);
//	}
//	@Test
//	void testReadOne() throws Exception{
////		Customers customers = new Customers(Arrays.asList(customer1));
//		when(customerService.getone(1)).thenReturn(customer1);
//		mockMvc.perform(get("/customer/getone/1"))
//			   .andExpect(status().isOk())
//			   .andExpect(jsonPath("$.address").value("Kadugodi"));
//	}
//	@Test
//	void testUpdate() throws Exception{
//		ObjectMapper objectMapper = new ObjectMapper();
//		String customerJson = objectMapper.writeValueAsString(customer1);
//		mockMvc.perform(put("/customer/update/1")
//			.contentType(MediaType.APPLICATION_JSON)
//			.content(customerJson))
//			.andExpect(status().isOk());
//		verify(customerService).update(1,customer1);
//	}
//	@Test
//	void testDelete() throws Exception{
//		mockMvc.perform(delete("/customer/delete/1"))
//			.andExpect(status().isOk());
//		verify(customerService).delete(1);
//	}
}