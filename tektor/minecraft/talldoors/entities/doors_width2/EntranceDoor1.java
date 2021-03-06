package tektor.minecraft.talldoors.entities.doors_width2;

import tektor.minecraft.talldoors.TallDoorsBase;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class EntranceDoor1 extends AbstractDoorWidth2 {


	public EntranceDoor1(World par1World) {
		super(par1World);
		this.setSize(2f, 4f);
	}
	@Override
	public ItemStack getDrop() {
		ItemStack ret;
		if (left)
			ret = new ItemStack(TallDoorsBase.doorPlacer, 1, 1);
		else
			ret = new ItemStack(TallDoorsBase.doorPlacer, 1, 0);
		return ret;
	}
	
	
}
