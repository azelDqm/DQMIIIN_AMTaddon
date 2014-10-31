package DQMIIaddAMT;

import java.io.File;

import net.minecraftforge.common.Configuration;
import net.minecraftforge.common.Property;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = "DQMNextConfigInjector4AMT", name = "DQMNextConfigInjector4AMT", version = "1.0.0",dependencies="after:DQMⅢ;before:DCsAppleMilk")

public class DqmAMTconfigInjector {

	//AMT_potionID
	public static int potionIDImmunity = 60;
	public static int potionIDPrvExplode = 61;
	public static int potionIDPrvProjectile = 62;
	public static int potionIDReflex = 63;
	public static int potionIDAbsEXP = 64;
	public static int potionIDAbsHeal = 65;
	public static int potionIDSuffocation = 66;
	public static int potionIDPrvSuffocation = 67;

	//Addon_PotionID
	public static int injectIDImmunity = 80;
	public static int injectIDPrvExplode = 81;
	public static int injectIDPrvProjectile = 82;
	public static int injectIDReflex = 83;
	public static int injectIDAbsEXP = 84;
	public static int injectIDAbsHeal = 85;
	public static int injectIDSuffocation = 86;
	public static int injectIDPrvSuffocation = 87;

	@Mod.PreInit
    public void load(FMLPreInitializationEvent event)
    {
    	Configuration cfg1 = new Configuration(new File(DQMII.DQM.proxy.getDir(), "config/DQMⅢNext/DQMⅢNext_addon_AMT.cfg"));
    	Configuration cfg2 = new Configuration(new File(DQMII.DQM.proxy.getDir(), "config/DCsAppleMilk.cfg"));

    	loadCfgThis(cfg1);
    	loadCfgAMT(cfg2);
    }

    public void loadCfgThis(Configuration cfg)
    {
    	cfg.load();
    	injectIDImmunity = cfg.get("AMT PotionID割り込み", "免疫ポーション", injectIDImmunity).getInt();
    	injectIDPrvExplode = cfg.get("AMT PotionID割り込み", "爆発無効ポーション", injectIDPrvExplode).getInt();
    	injectIDPrvProjectile = cfg.get("AMT PotionID割り込み", "射撃無効ポーション", injectIDPrvProjectile).getInt();
    	injectIDReflex = cfg.get("AMT PotionID割り込み", "反射ポーション", injectIDReflex).getInt();
    	injectIDAbsEXP = cfg.get("AMT PotionID割り込み", "EXP吸収ポーション", injectIDAbsEXP).getInt();
    	injectIDAbsHeal = cfg.get("AMT PotionID割り込み", "ダメージ吸収ポーション", injectIDAbsHeal).getInt();
    	injectIDSuffocation = cfg.get("AMT PotionID割り込み", "窒息ポーション", injectIDSuffocation).getInt();
    	injectIDPrvSuffocation = cfg.get("AMT PotionID割り込み", "窒息無効ポーション", injectIDPrvSuffocation).getInt();
    	cfg.save();
    }

    public void loadCfgAMT(Configuration cfg)
    {
    	cfg.load();

		Property DCpotionID = cfg.get("potionID", "Immunization", potionIDImmunity,
				"Set new potion ID for this mod. These must be bigger than 32, and smaller than 127.");
		Property DCpotionID2 = cfg.get("potionID", "Protection:Projectile", potionIDPrvProjectile,
				"Set new potion ID for this mod. These must be bigger than 32, and smaller than 127.");
		Property DCpotionID3 = cfg.get("potionID", "Protection:Explode", potionIDPrvExplode,
				"Set new potion ID for this mod. These must be bigger than 32, and smaller than 127.");
		Property DCpotionID4 = cfg.get("potionID", "Reflex", potionIDReflex,
				"Set new potion ID for this mod. These must be bigger than 32, and smaller than 127.");
		Property DCpotionID5 = cfg.get("potionID", "EXPAbsorption", potionIDAbsEXP,
				"Set new potion ID for this mod. These must be bigger than 32, and smaller than 127.");
		Property DCpotionID6 = cfg.get("potionID", "DamageAbsorption", potionIDAbsHeal,
				"Set new potion ID for this mod. These must be bigger than 32, and smaller than 127.");
		Property DCpotionID7 = cfg.get("potionID", "Suffocation", potionIDSuffocation,
				"Set new potion ID for this mod. These must be bigger than 32, and smaller than 127.");
		Property DCpotionID8 = cfg.get("potionID", "Protection:Suffocation", potionIDPrvSuffocation,
				"Set new potion ID for this mod. These must be bigger than 32, and smaller than 127.");


		potionIDImmunity = DCpotionID.getInt();
		potionIDPrvExplode = DCpotionID3.getInt();
		potionIDPrvProjectile = DCpotionID2.getInt();
		potionIDReflex = DCpotionID4.getInt();
		potionIDAbsEXP = DCpotionID5.getInt();
		potionIDAbsHeal = DCpotionID6.getInt();
		potionIDSuffocation = DCpotionID7.getInt();
		potionIDPrvSuffocation = DCpotionID8.getInt();

		int value = 0;
		if(potionIDImmunity < 69)
		{
			DCpotionID.set(injectIDImmunity);
		}
		if(potionIDPrvExplode < 69)
		{
			DCpotionID3.set(injectIDPrvExplode);
		}
		if(potionIDPrvProjectile < 69)
		{
			DCpotionID2.set(injectIDPrvProjectile);
		}
		if(potionIDReflex < 69)
		{
			DCpotionID4.set(injectIDReflex);
		}
		if(potionIDAbsEXP < 69)
		{
			DCpotionID5.set(injectIDAbsEXP);
		}
		if(potionIDAbsHeal < 69)
		{
			DCpotionID6.set(injectIDAbsHeal);
		}
		if(potionIDSuffocation < 69)
		{
			DCpotionID7.set(injectIDSuffocation);
		}
		if(potionIDPrvSuffocation < 69)
		{
			DCpotionID8.set(injectIDPrvSuffocation);
		}
		cfg.save();
    }
}
