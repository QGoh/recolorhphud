package com.recolorhphud;

import com.google.inject.Provides;
import javax.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.Client;
import net.runelite.api.events.BeforeRender;
import net.runelite.api.events.ScriptPreFired;
import net.runelite.api.gameval.InterfaceID;
import net.runelite.api.widgets.Widget;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;

@Slf4j
@PluginDescriptor(
	name = "Recolor HP Hud"
)
public class RecolorHpHudPlugin extends Plugin
{;
	public static final int HP_HUD_GREEN = 51200;
	public static final int HP_HUD_OPEN = 2377;

	@Inject
	private Client client;

	@Inject
	private RecolorHpHudConfig config;

	private boolean isGreenBar = false;
	private int nativeTargetNameColor;
	private int nativeBarValueColor;
	private int nativeBarForegroundColor;
	private int nativeBarBackgroundColor;
	private int nativeBarProgressColor;
	private final int nativeBorderColor = 1643793;

	@Override
	protected void shutDown() throws Exception
	{
		resetHpHud();
	}

	@Subscribe
	public void onBeforeRender(BeforeRender beforeRender)
	{
		Widget targetName = client.getWidget(InterfaceID.HpbarHud.CREATURE_NAME);
		recolorWidget(targetName, config.targetNameColor().getRGB(), config.nameOpacity(), config.nameShadow());

		Widget barValue = client.getWidget(InterfaceID.HpbarHud.HP_BAR_TEXT);
		recolorWidget(barValue, config.barValueColor().getRGB(), config.barValueOpacity(), config.barValueShadow());

		Widget barForeground = client.getWidget(InterfaceID.HpbarHud.HEALTH_BAR_REMAINING);
		recolorWidget(barForeground, config.barForegroundColor().getRGB(), config.barForegroundOpacity(), null);

		Widget barBackground = client.getWidget(InterfaceID.HpbarHud.HEALTH_BAR_BACK);
		recolorWidget(barBackground, config.barBackgroundColor().getRGB(), config.barBackgroundOpacity(), null);

		Widget barProgress = client.getWidget(InterfaceID.HpbarHud.HEALTH_BAR_SLIDING);
		recolorWidget(barProgress, config.barProgressColor().getRGB(), config.barProgressOpacity(), null);

		Widget barBorder = client.getWidget(InterfaceID.HpbarHud.INNER_BORDER);
		recolorWidget(barBorder, config.barBorderColor().getRGB(), config.barBorderOpacity(), null);

		Widget frameBorder = client.getWidget(InterfaceID.HpbarHud.OUTER_BORDER);
		recolorWidget(frameBorder, config.frameBorderColor().getRGB(), config.frameBorderOpacity(), null);

		Widget frame = client.getWidget(InterfaceID.HpbarHud.NAME_BACKING);
		if (frame != null)
		{
			frame.setOpacity(255 - config.frameOpacity());
		}

		Widget extraBarForeground = client.getWidget(InterfaceID.HpbarHud.HEALTH_BAR_REMAINING_EXTRA);
		if (extraBarForeground != null)
		{
			extraBarForeground.setOpacity(255 - config.barForegroundOpacity());
		}

		Widget extraBarBackground = client.getWidget(InterfaceID.HpbarHud.HEALTH_BAR_BACK_EXTRA);
		if (extraBarBackground != null)
		{
			extraBarBackground.setOpacity(255 - config.barBackgroundOpacity());
		}

		Widget extraBarProgress = client.getWidget(InterfaceID.HpbarHud.HEALTH_BAR_SLIDING_EXTRA);
		if (extraBarProgress != null)
		{
			extraBarProgress.setOpacity(255 - config.barProgressOpacity());
		}

		Widget extraBarBorder = client.getWidget(InterfaceID.HpbarHud.INNER_BORDER_EXTRA);
		recolorWidget(extraBarBorder, config.barBorderColor().getRGB(), config.barBorderOpacity(), null);
	}
	
	private void resetHpHud()
	{
		Widget targetName = client.getWidget(InterfaceID.HpbarHud.CREATURE_NAME);
		recolorWidget(targetName, nativeTargetNameColor, 255, true);

		Widget barValue = client.getWidget(InterfaceID.HpbarHud.HP_BAR_TEXT);
		recolorWidget(barValue, nativeBarValueColor, 255, true);

		Widget barForeground = client.getWidget(InterfaceID.HpbarHud.HEALTH_BAR_REMAINING);
		recolorWidget(barForeground, nativeBarForegroundColor, 255, null);

		Widget barBackground = client.getWidget(InterfaceID.HpbarHud.HEALTH_BAR_BACK);
		recolorWidget(barBackground, nativeBarBackgroundColor, 255, null);

		Widget barProgress = client.getWidget(InterfaceID.HpbarHud.HEALTH_BAR_SLIDING);
		recolorWidget(barProgress, nativeBarProgressColor, 255, null);

		Widget barBorder = client.getWidget(InterfaceID.HpbarHud.INNER_BORDER);
		recolorWidget(barBorder, nativeBorderColor, 255, null);

		Widget frameBorder = client.getWidget(InterfaceID.HpbarHud.OUTER_BORDER);
		recolorWidget(frameBorder, nativeBorderColor, 255, null);

		Widget frame = client.getWidget(InterfaceID.HpbarHud.NAME_BACKING);
		if (frame != null)
		{
			frame.setOpacity(0);
		}

		Widget extraBarForeground = client.getWidget(InterfaceID.HpbarHud.HEALTH_BAR_REMAINING_EXTRA);
		if (extraBarForeground != null)
		{
			extraBarForeground.setOpacity(0);
		}

		Widget extraBarBackground = client.getWidget(InterfaceID.HpbarHud.HEALTH_BAR_BACK_EXTRA);
		if (extraBarBackground != null)
		{
			extraBarBackground.setOpacity(0);
		}

		Widget extraBarProgress = client.getWidget(InterfaceID.HpbarHud.HEALTH_BAR_SLIDING_EXTRA);
		if (extraBarProgress != null)
		{
			extraBarProgress.setOpacity(0);
		}

		Widget extraBarBorder = client.getWidget(InterfaceID.HpbarHud.INNER_BORDER_EXTRA);
		recolorWidget(extraBarBorder, nativeBorderColor, 255, null);
	}

	@Subscribe
	public void onScriptPreFired(ScriptPreFired scriptPreFired)
	{
		if (scriptPreFired.getScriptId() != HP_HUD_OPEN)
		{
			return;
		}

		Widget targetName = client.getWidget(InterfaceID.HpbarHud.CREATURE_NAME);
		if (targetName != null)
		{
			nativeTargetNameColor = targetName.getTextColor();
		}

		Widget barValue = client.getWidget(InterfaceID.HpbarHud.HP_BAR_TEXT);
		if (barValue != null)
		{
			nativeBarValueColor = barValue.getTextColor();
		}

		Widget barForeground = client.getWidget(InterfaceID.HpbarHud.HEALTH_BAR_REMAINING);
		if (barForeground != null)
		{
			nativeBarForegroundColor = barForeground.getTextColor();
			isGreenBar = (nativeBarForegroundColor == HP_HUD_GREEN);
		}

		Widget barBackground = client.getWidget(InterfaceID.HpbarHud.HEALTH_BAR_BACK);
		if (barBackground != null)
		{
			nativeBarBackgroundColor = barBackground.getTextColor();
		}

		Widget barProgress = client.getWidget(InterfaceID.HpbarHud.HEALTH_BAR_SLIDING);
		if (barProgress != null)
		{
			nativeBarProgressColor = barProgress.getTextColor();
		}
	}

	private void recolorWidget(Widget widget, int color, int opacity, Boolean shadow)
	{
		if (widget == null)
		{
			return;
		}

		if (isGreenBar)
		{
			widget.setTextColor(color);
		}

		widget.setOpacity(255 - opacity);

		if (shadow != null)
		{
			widget.setTextShadowed(shadow);
		}
	}

	@Provides
	RecolorHpHudConfig provideConfig(ConfigManager configManager)
	{
		return configManager.getConfig(RecolorHpHudConfig.class);
	}
}
