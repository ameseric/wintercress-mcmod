package plinth.datagen;

import java.util.function.Consumer;

import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.data.recipes.SimpleCookingRecipeBuilder;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.Tags;
import plinth.ObjectCatalog;

public class Recipes extends RecipeProvider{

	public Recipes(DataGenerator generator) {
		super( generator);
	}
	
	
	@Override
	protected void buildCraftingRecipes( Consumer<FinishedRecipe> consumer) {
		
		SimpleCookingRecipeBuilder.smelting( 
				Ingredient.of( ObjectCatalog.TEST_ORE_ITEM.get())
				,ObjectCatalog.TEST_INGOT.get() 
				,1.0f 
				,100)
			.unlockedBy( "has_ore" ,has(ObjectCatalog.TEST_ORE_ITEM.get()))
			.save( consumer ,"test_ingot1");
		
		SimpleCookingRecipeBuilder.smelting( 
				Ingredient.of( ObjectCatalog.TEST_CHUNK.get())
				,ObjectCatalog.TEST_INGOT.get() 
				,0.0f 
				,100)
			.unlockedBy( "has_ore" ,has(ObjectCatalog.TEST_CHUNK.get()))
			.save( consumer ,"test_ingot2");
		
        ShapedRecipeBuilder.shaped( ObjectCatalog.POWERGEN.get())
	        .pattern("mmm")
	        .pattern("x#x")
	        .pattern("#x#")
	        .define('x', Tags.Items.DUSTS_REDSTONE)
	        .define('#', Tags.Items.INGOTS_IRON)
	        .define('m', ObjectCatalog.TEST_INGOT.get())
	        .group("plinth")
	        .unlockedBy("has_ore", InventoryChangeTrigger.TriggerInstance.hasItems( ObjectCatalog.TEST_INGOT.get()))
	        .save(consumer);
		
	}
	
	

}
