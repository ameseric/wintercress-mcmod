package plinth.disk;

import net.minecraft.nbt.CompoundTag;

//TODO refactor to WorldSave being ticked, children will lose access
public abstract class NBTSaveObject implements NBTSaveFormat{ 
	
	private boolean dirty;
	
	
	
	
	public abstract CompoundTag save( CompoundTag compound);
	
	public abstract void load( CompoundTag compound);
	

	public abstract String getStorageName();
	
	
    /**
     * Marks this MapDataBase dirty, to be saved to disk when the level next saves.
     */
    protected void markDirty(){
        this.dirty = true;
    }
    
    protected void markClean() {
    	this.dirty = false;
    }


    /**
     * Whether this obj has data to save.
     */
    public boolean isDirty(){
        return this.dirty;
    }
	
}
