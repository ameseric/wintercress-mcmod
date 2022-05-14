package plinth.hermetics;

import java.util.HashSet;

import net.minecraft.core.BlockPos;



public interface ElementalFluidContainer {
	
	public Muir _takeFluid( BlockPos requesterPos ,HashSet<BlockPos> traversed);
	
	public Muir peekAtContents();
	
	
	
	
}
