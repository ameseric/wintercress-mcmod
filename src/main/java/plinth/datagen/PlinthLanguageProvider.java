package plinth.datagen;

import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.LanguageProvider;
import plinth.ObjectCatalog;
import plinth.PlinthMod;
import plinth.blocks.PowergenBlock;
import plinth.client.KeyBindings;

public class PlinthLanguageProvider extends LanguageProvider{

	public PlinthLanguageProvider(DataGenerator gen ,String locale) {
		super(gen ,PlinthMod.MODID ,locale);
	}

	@Override
	protected void addTranslations() {
		add( "itemGroup.plinthtab" ,"Plinth");
		add( ObjectCatalog.TEST_ORE.get() ,"Test Ore");
		
		add( PowergenBlock.MESSAGE_POWERGEN ,"Power generator generating %s per tick!");
		add( PowergenBlock.SCREEN_TUTORIAL_POWERGEN ,"Power Generator");
		add( ObjectCatalog.POWERGEN.get() ,"Power Generator");
		
		add( ObjectCatalog.TEST_CHUNK.get() ,"Test Ore Chunk");
		add( ObjectCatalog.TEST_INGOT.get() ,"Test Ore Ingot");
		
		add(  KeyBindings.KEY_CATEGORIES_PLINTH ,"Plinth Keys");
		add(  KeyBindings.KEY_DASH ,"Player Dash");
		
		add( ObjectCatalog.SCION.get() ,"Test Weapon");
		
		add( ObjectCatalog.THIEF_EGG.get() ,"Thief Egg");
		add( ObjectCatalog.THIEF.get() ,"Thief");
	}
	
	

}
