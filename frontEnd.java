// --== CS400 File Header Information ==--
// Name: Xintong Li
// Email: xli2224@wisc.edu
// Team: DB
// Role: FrontEnd Engineer
// TA: Yelun Bao
// Lecturer: Gary Dahl
// Notes to Grader: <optional extra notes>
import java.io.File;
import java.util.Scanner;
import java.util.List;
import java.util.LinkedList;

/**
 * FrontEnd class: create a location map and do some command to add, remove edge and vertex
 * 
 * @author Xintong Li
 *
 */
public class frontEnd {
  
  BackEnd back = new BackEnd();
  
  public static void main(String[] args) {
    
    String welcomeMessage = "Welcome to the location map!";    
    System.out.println(welcomeMessage);
    new frontEnd().run( new Scanner(System.in) );
    displayOptionsMenu();
  }

  /**
   * To front end engineers: Call the method processFileOfCommands() if the user
   * enters a command (whatever you set it to be) to upload a file of commands.
   * 
   * @author lillian
   *
   */
  public void processFileOfCommands(File file) { //Data Wrangler: Lillian
    Scanner sfile = null;
    try {
      sfile = new Scanner(file);

      while (sfile.hasNextLine()) {
        String nextLine = sfile.nextLine().trim();
        if (nextLine.length() > 0) {
          processCommand("A " + nextLine);
        }
      }
      sfile.close();
    } catch (Exception e) {
      System.out.println("Not able to read from " + file);
    } finally {
      if (sfile != null)
        sfile.close();
    }
  }
  
  /**
   * Process a single command from the user
   * 
   * @param command is the command to process
   */
  public void processCommand(String com) {
    try {
      switch (com.charAt(0)) {
        case 'a': //[A]dd an edge or a vertex
        case 'A': {
          String[] command = com.substring(2).trim().split(",");
          String target = command[0].trim();
          if (target.equals("V") ) {
            String vertex = command[1].trim();
            System.out.println(vertex);
            if(back.insertVertex(vertex)) {
              System.out.println("vertex " + vertex + " added successfully!");
            }
          }else if(target.equals("E")) {
            String sourceVertex = command[1].trim();
            String destVertex = command[2].trim();
            int weight = Integer.parseInt (command[3].trim());
            if (back.insertEdge(sourceVertex, destVertex, weight)) {
              System.out.println("edge " + sourceVertex + "-" + destVertex + " added successfully!");
            }
          }else {
            System.out.println("Invalid target\n");            
          }
          break;
        }
        case 'c': // [C]heck whether a vertex or an edge was recorded
        case 'C': {
          String[] command = com.substring(2).trim().split(",");
          String target = command[0].trim();
          if (target.equals("V")) {
            String vertex = command[1].trim();
            if(!back.containsVertex(vertex)) {
              System.out.println("No record for the vertex" + vertex);
            }else {
              System.out.println("There is a record for the vertex" + vertex);
            }
          }else if(target.equals("E")) {
            String sourceVertex = command[1].trim();
            String destVertex = command[2].trim();
            if (!back.containsEdge(sourceVertex, destVertex)) {
              System.out.println("No record for the edge between" + sourceVertex 
                  + " and " + destVertex);
            }else {
              System.out.println("There is a record for edge between" + sourceVertex 
                  + " and " + destVertex);
            }
          }else {
            System.out.println("Invalid target\n");            
          }
          break;
        }
        case 'l': // [L]ook up an edge
        case 'L': {
          String[] command = com.substring(2).trim().split(",");
          String sourceVertex = command[0].trim();
          String destVertex = command[1].trim();
          int weight = back.getWeight(sourceVertex, destVertex);
          System.out.println("The corresponding weight between " + sourceVertex + " and "
          + destVertex + " is " + weight); 
          break;
        }
        case 'm': // [M]odify the name
        case 'M': {
          String[] command = com.substring(2).trim().split(",");
          String oldName = command[0].trim();
          String newName = command[1].trim();
          if(back.changeName(oldName, newName)) {
          System.out.println("The vertex name has been changed from " + oldName + " to " + newName); 
          }else {
            System.out.println("Name hasn't been changed successfully");
          }
          break;
        }
        case 'n': // [N]eighbours from the graph
        case 'N': {
          String[] command = com.substring(2).trim().split(" ");
          String vertex = command[0].trim();
          LinkedList<String> neighbour = back.findNeighbours(vertex);
          System.out.print("The neighbours of the " + vertex + " is " + neighbour.toString());
          break;
        }
        case 'r': // [R]emove an edge or a vertex 
        case 'R': {
          String[] command = com.substring(2).trim().split(",");
          String target = command[0].trim();
          if (target.equals("V")) {
            String vertex = command[1].trim();
            if(back.removeVertex(vertex)) {
              System.out.println("vertex " + vertex + " removed successfully!");
            }
          }else if(target.equals("E")) {
            String sourceVertex = command[1].trim();
            String destVertex = command[2].trim();
            if (back.removeEdge(sourceVertex, destVertex)) {
              System.out.println("edge " + sourceVertex + "-" + destVertex + " removed successfully!");
            }
          }else {
            System.out.println("Invalid target\n");            
          }
          break;
        }
        case 'g': // [G]et the shortest path
        case 'G':
          String[] command = com.substring(2).trim().split(",");
          String start = command[0].trim();
          String end = command[1].trim();
          int cost = back.getPathCost(start, end);
          List<String> shortPath = back.shortestPath(start, end);
          System.out.println("The shoetest path between " + start + " and " + end + " is "
          + shortPath.toString() + ", and the cost is " + cost); 
          break;
        case 'p': // [P]rint the information about edges and vertices
        case 'P':
          System.out.println("There is(are) " + back.getVertexCount() + " vertices and " + 
              back.getEdgeCount() +  " edges in the loaction map");
          break;
        case 'Q': // [Q]uit: q
        case 'q':
          System.out.println("Thanks for using the location map!");
          break;
        default:
          System.out.println("Please enter a command listed below.");
          break;
      }
    } catch (Exception e) {
      System.out.println("Process aborted. Check the input, and "
          + "make sure you are following the instructions below.");
    }
  }
  
  
  /**
   * Process commands until the user enter Q or q
   * Allow the user to enter a file of commands
   * @param in the input from user
   */
  public void run(Scanner in) {
    String com = "a command";
    while (com.charAt(0) != 'q' && com.charAt(0) != 'Q') { // quit 
      displayOptionsMenu();
      com = in.nextLine().trim(); // read command from user

      // process the file of commands if user enters f
      if (com.charAt(0) == 'f' || com.charAt(0) == 'F') {
        File commandFile = new File(com.substring(1).trim());
        processFileOfCommands(commandFile);
      } else // process other (non file) commands
        processCommand(com);
    }
  }
  
  /**
   * Print command options to the user.
   */
  private static void displayOptionsMenu() {
    String display = "\nCommand options:\n" + "  [A]dd a vertex: A V, <vertex name>\n"
        + "  [A]dd an edge: A E, <edge source>, <edge destination>, <length>\n"
        + "  [C]heck whether a vertex was recorded: C V, <vertex name>\n"
        + "  [C]heck whether an edge was recorded: C E, <source edge>, <destination edge>\n"
        + "  [L]ook up an edge: L <source edge>, <destination edge>\n"
        + "  [M]odify the name: M <old name>, <new name>\n"
        + "  [N]eighbours from the graph: N <vertex name>\n"
        + "  [R]emove an edge: R E, <edge source>, <edge destination>, <length>\n"
        + "  [R]emove a vertex: R V, <vertex name>\n"
        + "  [P]rint the information about edges and vertices : p\n"
        + "  [G]et the shortest path: G <start vertex>, <end vertex>\n"
        + "  [F]ile Commands: f <filename>\n" 
        + "  [Q]uit: q\n" + "Please enter command: ";

    System.out.print(display);
  }
}
