/**
 * 
 */
package plant;

/**
 * <h1>FlowerPlant</h1>
 * <p> It have different kinds of flower plants </p>
 * @author Shivanagesh Chandra
 * Feb 19, 2016 
 * FlowerPlant.java
 */
public class FlowerPlant extends Plant {

	
	/**
	 * Parameterized constructor
	 * @param name  Name of plant 
	 * @param zone  Zone in which is present 
	 * @param daysTomaturity Number of days after plant can be reached mature stage
	 */
	public FlowerPlant(String name, int zone, int daysTomaturity) {
		super("FlowerPlant", name, zone,daysTomaturity);
		// TODO Auto-generated constructor stub
	}

}
