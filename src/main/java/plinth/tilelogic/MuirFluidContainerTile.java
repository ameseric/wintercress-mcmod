package plinth.tilelogic;


import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.Block;
import plinth.hermetics.ElementalFluidContainer;
import plinth.hermetics.Muir;


public abstract class MuirFluidContainerTile extends TileLogic implements ElementalFluidContainer{

	
	protected Muir contents = new Muir();
	
	
	
	
	public MuirFluidContainerTile(BlockPos pos, Block homeblock, boolean active ,int ticksUntilUpdate) {
		super(pos, homeblock, active ,ticksUntilUpdate);
	}	
	public MuirFluidContainerTile() {}
	
	
	
	
	protected Muir _cycleFluid( Muir input) {
		Muir output = peekAtContents();
		setContents( input);
		return output;
	}
	
	
	

	public Muir peekAtContents() {
		return this.contents;
	}
	
	
	protected void setContents( Muir f) {
		this.contents = f;
	}
	

}
