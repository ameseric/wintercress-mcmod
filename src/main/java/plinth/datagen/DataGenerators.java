package plinth.datagen;

import net.minecraft.data.DataGenerator;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.forge.event.lifecycle.GatherDataEvent;
import plinth.PlinthMod;





@Mod.EventBusSubscriber(modid = PlinthMod.MODID ,bus = Mod.EventBusSubscriber.Bus.MOD)
public class DataGenerators {
	
	@SubscribeEvent
	public static void gatherData( GatherDataEvent event) {
		DataGenerator generator = event.getGenerator();
		
		if( event.includeServer()) {
			generator.addProvider( new Recipes(generator));
			generator.addProvider( new LootTablesPlinth(generator));
			
			BlockTagsPlinth blockTags = new BlockTagsPlinth( generator ,event.getExistingFileHelper());
			generator.addProvider( blockTags);
			generator.addProvider( new ItemTags(generator ,blockTags ,event.getExistingFileHelper()));
		}
		
		if( event.includeClient()) {
			generator.addProvider( new BlockStates( generator ,event.getExistingFileHelper()));
			generator.addProvider( new ItemModels( generator ,event.getExistingFileHelper()));
			generator.addProvider( new PlinthLanguageProvider( generator ,"en_us"));
		}
	}

}
