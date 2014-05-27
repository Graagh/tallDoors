package tektor.minecraft.talldoors.doorworkshop.network;

import cpw.mods.fml.common.network.ByteBufUtils;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import tektor.minecraft.talldoors.doorworkshop.entity.ModuleAssemblerTileEntity;
import tektor.minecraft.talldoors.packet.AbstractPacket;

public class ModuleAssemblerPacket extends AbstractPacket{

	int corX,corY,corZ;
	int dSizeX, dSizeY;
	boolean left;
	String doortype;
	
	public ModuleAssemblerPacket()
	{
		
	}
	
	public ModuleAssemblerPacket(int corX, int corY, int corZ, int dX, int dY,boolean left, String doortype)
	{
		this.corX = corX;
		this.corY = corY;
		this.corZ = corZ;
		this.dSizeX = dX;
		this.dSizeY = dY;
		this.left = left;
		this.doortype = doortype;
	}
	
	@Override
	public void encodeInto(ChannelHandlerContext ctx, ByteBuf buffer) {
		buffer.writeInt(corX);
		buffer.writeInt(corY);
		buffer.writeInt(corZ);
		buffer.writeInt(dSizeX);
		buffer.writeInt(dSizeY);
		buffer.writeBoolean(left);
		ByteBufUtils.writeUTF8String(buffer, doortype);
		
	}

	@Override
	public void decodeInto(ChannelHandlerContext ctx, ByteBuf buffer) {
		corX = buffer.readInt();
		corY = buffer.readInt();
		corZ = buffer.readInt();
		dSizeX = buffer.readInt();
		dSizeY = buffer.readInt();
		left = buffer.readBoolean();
		doortype = ByteBufUtils.readUTF8String(buffer);
	}

	@Override
	public void handleClientSide(EntityPlayer player) {
		
	}

	@Override
	public void handleServerSide(EntityPlayer player) {
		EntityPlayerMP play = (EntityPlayerMP) player;
		if (play.worldObj.getTileEntity(corX, corY, corZ) instanceof ModuleAssemblerTileEntity) {
			ModuleAssemblerTileEntity ent = (ModuleAssemblerTileEntity) play.worldObj
					.getTileEntity(corX, corY, corZ);
			ent.produce(dSizeX,dSizeY,left,doortype);
		}
	}

}
