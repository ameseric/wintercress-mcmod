package plinth.datagen;

import net.minecraft.data.DataGenerator;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import plinth.ObjectCatalog;
import plinth.PlinthMod;

public class BlockStates extends BlockStateProvider{

	public BlockStates( DataGenerator gen ,ExistingFileHelper helper) {
		super( gen ,PlinthMod.MODID ,helper);
	}

	@Override
	protected void registerStatesAndModels() {
		simpleBlock( ObjectCatalog.TEST_ORE.get());		
	}
	
}
