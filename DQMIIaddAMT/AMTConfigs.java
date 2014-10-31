package DQMIIaddAMT;

import net.minecraftforge.common.Configuration;

public class AMTConfigs {

	public static int blLiquorCup = 623;
	public static int blCocktail = 618;
	public static int blChocolateGift = 585;
	public static int blIceCreamBlock = 615;
	public static int blFoodPlayer = 591;
	public static int blRoteryDial = 619;

	public static int blTeaSapling = 606;
	public static int blFilledCap = 599;
	public static int blTeaMakerNext = 580;
	public static int blEmptySoupPan = 609;

	public static int itmPrincessClam = 6015;

	public static int itmEXItems = 6004;
	public static int itmGratedApple = 5991;
	public static int itmMincedFoods = 5992;
	public static int itmGrater = 5990;
	public static int itmLargeBottle = 6013;

	public static int ptDamageAbsorption = 65;
	public static int ptEXPAbsorption = 64;
	public static int ptImmunization = 60;
	public static int ptProtecExplode = 61;
	public static int ptProtecProjectile = 62;
	public static int ptProtecSuffocation = 67;
	public static int ptReflex = 63;
	public static int ptSuffocation = 66;



	public AMTConfigs(Configuration config)
	{
		//System.out.println("DEBUGLINE:3");
		config.load();
		blLiquorCup = config.get("block", "LiquorCup", blLiquorCup).getInt();
		blCocktail = config.get("block", "Cocktail", blCocktail).getInt();
		blChocolateGift = config.get("block", "ChocolateGift", blChocolateGift).getInt();
		blIceCreamBlock = config.get("block", "IceCreamBlock", blIceCreamBlock).getInt();
		blFoodPlayer = config.get("block", "FoodPlate", blFoodPlayer).getInt();
		blRoteryDial = config.get("block", "RoteryDial", blRoteryDial).getInt();

		itmPrincessClam = config.get("item", "PrincessClam", itmPrincessClam).getInt();

		ptDamageAbsorption = config.get("potionid", "DamageAbsorption", ptDamageAbsorption).getInt();
		ptEXPAbsorption = config.get("potionid", "EXPAbsorption", ptEXPAbsorption).getInt();
		ptImmunization = config.get("potionid", "Immunization", ptImmunization).getInt();
		ptProtecExplode = config.get("potionid", "Protection:Explode", ptProtecExplode).getInt();
		ptProtecProjectile = config.get("potionid", "Protection:Projectile", ptProtecProjectile).getInt();
		ptProtecSuffocation = config.get("potionid", "Protection:Suffocation", ptProtecSuffocation).getInt();
		ptReflex = config.get("potionid", "Reflex", ptReflex).getInt();
		ptSuffocation = config.get("potionid", "Suffocation", ptSuffocation).getInt();

	}
}
