package committee.nova.epr

import committee.nova.epr.config.ConfigManager
import committee.nova.epr.gui.GuiRenderConfig
import net.minecraft.client.Minecraft
import net.minecraft.client.settings.KeyBinding
import net.minecraft.client.util.InputMappings
import org.apache.logging.log4j.{LogManager, Logger}
import org.dimdev.rift.listener.BootstrapListener
import org.dimdev.rift.listener.client.KeybindHandler
import org.lwjgl.glfw.GLFW

object ExtraPlayerRenderer {
  val LOGGER: Logger = LogManager.getLogger
  val openCfg = new KeyBinding("key.epr.opencfg.desc", InputMappings.Type.KEYSYM, GLFW.GLFW_KEY_P,
    "key.category.epr")
}

class ExtraPlayerRenderer extends BootstrapListener with KeybindHandler {
  override def processKeybinds(): Unit = {
    if (!InputMappings.isKeyDown(GLFW.GLFW_KEY_LEFT_ALT) && !InputMappings.isKeyDown(GLFW.GLFW_KEY_RIGHT_ALT)) return
    if (!InputMappings.isKeyDown(GLFW.GLFW_KEY_P)) return
    Minecraft.getInstance().displayGuiScreen(new GuiRenderConfig)
  }

  override def afterVanillaBootstrap(): Unit = ConfigManager.readConfigFile()
}
