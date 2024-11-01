package dev.cakend.growthaccelerator.item;

import dev.cakend.growthaccelerator.GrowthAccelerator;
import dev.cakend.growthaccelerator.block.ModBlocks;
import dev.cakend.growthaccelerator.item.items.ModItems;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModCreativeModeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TAB = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, GrowthAccelerator.MODID);

    public static final Supplier<CreativeModeTab> Growth_Accelerator_Tab = CREATIVE_MODE_TAB.register("growth_accelerator_tab",
        () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModItems.TEST_ITEM.get()))
            .title(Component.translatable("creativetab.growthaccelerator.growthaccelerator_items"))
            .displayItems((itemDisplayParameters, output) -> {
                output.accept(ModItems.TEST_ITEM);
                output.accept(ModBlocks.GROWTH_ACCELERATOR_1);
                output.accept(ModBlocks.GROWTH_ACCELERATOR_2);
                output.accept(ModBlocks.GROWTH_ACCELERATOR_3);
                output.accept(ModBlocks.GROWTH_ACCELERATOR_4);
            }).build());

    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TAB.register(eventBus);
    }

}
