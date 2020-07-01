package tfar.cinematicrails.mixin;

import net.minecraft.block.BlockState;
import net.minecraft.block.RailBlock;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.item.minecart.AbstractMinecartEntity;
import net.minecraft.entity.item.minecart.MinecartEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.state.properties.RailShape;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkDirection;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import tfar.cinematicrails.*;
import tfar.cinematicrails.network.PacketHandler;
import tfar.cinematicrails.network.S2CSyncRotation;

@Mixin(AbstractMinecartEntity.class)
public abstract class AbstractMinecartEntityMixin extends Entity {

	public BlockPos oldPos;

	public AbstractMinecartEntityMixin(EntityType<?> entityTypeIn, World worldIn) {
		super(entityTypeIn, worldIn);
	}

	@Inject(method = "tick", at = @At("RETURN"))
	private void rotatePlayer(CallbackInfo ci) {
		if ((Object) this instanceof MinecartEntity && !world.isRemote && !getPassengers().isEmpty()) {
			BlockPos current = getPosition();

			if (!current.equals(oldPos) && oldPos != null) {
				BlockState below = world.getBlockState(current);
				BlockState oldState = world.getBlockState(oldPos);
				if (below.getBlock() == ExampleMod.diagonal_rail && oldState.getBlock() == ExampleMod.diagonal_rail) {

						RailShape oldShape = oldState.get(RailBlock.SHAPE);
						boolean oldDiagonal = oldState.get(CinematicRailBlock.DIAGONAL);

						RailShape shape = below.get(RailBlock.SHAPE);
						boolean diagonal = below.get(CinematicRailBlock.DIAGONAL);

						Vec3d motionDir = getMotion().normalize();

						if (!motionDir.equals(Vec3d.ZERO)) {

							Facing facing = Facing.getFacing(motionDir);

							final int rotate = Util.getRotate(oldShape, oldDiagonal, shape, diagonal,facing);

							this.getPassengers().stream().filter(Duck.class::isInstance).findFirst().ifPresent(duck ->
											PacketHandler.INSTANCE.sendTo(new S2CSyncRotation(rotate), ((ServerPlayerEntity) duck).connection.getNetworkManager(),
															NetworkDirection.PLAY_TO_CLIENT));
						}
					}
				}
			oldPos = current;
		}
	}
}
