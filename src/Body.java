
public class Body {
	
	private double myXVel;
	private double myYVel;
	private double myXPos; 
	private double myYPos; 
	private double myMass; 
	private String myFileName;
	private final double GRAVITY_CONSTANT = 6.67*1e-11; 
	
	
	/**
	 * This generates a Body object with the parameters provided in the constructor.
	 * @param x initial x position
	 * @param y initial y position
	 * @param xv initial xvelocity
	 * @param yv initial yvelocity
	 * @param mass mass of the object
	 * @param filename filename for loading the objects. 
	 */
	public Body(double x, double y, double xv, double yv, double mass, String filename) { 
		myXVel = xv; 
		myYVel = yv; 
		myXPos = x; 
		myYPos = y; 
		myMass = mass; 
		myFileName = filename; 
		
		
	}
	
	/**
	 * This generates another Body object that copies all the information of the body parameter provided.
	 * @param c used to initialize this body
	 */
	public Body(Body c) { 
		myXVel = c.myXVel; 
		myYVel = c.myYVel; 
		myXPos = c.myXPos; 
		myYPos = c.myYPos; 
		myMass = c.myMass; 
		myFileName = c.myFileName;
	}
	
	/**
	 * Returns the x velocity of the object
	 * @return double objects xvel
	 */
	public double getXVel() { 
		return myXVel;
	}
	
	/**
	 * Returns the y velocity of the object
	 * @return double objects yvel
	 */
	public double getYVel() { 
		return myYVel; 
	}

	/**
	 * Returns objects xposition 
	 * @return the myXPos of the object
	 */
	public double getX() {
		return myXPos;
	}

	/**
	 * Returns objects yposition
	 * @return the myYPos of the object
	 */
	public double getY() {
		return myYPos;
	}

	/**
	 * returns the objects mass
	 * @return the myMass of the object
	 */
	public double getMass() {
		return myMass;
	}

	/**
	 * returns the filename of the object for the animation
	 * @return the myFileName 
	 */
	public String getName() {
		return myFileName;
	}
	
	/**
	 * Calculates the distance between two objects
	 * @param b is another object that is used to calculate the distance
	 * @return a double of the distance between the this object and the parameter object.
	 */
	public double calcDistance(Body b) { 
		double r = 0; 
		r = Math.pow(this.myXPos-b.myXPos, 2) + Math.pow(this.myYPos-b.myYPos, 2);
		/**System.out.println(r);*/
		r = Math.sqrt(r);
		return r; 
	}
	
	/**
	 * Returns the force exerted by an object on the "this" object.
	 * @param p this is the object that is exerting the force on the object
	 * @return a double of the force exerted by the parameter object on the this object
	 */
	public double calcForceExertedBy(Body p) { 
		double force = 0; 
		double numerator = (this.getMass() * p.getMass()); 
		double denominator = Math.pow((this.calcDistance(p)), 2); 
		force = GRAVITY_CONSTANT * (numerator/denominator); 
		return force; 
	}
	
	/**
	 * Returned the x component of force exerted by an object on the "this" object
	 * @param p this is the object that is exerting the force on the object
	 * @return a double of the x component of force exerted by the parameter object on the this object
	 */
	public double calcForceExertedByX(Body p) { 
		double forceX = 0; 
		double numerator = (p.getX()- this.getX()); 
		forceX = (calcForceExertedBy(p)) * (numerator/calcDistance(p)); 
		return forceX; 
		
	}
	
	/**
	 * Returns the y component of force exerted by an object on the "this" object
	 * @param p this is the object that is exerting the force on the object
	 * @return a double of the y component of force exerted by the parameter object on the this object
	 */
	public double calcForceExertedByY(Body p) { 
		double forceY = 0; 
		double numerator = (p.getY()- this.getY()); 
		forceY = (calcForceExertedBy(p)) * (numerator/calcDistance(p)); 
		return forceY; 
		
	}
	
	/**
	 * Returns the sum of the x component forces of all the bodies on a specific body
	 * @param bodies this is the array of bodies that are exerting a force of some sort on the object
	 * @return a double of the net xForce exerted by all the objects in a solar system on a specific object
	 */
	public double calcNetForceExertedByX(Body[] bodies) { 
		double netForceX = 0; 
		for (Body b : bodies) { 
			if(! b.equals(this)) { 
				netForceX += this.calcForceExertedByX(b);
			}
			
		}
		return netForceX; 
	}

	/**
	 * Returns the sum of the y component forces of all the bodies on a specific body
	 * @param bodies this is the array of bodies that are exerting a force of some sort on the object
	 * @return a double of the net yForce exerted by all the objects in a solar system on a specific object
	 */
	public double calcNetForceExertedByY(Body[] bodies) { 
		double netForceY = 0; 
		for (Body b : bodies) { 
			if(! b.equals(this)) { 
				netForceY += this.calcForceExertedByY(b);
			}
			
		}
		return netForceY; 
	}
	
	/**
	 * Mutator method that updates the object's positions and velocities based on the netXForce and net Yforce and the change in time
	 * Does not return a value
	 * @param deltaT the change in time
	 * @param xForce the net xforce of all the object on a specific object
	 * @param yForce the net yforce of all the object on a specific object
	 */
	public void update (double deltaT , double xForce, double yForce) { 
		double accelerationX = xForce/this.myMass; 
		double accelerationY = yForce/this.myMass; 
		
		this.myXVel = this.myXVel + (deltaT * accelerationX); 
		this.myYVel = this.myYVel + (deltaT * accelerationY); 
		
		this.myXPos = this.myXPos + (deltaT * this.myXVel); 
		this.myYPos = this.myYPos + (deltaT * this.myYVel); 
	}
	
	/**
	 * A method that takes the xpos and ypos and the filename of the object and draws the object on a grid. 
	 */
	public void draw() { 
		StdDraw.picture(myXPos, myYPos, "images/"+myFileName);
	}

}
