package tektor.minecraft.talldoors.doorworkshop.entity.doorparts;

import net.minecraft.world.World;

public class FBVerticalBalkPartEntity extends AbstractDoorPart {

	public FBVerticalBalkPartEntity(World par1World, int posX,
			int heightPosition, int posZ, int heightSize, int orientation,
			float depth) {
		super(par1World, posX, heightPosition, posZ, heightSize, orientation, depth);
	}
	public FBVerticalBalkPartEntity(World world)
	{
		super(world);
	}

}