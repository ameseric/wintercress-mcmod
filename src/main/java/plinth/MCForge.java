package plinth;

import java.util.HashMap;
import java.util.List;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.server.ServerLifecycleHooks;
import plinth.tilelogic.TileLogic;
import plinth.utility.Tile;
import plinth.PlinthMod;




/**
 * 
 * @author Guiltygate
 * 
 * This is an attempt to segregate Forge API calls that have a strong chance of breaking in future versions
 * and provide wrapper methods for Forge objects which do not fit our design pattern, but we cannot modify.
 * 
 * NOT INTENDED FOR CLIENT-SIDE USE
 *
 */
public class MCForge {
	


	private MCForge() {}
	
	
//	public static List<EntityPlayerMP> getAllPlayersOnServer(){
//		return FMLCommonHandler.instance().getMinecraftServerInstance().getPlayerList().getPlayers();
//	}
	
	
	
	
	public static Tile getTile( Level world ,BlockPos pos) {
		BlockState bs = world.getBlockState( pos);
		TileLogic l = null;// = PlinthMod.instance.getTileLogic( pos);
		return new Tile( bs ,l);
	}
	
	
	public static Tile getTile( BlockPos pos) {
		return getTile( getOverworldServer() ,pos);
	}
	
	
	public static Block getBlock( BlockPos pos) {
		return getBlockState( pos).getBlock();
	}
	
	
	public static TileLogic getTileLogic( BlockPos pos) {
		return null;//PlinthMod.instance.getTileLogic( pos);
	}
	
	
	public static BlockState getBlockState( BlockPos pos) {
		return getOverworldServer().getBlockState( pos);
	}
	
	
	
	
	public static ServerLevel getOverworldServer() {
//		return FMLCommonHandler.instance().getMinecraftServerInstance().getWorld(0);
		return ServerLifecycleHooks.getCurrentServer().overworld();
	}
	
	
	//TODO: Move to appropriate class
	public static HashMap<BlockPos,Tile> getNeighbors( BlockPos pos){
		HashMap<BlockPos,Tile> neighbors = new HashMap<>();
		
		for( Direction direction : Direction.values()) {
			BlockPos npos = pos.offset( direction.getNormal());
			neighbors.put( npos ,getTile( npos));
		}
		return neighbors;
	}
	
	
	
//	public static void setBlock( Block b ,BlockPos pos ,World world) {
//		world.setBlockState( pos ,b.getDefaultState());
//		TEinTE.instance.spawnTileLogicIfNeeded( b ,pos);
//	}
	
	
	

	
	

}




