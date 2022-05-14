package plinth;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;


public class ObjectCatalog {
	
	
	private static final DeferredRegister<Block> BLOCKS = DeferredRegister.create( ForgeRegistries.BLOCKS ,PlinthMod.MODID);
	private static final DeferredRegister<Item> ITEMS   = DeferredRegister.create( ForgeRegistries.ITEMS ,PlinthMod.MODID);
	
	
	public static final BlockBehaviour.Properties ORE_PROPERTIES = BlockBehaviour.Properties.of( Material.STONE);
	public static final Item.Properties ITEM_PROPERTIES = new Item.Properties().tab( PlinthMod.CREATIVE_TAB);
	
	public static final RegistryObject<Block> TEST_ORE = BLOCKS.register( "test_ore" ,() -> new Block(ORE_PROPERTIES));
	public static final RegistryObject<Item> TEST_ORE_ITEM = fromBlock( TEST_ORE);
	
	
	//private ObjectCatalog() {}
	
	
	public static void init() {
		IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
		
		BLOCKS.register(bus);
		ITEMS.register(bus);
	}
	
	
	public static <B extends Block> RegistryObject<Item> fromBlock( RegistryObject<B> block){
		return ITEMS.register( block.getId().getPath() ,() -> new BlockItem( block.get() ,ITEM_PROPERTIES));
	}
	
	
	
	
}