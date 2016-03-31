/**
 * 
 */
package plant;

/**
 * 
 * <h1>FruitPlant</h1>
 * <p>
 * It have different kinds of fruit plants
 * </p>
 * 
 * @author Shivanagesh Chandra Feb 19, 2016 FruitPlant.java
 */
public class FruitPlant extends Plant {

	protected int harvestDays;
	protected int ageInDays;

	/**
	 * Parameterized constructor
	 * 
	 * @param name
	 *            Name of plant
	 * @param zone
	 *            Zone in which is present
	 * @param harvestDays
	 *            Number of days after plant can be harvested
	 */
	public FruitPlant(String name, int zone, int harvestDays) {
		super("FruitPlant", name, zone, harvestDays);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return the harvestDays
	 */
	public int getHarvestDays() {
		return harvestDays;
	}

	/**
	 * @param harvestDays
	 *            the harvestDays to set
	 */
	public void setHarvestDays(int harvestDays) {
		this.harvestDays = harvestDays;
	}

	/**
	 * @return True or false, if it is ready to harvest then true , if not
	 *         return false
	 */

	public boolean isReadyHarvest() {
		if (ageInDays >= harvestDays)
			return true;
		else
			return false;
	}

}
