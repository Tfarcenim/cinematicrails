package tfar.cinematicrails;

import net.minecraft.state.properties.RailShape;

public class Util {
	public static int getRotate(RailShape oldShape, boolean oldDiagonal, RailShape newShape, boolean newDiagonal, Facing facing) {

		if (oldShape == newShape && oldDiagonal == newDiagonal) return 0;

		if (!oldDiagonal && !newDiagonal)
			return FF(oldShape, newShape, facing);
		if (!oldDiagonal)
			return FT(oldShape, newShape, facing);
		if (!newDiagonal)
			return TF(oldShape, newShape, facing);
		return TT(oldShape, newShape, facing);
	}

	public static int FF(RailShape old, RailShape newS, Facing facing) {

		switch (facing) {
			case NORTH: {
						if (old == RailShape.NORTH_SOUTH &&newS == RailShape.NORTH_EAST)
							return -90;
						if ((old == RailShape.NORTH_SOUTH|| old == RailShape.SOUTH_WEST) &&newS == RailShape.NORTH_WEST)
							return 90;
						if (old == RailShape.SOUTH_EAST && newS == RailShape.NORTH_EAST)
							return -90;
			}
			break;
			case NORTH_EAST:
			case EAST: {
				if ((old == RailShape.EAST_WEST || old == RailShape.SOUTH_EAST) && newS == RailShape.SOUTH_WEST)
					return 90;
				if (old == RailShape.EAST_WEST && newS == RailShape.NORTH_WEST ||
								old == RailShape.NORTH_EAST && newS == RailShape.NORTH_WEST)
					return -90;
			}
			break;
			case SOUTH_EAST:
			case SOUTH: {
				switch (old) {
					case NORTH_SOUTH: {

					}
					case EAST_WEST: {
						if (newS == RailShape.SOUTH_WEST)
							return -90;
						if (newS == RailShape.SOUTH_EAST)
							return 90;
					}
					case ASCENDING_EAST:
					case ASCENDING_WEST:
					case ASCENDING_NORTH:
					case ASCENDING_SOUTH:
					case NORTH_WEST:
						break;
					case SOUTH_WEST: {
						return 90;
					}
					case SOUTH_EAST:
					case NORTH_EAST:
						if (newS == RailShape.SOUTH_EAST)
							return 90;
				}
			}
			case SOUTH_WEST:
			case WEST: {
				if ((old == RailShape.EAST_WEST ||old == RailShape.SOUTH_WEST) && newS == RailShape.SOUTH_EAST) return -90;
				if (old == RailShape.EAST_WEST && newS == RailShape.NORTH_EAST || old == RailShape.NORTH_WEST && newS == RailShape.NORTH_EAST)
					return 90;
			}
			case NORTH_WEST:
		}

		return 0;
	}

	public static int FT(RailShape old, RailShape newS, Facing facing) {

		switch (facing) {
			case NORTH: {
				if (old == RailShape.NORTH_SOUTH && newS == RailShape.NORTH_WEST)
					return 45;
				if (old == RailShape.NORTH_SOUTH && newS == RailShape.NORTH_EAST)
					return -45;
			}
			break;
			case NORTH_EAST:
			case EAST: {
				if (old == RailShape.EAST_WEST && newS == RailShape.SOUTH_WEST)
					return 45;
				if (old == RailShape.EAST_WEST && newS == RailShape.NORTH_WEST)
					return -45;
			}
			case SOUTH_EAST:
			case SOUTH: {
				switch (old) {
					case NORTH_SOUTH: {
						if (newS == RailShape.SOUTH_WEST)
							return -45;
					}
					case EAST_WEST: {
						if (newS == RailShape.SOUTH_WEST)
							return -45;
						if (newS == RailShape.SOUTH_EAST)
							return 45;
					}
					case ASCENDING_EAST:
					case ASCENDING_WEST:
					case ASCENDING_NORTH:
					case ASCENDING_SOUTH:
					case NORTH_WEST:
						break;
					case SOUTH_WEST: {
						return 45;
					}
					case SOUTH_EAST:
					case NORTH_EAST:
				}
			}
			case SOUTH_WEST:
			case WEST: {
				if (old == RailShape.EAST_WEST && newS == RailShape.NORTH_EAST)
					return 45;
				if (old == RailShape.EAST_WEST && newS == RailShape.SOUTH_EAST)
					return -45;
			}
			case NORTH_WEST:
		}

		return 0;
	}

	public static int TF(RailShape old, RailShape newS, Facing facing) {

		switch (facing) {
			case NORTH: {
				if (old == RailShape.SOUTH_EAST && newS == RailShape.NORTH_SOUTH)
					return -45;
			}
			case NORTH_EAST:
			case EAST: {
				if (old == RailShape.NORTH_EAST && newS == RailShape.EAST_WEST)
					return -45;
			}
			case SOUTH_EAST:
			case SOUTH: {
				if (old == RailShape.NORTH_EAST && newS == RailShape.NORTH_SOUTH)
					return 45;
				if (old == RailShape.NORTH_WEST && newS == RailShape.NORTH_SOUTH)
					return -45;
				switch (old) {
					case NORTH_SOUTH: {
						return 45;
					}
					case EAST_WEST: {
						if (newS == RailShape.SOUTH_WEST)
							return -45;
						if (newS == RailShape.SOUTH_EAST)
							return 45;
					}
					case ASCENDING_EAST:
					case ASCENDING_WEST:
					case ASCENDING_NORTH:
					case ASCENDING_SOUTH:
					case NORTH_WEST:
						break;
					case SOUTH_WEST: {
						return 45;
					}
					case SOUTH_EAST:
					case NORTH_EAST:
				}
			}
			case SOUTH_WEST: {
				switch (old) {
					case NORTH_SOUTH: {
						return 45;
					}
					case EAST_WEST: {
						if (newS == RailShape.SOUTH_WEST)
							return -45;
						if (newS == RailShape.SOUTH_EAST)
							return 45;
					}
					case ASCENDING_EAST:
					case ASCENDING_WEST:
					case ASCENDING_NORTH:
					case ASCENDING_SOUTH:
					case NORTH_WEST:
						break;
					case SOUTH_WEST: {
						return 45;
					}
					case SOUTH_EAST:
					case NORTH_EAST:
						return 45;
				}
			}
			case WEST: {
				if (old == RailShape.EAST_WEST && newS == RailShape.SOUTH_WEST ||
								old == RailShape.SOUTH_WEST && newS == RailShape.NORTH_SOUTH ||
								old == RailShape.NORTH_WEST && newS == RailShape.EAST_WEST)
					return 45;
				if (old == RailShape.SOUTH_WEST && newS == RailShape.EAST_WEST)
					return -45;
			}
			case NORTH_WEST:
		}
		return 0;
	}

	public static int TT(RailShape old, RailShape newS, Facing facing) {
		switch (facing) {
			case NORTH:
				switch (old) {
					case NORTH_WEST:
						if (newS == RailShape.SOUTH_EAST)
							return 0;
				}
				break;
			case NORTH_EAST:
			case EAST:
			case SOUTH_EAST:
			case SOUTH: {
				switch (old) {
					case NORTH_SOUTH: {
						return 45;
					}
					case EAST_WEST: {
						if (newS == RailShape.SOUTH_WEST)
							return -45;
						if (newS == RailShape.SOUTH_EAST)
							return 45;
					}
					case ASCENDING_EAST:
					case ASCENDING_WEST:
					case ASCENDING_NORTH:
					case ASCENDING_SOUTH:
					case NORTH_WEST:
						break;
					case SOUTH_WEST: {
						return 45;
					}
					case SOUTH_EAST:
					case NORTH_EAST:
				}
			}
			break;
			case SOUTH_WEST:
			case WEST:
			case NORTH_WEST:
		}
		return 0;
	}
}
