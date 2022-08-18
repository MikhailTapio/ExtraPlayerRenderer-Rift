package committee.nova.epr.util

import committee.nova.epr.config.ConfigManager
import net.minecraft.client.Minecraft
import net.minecraft.client.util.InputMappings

object ScreenUtils {
  var posX: Float = ConfigManager.getCfg.posX
  var posY: Float = ConfigManager.getCfg.posY
  var scale: Float = ConfigManager.getCfg.scale
  var yawOffset: Float = ConfigManager.getCfg.yawOffset

  def isAltKeyDown: Boolean = InputMappings.isKeyDown(56) || InputMappings.isKeyDown(184)
  def getPartialTick: Float = Minecraft.getInstance().getRenderPartialTicks
}
