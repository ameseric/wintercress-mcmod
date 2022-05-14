package plinth.hermetics;

import java.util.HashSet;

import net.minecraft.core.BlockPos;



public interface MuirContainer {

	public Muir _takeMatter( int numOfUnits ,HashSet<BlockPos> traversed ,BlockPos requester);
	
	
	
}
