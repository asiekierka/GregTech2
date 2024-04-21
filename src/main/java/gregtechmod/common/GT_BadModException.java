package gregtechmod.common;

public class GT_BadModException extends RuntimeException {
	
	private String mError;
	
	public GT_BadModException(String aError) {
		mError = aError;
	}
	
	@Override
	public String toString() {
		return "The GregTech-Addon has a Problem.\nOne of the other Mods doesnt work properly. Below is how to fix it.\n" + mError + "\nDO NOT COME TO ME WITH THIS CRASH. YOU CAUSED IT YOURSELF, AND I TOLD YOU HOW TO FIX IT!!!";
	}
}