package javacourse.telegram;

import javacourse.config.TelegramBotConfig;
import javacourse.controller.CommandController;
import lombok.extern.slf4j.Slf4j;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

/**
 * Provides methods for working with a Telegram bot
 */
@Slf4j
public class TelegramBot extends TelegramLongPollingBot {
    private final TelegramBotConfig botConfig;
    private final CommandController commandController;

    /**
     * Builds telegram bot
     * @param botConfig bot configuration
     * @param commandController input command controller
     */
    public TelegramBot(TelegramBotConfig botConfig, CommandController commandController) {
        super(botConfig.getToken());
        this.botConfig = botConfig;
        this.commandController = commandController;
    }

    /**
     * Gets bot username
     * @return bot username
     */
    @Override
    public String getBotUsername() {
        return botConfig.getBotName();
    }

    /**
     * Processes the entered text
     * @param update entered text
     */
    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            String messageText = update.getMessage().getText();
            long chatId = update.getMessage().getChatId();

            switch (messageText) {
                case "/start":
                    startCommandReceived(chatId);
                    break;
                default:
                    String result = commandController.command(messageText);
                    sendMessage(chatId, result);
            }
        }
    }

    private void startCommandReceived(Long chatId) {
        String answer = "Enter command";
        sendMessage(chatId, answer);
    }

    private void sendMessage(Long chatId, String textToSend) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(String.valueOf(chatId));
        sendMessage.setText(textToSend);
        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            log.error(e.getMessage());
        }
    }
}
