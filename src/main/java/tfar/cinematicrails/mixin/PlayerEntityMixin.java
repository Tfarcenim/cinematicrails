package tfar.cinematicrails.mixin;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import tfar.cinematicrails.Duck;

@Mixin(PlayerEntity.class)
public abstract class PlayerEntityMixin extends LivingEntity implements Duck {

	public float rotate;

	protected PlayerEntityMixin(EntityType<? extends LivingEntity> type, World worldIn) {
		super(type, worldIn);
	}

	@Override
	public float getRotate() {
		return rotate;
	}

	@Override
	public void setRotate(float rotate) {
		this.rotate = rotate;
	}

	@Override
	public void addRotate(float rotate) {
		this.rotate += rotate;
	}

	@Inject(method = "tick",at = @At("RETURN"))
	private void smooth(CallbackInfo ci) {
		if (world.isRemote) {
			if (Math.abs(rotate) > 0) {
				if (Math.abs(rotate) <= 18) {
					this.rotationYaw += rotate;
					rotate = 0;
				} else {
					if (rotate > 0) {
						this.rotationYaw += 18;
						this.rotate -= 18;
					} else {
						this.rotationYaw -= 18;
						this.rotate += 18;
					}
				}
			}
		}
	}
}
