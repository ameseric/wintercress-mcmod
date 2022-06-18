package plinth.survival;

import java.util.HashMap;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.event.TickEvent.ServerTickEvent;
import net.minecraftforge.event.world.BlockEvent.CropGrowEvent;
import net.minecraftforge.eventbus.api.Event.Result;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.LogicalSide;
import net.minecraftforge.server.ServerLifecycleHooks;

public class SurvivalServerTickHandler {
	
	
	private static HashMap<Player,Float> temperature = new HashMap<>();
	private static float OVERHEAT = 75;
	private static float FREEZE = 25;
	
	private static int tickcount = 0;
	
	private static HashMap<ResourceLocation,Float> biomes = new HashMap<>();
	
	
	
	static {
		biomes.put( Biomes.BADLANDS.location() ,70f);
		biomes.put( Biomes.BEACH.location() ,70f);	
		biomes.put( Biomes.DESERT.location() ,100f);	
		
	}
	
	
	
	
	
	
	
	
	
	
    @SubscribeEvent
    public static void _onServerTick( ServerTickEvent event) {
    	
    	if( event.side == LogicalSide.CLIENT) {
    		return;
    	}
    	
    	tickcount++;
    	
    	if( tickcount % 20 == 0) { //every 15 seconds
	    	MinecraftServer server = ServerLifecycleHooks.getCurrentServer();
	    	
	    	for( Player p : server.getPlayerList().getPlayers()) {
	    		if( !temperature.containsKey(p)) {
	    			System.out.println( "Adding player");
	    			temperature.put( p ,50f);
	    		}
	    		
	    		boolean isDayTime = false;
	    		if( server.overworld().getTimeOfDay(0) > 1000 && server.overworld().getTimeOfDay(0) < 2300) { //find actual values
	    			System.out.println( "Day time?");
	    			isDayTime = true;
	    		}
	    		
//	    		if( server.overworld().isThundering()) {
//	    			//stuff?
//	    		}
	    		
	    		BlockPos pos = new BlockPos( p.getPosition(0));
	    		Biome b = server.overworld().getBiome( pos).value();
   		
	    		float targetTemp = 30;
	    		if(  biomes.get( b.getRegistryName()) != null) {
	    			targetTemp = biomes.get( b.getRegistryName());
	    		}
	    		
	    		System.out.println( "Target temp?: " + targetTemp);
	    		System.out.println( "Stored temp in biome: " + b.getBaseTemperature());
	    		float tempRate = (targetTemp - temperature.get(p)) * 0.01f;
	    		float newTemp = temperature.get(p);
	    		
	    		if( p.isSprinting()) {
	    			newTemp += 0.1;
	    		}	    		
	    		if( p.isInPowderSnow) {
	    			newTemp -= 0.1;
	    		}	    		
	    		if( p.isOnFire()) {
	    			newTemp += 2;
	    		}
	    		if( p.isInWater()) {
	    			newTemp -= 0.2;
	    			targetTemp = (targetTemp + 50f) / 2f;
	    		}
	    		
	    		// server.overworld().isRaining()
	    		//hot biome, apply water
	    		//cold biome, just freeze
	    		
	    		//can't find way to use campfires...
	    		
	    		
	    		if( tempRate > 0) {
	    			newTemp = Math.min( newTemp + tempRate ,targetTemp);
	    		}else {
	    			newTemp = Math.max( newTemp + tempRate ,targetTemp);
	    		}
	    		
	    		temperature.put( p ,newTemp);
	    		System.out.println( "Player temperature: " + newTemp);
	    		if( newTemp > OVERHEAT) {
	    			System.out.println( "burning up!");
	    			p.hurt(DamageSource.ON_FIRE ,1);
	    		}else if( newTemp < FREEZE) {
	    			System.out.println( "freezing!");
	    			p.hurt(DamageSource.FREEZE ,1);
	    		}
	    		
	    		
	    	}
    	}    	
    }
    
    
    @SubscribeEvent
    public static void onCropGrowTick( CropGrowEvent.Pre event) {
    	//if hot or cold biome, 75% chance of failure to grow
//    	event.setResult( Result.DENY);
//    	event.setResult( Result.DEFAULT);
    }
    
    
    
    
    
    
    
//    
//    public class BiomeSurvivalInfo{
//    	
//    	int temperature;
//    	
//    	
//    	public BiomeSurvivalInfo( int temperature) {
//    		this.temperature = temperature;
//    	}
//    	
//    	
//    }
    
    
    
    
    
    
    
    
    

}
























