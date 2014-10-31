package DQMIIaddAMT;

import java.io.File;

import net.minecraftforge.common.Configuration;
import net.minecraftforge.common.MinecraftForge;
import DQMIIaddAMT.Handler.DenwaHandler;
import DQMIIaddAMT.Handler.EatEdiblesHandler;
import DQMIIaddAMT.Handler.LivingEventHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.registry.LanguageRegistry;

@Mod(modid = "DQMNextAMTAddon", name = "DQMNextAMTAddon", version = "1.0.0",dependencies="after:DQMⅢ;after:DCsAppleMilk")
//@Mod(modid = "DQMNextBoPAddon", name = "DQMNextBoPAddon", version = "1.0.0",dependencies="after:DQMⅢ")
@NetworkMod(clientSideRequired = false, serverSideRequired = false)

public class DqmAMTAddon {

	//public static BoPConfigs bopCfg;
	public static AMTConfigs amtCfg;

    @Mod.Init
    public void load(FMLInitializationEvent event)
    {

    	Configuration cfg1 = new Configuration(new File(DQMII.DQM.proxy.getDir(), "config/DCsAppleMilk.cfg"));
    	amtCfg = new AMTConfigs(cfg1);

		LanguageRegistry.instance().addStringLocalization("DCs.potion.suffocation", "suffocation");
		LanguageRegistry.instance().addStringLocalization("DCs.potion.suffocation", "ja_JP", "窒息");
		LanguageRegistry.instance().addStringLocalization("DCs.potion.protectionSuffocation", "protectionSuffocation");
		LanguageRegistry.instance().addStringLocalization("DCs.potion.protectionSuffocation", "ja_JP", "窒息無効");

    	//System.out.println("DEBUGLINE:1");
    	MinecraftForge.EVENT_BUS.register(new EatEdiblesHandler());
    	MinecraftForge.EVENT_BUS.register(new LivingEventHandler());
    	MinecraftForge.EVENT_BUS.register(new DenwaHandler());

    }
}

