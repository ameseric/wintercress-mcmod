package plinth.datagen;

import net.minecraft.data.DataGenerator;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import plinth.ObjectCatalog;
import plinth.PlinthMod;

public class ItemModels extends ItemModelProvider{

	public ItemModels( DataGenerator gen ,ExistingFileHelper helper) {
		super( gen ,PlinthMod.MODID ,helper);
	}

	@Override
	protected void registerModels() {
		withExistingParent( ObjectCatalog.TEST_ORE.get().getRegistryName().getPath()
				,modLoc("block/test_ore"));
		
		singleTexture( ObjectCatalog.TEST_INGOT.get().getRegistryName().getPath()
				,mcLoc( "item/generated")
				,"layer0" ,modLoc( "item/test_ingot"));
		
		singleTexture( ObjectCatalog.TEST_CHUNK.get().getRegistryName().getPath()
				,mcLoc( "item/generated")
				,"layer0" ,modLoc( "item/test_chunk"));
		
	}
	
}
