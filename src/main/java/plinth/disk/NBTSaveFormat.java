package plinth.disk;


import net.minecraft.nbt.CompoundTag;

public interface NBTSaveFormat {
	
//	@Retention(RetentionPolicy.RUNTIME)
//	@interface AutoSaveValue{}

	
	
	public CompoundTag save( CompoundTag compound);
	
	public void load( CompoundTag compound) throws InstantiationException, IllegalAccessException;
	
	public String getStorageName();

	
}
