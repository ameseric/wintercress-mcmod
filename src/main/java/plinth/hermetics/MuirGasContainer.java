package plinth.hermetics;

import net.minecraft.core.BlockPos;

public interface MuirGasContainer {

	public void averageWith( Muir m ,BlockPos requester);
	
	public void add( Muir m);
	
}
