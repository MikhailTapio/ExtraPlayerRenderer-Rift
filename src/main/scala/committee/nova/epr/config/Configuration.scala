package committee.nova.epr.config

import com.google.gson.annotations.SerializedName

object Configuration {
  def getDefaultInstance: Configuration = new Configuration(50.0F, 320.0F, 80.0F, 5.0F)
}

class Configuration {
  @SerializedName("pos_x") var posX = .0F
  @SerializedName("pos_Y") var posY = .0F
  @SerializedName("scale") var scale = .0F
  @SerializedName("yaw_offset") var yawOffset = .0F

  def this(posX: Float, posY: Float, scale: Float, yawOffset: Float) = {
    this()
    this.posX = posX
    this.posY = posY
    this.scale = scale
    this.yawOffset = yawOffset
  }
}
