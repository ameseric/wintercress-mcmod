package plinth.datagen;

import org.jetbrains.annotations.Nullable;

import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.ExistingFileHelper;
import plinth.ObjectCatalog;
import plinth.PlinthMod;

public class ItemTags extends ItemTagsProvider{

	public ItemTags(DataGenerator generator, BlockTagsProvider blockTags ,ExistingFileHelper helper) {
		super( generator ,blockTags ,PlinthMod.MODID ,helper);
	}


	@Override
	protected void addTags() {
		tag( Tags.Items.ORES)
			.add( ObjectCatalog.TEST_ORE_ITEM.get());
		
		tag( Tags.Items.INGOTS)
			.add( ObjectCatalog.TEST_INGOT.get());
		
		
		
	}
	
	
	@Override
	public String getName() { return "Plinth Tags"; }
	
	
}
