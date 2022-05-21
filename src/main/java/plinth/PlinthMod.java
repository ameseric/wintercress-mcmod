package plinth;

import com.mojang.logging.LogUtils;


import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.TickEvent.ServerTickEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.server.ServerLifecycleHooks;
import plinth.disk.NBTSaveObject;
import plinth.disk.PlinthSaveData;
import plinth.network.Messages;
import plinth.setup.ClientSetup;

import org.slf4j.Logger;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;




// The value here should match an entry in the META-INF/mods.toml file
@Mod("plinth")
public class PlinthMod {
    // Directly reference a slf4j logger
    private static final Logger LOGGER = LogUtils.getLogger();
	public static final int TICKSASECOND = 20;
	public static final String MODID = "plinth";
	public static final Random RNG = new Random();
	public static final CreativeModeTab CREATIVE_TAB = new CreativeModeTab( "plinthtab") {
		@Override
		public ItemStack makeIcon() { return new ItemStack(Items.NETHER_STAR); }
	};
	
	private static int tickcount = 0;
	private static PlinthMod instance;
	
    private PlinthSaveData savedata;
    private TestSaveObj tso;
    
    public HashMap<ServerPlayer ,ArrayDeque<Vec3>> lastPlayerPos = new HashMap<>();
	

    
    
    public PlinthMod()
    {
        ObjectCatalog.init();
    	
    	// Register the setup method for modloading
        IEventBus modbus = FMLJavaModLoadingContext.get().getModEventBus();
        modbus.addListener(this::setup);

        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);
        
        DistExecutor.unsafeRunWhenOn( Dist.CLIENT ,() -> () -> modbus.addListener(ClientSetup::init));
    }

    
    
    private void setup(final FMLCommonSetupEvent event)
    {
        // some preinit code
        LOGGER.info("HELLO FROM PREINIT");
        LOGGER.info("DIRT BLOCK >> {}", Blocks.DIRT.getRegistryName());
        
        PlinthMod.instance = this; //copying functionality from previous MCForge version, maybe bad idea?
        
        Messages.register();
    }

    
    


    
    
    
//============================== Event Handlers ==========================================
    
    
    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void _onServerStarting(ServerStartingEvent event)
    {
        // Do something when the server starts
        LOGGER.info("HELLO from server starting");
        
        MinecraftServer server = ServerLifecycleHooks.getCurrentServer();
    	
        tso = new TestSaveObj();
        
        NBTSaveObject[] objectsOnDisk = { tso};
        savedata = PlinthSaveData.getInstance( server ,objectsOnDisk);
        savedata.printData();
    }    

    

    
    
    @SubscribeEvent
    public void _onServerTick( ServerTickEvent event) {
    	tickcount++;
    	
    	MinecraftServer server = ServerLifecycleHooks.getCurrentServer();
//    	server.overworld().getDataStorage();
    	
    	
		for( ServerPlayer player : server.getPlayerList().getPlayers()) {
    		if( !lastPlayerPos.containsKey(player)) {
    			lastPlayerPos.put( player ,new ArrayDeque<>());
    		}
    		
    		ArrayDeque<Vec3> posQ = lastPlayerPos.get( player);
    		
    		if( posQ.size() > 1) {
    			posQ.poll();
    		}	    		
    		posQ.add( player.getPosition(0));
			
		}
    	
    	
    	if( tickcount % 10 == 0) {
    		
    		
//    		tso.count();
//    		System.out.println( tso.getCount());
    		
    		
    		savedata.__tick( tickcount ,null);
    		
    		
    	}
    	
    }
    
    
    
//	@SubscribeEvent
//    @SideOnly(Side.CLIENT)
//	public void _onClientTick(TickEvent.ClientTickEvent event) throws Exception {
//		
//		if(event.phase.equals(Phase.END)){
//			if( config.allowPlayerDash && proxy.playerIsDashing()) {
//				PlinthMod.networkwrapper.sendToServer( new PlayerDashMessage( ));
//			}
//			
//			TEinTE.proxy.renderMuirParticles( this.currentFogDensity ,this.rawColor);
//			
//		}
//
//	}
    

    
//================= Exposed functions    
    
    
    
    public static PlinthMod instance() {
    	return PlinthMod.instance;
    }
    
    

    // You can use EventBusSubscriber to automatically subscribe events on the contained class (this is subscribing to the MOD
    // Event bus for receiving Registry Events)
    @Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class RegistryEvents
    {
        @SubscribeEvent
        public static void onBlocksRegistry(final RegistryEvent.Register<Block> blockRegistryEvent)
        {
            // Register a new block here
            LOGGER.info("HELLO from Register Block");
        }
    }
    
    
    
    
    
    
    
    
    
}
