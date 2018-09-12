
public class Body {
	
	private double myXVel;
	private double myYVel;
	private double myXPos; 
	private double myYPos; 
	private double myMass; 
	private String myFileName;
	private final double GRAVITY_CONSTANT = 6.67*1e-11; 
	
	
	
	public Body(double x, double y, double xv, double yv, double mass, String filename) { 
		myXVel = xv; 
		myYVel = yv; 
		myXPos = x; 
		myYPos = y; 
		myMass = mass; 
		myFileName = filename; 
		
		
	}
	
	public Body(Body c) { 
		myXVel = c.myXVel; 
		myYVel = c.myYVel; 
		myXPos = c.myXPos; 
		myYPos = c.myYPos; 
		myMass = c.myMass; 
		myFileName = c.myFileName;
	}
	
	public double getXVel() { 
		return myXVel;
	}
	
	public double getYVel() { 
		return myYVel; 
	}

	/**
	 * @return the myXPos
	 */
	public double getX() {
		return myXPos;
	}

	/**
	 * @return the myYPos
	 */
	public double getY() {
		return myYPos;
	}

	/**
	 * @return the myMass
	 */
	public double getMass() {
		return myMass;
	}

	/**
	 * @return the myFileName
	 */
	public String getName() {
		return myFileName;
	}

	public double calcDistance(Body b) { 
		double r = 0; 
		r = Math.pow(this.myXPos-b.myXPos, 2) + Math.pow(this.myYPos-b.myYPos, 2);
		/**System.out.println(r);*/
		r = Math.sqrt(r);
		return r; 
	}
	
	public double calcForceExertedBy(Body p) { 
		double force = 0; 
		double numerator = (this.getMass() * p.getMass()); 
		double denominator = Math.pow((this.calcDistance(p)), 2); 
		force = GRAVITY_CONSTANT * (numerator/denominator); 
		return force; 
	}
	
	public double calcForceExertedByX(Body p) { 
		double forceX = 0; 
		double numerator = (p.getX()- this.getX()); 
		forceX = (calcForceExertedBy(p)) * (numerator/calcDistance(p)); 
		return forceX; 
		
	}
	
	
	public double calcForceExertedByY(Body p) { 
		double forceY = 0; 
		double numerator = (p.getY()- this.getY()); 
		forceY = (calcForceExertedBy(p)) * (numerator/calcDistance(p)); 
		return forceY; 
		
	}
	
	public double calcNetForceExertedByX(Body[] bodies) { 
		double netForceX = 0; 
		for (Body b : bodies) { 
			if(! b.equals(this)) { 
				netForceX += this.calcForceExertedByX(b);
			}
			
		}
		return netForceX; 
	}

	
	public double calcNetForceExertedByY(Body[] bodies) { 
		double netForceY = 0; 
		for (Body b : bodies) { 
			if(! b.equals(this)) { 
				netForceY += this.calcForceExertedByY(b);
			}
			
		}
		return netForceY; 
	}
	
	public void update (double deltaT , double xForce, double yForce) { 
		double accelerationX = xForce/this.myMass; 
		double accelerationY = yForce/this.myMass; 
		
		this.myXVel = this.myXVel + (deltaT * accelerationX); 
		this.myYVel = this.myYVel + (deltaT * accelerationY); 
		
		this.myXPos = this.myXPos + (deltaT * this.myXVel); 
		this.myYPos = this.myYPos + (deltaT * this.myYVel); 
	}
	
	
	public void draw() { 
		StdDraw.picture(myXPos, myYPos, "images/"+myFileName);
	}

}
