import com.sksamuel.hoplite.ConfigLoaderBuilder
import com.sksamuel.hoplite.addResourceSource
import dev.kord.core.Kord
import dev.kord.core.behavior.reply
import dev.kord.core.entity.ReactionEmoji
import dev.kord.core.event.message.MessageCreateEvent
import dev.kord.core.on
import dev.kord.gateway.Intent
import dev.kord.gateway.PrivilegedIntent
import java.net.URLEncoder

data class APIConfig(val token: String)

@OptIn(PrivilegedIntent::class)
suspend fun main(args: Array<String>) {
  val config =
      ConfigLoaderBuilder.default()
          .addResourceSource("/bot-config.yaml")
          .build()
          .loadConfigOrThrow<APIConfig>()
  val kord = Kord(config.token)
  kord.on<MessageCreateEvent> {
    val trimmedMessage = message.content.trim().lowercase().replace(Regex("[^a-zA-Z0-9]"), "")
    if ((trimmedMessage.startsWith("whatis") ||
        trimmedMessage.startsWith("whats") && message.content.length < 30)) {
      message.addReaction(ReactionEmoji.Unicode("\uD83E\uDEE4"))
      message.reply {
        content = "https://lmgt.org/?q=" + URLEncoder.encode(message.content, "UTF-8")
      }
    }
  }

  kord.login { intents += Intent.MessageContent }
}
