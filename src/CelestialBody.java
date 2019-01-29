

/**
 * Celestial Body class for NBody
 * @author ola
 *
 */
public class CelestialBody {

	private double myXPos;
	private double myYPos;
	private double myXVel;
	private double myYVel;
	private double myMass;
	private String myFileName;
	
	/**
	 * Create a body from parameters
	 * @param xp initial x
	 * @param yp initial y
	 * @param xv initial x velocity
	 * @param yv initial y velocity
	 * @param mass mass of body
	 * @param filename for animation
 	 */
	public CelestialBody(double xp, double yp, double xv,
			             double yv, double mass, String filename){
		myXPos = xp;
		myYPos = yp;
		myXVel = xv;
		myYVel = yv;
		myMass = mass;
		myFileName = filename;
		
	}

	/**
	 * Copy constructor: copy instance variables from one
	 * body to this body
	 * @param b used to initialize this body
	 */
	public CelestialBody(CelestialBody b){
		//myXPos = b.myXPos;
		
		myXPos = b.myXPos;
		myYPos = b.myYPos;
		myXVel = b.myXVel;
		myYVel = b.myYVel;
		myMass = b.myMass;
		myFileName = b.myFileName;
			
	}
/**
 * allows us to use getX or myXPos
 * @return value of x
 */
	public double getX() {

		return myXPos;
	}
	/**
	 * allows us to use getY or myYPos
	 * @return value of y
	 */
	public double getY() {
		
		return myYPos;
	}
	/**
	 * allows us to use getXVel or myXVel
	 * @return value of x velocity
	 */
	public double getXVel() {
		
		return myXVel;
	}
	/**
	 * Return y-velocity of this Body.
	 * @return value of y-velocity.
	 */
	public double getYVel() {
		
		return myYVel;
	}
	/**
	 * return mass of body
	 * @return mass of body
	 */
	public double getMass() {
	
		return myMass;
	}
	/**
	 * returns name
	 * @return name for animation
	 */
	public String getName() {
		
		return myFileName;
	}

	/**
	 * Return the distance between this body and another
	 * @param b the other body to which distance is calculated
	 * @return distance between this body and b
	 */
	public double calcDistance(CelestialBody b) {
		double x = b.myXPos - myXPos;
		double y = b.myYPos - myYPos;
		double squared = (x * x) + (y * y);
		
		return Math.sqrt(squared);
	}
/**
 * return force between bodies
 * @param p other body force is calculated
 * @return force
 */
	public double calcForceExertedBy(CelestialBody p) {
		double distancesquared = calcDistance(p) * calcDistance(p);
		double g = 6.67e-11;
		double masses = p.myMass * myMass;
		return (g * masses) / distancesquared;
		
	}
/**
 * force in x direction on
 * @param p is body exerting force
 * @return amount of force in x direction
 */
	public double calcForceExertedByX(CelestialBody p) {
		double F = calcForceExertedBy(p);
		double dx = p.getX() - myXPos;
		
		return (F * dx) / calcDistance(p);
	}
	/**
	 * force in y direction on
	 * @param p is body exerting force
	 * @return amount of force in y direction
	 */
	public double calcForceExertedByY(CelestialBody p) {
		double F = calcForceExertedBy(p);
		double dy = p.getY() - myYPos;
		
		return (F * dy) / calcDistance(p);
	}
/**
 * returns net x force
 * @param bodies with force calculated
 * @return net x force
 */
	public double calcNetForceExertedByX(CelestialBody[] bodies) {
		//cnet saves who called it in a variable called this
		// who called me name it this = who called me
		double xForce = 0;
		for (CelestialBody body : bodies) {
			if (! body.equals(this)) {
				xForce += calcForceExertedByX(body);
			}
		}
		return xForce;
	}

	/**
	 * returns net y force
	 * @param bodies with force calculated
	 * @return net y force
	 */
	public double calcNetForceExertedByY(CelestialBody[] bodies) {
		double yForce = 0;
		for(CelestialBody body : bodies) {
			if (! body.equals(this)) {
				yForce += calcForceExertedByY(body);
			}
		}
		return yForce;
	}

	/**
	 * illustrates movement
	 */
	public void draw() {
		StdDraw.picture(myXPos, myYPos, "images/"+myFileName);
	}
	
	/**
	 * updates over time
	 * @param deltaT change in time
	 * @param xforce calculates a
	 * @param yforce calculates a
	 */
	public void update(double deltaT, 
			           double xforce, double yforce) {
		double ax = xforce / myMass;
		double ay = yforce / myMass;
		double nvx = myXVel + deltaT * ax;
		double nvy = myYVel + deltaT * ay;
		double nx = myXPos + deltaT * nvx;
		double ny = myYPos + deltaT * nvy;
		myXPos = nx;
		myYPos = ny;
		myXVel = nvx;
		myYVel = nvy;

	}
	
}
