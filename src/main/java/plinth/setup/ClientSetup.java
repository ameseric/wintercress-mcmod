package plinth.setup;

import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.texture.TextureAtlas;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.client.model.ModelLoaderRegistry;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import plinth.ObjectCatalog;
import plinth.PlinthMod;
import plinth.client.KeyBindings;
import plinth.client.KeyInputHandler;
import plinth.client.PowergenScreen;
import plinth.entities.ThiefModel;
import plinth.entities.ThiefRenderer;




@Mod.EventBusSubscriber(modid = PlinthMod.MODID ,value=Dist.CLIENT ,bus = Mod.EventBusSubscriber.Bus.MOD)
public class ClientSetup {

	public static void init( FMLClientSetupEvent event) {
		MinecraftForge.EVENT_BUS.addListener( KeyInputHandler::onKeyInput);
		KeyBindings.init();
		
		event.enqueueWork(() -> {
			MenuScreens.register( ObjectCatalog.POWERGEN_CONTAINER.get() ,PowergenScreen::new);
			ItemBlockRenderTypes.setRenderLayer( ObjectCatalog.POWERGEN.get() ,RenderType.translucent());
		});
		
	}
	
	
//	@SubscribeEvent
//	public static void onModelRegistryEvent( ModelRegistryEvent event) {
//		ModelLoaderRegistry.registerLoader( GeneratorModelLoader.GENERATOR_LOADER ,new GeneratorModelLoader());
//	}
	
	
	@SubscribeEvent
	public static void onRegisterLayers( EntityRenderersEvent.RegisterLayerDefinitions event) {
		event.registerLayerDefinition( ThiefModel.THIEF_LAYER ,ThiefModel::createBodyLayer);
	}
	
	
	@SubscribeEvent
	public static void onRegisterRenderer( EntityRenderersEvent.RegisterRenderers event) {
		event.registerEntityRenderer( ObjectCatalog.THIEF.get() ,ThiefRenderer::new);
	}
	
	
//	@SubscribeEvent
//	public static void onTextureStitch( TextureStitchEvent.Pre event) {
//		if( !event.getAtlas().location().equals(TextureAtlas.LOCATION_BLOCKS)) {
//			return;
//		}
//		event.addSprite( PowergenRenderer.HALO);
//	}
	
	
}
