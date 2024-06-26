package gregtechmod.api;

/**
 * You are allowed to include this File in your Download, as i will not change it.
 * Simple Interface for Machines, which need my Machine Blocks for Multiblockstructures.
 * 
 * Every Machine implementing this Interface will conduct Machine updates.
 */
public interface IMachineBlockUpdateable {
	/**
	 * The Machine Update
	 */
	public void onMachineBlockUpdate();
}
