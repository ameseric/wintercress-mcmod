package plinth.block;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;


public class BlockAsh extends Block{// extends BlockFalling{
	
	public BlockAsh() {
		super( BlockBehaviour.Properties.of( Material.SAND));
//		setRegistryName( "dead_ash");
	}

}



/**
class MaterialFlesh extends Material{

	public MaterialFlesh(MapColor color) {
		super(color);
		this.setRequiresTool();
	}
	
}**/