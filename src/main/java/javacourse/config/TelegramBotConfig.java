package javacourse.config;

import lombok.Builder;
import lombok.Data;

/**
 * Stores the details for connecting to Telegram
 */
@Data
@Builder
public class TelegramBotConfig {
    String botName;
    String token;
}
