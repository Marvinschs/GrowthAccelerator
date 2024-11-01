package dev.cakend.growthaccelerator.block.blocks;

import com.thevortex.allthemodium.registry.ModRegistry;
import dev.cakend.growthaccelerator.block.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;

import java.util.List;

public class GrowthAcceleratorBlock extends Block {

    private Integer tier;

    private Integer bonusTicks;

    public GrowthAcceleratorBlock(Integer tier) {
        this(BlockBehaviour.Properties.of().strength(3f).requiresCorrectToolForDrops().sound(SoundType.STONE).lightLevel((obj) -> 15));
        this.tier = tier;
        this.bonusTicks = this.tier * 3;
    }

    public GrowthAcceleratorBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected void onPlace(BlockState state, Level level, BlockPos pos, BlockState oldState, boolean movedByPiston) {
        level.scheduleTick(pos, this, 1);
    }

    @Override
    protected void tick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        BlockPos.betweenClosedStream(pos.offset(-4, 0, -4), pos.offset(4, 0, 4))
            .filter(aoePos -> level.getBlockState(aoePos).getBlock() instanceof BonemealableBlock)
            .forEach(aoePos -> {
                for (int i = 0; i < this.bonusTicks; i++) {
                    level.getBlockState(aoePos).randomTick(level, aoePos, random);
                }
            });

        level.scheduleTick(pos, this, 1);
    }

    @Override
    public void appendHoverText(ItemStack stack, Item.TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
        tooltipComponents.add(Component.literal("Grows the plants in a 4x4 Area around it faster."));
        tooltipComponents.add(Component.literal("Adds: §c" + bonusTicks.toString() + " §rbonus ticks per block"));
        super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);
    }

    @Override
    protected ItemInteractionResult useItemOn(ItemStack stack, BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitResult) {
        if (this.tier != 4) {
            if (!level.isClientSide()) {
                if (this.tier == 1 && stack.is(Items.EMERALD_BLOCK) && stack.getCount() >= 8) {
                    stack.setCount(stack.getCount() - 8);
                    level.setBlockAndUpdate(pos, ModBlocks.GROWTH_ACCELERATOR_2.get().defaultBlockState());
                    return ItemInteractionResult.SUCCESS;
                } else if (this.tier == 2 && stack.is(Items.NETHERITE_BLOCK) && stack.getCount() >= 8) {
                    stack.setCount(stack.getCount() - 8);
                    level.setBlockAndUpdate(pos, ModBlocks.GROWTH_ACCELERATOR_3.get().defaultBlockState());
                    return ItemInteractionResult.SUCCESS;
                } else if (this.tier == 3 && stack.is(ModRegistry.ALLTHEMODIUM_BLOCK.get().asItem()) && stack.getCount() >= 8) {
                    stack.setCount(stack.getCount() - 8);
                    level.setBlockAndUpdate(pos, ModBlocks.GROWTH_ACCELERATOR_4.get().defaultBlockState());
                    return ItemInteractionResult.SUCCESS;
                }
            }
        }
        return super.useItemOn(stack, state, level, pos, player, hand, hitResult);
    }
}
