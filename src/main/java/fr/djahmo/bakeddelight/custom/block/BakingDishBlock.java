package fr.djahmo.bakeddelight.custom.block;

import fr.djahmo.bakeddelight.custom.entity.BakingDishEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import fr.djahmo.bakeddelight.registry.ModBlocks;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class BakingDishBlock extends BaseEntityBlock {
    public static final DirectionProperty FACING;
    public static final IntegerProperty SLICE;
    public static final EnumProperty<RecipeType> RECIPE;

    public enum RecipeType implements StringRepresentable {
        LASAGNA, GRATIN;
        public @NotNull String getSerializedName() {
            return switch (this) {
                case LASAGNA -> "lasagna";
                case GRATIN -> "gratin";
            };
        }
    }
    public static final int maxSlice = 6;


    public BakingDishBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.getStateDefinition().any().setValue(SLICE, 0));
    }

    public static BlockState getBakingDish(BlockState state) {
        return ModBlocks.BACKING_DISH.get().defaultBlockState().setValue(FACING, state.getValue(FACING));
    }

    @Override
    public BlockState updateShape(BlockState stateIn, Direction facing, BlockState facingState, LevelAccessor level, BlockPos currentPos, BlockPos facingPos) {
        return facing == Direction.DOWN && !stateIn.canSurvive(level, currentPos) ? Blocks.AIR.defaultBlockState() : super.updateShape(stateIn, facing, facingState, level, currentPos, facingPos);
    }
    @Override
    public int getAnalogOutputSignal(BlockState blockState, Level level, BlockPos pos) {
        return maxSlice - blockState.getValue(SLICE);
    }
    @Override
    public boolean hasAnalogOutputSignal(BlockState state) {
        return true;
    }

    @Override
    public VoxelShape getShape(BlockState blockState, BlockGetter blockGetter, BlockPos blockPos, CollisionContext collisionContext) {
        switch (blockState.getValue(FACING)) {
            case NORTH, SOUTH -> {
                return Block.box(1,0,3,15,7.5,13);
            }
            case EAST, WEST -> {
                return Block.box(3,0,1,13,7.5,15);
            }
        }
        return null;
    }

    @Override
    public BlockState getStateForPlacement( BlockPlaceContext pContext) {
        return this.defaultBlockState().setValue(FACING, pContext.getHorizontalDirection().getOpposite());
    }

    @Override
    public BlockState rotate(BlockState pState, Rotation pRotation) {
        return pState.setValue(FACING, pRotation.rotate(pState.getValue(FACING)));
    }

    @Override
    public BlockState mirror( BlockState pState, Mirror pMirror) {
        return pState.rotate(pMirror.getRotation(pState.getValue(FACING)));
    }
    public boolean canSurvive(BlockState state, LevelReader level, BlockPos pos) {
        return level.getBlockState(pos.below()).getMaterial().isSolid();
    }
    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING, SLICE);
    }

    static {
        FACING = BlockStateProperties.HORIZONTAL_FACING;
        SLICE = IntegerProperty.create("slice", 0, 5);
        RECIPE = EnumProperty.create("recipe", RecipeType.class);
    }

    /* BLOCK ENTITY */
    @Override
    public RenderShape getRenderShape(BlockState state) {
        return RenderShape.MODEL;
    }
    @Override
    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
        ItemStack heldStack = player.getItemInHand(hand);
        if(!level.isClientSide) {
            BlockEntity entity = level.getBlockEntity(pos);
            if(entity instanceof BakingDishEntity) {
                return addSlice(state, level, pos, heldStack);
            } else {
                throw new IllegalStateException("BakingDishEntity is missing");
            }
        }
        return InteractionResult.PASS;
    }

    protected InteractionResult addSlice(BlockState state, Level level, BlockPos pos, ItemStack heldStack) {
        int slice = state.getValue(SLICE);
        if(slice < maxSlice-1) {
            level.setBlock(pos, state.setValue(SLICE, slice+1), Block.UPDATE_ALL);
            return InteractionResult.SUCCESS;
        }
        else {
            level.setBlock(pos, ModBlocks.UNCOOKED_LASAGNA_DISH.get().defaultBlockState().setValue(FACING, state.getValue(FACING)), Block.UPDATE_ALL);
        }
        return InteractionResult.PASS;
    }
    @Override
    public void onRemove(BlockState state, Level level, BlockPos pos, BlockState newState, boolean isMoving) {
        if(state.getBlock() != newState.getBlock()){
            BlockEntity blockEntity = level.getBlockEntity(pos);
            if(blockEntity instanceof BakingDishEntity){
                ((BakingDishEntity) blockEntity).drops();
            }
        }
        super.onRemove(state, level, pos, newState, isMoving);
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new BakingDishEntity(pos, state);
    }
}
