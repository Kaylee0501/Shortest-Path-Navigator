// --== CS400 File Header Information ==--
// Name: Khalid Shallhoub
// Email: shallhoub@wisc.edu
// Team: DB
// TA: Yelun
// Lecturer: Professor Gary Dahl
// Notes to teammates:

import org.junit.Assert.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.assertThrows;
import java.util.NoSuchElementException;
import static org.junit.jupiter.api.Assertions.*;

class TTest {
	frontEnd frontEndTest;
	
	/**
	 * This method will run before the start fo each test to initialize a frontEnd with some 
	 * initial location values.
	 */
	@BeforeEach
	public void initialGraph() {
		frontEndTest = new frontEnd();
		frontEndTest.back.insertVertex("Home");
		frontEndTest.back.insertVertex("School");
		frontEndTest.back.insertVertex("Park");
	}
	
	/**
	 * This test method will check if the following functions of the application work properly:
	 * adding a number of locations, and the removal of existing locations.
	 */
	@Test
	public void testAddNode() {
		frontEndTest.back.insertVertex("Art school");
		if (!frontEndTest.back.containsVertex("Art school") ||
				!frontEndTest.back.containsVertex("Home") ||
				!frontEndTest.back.containsVertex("School") ||
				!frontEndTest.back.containsVertex("Park")) {
			fail("This test did not pass as your application has an issue with inserting locations." +
					"The following locations were attempted to be added: Home, School, Park, Art School.");
		}
		frontEndTest.back.insertVertex("Stadium");
		if (!frontEndTest.back.containsVertex("Art school") ||
				!frontEndTest.back.containsVertex("Home") ||
				!frontEndTest.back.containsVertex("School") ||
				!frontEndTest.back.containsVertex("Park") ||
				!frontEndTest.back.containsVertex("Stadium")) {
			fail("This test did not pass as your application has an issue with inserting locations." +
					"The following locations were attempted to be added: Home, School, Park, Art School, Stadium." 
					);
		}
		frontEndTest.back.removeVertex("School");
		if (frontEndTest.back.containsVertex("School")) {
			fail("This test did not pass as your application has an issue with removing locations." +
					"The following locations were attempted to be added: Home, School, Park, Art School, Stadium." +
					"After the additions were succesfully added, <School> was supposed to be removed." 
					);
		}
	}
	
	/**
	 * This test will check the property of changing a name of a certain location works as expected within
	 * the application.
	 */
	@Test
	public void testChangeName() {
		frontEndTest.back.changeName("Home", "Grandpa's house");
		if (!frontEndTest.back.containsVertex("Grandpa's house") ||
				!frontEndTest.back.containsVertex("School") ||
				!frontEndTest.back.containsVertex("Park") ||
				frontEndTest.back.containsVertex("Home")) {
			fail("This test did not pass as your application has an issue with changing the name of an existing location." +
					"The following locations were attempted to be added at first: Home, School, Park." +
					"After the addition of locations, it was attempted to change <Home> to be the name of <Grandpa's home>" +
					"However, your application returned the following: " );
		}
		
		if (frontEndTest.back.changeName("Grandma's house", "Granchildren's house") == true) {
				fail("Your application behave properly when expected in the case of changing the name of a location that"
				+ " has not been added to this application.");
		}
	}
	
	/**
	 * This test will check the functionality of the application in determining the shortest path from a certain
	 * location to a specified destination. This test checks if the path itself is returned as expected.
	 */
	@Test
	public void testTwoDiffDest() {
		frontEndTest.back.insertVertex("Supermarket");
		frontEndTest.back.insertVertex("The mall");
		frontEndTest.back.insertEdge("Home", "School", 7);
		frontEndTest.back.insertEdge("Home", "Park", 7);
		frontEndTest.back.insertEdge("School", "Supermarket", 7);
		if ( !frontEndTest.back.shortestPath("Home", "School").toString().equals("[Home, School]") ) {
			fail("This test did not pass as your application has an issue with finding the path from a location to another." +
					"The following locations were attempted to be added at first: Home, School, Park, Supermarket, The Mall." +
					" After the addition of locations, it was attempted to get the path from <Home> to school." +
					" However, your application did not return the correct path." +
					" Instead, your application returned (expected is: Home, School): "+
					frontEndTest.back.shortestPath("Home", "School").toString());
		};
		
		if ( !frontEndTest.back.shortestPath("Home", "Supermarket").toString().equals("[Home, School, Supermarket]") ) {
			fail("This test did not pass as your application has an issue with finding the path from a location to another." +
					"The following locations were attempted to be added at first: Home, School, Park, Supermarket, The Mall." +
					"After the addition of locations, it was attempted to get the path from <Home> to <Supermarket>." +
					"However, your application did not return the correct path." +
					"Instead, your application returned (expected is: Home, School, Supermarket): "+ 
					frontEndTest.back.shortestPath("Home", "Supermarket"));
		};
	}
	
	/**
	 * This test will check if the application throws exception when expected: no path exists between two locations, or
	 * one (or either) of the locations does not exist.
	 */
	@Test
	public void testNonexistantPath() {
		frontEndTest.back.insertVertex("Coca-Cola store");
		frontEndTest.back.insertVertex("An Igloo");
		frontEndTest.back.insertVertex("TMG HQ");
		frontEndTest.back.insertVertex("The zoo");
		frontEndTest.back.insertEdge("Home", "School", 7);
		frontEndTest.back.insertEdge("Home", "Park", 7);
		frontEndTest.back.insertEdge("School", "An Igloo", 3);
		frontEndTest.back.insertEdge("School", "The zoo", 16);
		frontEndTest.back.insertEdge("The zoo", "Home", 1);
		assertThrows(NoSuchElementException.class, () -> frontEndTest.back.dijkstrasShortestPath("Home", "Coca-Cola store")
				, "Your application did not throw a "
				+ "NoSuchElementException when expected and searching for a non-existant path between <Home> and <Coca-Cola store>");
		assertThrows(NoSuchElementException.class, () -> frontEndTest.back.dijkstrasShortestPath("Home", "Igloo park")
				, "Your application did not throw a "
				+ "NoSuchElementException when expected and searching for a non-existant path between <Home> and "
				+ "a non-existant location of <Igloo Park>");
		
	}
	
	/**
	 * This test will check if the application can correctly return the (int) distance of a certain path.
	 */
	@Test
	public void testDistanceOfPath() {
		frontEndTest.back.insertVertex("Supermarket");
		frontEndTest.back.insertVertex("The mall");
		frontEndTest.back.insertEdge("Home", "School", 7);
		frontEndTest.back.insertEdge("Home", "Park", 7);
		frontEndTest.back.insertEdge("School", "Supermarket", 7);
		if ( frontEndTest.back.getPathCost("Home", "School") != 7 ) {
			fail("This test did not pass as your application has an issue with finding the path from a location to another." +
					"The following locations were attempted to be added at first: Home, School, Park, Supermarket, The Mall." +
					"After the addition of locations, it was attempted to get the path from <Home> to <School>." +
					"However, your application did not return the correct distance of the path." +
					"Instead, your application returned (expected is 7): "+ frontEndTest.back.getWeight("Home", "School"));
		}
		
		if ( frontEndTest.back.getPathCost("Home", "Supermarket") != 14 ) {
			fail("This test did not pass as your application has an issue with finding the path from a location to another." +
					"The following locations were attempted to be added at first: Home, School, Park, Supermarket, The Mall." +
					"After the addition of locations, it was attempted to get the path from <Home> to <Supermarket>." +
					"However, your application did not return the correct distance of the path." +
					"Instead, your application returned (expected is 14): "+ frontEndTest.back.getWeight("Home", "Supermarket"));
		}
		
		frontEndTest.back.insertVertex("Domo's house");
		frontEndTest.back.insertVertex("Shoe store");
		frontEndTest.back.insertEdge("Domo's house", "School", 25);
		frontEndTest.back.insertEdge("Shoe store", "Park", 2);
		if ( frontEndTest.back.getPathCost("Domo's house", "Domo's house") != 0 ) {
			fail("This test did not pass as your application has an issue with finding the path from a location to another." +
					"The following locations were attempted to be added at first: Home, School, Park, Supermarket, The Mall, "
					+ " Shoe store, Domo's house." +
					"After the addition of locations, it was attempted to get the path from <Domo's house> to itself." +
					"However, your application did not return the correct distance of the path." +
					"Instead, your application returned (expected is 0): "+ frontEndTest.back.getWeight("Domo's house", "Domo's house"));
		}
	}
}