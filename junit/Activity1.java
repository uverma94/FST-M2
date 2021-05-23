package activity;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class Activity1 {

	//
	static ArrayList<String> list;
    
	@BeforeEach
	void setUp() throws Exception {
		//Initialize new array list
		list = new ArrayList<String>();
		
		list.add("alpha");
		list.add("beta");		
	}
	
	@Test
	void insertTest() throws Exception {
		
		assertEquals(2, list.size(), "Wrong Size");		
		list.add("gama");		
		assertEquals(3, list.size(), "Wrong Element");
		
		assertEquals("alpha", list.get(0), "Wrong Element");
		assertEquals("beta", list.get(1), "Wrong Element");
		assertEquals("gama", list.get(2), "Wrong Element");
		
	}
	
	@Test
	void replaceTest() throws Exception{		
		
		list.set(1, "Junit");		
		assertEquals(2, list.size(), "Wrong Size");		
		
		assertEquals("alpha", list.get(0), "Wrong Element");
		assertEquals("Junit", list.get(1), "Wrong Element");
		
		
	}
	

}
