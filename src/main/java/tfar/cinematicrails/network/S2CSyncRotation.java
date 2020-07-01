package tfar.cinematicrails.network;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.container.Container;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.network.NetworkEvent;
import tfar.cinematicrails.Duck;

import java.util.function.Supplier;

public class S2CSyncRotation {

  private int rotate = 0;

  public S2CSyncRotation() {}

  public S2CSyncRotation(int rotate) {
    this.rotate = rotate;
  }

  public S2CSyncRotation(PacketBuffer buf) {
    this.rotate = buf.readInt();
  }

  public void encode(PacketBuffer buf) {
    buf.writeInt(this.rotate);
  }

  public void handle(Supplier<NetworkEvent.Context> ctx) {
    PlayerEntity player = DistExecutor.callWhenOn(Dist.CLIENT, () -> () -> Minecraft.getInstance().player);
    if (player == null) return;
    ctx.get().enqueueWork(() -> ((Duck)player).addRotate(rotate));
    ctx.get().setPacketHandled(true);
  }
}