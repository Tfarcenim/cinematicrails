package tfar.cinematicrails;

import net.minecraft.util.math.Vec3d;

public enum Facing {
	NORTH,
	NORTH_EAST,
	EAST,
	SOUTH_EAST,
	SOUTH,
	SOUTH_WEST,
	WEST,
	NORTH_WEST;
	public static Facing getFacing(Vec3d vec3d) {
		double x = vec3d.x;
		//double y = vec3d.y;
		double z = vec3d.z;

		if (x > .6) {
		if (z > .6)	{
			return NORTH_EAST;
		} else if (z > -.1 && z < .1) {
			return EAST;
		} else {
			return SOUTH_EAST;
		}
		} else if (x > -.1 && x < .1) {
			if (z > .6)	{
				return NORTH;
			} else if (z > -.1 && z < .1) {
				throw new RuntimeException("impossible");
			} else {
				return SOUTH;
			}
		} else {
			if (z > .6)	{
				return NORTH_WEST;
			} else if (z > -.1 && z < .1) {
				return WEST;
			} else {
				return SOUTH_WEST;
			}
		}
	}
}
