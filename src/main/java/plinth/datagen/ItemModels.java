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
		withExistingParent( ObjectCatalog.TEST_ORE.get().getRegistryName().getPath() ,modLoc("block/test_ore"));		
		withExistingParent( ObjectCatalog.POWERGEN_ITEM.get().getRegistryName().getPath() ,modLoc( "block/powergen/main"));
		withExistingParent( ObjectCatalog.SCION.get().getRegistryName().getPath() ,modLoc( "block/powergen/main"));
		
		withExistingParent( ObjectCatalog.THIEF_EGG.get().getRegistryName().getPath() ,mcLoc("item/template_spawn_egg"));
		
		singleTexture( ObjectCatalog.TEST_INGOT.get().getRegistryName().getPath()
				,mcLoc( "item/generated")
				,"layer0" ,modLoc( "item/test_ingot"));
		
		singleTexture( ObjectCatalog.TEST_CHUNK.get().getRegistryName().getPath()
				,mcLoc( "item/generated")
				,"layer0" ,modLoc( "item/test_chunk"));
		
	}
	
}
