package plinth.tilelogic;


import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.Block;
import plinth.hermetics.MuirContainer;

public abstract class MuirContainerTile extends TileLogic implements MuirContainer{


	
	public MuirContainerTile(BlockPos pos, Block homeblock, boolean active ,int ticksUntilUpdate) {
		super( pos ,homeblock ,active ,ticksUntilUpdate);			
	}
	public MuirContainerTile() {}
	
	
	
	
	
	
	



}
