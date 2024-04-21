package gregtechmod.api;

import buildcraft.api.power.IPowerProvider;
import buildcraft.api.power.IPowerReceptor;
import buildcraft.api.power.PowerFramework;

/**
 * Do never include this Class in a Copy of my API. It will cause incompatiblities!
 * 
 * This file contains all the needed 'implements' of the Interfaces for the Michael Jackson Stuff.
 */
public class BaseMetaTileEntityMJ extends BaseMetaTileEntity implements IPowerReceptor {
	
	IPowerProvider mProvider;
	
	public BaseMetaTileEntityMJ() {
		super();
	}
	
	@Override
	public void updateStatus() {
		if (mMJConverter) {
			if (mProvider == null) {
				mProvider = PowerFramework.currentFramework.createPowerProvider();
				mProvider.configure(100, 1, 100, 1, 10000);
			} else {
				if (getMJCapacity() > 0 && getStoredMJ() < getMJCapacity()) {
					increaseStoredMJ((int)mProvider.useEnergy(1, getMJCapacity() - getStoredMJ(), true), true);
				}
			}
		}
	}
	
	@Override
	public void setPowerProvider(IPowerProvider aProvider) {
		mProvider = aProvider;
	}
	
	@Override
	public IPowerProvider getPowerProvider() {
		return mProvider;
	}
	
	@Override
	public void doWork() {
		
	}
	
	@Override
	public int powerRequest() {
		return (int) Math.ceil(Math.min(getPowerProvider().getMaxEnergyReceived(), getPowerProvider().getMaxEnergyStored() - getPowerProvider().getEnergyStored()));
	}
}