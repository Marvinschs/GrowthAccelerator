package dev.cakend.growthaccelerator.block;

import dev.cakend.growthaccelerator.GrowthAccelerator;
import dev.cakend.growthaccelerator.block.blocks.GrowthAcceleratorBlock;
import dev.cakend.growthaccelerator.item.items.ModItems;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModBlocks {

    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(GrowthAccelerator.MODID);

    public static final DeferredBlock<Block> GROWTH_ACCELERATOR_1 = registerBlock("growth_accelerator1", () -> new GrowthAcceleratorBlock(1));
    public static final DeferredBlock<Block> GROWTH_ACCELERATOR_2 = registerBlock("growth_accelerator2", () -> new GrowthAcceleratorBlock(2));
    public static final DeferredBlock<Block> GROWTH_ACCELERATOR_3 = registerBlock("growth_accelerator3", () -> new GrowthAcceleratorBlock(3));
    public static final DeferredBlock<Block> GROWTH_ACCELERATOR_4 = registerBlock("growth_accelerator4", () -> new GrowthAcceleratorBlock(4));


    private static <T extends Block> DeferredBlock<T> registerBlock(String name, Supplier<T> block) {
        DeferredBlock<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }

    private static <T extends Block> void registerBlockItem(String name, DeferredBlock<T> block) {
        ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}
