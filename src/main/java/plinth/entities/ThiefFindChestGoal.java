package plinth.entities;

import java.util.Random;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.ai.goal.MoveToBlockGoal;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.ChestBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.items.CapabilityItemHandler;

//written by mcjty / jorrit tyberghein

public class ThiefFindChestGoal extends MoveToBlockGoal {

	   private final ThiefEntity thief;
	   private final Random random = new Random();

	   private int stealingCounter = 20;

	   public ThiefFindChestGoal(ThiefEntity mob, double pSpeedModifier) {
	      super(mob, pSpeedModifier, 16);
	      this.thief = mob;
	   }

	   /**
	    * Reset the task's internal state. Called when this task is interrupted by another one
	    */
	   public void stop() {
	      super.stop();
	      thief.setStealing(false);
	      BlockEntity be = mob.level.getBlockEntity(blockPos);
	      if (be instanceof ChestBlockEntity) {
	         mob.level.blockEvent(blockPos, be.getBlockState().getBlock(), 1, 0);
	      }
	   }

	   public void tick() {
	      super.tick();
	      if (isReachedTarget()) {
	         BlockEntity be = mob.level.getBlockEntity(blockPos);
	         if (be instanceof ChestBlockEntity chest) {
	            if (thief.isStealing()) {
	               stealingCounter--;
	               if (stealingCounter <= 0) {
	                  stealingCounter = 20;
	                  ItemStack stack = extractRandomItem(chest);
	                  if (!stack.isEmpty()) {
	                	  BlockPos pos = blockPos.above();
	                     //Tools.spawnInWorld(mob.level, blockPos.above(), stack);
	                	  if(!stack.isEmpty()) {
	                		  ItemEntity entityitem = new ItemEntity( mob.level ,pos.getX() + 0.5 ,pos.getY() + 0.5 ,pos.getZ() + 0.5 ,stack);
	                		  entityitem.setPickUpDelay( 40);
	                		  entityitem.setDeltaMovement( entityitem.getDeltaMovement().multiply(0,1,0));
	                		  mob.level.addFreshEntity(entityitem);
	                	  }
	                  }
	               }
	            } else {
	               mob.level.blockEvent(blockPos, be.getBlockState().getBlock(), 1, 1);
	               stealingCounter = 20;
	               thief.setStealing(true);
	            }
	         }
	      }
	   }

	   private ItemStack extractRandomItem(BlockEntity e) {
	      return e.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, Direction.UP).map(handler -> {
	         for (int i = 0 ; i < handler.getSlots() ; i++) {
	            ItemStack stack = handler.getStackInSlot(i);
	            if (!stack.isEmpty()) {
	               if (random.nextFloat() < .3f) {
	                  return handler.extractItem(i, 1, false);
	               }
	            }
	         }
	         return ItemStack.EMPTY;
	      }).orElse(ItemStack.EMPTY);
	   }

	   /**
	    * Return true to set given position as destination
	    */
	   protected boolean isValidTarget(LevelReader pLevel, BlockPos pPos) {
	      if (!pLevel.isEmptyBlock(pPos.above())) {
	         return false;
	      } else {
	         BlockState blockstate = pLevel.getBlockState(pPos);
	         return blockstate.is(Blocks.CHEST);
	      }
	   }
	}
