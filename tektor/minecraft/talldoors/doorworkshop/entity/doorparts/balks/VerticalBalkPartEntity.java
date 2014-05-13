package tektor.minecraft.talldoors.doorworkshop.entity.doorparts.balks;

import tektor.minecraft.talldoors.doorworkshop.entity.doorparts.AbstractDoorPart;
import net.minecraft.world.World;

public class VerticalBalkPartEntity extends AbstractDoorPart{

	public VerticalBalkPartEntity(World par1World, int posX,
			int heightPosition, int posZ, int heightSize, int orientation,
			float depth) {
		super(par1World, posX, heightPosition, posZ, heightSize, orientation, depth);
	}
	public VerticalBalkPartEntity(World world)
	{
		super(world);
	}

}
