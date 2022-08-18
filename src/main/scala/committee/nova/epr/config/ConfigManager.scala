package committee.nova.epr.config

import com.google.gson.Gson
import net.minecraft.client.Minecraft
import org.apache.commons.io.FileUtils

import java.io.{File, FileReader}
import java.nio.charset.StandardCharsets
import java.nio.file.Files
import scala.util.Try

object ConfigManager {
  val cfgFile: File = Minecraft.getInstance().gameDir.toPath.resolve("config").resolve("ExtraPlayerRenderer-Rift.json").toFile
  val GSON = new Gson
  private var cfg: Configuration = _

  def getCfg: Configuration = cfg

  def readConfigFile(): Unit = {
    if (!cfgFile.isFile) {
      generateCfg()
      return
    }
    cfg = Try(GSON.fromJson(new FileReader(cfgFile), classOf[Configuration])).getOrElse(Configuration.getDefaultInstance)
  }

  def writeConfigFile(cfg: Configuration): Unit = {
    if (!cfgFile.isFile) {
      generateCfg()
      return
    }
    try {
      FileUtils.write(cfgFile, GSON.toJson(cfg), StandardCharsets.UTF_8)
    } catch {
      case e: Exception => e.printStackTrace()
    }
  }

  private def generateCfg(): Unit = {
    try {
      Files.createFile(cfgFile.toPath)
      cfg = Configuration.getDefaultInstance
      FileUtils.write(cfgFile, GSON.toJson(cfg), StandardCharsets.UTF_8)
    } catch {
      case e: Exception => e.printStackTrace()
    }
  }
}
