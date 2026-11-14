package com.recolorhphud;

import net.runelite.client.RuneLite;
import net.runelite.client.externalplugins.ExternalPluginManager;

public class RecolorHpHudPluginTest
{
	public static void main(String[] args) throws Exception
	{
		ExternalPluginManager.loadBuiltin(RecolorHpHudPlugin.class);
		RuneLite.main(args);
	}
}