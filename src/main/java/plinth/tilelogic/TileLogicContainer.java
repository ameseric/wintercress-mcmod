package plinth.tilelogic;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

public interface TileLogicContainer {

	
	public void onBlockAdded( Level world ,BlockPos pos ,BlockState state);

	
}
