package plinth.datagen;

import net.minecraft.data.DataGenerator;
import plinth.ObjectCatalog;





public class LootTablesPlinth extends BaseLootTableProvider{

	public LootTablesPlinth(DataGenerator pGenerator) {
		super(pGenerator);
	}

	
	@Override
	protected void addTables() {
		lootTables.put( 
				ObjectCatalog.TEST_ORE.get() 
				,createSilkTouchTable( 
						"test_ore" 
						,ObjectCatalog.TEST_ORE.get() 
						,ObjectCatalog.TEST_CHUNK.get() 
						,1 
						,3 ));
		
		lootTables.put( 
				ObjectCatalog.POWERGEN.get() 
				,createStandardTable( 
						"powergen" 
						,ObjectCatalog.POWERGEN.get() 
						,ObjectCatalog.POWERGEN_BE.get()));
	}
	
	
	
}
