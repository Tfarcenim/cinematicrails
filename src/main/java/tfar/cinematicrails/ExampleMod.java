package tfar.cinematicrails;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.state.properties.RailShape;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.IForgeRegistryEntry;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import tfar.cinematicrails.network.PacketHandler;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(ExampleMod.MODID)
public class ExampleMod {
	// Directly reference a log4j logger.

	public static final String MODID = "cinematicrails";

	private static final Logger LOGGER = LogManager.getLogger();

	public ExampleMod() {
		IEventBus iEventBus = FMLJavaModLoadingContext.get().getModEventBus();
		// Register the setup method for modloading
		iEventBus.addListener(this::setup);
		// Register the doClientStuff method for modloading
		iEventBus.addListener(this::doClientStuff);
		iEventBus.addGenericListener(Block.class, this::blocks);
		iEventBus.addGenericListener(Item.class, this::items);
	}

	public static Block diagonal_rail;

	private void blocks(RegistryEvent.Register<Block> event) {
		diagonal_rail = register(new CinematicRailBlock(Block.Properties.from(Blocks.RAIL)), "diagonal_rail", event.getRegistry());
	}

	private void items(RegistryEvent.Register<Item> event) {
		register(new BlockItem(diagonal_rail, new Item.Properties().group(ItemGroup.REDSTONE)), diagonal_rail.getRegistryName(), event.getRegistry());
	}

	private void setup(final FMLCommonSetupEvent event) {
		PacketHandler.registerMessages(MODID);
	}

	private void doClientStuff(final FMLClientSetupEvent event) {
		RenderTypeLookup.setRenderLayer(diagonal_rail, RenderType.cutout());
	}


	public static <T extends IForgeRegistryEntry<T>> T register(T obj, String name, IForgeRegistry<T> registry) {
		return register(obj, MODID, name, registry);
	}

	public static <T extends IForgeRegistryEntry<T>> T register(T obj, String modid, String name, IForgeRegistry<T> registry) {
		return register(obj, new ResourceLocation(modid, name), registry);
	}

	public static <T extends IForgeRegistryEntry<T>> T register(T obj, ResourceLocation location, IForgeRegistry<T> registry) {
		registry.register(obj.setRegistryName(location));
		return obj;
	}
}
