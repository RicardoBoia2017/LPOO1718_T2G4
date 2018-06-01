package mono.controller.entities;

public class EntityModel {

	private float x;
	
	private float y;
	
	private float rotation;
	
	/**
	 * Contructor 
	 * 
	 * @param x initial x-coordinate
	 * @param y initial y-coordinate
	 * @param rotation initial rotation
	 */
	public EntityModel(float x, float y, float rotation) {
		this.x = x;
        this.y = y;
        this.rotation = rotation;
	}
	
    /**
     * Returns the x-coordinate of this entity.
     *
     * @return The x-coordinate of this entity in meters.
     */
    public float getX() {
        return x;
    } 

    /**
     * Returns the y-coordinate of this entity.
     *
     * @return The y-coordinate of this entity in meters.
     */
    public float getY() {
        return y;
    }

    /**
     * Returns the rotation of this entity.
     *
     * @return The rotation of this entity in radians.
     */
    public float getRotation() {
        return rotation;
    }	

}
	