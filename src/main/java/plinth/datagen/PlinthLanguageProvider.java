package plinth.datagen;

import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.LanguageProvider;
import plinth.ObjectCatalog;
import plinth.PlinthMod;

public class PlinthLanguageProvider extends LanguageProvider{

	public PlinthLanguageProvider(DataGenerator gen ,String locale) {
		super(gen ,PlinthMod.MODID ,locale);
	}

	@Override
	protected void addTranslations() {
		add( "itemGroup.plinthtab" ,"Plinth");
		add( ObjectCatalog.TEST_ORE.get() ,"Test Ore");
	}
	
	

}
