/**
 * 
 */
package gardenControl;

import java.util.logging.Level;
import java.util.logging.Logger;

import logger.EventLogger;

/**
 * <h1> Garden Control </h1>
 * 
 * @author Shivanagesh Chandra
 * Feb 15, 2016 2016
 * GardenController.java
 */
public class GardenController {
   
   public static void main(String [] args){
          EventLogger eventLogger =  new EventLogger();
          eventLogger.getLogger().log(Level.INFO, "hey");
          EventLogger.logger.log(Level.INFO, "Graden controller started");
         
          
   }

}
