package plinth.datagen;

import net.minecraft.data.DataGenerator;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import plinth.ObjectCatalog;
import plinth.PlinthMod;

public class DGItemModels extends ItemModelProvider{

	public DGItemModels( DataGenerator gen ,ExistingFileHelper helper) {
		super( gen ,PlinthMod.MODID ,helper);
	}

	@Override
	protected void registerModels() {
		withExistingParent( ObjectCatalog.TEST_ORE.get().getRegistryName().getPath()
				,modLoc("block/test_ore"));
	}
	
}
