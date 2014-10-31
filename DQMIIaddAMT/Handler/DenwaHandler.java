package DQMIIaddAMT.Handler;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import DQMII.DqmEvent1;
import DQMII.DqmEvent2;
import DQMII.Function.ExtendedPlayerProperties;
import DQMII.Handler.DqmPacketHandler;
import DQMIIaddAMT.DqmAMTAddon;
import cpw.mods.fml.common.network.PacketDispatcher;
import cpw.mods.fml.common.network.Player;

public class DenwaHandler {
	public String[] shopItem = {"茶の苗", "緑茶の茶葉", "紅茶の茶葉", "アールグレイの茶葉", "アップルティーの茶葉",
								"はちみつレモン", "コーヒー粉", "ライムペース", "トマトペースト", "ベリーペースト",
								"ぶどうペースト", "豆乳鍋の材料", "かぼちゃポタージュの材料", "BLTスープの材料", "空のティーカップ",
								"ティーメーカー", "おろし金", "空の鍋", "ワインボトル", "ウォッカボトル",
								"ウィスキーボトル", "冒険者スティーブ デリバリー", "謎の老人 デリバリー"};
	public int[] shopGold = {1000, 150, 150, 500, 300,
							 400, 400, 400, 400, 400,
							 400, 750, 750, 750, 300,
							 30000, 3000, 7500, 20000, 75000,
							 75000, 150000, 150000};
	public int[] shopItemID = {DqmAMTAddon.amtCfg.blTeaSapling, DqmAMTAddon.amtCfg.itmEXItems, DqmAMTAddon.amtCfg.itmEXItems, DqmAMTAddon.amtCfg.itmEXItems, DqmAMTAddon.amtCfg.itmEXItems,
							   DqmAMTAddon.amtCfg.blFilledCap, DqmAMTAddon.amtCfg.itmGratedApple, DqmAMTAddon.amtCfg.itmGratedApple, DqmAMTAddon.amtCfg.itmGratedApple, DqmAMTAddon.amtCfg.itmGratedApple,
							   DqmAMTAddon.amtCfg.itmGratedApple, DqmAMTAddon.amtCfg.itmMincedFoods, DqmAMTAddon.amtCfg.itmMincedFoods, DqmAMTAddon.amtCfg.itmMincedFoods, DqmAMTAddon.amtCfg.blFilledCap,
							   DqmAMTAddon.amtCfg.blTeaMakerNext, DqmAMTAddon.amtCfg.itmGrater, DqmAMTAddon.amtCfg.blEmptySoupPan, DqmAMTAddon.amtCfg.itmLargeBottle, DqmAMTAddon.amtCfg.itmLargeBottle,
							   DqmAMTAddon.amtCfg.itmLargeBottle, 0, 0};
	public int[] shopItemMeta = {0, 2, 3, 8, 9,
								 10, 3, 5, 6, 7,
								 8, 5, 6, 7, 0,
								 0, 0, 0, 3, 6,
								 7, 0, 0};
	
	@ForgeSubscribe
	public void onPlayerInteractEvent(PlayerInteractEvent event)
	{
		EntityPlayer ep = event.entityPlayer;

		if(!ep.worldObj.isRemote)
		{
			World wld = ep.worldObj;
			int blcId = wld.getBlockId(event.x, event.y, event.z);

			if(blcId == DqmAMTAddon.amtCfg.blRoteryDial)
			{
				int talkParam = ExtendedPlayerProperties.get(ep).getTalkCnt(31);

				if(talkParam >= 100 && ep.isSneaking())
				{
					if(ep.Gold >= shopGold[talkParam - 100])
					{
						//ep.sendChatToPlayer("「" + shopItem[talkParam - 100] + "」ですね。お買い上げありがとうございます。");
						if(talkParam != 121 && talkParam != 122)
						{
		                    ep.Gold = ep.Gold - shopGold[talkParam - 100];
		                    ItemStack itm = new ItemStack(shopItemID[talkParam - 100] ,1, shopItemMeta[talkParam - 100]);

		                    if(ep.inventory.addItemStackToInventory(itm))
		                    {
		                    	ep.Gold = ep.Gold - shopGold[talkParam - 100];
		                    	ep.sendChatToPlayer("「" + shopItem[talkParam - 100] + "」ですね。お買い上げありがとうございます。");
		                    }else
		                    {
		                    	ep.sendChatToPlayer("アイテムを持ちきれないようです。");
		                    }
						}else
						{
							if(talkParam == 121)
							{
								DqmEvent1 shopEv = new DqmEvent1(wld);
								shopEv.setLocationAndAngles(ep.posX, ep.posY + 0.5D, ep.posZ, 0.0F, 0.0F);
								wld.spawnEntityInWorld(shopEv);
								shopEv.spawnExplosionParticle();
								ep.Gold = ep.Gold - shopGold[talkParam - 100];
		                    	ep.sendChatToPlayer("「" + shopItem[talkParam - 100] + "」ですね。ご利用ありがとうございます。");
		                    	PacketDispatcher.sendPacketToPlayer(DqmPacketHandler.getPacket(event.entityPlayer), (Player) event.entityPlayer);
							}else
							{
								DqmEvent2 shopEv = new DqmEvent2(wld);
								shopEv.setLocationAndAngles(ep.posX, ep.posY + 0.5D, ep.posZ, 0.0F, 0.0F);
								wld.spawnEntityInWorld(shopEv);
								shopEv.spawnExplosionParticle();
								ep.Gold = ep.Gold - shopGold[talkParam - 100];
		                    	ep.sendChatToPlayer("「" + shopItem[talkParam - 100] + "」ですね。ご利用ありがとうございます。");
							}
						}
					}else
					{
						ep.sendChatToPlayer("申し訳ありません。ゴールドが足りないようです。");
					}
				}else
				{
					if(ep.Gold >= 100 || talkParam != 0)
					{
						if(talkParam == 0)
						{
							ep.Gold = ep.Gold - 100;
						}

						talkParam = talkParam + 1;
						if(talkParam == 6 || talkParam ==123)
						{
							talkParam = 100;
						}

						ExtendedPlayerProperties.get(ep).setTalkCnt(31, talkParam);


						talkDenwa(talkParam ,ep);
					}else
					{
						ep.sendChatToPlayer("電話をかけるためには100G必要です。");
					}
				}

			}else
			{
				int talkParam = ExtendedPlayerProperties.get(ep).getTalkCnt(31);
				if(talkParam != 0 && !ep.isSneaking())
				{
					ep.sendChatToPlayer("電話の通話を終了しました。");
					ExtendedPlayerProperties.get(ep).setTalkCnt(31, 0);
				}
			}
		}
	}

	public void talkDenwa(int par1, EntityPlayer ep)
	{
		String talkMessage = "";

		if(par1 < 100)
		{
			switch(par1)
			{
				case 1: talkMessage = "こちらはテレホンショッピングです。"; break;
				case 2: talkMessage = "様々な商品を購入することができます。";break;
				case 3: talkMessage = "購入したい商品がありましたら";break;
				case 4: talkMessage = "スニーク+右クリックで商品を購入できます。";break;
				case 5: talkMessage = "それでは、商品の紹介をしていきます。";break;
				default : talkMessage = ""; break;
			}
		}else
		{
			talkMessage = "「" + shopItem[par1 - 100] + "」を " + shopGold[par1 - 100] + "Gで提供しております。";
		}

		ep.sendChatToPlayer(talkMessage);

	}
}
