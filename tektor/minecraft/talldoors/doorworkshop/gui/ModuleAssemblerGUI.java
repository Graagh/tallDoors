package tektor.minecraft.talldoors.doorworkshop.gui;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;

import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.network.packet.Packet250CustomPayload;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import tektor.minecraft.talldoors.doorworkshop.entity.ModuleAssemblerTileEntity;
import cpw.mods.fml.common.network.PacketDispatcher;

public class ModuleAssemblerGUI extends GuiContainer {

	private ModuleAssemblerTileEntity te;
	private GuiTextField itemNameField;
	private GuiTextField itemNameField2;
	private int x, y = 0;
	private int dSizeX, dSizeY;
	private EntityPlayer play;
	
	public ModuleAssemblerGUI(EntityPlayer player,
			InventoryPlayer inventoryPlayer, ModuleAssemblerTileEntity e) {
		super(new ModuleAssemblerContainer(inventoryPlayer, e));
		te = e;
		xSize = 328;
		ySize = 220;
		dSizeX = 1;
		dSizeY = 1;
		play = player;
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float var1, int var2,
			int var3) {
		this.mc.renderEngine.bindTexture(new ResourceLocation("talldoors",
				"textures/gui/moduleAssemblerGUI.png"));
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		int x = (width - xSize) / 2;
		int y = (height - ySize) / 2;
		this.drawTexturedModalRect(x, y, 0, 0, 256, 189);
		this.drawTexturedModalRect(x - 36, y + 3, 0, 189, 36, 67);
		this.drawTexturedModalRect(x + 256, y + 3, 72, 189, 36, 67);
		this.drawTexturedModalRect(x - 36, y + 70, 36, 189, 36, 67);
		this.drawTexturedModalRect(x + 256, y + 70, 108, 189, 36, 49);
		this.drawTexturedModalRect(x - 56, y + 120, 144, 189, 56, 67);
		this.drawTexturedModalRect(x + 256, y + 120, 200, 189, 56, 67);

	}

	@Override
	protected void drawGuiContainerForegroundLayer(int param1, int param2) {
		fontRenderer.drawString("Width: " + this.dSizeX, -51, 123, -1);
		fontRenderer.drawString("Height: " + this.dSizeY, -51, 152, -1);
		int negX = (int) (Math.ceil(dSizeX / 2D)) - 1;
		int posX = (int) (Math.floor(dSizeX / 2D));
		int negY = (int) (Math.ceil(dSizeY / 2D) - 1);
		int posY = (int) (Math.floor(dSizeY / 2D));
		if (posX < (x + 7) && posX > (x - 8)) {
			this.drawVerticalLine(137 + (posX - x) * 18, 4, 150, -20000000);
		}
		if (-negX > (x - 7) && -negX < (x + 8)) {
			this.drawVerticalLine(118 - (negX + x) * 18, 4, 150, -20000000);
		}
		if (posY < (y + 5) && posY > (y - 5)) {
			this.drawHorizontalLine(11, 244, 77 + (posY - y) * 18, -20000000);
		}
		if (-negY > (y - 4) && -negY < (y + 6)) {
			this.drawHorizontalLine(11, 244, 58 - (negY + y) * 18, -20000000);
		}
	}

	@Override
	public void initGui() {
		super.initGui();

		int i = (this.width - this.xSize) / 2;
		int j = (this.height - this.ySize) / 2;
		this.buttonList.clear();
		this.buttonList.add(new GuiButton(0, i + 260, j + 125, 20, 20, "L"));
		this.buttonList.add(new GuiButton(10,i+288, j+125,20,20,"R"));
		this.buttonList.add(new GuiButton(1, i + 300, j + 70, 20, 20, "\u2190"));
		this.buttonList.add(new GuiButton(2, i + 320, j + 60, 20, 20, "\u2191"));
		this.buttonList.add(new GuiButton(3, i + 320, j + 80, 20, 20, "\u2193"));
		this.buttonList.add(new GuiButton(4, i + 340, j + 70, 20, 20, "\u2192"));
		this.buttonList
				.add(new GuiButton(5, i + 345, j + 28, 40, 20, "Center"));
		this.buttonList.add(new GuiButton(6, i - 53, j + 131, 20, 20, "-"));
		this.buttonList.add(new GuiButton(7, i - 23, j + 131, 20, 20, "+"));
		this.buttonList.add(new GuiButton(8, i - 53, j + 160, 20, 20, "-"));
		this.buttonList.add(new GuiButton(9, i - 23, j + 160, 20, 20, "+"));

		this.itemNameField = new GuiTextField(this.fontRenderer, i + 300,
				j + 20, 40, 15);
		this.itemNameField.setTextColor(-1);
		this.itemNameField.setDisabledTextColour(-1);
		this.itemNameField.setEnableBackgroundDrawing(true);
		this.itemNameField.setMaxStringLength(3);
		this.itemNameField.setText("" + y);

		this.itemNameField2 = new GuiTextField(this.fontRenderer, i + 300,
				j +40, 40, 15);
		this.itemNameField2.setTextColor(-1);
		this.itemNameField2.setDisabledTextColour(-1);
		this.itemNameField2.setEnableBackgroundDrawing(true);
		this.itemNameField2.setMaxStringLength(3);
		this.itemNameField2.setText("" + x);

		sendDirectionPacket();

	}

	private void sendDirectionPacket() {
		ByteArrayOutputStream bos = new ByteArrayOutputStream(8);
		DataOutputStream outputStream = new DataOutputStream(bos);
		try {
			outputStream.writeUTF("direction");

			outputStream.writeInt(te.xCoord);
			outputStream.writeInt(te.yCoord);
			outputStream.writeInt(te.zCoord);
			
			outputStream.writeInt(this.x);
			outputStream.writeInt(this.y);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		Packet250CustomPayload packet = new Packet250CustomPayload();
		packet.channel = "TallDoors";
		packet.data = bos.toByteArray();
		packet.length = bos.size();
		if (!te.worldObj.isRemote) {
		} else if (te.worldObj.isRemote) {
			EntityClientPlayerMP player2 = (EntityClientPlayerMP) this.play;
			PacketDispatcher.sendPacketToServer(packet);
		}
	}

	@Override
	public void drawScreen(int par1, int par2, float par3) {
		super.drawScreen(par1, par2, par3);
		GL11.glDisable(GL11.GL_LIGHTING);
		this.itemNameField.drawTextBox();
		this.itemNameField2.drawTextBox();
	}

	protected void actionPerformed(GuiButton button) {
		switch (button.id) {
		case 0: {
			sendAssemblerPacket(true);
			
			break;
		}
		case 1: {
			x--;
			sendDirectionPacket();
			this.itemNameField2.setText("" + x);
			break;
		}
		case 2: {
			y--;
			sendDirectionPacket();
			this.itemNameField.setText("" + y);
			break;
		}
		case 3: {
			y++;
			sendDirectionPacket();
			this.itemNameField.setText("" + y);
			break;
		}
		case 4: {
			x++;
			sendDirectionPacket();
			this.itemNameField2.setText("" + x);
			break;
		}
		case 5: {
			x = 0;
			y = 0;
			sendDirectionPacket();
			this.itemNameField.setText("" + y);
			this.itemNameField2.setText("" + x);
			break;
		}
		case 6: {
			if (this.dSizeX > 1) {
				this.dSizeX--;
			}
			break;
		}
		case 7: {
			this.dSizeX++;
			break;
		}
		case 8: {
			if (this.dSizeY > 1) {
				this.dSizeY--;
			}
			break;
		}
		case 9: {
			this.dSizeY++;
			break;
		}
		case 10: {
			sendAssemblerPacket(false);
			break;
		}
		}
	}

	private void sendAssemblerPacket(boolean left) {
		ByteArrayOutputStream bos = new ByteArrayOutputStream(8);
		DataOutputStream outputStream = new DataOutputStream(bos);
		try {
			outputStream.writeUTF("assembler");
			outputStream.writeInt(te.xCoord);
			outputStream.writeInt(te.yCoord);
			outputStream.writeInt(te.zCoord);
			outputStream.writeInt(this.dSizeX);
			outputStream.writeInt(this.dSizeY);
			outputStream.writeBoolean(left);
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		Packet250CustomPayload packet = new Packet250CustomPayload();
		packet.channel = "TallDoors";
		packet.data = bos.toByteArray();
		packet.length = bos.size();
		if (!te.worldObj.isRemote) {
		} else if (te.worldObj.isRemote) {
			EntityClientPlayerMP player2 = (EntityClientPlayerMP) this.play;
			PacketDispatcher.sendPacketToServer(packet);
		}
	}

}
