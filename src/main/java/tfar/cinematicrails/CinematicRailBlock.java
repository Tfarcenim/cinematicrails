package tfar.cinematicrails;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.RailBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.Items;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;

public class CinematicRailBlock extends RailBlock {

	public static final BooleanProperty DIAGONAL = BooleanProperty.create("diagonal");

	protected CinematicRailBlock(Properties builder) {
		super(builder);
		setDefaultState(getDefaultState().with(DIAGONAL,false));
	}

	@Override
	public ActionResultType onBlockActivated(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult p_225533_6_) {
		if (player.getHeldItem(handIn).getItem() == Items.STICK){
			if (!world.isRemote) {
				world.setBlockState(pos,state.with(DIAGONAL,!state.get(DIAGONAL)));
			}
			return ActionResultType.SUCCESS;
		}
		return super.onBlockActivated(state, world, pos, player, handIn, p_225533_6_);
	}

	@Override
	public BlockState getStateForPlacement(BlockItemUseContext context) {
		return super.getStateForPlacement(context);
	}

	@Override
	public boolean isFlexibleRail(BlockState state, IBlockReader world, BlockPos pos) {
		return true;
	}

	@Override
	protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
		super.fillStateContainer(builder);
		builder.add(DIAGONAL);
	}
}
