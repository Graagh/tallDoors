package tektor.minecraft.talldoors.entities;

import tektor.minecraft.chalith.ChalithBase;
import tektor.minecraft.talldoors.TallDoorsBase;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public class EntranceDoor1 extends Entity {

	public int pos;
	public boolean left;

	public EntranceDoor1(World par1World) {
		super(par1World);
		this.setSize(2f, 4f);
		this.ignoreFrustumCheck = true;
		pos = 0;
		left = false;
	}

	@Override
	protected void entityInit() {

		this.dataWatcher.addObject(30, 0);
		this.dataWatcher.addObject(29, 0);

	}

	@Override
	public AxisAlignedBB getBoundingBox() {
		return this.boundingBox;
	}

	@Override
	public AxisAlignedBB getCollisionBox(Entity par1Entity) {
		return null;
	}

	public boolean canBeCollidedWith() {
		return true;
	}

	public void setOrientation(boolean setLeft) {
		left = setLeft;
		if (left)
			this.dataWatcher.updateObject(29, 1);
		else
			this.dataWatcher.updateObject(29, 0);
	}

	@Override
	public void onUpdate() {
		if (this.worldObj.isRemote) {
			pos = this.dataWatcher.getWatchableObjectInt(30);
			left = this.dataWatcher.getWatchableObjectInt(29) == 1;
		}
		setBoundsAt(posX, posY, posZ);
	}

	public int func_82329_d() {
		return 16;
	}

	public int func_82330_g() {
		return 64;
	}

	public boolean attackEntityFrom(DamageSource par1DamageSource, float par2) {
		if (this.isEntityInvulnerable()) {
			return false;
		} else {
			if (!this.isDead && !this.worldObj.isRemote) {
				this.setDead();
				this.setBeenAttacked();
				this.func_110128_b(par1DamageSource.getEntity());
			}

			return true;
		}
	}

	public void func_110128_b(Entity par1Entity) {
		if (par1Entity instanceof EntityPlayer) {
			EntityPlayer entityplayer = (EntityPlayer) par1Entity;

			if (entityplayer.capabilities.isCreativeMode) {
				return;
			}
		}

		if (left)
			this.entityDropItem(new ItemStack(TallDoorsBase.doorPlacer, 1, 1),
					0.0F);
		else
			this.entityDropItem(new ItemStack(TallDoorsBase.doorPlacer, 1, 0),
					0.0F);
	}

	public boolean func_130002_c(EntityPlayer player) {

		if (!this.worldObj.isRemote) {
			if (pos == 0)
				pos = 1;
			else
				pos = 0;
		}
		this.dataWatcher.updateObject(30, pos);
		setBoundsAt(posX, posY, posZ);
		return true;
	}

	@Override
	protected void readEntityFromNBT(NBTTagCompound nbt) {
		pos = nbt.getInteger("pos");
		this.dataWatcher.updateObject(30, pos);
		left = nbt.getBoolean("left");
		if (left)
			this.dataWatcher.updateObject(29, 1);
		else
			this.dataWatcher.updateObject(29, 0);

	}

	@Override
	protected void writeEntityToNBT(NBTTagCompound nbt) {
		nbt.setBoolean("left", left);
		nbt.setInteger("pos", pos);

	}

	public void setPosition(double par1, double par3, double par5) {
		this.posX = par1;
		this.posY = par3;
		this.posZ = par5;
		setBoundsAt(par1, par3, par5);

	}

	public void setBoundsAt(double par1, double par3, double par5) {
		float f = this.width / 2.0F;
		float f1 = this.height;
		if (!left) {
			if (pos == 0) {
				this.boundingBox.setBounds(par1, par3 - (double) this.yOffset
						+ (double) this.ySize, par5, par1 + (double) f * 2D,
						par3 - (double) this.yOffset + (double) this.ySize
								+ (double) f1, par5 + 0.1D);
			} else {
				this.boundingBox.setBounds(par1, par3 - (double) this.yOffset
						+ (double) this.ySize, par5, par1 + 0.1D, par3
						- (double) this.yOffset + (double) this.ySize
						+ (double) f1, par5 + 2D * f);
			}
		}
		else
		{
			if (pos == 0) {
				this.boundingBox.setBounds(par1-1D, par3 - (double) this.yOffset
						+ (double) this.ySize, par5, par1 + (double) f * 2D - 1D,
						par3 - (double) this.yOffset + (double) this.ySize
								+ (double) f1, par5 + 0.1D);
			} else {
				this.boundingBox.setBounds(par1+1D, par3 - (double) this.yOffset
						+ (double) this.ySize, par5, par1 +0.9D, par3
						- (double) this.yOffset + (double) this.ySize
						+ (double) f1, par5 + 2D * f);
		}
	}
	}

}
