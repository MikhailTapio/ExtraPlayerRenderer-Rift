package committee.nova.epr.gui

import committee.nova.epr.config.{ConfigManager, Configuration}
import committee.nova.epr.gui.GuiRenderConfig.{SCALE_MAX, SCALE_MIN}
import committee.nova.epr.util.ScreenUtils
import net.minecraft.client.gui.GuiScreen
import net.minecraft.client.resources.I18n
import net.minecraft.util.math.MathHelper
import org.lwjgl.glfw.GLFW

object GuiRenderConfig {
  val SCALE_MAX = 360F
  val SCALE_MIN = 8F
}

class GuiRenderConfig extends GuiScreen {
  override def render(mouseX: Int, mouseY: Int, partialTicks: Float): Unit = {
    super.render(mouseX, mouseY, partialTicks)
    val midX = width / 2
    val midY = height / 2
    drawGradientRect(midX - 60, midY - 45, midX + 70, midY + 45, 0xcc111111, 0xcc111111)
    val text = I18n.format("gui.epr.cfg.text")
    fontRenderer.drawSplitString(text, midX - 50, midY - 35, 140, 0xffffffff)
  }

  override def mouseScrolled(scroll: Double): Boolean = {
    if (scroll == 0) return false
    changeScaleValue((scroll * 3F).floatValue)
    true
  }

  override def mouseDragged(mouseX: Double, mouseY: Double, button: Int, deltaX: Double, deltaY: Double): Boolean = {
    var result = false
    if (button == GLFW.GLFW_MOUSE_BUTTON_LEFT) {
      val oldYaw = ScreenUtils.yawOffset
      ScreenUtils.yawOffset = deltaX.floatValue + oldYaw
      result = true
    }
    if (button == GLFW.GLFW_MOUSE_BUTTON_RIGHT) {
      val oldP = (ScreenUtils.posX, ScreenUtils.posY)
      ScreenUtils.posX = deltaX.floatValue + oldP._1
      ScreenUtils.posY = deltaY.floatValue + oldP._2
      result = true
    }
    result
  }

  override def charTyped(typedChar: Char, keyCode: Int): Boolean = {
    if (Character.toLowerCase(typedChar) != 'r' || !ScreenUtils.isAltKeyDown) return super.charTyped(typedChar, keyCode)
    val cfg = Configuration.getDefaultInstance
    ScreenUtils.posX = cfg.posX
    ScreenUtils.posY = cfg.posY
    ScreenUtils.scale = cfg.scale
    ScreenUtils.yawOffset = cfg.yawOffset
    super.charTyped(typedChar, keyCode)
  }

  override def onGuiClosed(): Unit = ConfigManager.writeConfigFile(new Configuration(ScreenUtils.posX, ScreenUtils.posY, ScreenUtils.scale, ScreenUtils.yawOffset))

  private def changeScaleValue(amount: Float): Unit = {
    val old = ScreenUtils.scale
    val tmp = old + amount * old / 80.0F
    ScreenUtils.scale = MathHelper.clamp(tmp, SCALE_MIN, SCALE_MAX)
  }

  override def doesGuiPauseGame(): Boolean = false
}
