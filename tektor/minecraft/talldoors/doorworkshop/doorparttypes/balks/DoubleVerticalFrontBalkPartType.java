package tektor.minecraft.talldoors.doorworkshop.doorparttypes.balks;

import java.util.ArrayList;

import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import tektor.minecraft.talldoors.TallDoorsBase;
import tektor.minecraft.talldoors.doorworkshop.doorparttypes.AbstractDoorPartType;
import tektor.minecraft.talldoors.doorworkshop.entity.doorparts.AbstractDoorPart;
import tektor.minecraft.talldoors.doorworkshop.entity.doorparts.balks.DoubleVerticalFrontBalkPartEntity;

public class DoubleVerticalFrontBalkPartType extends AbstractDoorPartType{

	@Override
	public void initialize() {
		depth = 0.25f;
		baseCost = new ArrayList<ItemStack>(2);
		baseCost.add(new ItemStack(Block.planks,3,0));
		baseCost.add(new ItemStack(TallDoorsBase.luiviteIngot,3,0));
		costPerSize = new ArrayList<ItemStack>(1);
		costPerSize.add(new ItemStack(Block.planks,1,0));
		entityClass = DoubleVerticalFrontBalkPartEntity.class;
		textureCount = 2;
	}

	@Override
	public AbstractDoorPart getNewEntity(World world, int posX,
			int heightPosition, int posZ, int heightSize, int orientation) {
		AbstractDoorPart part = new DoubleVerticalFrontBalkPartEntity(world, posX, heightPosition, posZ, heightSize, orientation,depth);
		return part;
	}
}
