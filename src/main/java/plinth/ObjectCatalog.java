package plinth;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.common.extensions.IForgeMenuType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import plinth.blocks.PowergenBE;
import plinth.blocks.PowergenBlock;
import plinth.blocks.PowergenContainer;
import plinth.entities.ThiefEntity;
import plinth.items.ScionItem;


public class ObjectCatalog {
	
	//Deferred Registries
	private static final DeferredRegister<Block> BLOCKS = DeferredRegister.create( ForgeRegistries.BLOCKS ,PlinthMod.MODID);
	private static final DeferredRegister<Item> ITEMS   = DeferredRegister.create( ForgeRegistries.ITEMS ,PlinthMod.MODID);
	private static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES   = DeferredRegister.create( ForgeRegistries.BLOCK_ENTITIES ,PlinthMod.MODID);
	private static final DeferredRegister<MenuType<?>> CONTAINERS   = DeferredRegister.create( ForgeRegistries.CONTAINERS ,PlinthMod.MODID);
	private static final DeferredRegister<EntityType<?>> ENTITIES   = DeferredRegister.create( ForgeRegistries.ENTITIES ,PlinthMod.MODID);
	
	//Properties
	public static final BlockBehaviour.Properties BLOCK_PROPERTIES = BlockBehaviour.Properties.of( Material.STONE).strength(2f).requiresCorrectToolForDrops();
	public static final Item.Properties ITEM_PROPERTIES = new Item.Properties().tab( PlinthMod.CREATIVE_TAB);
	
	//Blocks
	public static final RegistryObject<Block> TEST_ORE = BLOCKS.register( "test_ore" ,() -> new Block(BLOCK_PROPERTIES)); //simple block
	public static final RegistryObject<PowergenBlock> POWERGEN = BLOCKS.register( "powergen" ,PowergenBlock::new);      //custom, complex block
	
	//Entities
	public static final RegistryObject<EntityType<ThiefEntity>> THIEF = ENTITIES.register( "thief" 
			,() -> EntityType.Builder.of( ThiefEntity::new ,MobCategory.CREATURE)
			.sized( 0.6f ,1.95f)
			.clientTrackingRange(8)
			.setShouldReceiveVelocityUpdates(false)
			.build("thief"));
	
	//Items
	public static final RegistryObject<Item> TEST_ORE_ITEM = fromBlock( TEST_ORE);
	public static final RegistryObject<Item> POWERGEN_ITEM = fromBlock( POWERGEN);
	public static final RegistryObject<Item> TEST_CHUNK = ITEMS.register( "test_chunk" ,() -> new Item(ITEM_PROPERTIES));
	public static final RegistryObject<Item> TEST_INGOT = ITEMS.register( "test_ingot" ,() -> new Item(ITEM_PROPERTIES));
	public static final RegistryObject<Item> SCION = ITEMS.register( "scion" , () -> new ScionItem( ITEM_PROPERTIES.durability(500)));
	public static final RegistryObject<Item> THIEF_EGG = ITEMS.register( "thief" ,() -> new ForgeSpawnEggItem(THIEF ,0xff0000 ,0x00ff00 ,ITEM_PROPERTIES));
	
	//BlockEntities
	public static final RegistryObject<BlockEntityType<PowergenBE>> POWERGEN_BE = BLOCK_ENTITIES.register( "powergen" 
			,() -> BlockEntityType.Builder.of( PowergenBE::new ,POWERGEN.get()).build(null));
	
	
	//Menus
	public static final RegistryObject<MenuType<PowergenContainer>> POWERGEN_CONTAINER = CONTAINERS.register( "powergen" 
			,() -> IForgeMenuType.create((windowId ,inv ,data) -> new PowergenContainer( windowId ,data.readBlockPos() ,inv ,inv.player)));
	
	
	//public static final Tags.IOptionalNamedTag<Block> 
	
	
	
	
	public static void init() {
		IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
		
		BLOCKS.register(bus);
		ITEMS.register(bus);
		BLOCK_ENTITIES.register(bus);
		CONTAINERS.register(bus);
		ENTITIES.register(bus);
	}
	
	
	
	
	public static <B extends Block> RegistryObject<Item> fromBlock( RegistryObject<B> block){
		return ITEMS.register( block.getId().getPath() ,() -> new BlockItem( block.get() ,ITEM_PROPERTIES));
	}
	
	
	
	
}