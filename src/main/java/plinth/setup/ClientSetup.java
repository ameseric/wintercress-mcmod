package plinth.setup;

import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import plinth.ObjectCatalog;
import plinth.PlinthMod;
import plinth.client.KeyBindings;
import plinth.client.KeyInputHandler;
import plinth.client.PowergenScreen;




//@Mod.EventBusSubscriber(modid = PlinthMod.MODID ,value=Dist.CLIENT ,bus = Mod.EventBusSubscriber.Bus.MOD)
public class ClientSetup {

	public static void init( FMLClientSetupEvent event) {
		MinecraftForge.EVENT_BUS.addListener( KeyInputHandler::onKeyInput);
		KeyBindings.init();
		
		event.enqueueWork(() -> {
			MenuScreens.register( ObjectCatalog.POWERGEN_CONTAINER.get() ,PowergenScreen::new);
			ItemBlockRenderTypes.setRenderLayer( ObjectCatalog.POWERGEN.get() ,RenderType.translucent());
		});
		
	}
	
	
}
