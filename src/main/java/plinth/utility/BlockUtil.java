package plinth.utility;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;


//import teinte.faction.ResourceMap;



/**
 * 
 * @author Guiltygate
 *
 *
 * Basic block utilities, but poorly structured (and implemented?).
 *
 * Probably going to be removed in the future.
 * 
 * TODO: if we're keeping this, utilize EnumFacing rather than Symbol.
 */
public abstract class BlockUtil {	
	
	public static boolean onlyTouchingBlockTypes( BlockPos currentPos ,BlockTypeCollection acceptedBlocks ,Level world) {
		HashMap<BlockPos,Block> map = getNeighborBlocks( currentPos ,world);
		
		for( Block b : map.values()) {
			if( !acceptedBlocks.includes( b)) {
				return false;
			}
		}
		return true;
	}
	
	
	public static boolean notTouchingBlockType( BlockPos pos ,BlockTypeCollection dontTouch ,Level world) {
		
		HashMap<BlockPos,Block> neighbors = getNeighborBlocks( pos ,world);
		for( Block b : neighbors.values()) {
			if( dontTouch.includes(b)) {
				return false;
			}
		}
		return true;
	}
	
	
	
	public static boolean notTouchingAir( BlockPos pos ,Level world) {
		return notTouchingBlockType( pos ,new HashBlockCollection( Blocks.AIR) ,world );
	}

	
	public static HashMap<BlockPos,Block> getNeighborBlocks( BlockPos pos ,Level world){
		
		return getNeighborBlocks( pos ,Symbol.nonNullValues() ,world);
	}
	
	public static HashMap<BlockPos,Block> getNeighborBlocks( BlockPos pos ,Level world ,boolean random){
		Symbol[] symbols;
		if( random) {
			symbols = Symbol.randomValues(6);
		}else {
			symbols = Symbol.nonNullValues();
		}
		
		return getNeighborBlocks( pos ,symbols ,world);
	}
	
	public static HashMap<BlockPos,Block> getNeighborBlocks( BlockPos pos ,Symbol[] array ,Level world){
		HashMap<BlockPos,Block> map = new HashMap<BlockPos,Block>();
		
		for( Symbol direction : array) {
			BlockPos currentPos = pos.offset( direction.mod);
			map.put( currentPos ,world.getBlockState( currentPos).getBlock());
		}
		return map;
	}
	
	public static HashSet<ChunkPos> getNeighborChunks( BlockPos pos){
		return getNeighborChunks( new ChunkPos(pos));
	}

	
	public static HashSet<ChunkPos> getNeighborChunks( ChunkPos pos){
		HashSet<ChunkPos> set = new HashSet<ChunkPos>();
		
		for( Symbol direction : Symbol.values2D()) {
			set.add( new ChunkPos( pos.x+direction.mod.getX() ,pos.z+direction.mod.getZ() ));
		}
		return set;
	}
	
	
	
	public static BlockPos chunkCenterPos( ChunkPos pos) {
		return new BlockPos( pos.getMinBlockX()+7 ,0 ,pos.getMinBlockZ()+7);
	}
	
	
	
	public static BlockPos getAirYPos( BlockPos pos ,Level world) {
		int y = 240;		
		Block b;
		
		do{
			b = world.getBlockState( pos.offset(0,y,0) ).getBlock();
			--y;
			
		}while( b == Blocks.AIR);
		
		return pos.offset( 0 ,y+2 ,0);
	}
	
	
	public static int calcR( ChunkPos pos ,ChunkPos center) {
		int xDiff = Math.abs( pos.x - center.x);
		int zDiff = Math.abs( pos.z - center.z);
		return xDiff > zDiff ? xDiff : zDiff;
	}
	
	
	protected Symbol[] getRandomizedPath( BlockPos a ,BlockPos b) {
		int x = b.getX() - a.getX();
		int y = b.getY() - a.getY();
		int z = b.getZ() - a.getZ();

		ArrayList<Symbol> path = new ArrayList<Symbol>();
		
		if( x > 0) {
			for( int i=0; i<x; i++) {
				path.add( Symbol.XP);
			}
		}else {
			for( int i=0; i>x; i--) {
				path.add( Symbol.XN);
			}			
		}
		
		if( y > 0) {
			for( int i=0; i<y; i++) {
				path.add( Symbol.YP);
			}
		}else {
			for( int i=0; i>y; i--) {
				path.add( Symbol.YN);
			}			
		}
		
		if( z > 0) {
			for( int i=0; i<z; i++) {
				path.add( Symbol.ZP);
			}
		}else {
			for( int i=0; i>z; i--) {
				path.add( Symbol.ZN);
			}			
		}

		Collections.shuffle( path);
		return  path.toArray( new Symbol[ path.size()]);
	}
}
