package DQMIIaddAMT.Handler;

import java.util.Random;

import mods.applemilk.api.ItemAPI;
import mods.applemilk.api.events.EatEdiblesEvent;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraftforge.event.ForgeSubscribe;
import DQMIIaddAMT.DqmAMTAddon;

public class EatEdiblesHandler {

	@ForgeSubscribe
	public void onEatEdiblesEvent(EatEdiblesEvent event)
	{
		//System.out.println("DEBUGLINE:2");
		//System.out.println("DEBUGLINE:" + event.edibles.getItemName() + "/" + event.edibles.getItemDamage());
		//event.player.Tikaranotane = 30;
		EntityPlayer ep = event.player;
		ItemStack st = event.edibles;
		//ItemStack st2 = ItemAPI.getBlock("teacupBlock", 2);
		Random rnd = new Random();

		//System.out.println(st.getItemName());

		if(st.itemID == DqmAMTAddon.amtCfg.blFoodPlayer && st.getItemDamage() != 3)
		{
			PotionEffect pe = ep.getActivePotionEffect(Potion.potionTypes[DqmAMTAddon.amtCfg.ptSuffocation]);
			if(pe == null)
			{
				ep.addPotionEffect(new PotionEffect(DqmAMTAddon.amtCfg.ptSuffocation, 200 , 0 , true));
			}else
			{
				if(pe.getAmplifier() < 15)
				{
					ep.addPotionEffect(new PotionEffect(DqmAMTAddon.amtCfg.ptSuffocation,  pe.getDuration() + 200, pe.getAmplifier() + 1, true));
				}else
				{
					ep.addPotionEffect(new PotionEffect(DqmAMTAddon.amtCfg.ptSuffocation,  pe.getDuration() + 200, 14, true));
				}
			}
		}

		if(st.itemID ==  DqmAMTAddon.amtCfg.blCocktail || st.itemID ==  ItemAPI.getBlock("teacupBlock", 0).itemID ||
		   st.itemID == ItemAPI.getBlock("teaCup2", 2).itemID || st.itemID == DqmAMTAddon.amtCfg.blLiquorCup)
		{
			ep.removePotionEffect(DqmAMTAddon.amtCfg.ptSuffocation);
			ep.addPotionEffect(new PotionEffect(DqmAMTAddon.amtCfg.ptProtecSuffocation, 60 , 0 , true));
		}

		if(st.itemID == ItemAPI.getBlock("teacupBlock", 2).itemID && (st.getItemDamage() == 0 || st.getItemDamage() == 2 || st.getItemDamage() == 3))
		{
			//紅茶・ミルクティー
			if(ep.HP + 50 > ep.HPMAX())
			{
				ep.HP = ep.HPMAX();
				//System.out.println(ep.HP);
			}else
			{
				ep.HP = ep.HP + 50;
				//System.out.println(ep.HP);
			}

			if(ep.MP + 25 > ep.MPMAX())
			{
				ep.MP = ep.MPMAX();
				//System.out.println(ep.HP);
			}else
			{
				ep.MP = ep.MP + 25;
				//System.out.println(ep.HP);
			}
			ep.processCommandLV(ep, null);
		}else if(st.itemID == ItemAPI.getBlock("teaCup2", 2).itemID && (st.getItemDamage() == 2 || st.getItemDamage() == 3))
		{
			//アールグレイ
			if(ep.HP + 75 > ep.HPMAX())
			{
				ep.HP = ep.HPMAX();
			}else
			{
				ep.HP = ep.HP + 75;
			}
			if(ep.MP + 50 > ep.MPMAX())
			{
				ep.MP = ep.MPMAX();
				//System.out.println(ep.HP);
			}else
			{
				ep.MP = ep.MP + 50;
				//System.out.println(ep.HP);
			}
			ep.processCommandLV(ep, null);
		}else if(st.itemID == DqmAMTAddon.amtCfg.blLiquorCup &&
				  (st.getItemDamage() == 8 || st.getItemDamage() == 0 || st.getItemDamage() == 2))
		{
			//紅茶酒・ワイン・日本酒
			if(ep.HP + (ep.HPMAX() / 4) > ep.HPMAX())
			{
				ep.HP = ep.HPMAX();
			}else
			{
				ep.HP = ep.HP + (ep.HPMAX() / 4);
			}

			if(ep.MP + (ep.MPMAX / 8) > ep.MPMAX())
			{
				ep.MP = ep.MPMAX();
				//System.out.println(ep.HP);
			}else
			{
				ep.MP = ep.MP + (ep.MPMAX / 8);
				//System.out.println(ep.HP);
			}
		}else if(st.itemID == DqmAMTAddon.amtCfg.blCocktail && st.getItemDamage() == 13)
		{
			//カシスティーカクテル
			if(ep.HP + (ep.HPMAX() / 2) > ep.HPMAX())
			{
				ep.HP = ep.HPMAX();
			}else
			{
				ep.HP = ep.HP + (ep.HPMAX() / 2);
			}

			if(ep.MP + (ep.MPMAX() / 2) > ep.MPMAX())
			{
				ep.MP = ep.MPMAX();
			}else
			{
				ep.MP = ep.MP + (ep.MPMAX() / 2);
			}
		}else if(st.itemID == DqmAMTAddon.amtCfg.blChocolateGift && st.getItemDamage() == 0)
		{
			int healVal = rnd.nextInt(ep.HPMAX());
			int healMPVal =  rnd.nextInt(ep.MPMAX());

			if(ep.HP + healVal > ep.HPMAX())
			{
				ep.HP = ep.HPMAX();
			}else
			{
				ep.HP = ep.HP + healVal;
			}

			if(ep.MP + healVal > ep.MPMAX())
			{
				ep.MP = ep.MPMAX();
			}else
			{
				ep.MP = ep.MP + healVal;
			}
			//チョコの贈り物
			//ep.HP = ep.HPMAX();
		}if(st.itemID == DqmAMTAddon.amtCfg.blIceCreamBlock && st.getItemDamage() == 1)
		{
			//紅茶アイス
			if(ep.HP + 100 > ep.HPMAX())
			{
				ep.HP = ep.HPMAX();
			}else
			{
				ep.HP = ep.HP + 100;
			}

			if(ep.MP + 100 > ep.MPMAX())
			{
				ep.MP = ep.MPMAX();
			}else
			{
				ep.MP = ep.MP + 100;
			}
		}

		if(!ep.worldObj.isRemote)
		{
			ep.processCommandLV(ep, null);
		}

		//else if(st.itemID == DqmAMTAddon.amtCfg.blIceCreamBlock
		//		&& (st.getItemDamage() == 5 || st.getItemDamage() == 6))
		//{
		//	//フルーツアイス・レモンアイス
		//	if(ep.HP + 50 > ep.HPMAX())
		//	{
		//		ep.HP = ep.HPMAX();
		//	}else
		//	{
		//		ep.HP = ep.HP + 50;
		//	}
		//}


	}
}
