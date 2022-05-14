package plinth;

import net.minecraft.nbt.CompoundTag;
import plinth.disk.NBTSaveObject;

public class TestSaveObj extends NBTSaveObject{

	private int count = 0;

	
	
	public void count() {
		this.count++;
		this.markDirty();
	}
	
	
	public int getCount() {
		return this.count;
	}
	
	
	@Override
	public CompoundTag save(CompoundTag compound) {
		compound = new CompoundTag();
		compound.putInt( "count" ,count);
		return compound;
	}

	@Override
	public void load(CompoundTag compound) {
		this.count = compound.getInt( "count");
	}

	@Override
	public String getStorageName() {
		return "testsaveobj";
	}
	
	
	
	
	
	
	
}
