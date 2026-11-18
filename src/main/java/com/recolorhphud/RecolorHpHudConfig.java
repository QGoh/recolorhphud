package com.recolorhphud;

import java.awt.Color;
import net.runelite.client.config.Config;
import net.runelite.client.config.ConfigGroup;
import net.runelite.client.config.ConfigItem;
import net.runelite.client.config.ConfigSection;
import net.runelite.client.config.Range;
import net.runelite.client.ui.ColorScheme;

@ConfigGroup("recolorhphud")
public interface RecolorHpHudConfig extends Config
{
	@ConfigSection(
		name = "Target Name",
		description = "",
		position = 0,
		closedByDefault = true
	)
	String targetName = "targetName";

	@ConfigSection(
		name = "Health Bar Value",
		description = "",
		position = 1,
		closedByDefault = true
	)
	String healthBarValue = "healthBarValue";

	@ConfigSection(
		name = "Health Bar",
		description = "",
		position = 2,
		closedByDefault = true
	)
	String healthBar = "healthBar";

	@ConfigSection(
		name = "Frame",
		description = "",
		position = 3,
		closedByDefault = true
	)
	String frame = "frame";

	@ConfigItem(
		keyName = "nameColor",
		name = "Color",
		description = "",
		position = 0,
		section = targetName
	)
	default Color nameColor()
	{
		return ColorScheme.BRAND_ORANGE;
	}

	@Range(min = 0, max = 255)
	@ConfigItem(
		keyName = "nameOpacity",
		name = "Opacity",
		description = "0 = fully transparent, 255 = fully opaque",
		position = 1,
		section = targetName
	)
	default int nameOpacity()
	{
		return 255;
	}

	@ConfigItem(
		keyName = "nameShadowed",
		name = "Shadow",
		description = "",
		position = 2,
		section = targetName
	)
	default boolean nameShadowed()
	{
		return true;
	}

	@ConfigItem(
		keyName = "valueColor",
		name = "Color",
		description = "",
		position = 3,
		section = healthBarValue
	)
	default Color valueColor()
	{
		return Color.YELLOW;
	}

	@Range(min = 0, max = 255)
	@ConfigItem(
		keyName = "valueOpacity",
		name = "Opacity",
		description = "0 = fully transparent, 255 = fully opaque",
		position = 4,
		section = healthBarValue
	)
	default int valueOpacity()
	{
		return 255;
	}

	@ConfigItem(
		keyName = "valueShadowed",
		name = "Shadow",
		description = "",
		position = 5,
		section = healthBarValue
	)
	default boolean valueShadowed()
	{
		return true;
	}

	@ConfigItem(
		keyName = "barForegroundColor",
		name = "Foreground Color",
		description = "",
		position = 6,
		section = healthBar
	)
	default Color barForegroundColor()
	{
		return new Color(0, 170, 0);
	}

	@Range(min = 0, max = 255)
	@ConfigItem(
		keyName = "barForegroundOpacity",
		name = "Foreground Opacity",
		description = "0 = fully transparent, 255 = fully opaque",
		position = 7,
		section = healthBar
	)
	default int barForegroundOpacity()
	{
		return 255;
	}

	@ConfigItem(
		keyName = "barBackgroundColor",
		name = "Background Color",
		description = "",
		position = 8,
		section = healthBar
	)
	default Color barBackgroundColor()
	{
		return new Color(170, 0, 0);
	}

	@Range(min = 0, max = 255)
	@ConfigItem(
		keyName = "barBackgroundOpacity",
		name = "Background Opacity",
		description = "0 = fully transparent, 255 = fully opaque",
		position = 9,
		section = healthBar
	)
	default int barBackgroundOpacity()
	{
		return 255;
	}

	@ConfigItem(
		keyName = "barProgressColor",
		name = "Sliding Color",
		description = "",
		position = 10,
		section = healthBar
	)
	default Color barProgressColor()
	{
		return ColorScheme.PROGRESS_INPROGRESS_COLOR;
	}

	@Range(min = 0, max = 255)
	@ConfigItem(
		keyName = "barProgressOpacity",
		name = "Sliding Opacity",
		description = "0 = fully transparent, 255 = fully opaque",
		position = 11,
		section = healthBar
	)
	default int barProgressOpacity()
	{
		return 255;
	}

	@ConfigItem(
		keyName = "barBorderColor",
		name = "Border Color",
		description = "",
		position = 12,
		section = healthBar
	)
	default Color barBorderColor() {
		return Color.BLACK;
	}

	@Range(min = 0, max = 255)
	@ConfigItem(
		keyName = "barBorderOpacity",
		name = "Border Opacity",
		description = "",
		position = 13,
		section = healthBar
	)
	default int barBorderOpacity() {
		return 255;
	}

	@ConfigItem(
		keyName = "frameBorderColor",
		name = "Frame Border Color",
		description = "",
		position = 14,
		section = frame
	)
	default Color frameBorderColor() {
		return Color.BLACK;
	}

	@Range(min = 0, max = 255)
	@ConfigItem(
		keyName = "frameBorderOpacity",
		name = "Frame Border Opacity",
		description = "0 = fully transparent, 255 = fully opaque",
		position = 15,
		section = frame
	)
	default int frameBorderOpacity()
	{
		return 255;
	}

	@Range(min = 0, max = 255)
	@ConfigItem(
		keyName = "frameOpacity",
		name = "Frame Opacity",
		description = "0 = fully transparent, 255 = fully opaque",
		position = 16,
		section = frame
	)
	default int frameOpacity()
	{
		return 255;
	}
}
