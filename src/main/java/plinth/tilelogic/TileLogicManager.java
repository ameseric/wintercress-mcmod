package plinth.tilelogic;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.annotation.Nullable;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.Level;
import plinth.disk.NBTSaveFormat;
import plinth.disk.NBTSaveObject;
import plinth.disk.PlinthSaveData;




public class TileLogicManager extends NBTSaveObject{

	private static final ArrayList<TileLogic> logicClasses = new ArrayList<>();
	
	
	
	static {
		TileLogicManager.registerClass( new MuirGeyserTile());
		TileLogicManager.registerClass( new RitualBlockTile());
	}
	

	
	private HashMap<BlockPos ,TileLogic> tiles;
	private HashMap<BlockPos ,TileLogic> tileclone;
	//private ArrayList<HashMap<BlockPos,TileLogic>> tilebuckets;
	
	//private NBTTagCompound localnbt; //TODO for now not tracking NBT changes, reconsider later
	
	private double tilePerTickLimit = 10.0;	
	private int tilesPerTick = -1;
	private int tickNumber = 1;
	private int ticksPerCycle = 1;
	private boolean performReset = false;
	
	private String dataname = "THISISANAME";
	
	
	
	
	
	public TileLogicManager( Level world ,String name) {
		this( world ,name ,10);
	}	
	
	public TileLogicManager( Level world ,String name ,int tilePerTickLimit) {
		this.tilePerTickLimit = tilePerTickLimit;
		this.tiles = new HashMap<>();
		this.tileclone = (HashMap<BlockPos, TileLogic>) this.tiles.clone();
		this.dataname = name;
	}	
	

	
	public void add( TileLogic be) {
		this.tiles.put( be.pos() ,be);
		this.markDirty();
	}
	

	
	public void remove( BlockPos pos) {
		this.tiles.remove( pos);
		this.markDirty();
	}
	
	
	
	public TileLogic get( BlockPos pos) {
		return this.tiles.get( pos);
	}
	
	
	public int size() {
		return this.tiles.size();
	}
	
	
	
	public static void registerClass( TileLogic tileLogicClass) {
		logicClasses.add( tileLogicClass);
	}
	
	
	public static boolean hasClassRegistered( TileLogic tl) {
		System.out.println( registeredClasses());
		for( TileLogic tlr : registeredClasses()) {
			if( tlr.getClass().equals( tl.getClass())) {
				return true;
			}
		}
		
//		if( registeredClasses().contains( tl)) {
//			return true;
//		}
		return false;
	}
	
	
	public static ArrayList<TileLogic> registeredClasses(){
		return logicClasses;
	}
	
	
	public static TileLogic buildTileLogic( CompoundTag nbt){
		String id = nbt.getString( "id");
		for( TileLogic tl : registeredClasses()) {
			if( tl.hasThisID(id)) {
				TileLogic tile;
				try {
					tile = tl.getClass().newInstance();
					tile.load( nbt);
					return tile;
				} catch (InstantiationException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				}
			}
		}
		System.out.println( "COULDN'T FIND TILE CLASS");
		return null;
	}
	
	
	
	

	
	//calls all entities every tick, and trusts them to distribute their workload
	public void tick( int tickcount ,Level world) {
		
		if( this.performReset) {
			this.resetCycle();
			return;
		}
		
		ArrayList<BlockPos> toRemove = new ArrayList<>();
		
		//We iterate over a shallow copy to allow insertion of new Tiles to the original
		//during TileLogic ticking.
		//HashMap<BlockPos ,TileLogic> clone = (HashMap<BlockPos, TileLogic>) this.tiles.clone();
		int tilesThisTick = 0;	
		
		
		//Set<Map.Entry<BlockPos ,TileLogic>> tilecloneSet = this.tileclone.entrySet();
		Iterator< Map.Entry<BlockPos ,TileLogic>> iter;
		
		for( iter = this.tileclone.entrySet().iterator(); iter.hasNext();) {			
			Map.Entry< BlockPos ,TileLogic> tl = iter.next();
			if( !(this.tickNumber == this.ticksPerCycle)) {
				if( tilesThisTick == this.tilesPerTick) {
					break;
				}
			}
			
			if( tl.getValue().isDead()) {
				toRemove.add( tl.getKey()); //TODO might be able to phase this out / removal from original / main
			}else {
				tl.getValue()._tick( tickcount ,world);
			}
			

			iter.remove();
			if( tl.getValue().isActive()) { //duplicate check, but allows us to not count inactive tiles
				tilesThisTick++;
			}
		}
		
//		System.out.println( tilesThisTick);
		
		for( BlockPos pos : toRemove) {
			this.remove( pos);
		}
		
		
		this.tickNumber++;
		if( this.tickNumber > this.ticksPerCycle) {
			this.performReset = true;
		}
		
	}
	

	
	
//	private void balanceLoad() {
//	if( this.CHANGES_TIL_BALANCE > this.changesSinceLastBalance) { return;}
//	
//	HashMap<BlockPos,TileLogic> tiles = this.tilebuckets.get(0);
//	int size = tiles.size();
//	if( size > this.LIMIT) {
//		int amountToRemove = size - (LIMIT - LIMIT/2);
//		
//		int index = 0;
//		while( amountToRemove > 0) {
//			if( tiles.size() > LIMIT) {
//				index++;
//				if( index == this.tilebuckets.size()) {
//					this.tilebuckets.add( new HashMap<BlockPos,TileLogic>());
//				}
//				
//				tiles = this.tilebuckets.get( index); 
//			}
//			
//			
//						
//			amountToRemove--;
//		}
//		
//	}		
//	
//	
//	this.changesSinceLastBalance = 0;
//}


	private void resetCycle() {
	
		
		int activeTileCount = size();
	
		double callsPerCycle = Math.ceil( activeTileCount / tilePerTickLimit);
		this.tilesPerTick = (int) Math.round( activeTileCount / callsPerCycle);
		this.tickNumber = 0;
		this.ticksPerCycle = (int) callsPerCycle;
		this.tileclone = (HashMap<BlockPos, TileLogic>) this.tiles.clone();
		this.performReset = false;
	
//		System.out.println( "Active Tile Count: " + activeTileCount + "  |Total Tile Count: " + this.tiles.size());
//		System.out.println( "Ticks per Cycle: " + callsPerCycle);
//		System.out.println( "Tiles per Tick: " + this.tilesPerTick);
	
	}
	
	
	
	
	
	
	
	@Override
	public String getStorageName() {
		return this.dataname;
	}



	@Override
	public CompoundTag save(CompoundTag compound) {
		compound = new CompoundTag(); //TODO?: if this becomes intensive, save local copy for comparison
		
		
		Integer i = 0;
		for( Map.Entry< BlockPos ,TileLogic> tl : this.tiles.entrySet()) {
			i++;
			String generatedName = "tilelogic" + i.toString(); 
			compound.put( generatedName ,tl.getValue().save( new CompoundTag()));
		}
		
		compound.putInt( "numOfTLs" ,i);
		markClean();
		return compound;
	}



	@Override
	public void load( CompoundTag compound){
//		this.localnbt = compound;
		int tlCount = compound.getInt( "numOfTLs");
		
		for(Integer i=1; i<=tlCount; i++) {
			CompoundTag beTag = compound.getCompound( "tilelogic" + i.toString());			
//			try{
			TileLogic tl = TileLogicManager.buildTileLogic( beTag); 
			add( tl);
//			}catch( Exception e){
////				System.out.println( e.getStackTrace()); //TODO make this acceptable
//				throw( Exception e);
//			}
			
//			BlockPos pos = new BlockPos( beTag.getInteger( "x") ,beTag.getInteger( "y") ,beTag.getInteger( "z") );
//			this.tiles.put( pos ,TileLogic.createTileLogicFromID( beTag.getInteger( "id") ,pos ,this.world));
//			this.tiles.get(pos).readFromNBT( beTag);			
		}
		
	}




	
	
	
}
