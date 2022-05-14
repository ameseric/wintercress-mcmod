package plinth.disk;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.saveddata.SavedData;

/**
 * 
 * @author Guiltygate
 *
 *	Code based off of example at https://mcforge.readthedocs.io/en/latest/datastorage/worldsaveddata/
 *  
 */




public class PlinthSaveData extends SavedData{

	private CompoundTag data;
	private NBTSaveObject[] objectsForReadWrite;
	
	public static String STORAGE_NAME = "plinthsavedata";
	
	
	public PlinthSaveData() {
		this.data = new CompoundTag();
		objectsForReadWrite = new NBTSaveObject[] {};
	}
	
	
	
	
	public static PlinthSaveData getInstance( MinecraftServer server ,NBTSaveObject[] objects) {
		PlinthSaveData newSave = server.overworld().getDataStorage().computeIfAbsent( PlinthSaveData::__load ,PlinthSaveData::__create ,"plinthsavedata");
		newSave.setAndLoadObjects( objects);
		return newSave;
	}
	
	
    public static PlinthSaveData __load( CompoundTag tag) {
    	PlinthSaveData newsave = __create();
    	newsave.setDataTag( tag);
    	return newsave;
    }
    
    
    public static PlinthSaveData __create() {
    	return new PlinthSaveData();
    }
	
    
    
	
	private void setAndLoadObjects( NBTSaveObject[] objects) {
		this.objectsForReadWrite = objects;
		for( NBTSaveObject systemToSave : this.objectsForReadWrite){
			CompoundTag objectNBT = this.data.getCompound( systemToSave.getStorageName());
			systemToSave.load( objectNBT);
		}
	}
    
    
    @Override
	public CompoundTag save(CompoundTag pCompoundTag) {		
		for( NBTSaveObject systemToSave : this.objectsForReadWrite){				
			CompoundTag objectNBT = this.data.getCompound( systemToSave.getStorageName());
			if( systemToSave.isDirty()) {
				objectNBT = systemToSave.save( objectNBT);
				this.data.put( systemToSave.getStorageName() ,objectNBT);
			}			
		}
		
		setDirty( false);
		return this.data;
	}
    

    
	public void __tick(int tickcount, ServerLevel world) {
		for( NBTSaveObject systemToSave : this.objectsForReadWrite){
			if( systemToSave.isDirty()) {
				setDirty();
			}
		}
	}
	
	
	


	
	private void setDataTag( CompoundTag tag) {
		this.data = tag.copy();
	}

	

	
	public void printData() {
		System.out.println( this.data);
	}
	
}








