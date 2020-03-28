package application;


public interface ControllerInterface {

	/**
	 * @author Tomomi
	 * Create a new form
	 * @return new form
	 */
	Form makeRectangle();
	
	/**
	 * @author Tomomi
	 * Move the form right 
	 */
	void moveRight(Form form);
	
	/**
	 * @author Tomomi
	 * Move the form left
	 */
	void moveLeft(Form form);
	
	/**
	 * @author Tomomi
	 * Turn the form in clockwise
	 */
	void turnRect(Form form);
	
	/**
	 * @author Tomomi
	 * Remove rows at the bottom
	 */
	//void removeRows();
	
	/**
	 * @author Tomomi
	 * Set the form automatically go down
	 */
	//boolean moveDown(Form form);
	
	/**
	 * @author Tomomi
	 * Make the form go down faster
	 */
	void moveFaster(Form form);
	
}
