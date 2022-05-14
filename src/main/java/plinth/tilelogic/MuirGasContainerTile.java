package plinth.tilelogic;


import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.Block;
import plinth.hermetics.Muir;
import plinth.hermetics.MuirGasContainer;

public abstract class MuirGasContainerTile extends TileLogic implements MuirGasContainer{


	protected Muir contents = new Muir();
	public static final int DEFAULT_MUIR_LIMIT = 5000;
	
	
	public MuirGasContainerTile(BlockPos pos, Block homeblock, boolean active ,int ticksUntilUpdate) {
		super( pos ,homeblock ,active ,ticksUntilUpdate);			
	}
	public MuirGasContainerTile() {}
	
	
	
	
	public void averageWith( Muir m ,BlockPos requester) {
		this.contents.averageWith( m);
	}
	
	
	public void add( Muir m) {
		this.contents.add( m);
	}
	
	
	public Muir contents() {
		return this.contents;
	}
	



}
