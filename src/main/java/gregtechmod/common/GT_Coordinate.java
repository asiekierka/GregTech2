package gregtechmod.common;

/**
 * Just a simple Coordinate Object, which makes calculating Machine Block Updates easier.
 */
public class GT_Coordinate {
	public int mX, mY, mZ;
	
	public GT_Coordinate(int aX, int aY, int aZ) {
		mX = aX;
		mY = aY;
		mZ = aZ;
	}

	@Override
	public boolean equals(Object aObject) {
		GT_Coordinate tCoord = (GT_Coordinate)aObject;
		if (tCoord == null) return false;
		return mX == tCoord.mX && mY == tCoord.mY && mZ == tCoord.mZ;
	}
}
