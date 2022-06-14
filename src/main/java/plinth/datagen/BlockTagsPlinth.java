package plinth.datagen;

import org.jetbrains.annotations.Nullable;

import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraft.tags.BlockTags;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.ExistingFileHelper;
import plinth.ObjectCatalog;
import plinth.PlinthMod;



public class BlockTagsPlinth extends BlockTagsProvider{

	public BlockTagsPlinth(DataGenerator pGenerator, @Nullable ExistingFileHelper existingFileHelper) {
		super( pGenerator ,PlinthMod.MODID ,existingFileHelper );
	}
	
	
	@Override
	protected void addTags() {
		
		tag( BlockTags.MINEABLE_WITH_PICKAXE)
			.add( ObjectCatalog.TEST_ORE.get())
			.add( ObjectCatalog.POWERGEN.get());
		
		tag( BlockTags.NEEDS_IRON_TOOL)
			.add( ObjectCatalog.TEST_ORE.get())
			.add( ObjectCatalog.POWERGEN.get());
		
		tag( Tags.Blocks.ORES)
			.add( ObjectCatalog.TEST_ORE.get());
		
	}
	
	
	@Override
	public String getName() { return "Plinth Tags"; }
	

}
