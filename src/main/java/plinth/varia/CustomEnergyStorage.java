package plinth.varia;

import net.minecraftforge.energy.EnergyStorage;



//taken from Jorrit Tyberghein https://www.youtube.com/watch?v=tv6oFjC8sq8



public class CustomEnergyStorage extends EnergyStorage{

	public CustomEnergyStorage(int capacity, int maxTransfer) {
		super(capacity, maxTransfer);
	}
	
	
	
	//override as needed to call setChanged
	protected void onEnergyChanged() {
		
	}
	
	
	@Override
	public int receiveEnergy( int maxReceive ,boolean simulate) {
		int rc = super.receiveEnergy(maxReceive, simulate);
		if( rc > 0 && !simulate) {
			onEnergyChanged();
		}
		
		return rc;
	}
	
	
	@Override
	public int extractEnergy( int maxExtract ,boolean simulate) {
		int rc = super.extractEnergy( maxExtract ,simulate);
		if( rc > 0 && !simulate) {
			onEnergyChanged();
		}
		
		return rc;	
	}
	
	
	public void setEnergy( int energy) {
		this.energy = energy;
		onEnergyChanged();
	}
	
	
	
	public void addEnergy( int energy) {
		this.energy += energy;
		if( this.energy > getMaxEnergyStored()) {
			this.energy = getEnergyStored();
		}
		onEnergyChanged();
	}
	
	
	public void consumeEnergy( int energy) {
		this.energy -= energy;
		if( this.energy < 0) {
			this.energy = 0;
		}
		onEnergyChanged();
	}
	
	

}






















