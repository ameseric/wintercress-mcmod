package plinth.utility;

import net.minecraft.world.level.block.Block;

public interface BlockTypeCollection {

	public boolean includes( Block b);
	
	public void add( Block b);
	
}
