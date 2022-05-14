package plinth.tilelogic;

import java.util.HashMap;
import java.util.HashSet;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.Level;
import plinth.MCForge;
import plinth.ObjectCatalog;
import plinth.PlinthMod;
import plinth.hermetics.Muir;
import plinth.hermetics.MuirElement;
import plinth.hermetics.MuirGasContainer;
import plinth.utility.Tile;

public class MuirGeyserTile extends MuirGasContainerTile{
	
	private static final int PRESSURE = 10000;
	
//	
//	
//	public MuirGeyserTile() {
//		this( new BlockPos(0,0,0));
//	}
//	public MuirGeyserTile(BlockPos pos) {
//		super(pos ,ObjectCatalog.GEYSER ,true ,20);
//		this.contents = Muir.random();
////		this.contents.add( MuirElement.B ,2000);
//	}
//	
//	
//	
//	
//
//	@Override
//	public String getStorageName() {
//		return null;
//	}
//
//	@Override
//	public void _ticklogic(Level world) {
//		HashMap<BlockPos ,Tile> neighbors = MCForge.getNeighbors( pos());
//		
//		for( BlockPos neighborPos : neighbors.keySet()) {
//			TileLogic neighbor = neighbors.get( neighborPos).logic();
//			if( neighbor instanceof MuirGasContainer) {
//				( (MuirGasContainer) neighbor).add( contents());
//			}else {
//				if( PlinthMod.instance.acceptingMuir( pos() ,PRESSURE)) {
//					PlinthMod.instance.addToAtmosphere( pos() ,contents());
//				}
//			}
//		}
//	}
//	
//	
//	@Override
//	public void averageWith( Muir m ,BlockPos requester) {}	
//	
//	@Override
//	public void add( Muir m) {}




	@Override
	protected CompoundTag __writeToNBT(CompoundTag nbt) {
		this.contents.save( nbt);
		return nbt;
	}

	@Override
	protected void __readFromNBT(CompoundTag nbt) {
		this.contents.load(nbt);		
	}

	@Override
	public void _ticklogic(Level world) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getStorageName() {
		return null;
	}

}
