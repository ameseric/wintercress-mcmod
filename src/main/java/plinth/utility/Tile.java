package plinth.utility;

import javax.annotation.Nullable;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import plinth.tilelogic.TileLogic;

public class Tile{
	
	private BlockState blockstate;
	private TileLogic logic;
	
	
	public Tile( BlockState blockstate ,@Nullable TileLogic logic) {
		this.blockstate = blockstate;
		this.logic = logic;
	}
	
	
	public TileLogic logic() {
		return this.logic;
	}
	
	
	public BlockState blockstate() {
		return this.blockstate;
	}
	
	
	public Block block() {
		return this.blockstate().getBlock();
	}
	
	
	public boolean hasLogic() {
		return this.logic != null;
	}
	
	
}
