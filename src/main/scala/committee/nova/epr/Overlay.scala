package committee.nova.epr

import committee.nova.epr.util.ScreenUtils
import committee.nova.epr.util.ScreenUtils.{posX, posY, scale, yawOffset}
import net.minecraft.client.Minecraft
import net.minecraft.client.renderer.{GlStateManager, OpenGlHelper, RenderHelper}
import org.dimdev.rift.listener.client.OverlayRenderer

class Overlay extends OverlayRenderer {
  override def renderOverlay(): Unit = {
    val mc = Minecraft.getInstance()
    val player = mc.player
    GlStateManager.enableColorMaterial()
    GlStateManager.pushMatrix()
    GlStateManager.translatef(posX, posY, -500.0F)
    GlStateManager.scalef(-scale, scale, scale)
    GlStateManager.rotatef(180.0F, 0.0F, 0.0F, 1.0F)
    GlStateManager.rotatef(player.rotationYaw + yawOffset, 0.0F, 1.0F, 0.0F)
    GlStateManager.rotatef(135.0F, 0.0F, 1.0F, 0.0F)
    RenderHelper.enableStandardItemLighting()
    GlStateManager.rotatef(-135.0F, 0.0F, 1.0F, 0.0F)

    val rm = mc.getRenderManager
    rm.setRenderShadow(false)
    rm.renderEntity(player, 0.0D, 0.0D, 0.0D, 0, ScreenUtils.getPartialTick, true)
    rm.setRenderShadow(true)

    GlStateManager.popMatrix()
    RenderHelper.disableStandardItemLighting()
    GlStateManager.disableRescaleNormal()
    GlStateManager.activeTexture(OpenGlHelper.GL_TEXTURE1)
    GlStateManager.disableTexture2D()
    GlStateManager.activeTexture(OpenGlHelper.GL_TEXTURE0)
  }

}
