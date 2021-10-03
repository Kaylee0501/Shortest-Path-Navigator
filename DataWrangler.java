// --== CS400 File Header Information ==--
// Name: Lillian Li
// Email: yli2244@wisc.edu
// Team: DB
// TA: Yelun Bao
// Lecturer: Gary Dahl

import java.io.File;
import java.util.Scanner;

/**
 * To front end engineers: Call the method processFileOfCommands() if the user
 * enters a command (whatever you set it to be) to upload a file of commands.
 * 
 * @author lillian
 *
 */
public class DataWrangler {
  /**
   * Process commands in a file
   * 
   * @param file the file to read the commands from
   */
  public void processFileOfCommands(File file) {
    Scanner sfile = null;
    try {
      sfile = new Scanner(file);

      while (sfile.hasNextLine()) {
        String nextLine = sfile.nextLine().trim();
        if (nextLine.length() > 0) {/*TODO: Call the front end process command function*/}

      }
      sfile.close();
    } catch (Exception e) {
      System.out.println("Not able to read from " + file);
    } finally {
      if (sfile != null)
        sfile.close();
    }
  }

}
