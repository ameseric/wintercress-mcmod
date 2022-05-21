package plinth.setup;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import plinth.PlinthMod;
import plinth.client.KeyBindings;
import plinth.client.KeyInputHandler;




//@Mod.EventBusSubscriber(modid = PlinthMod.MODID ,value=Dist.CLIENT ,bus = Mod.EventBusSubscriber.Bus.MOD)
public class ClientSetup {

	public static void init( FMLClientSetupEvent event) {
		MinecraftForge.EVENT_BUS.addListener( KeyInputHandler::onKeyInput);
		KeyBindings.init();
	}
	
	
}
