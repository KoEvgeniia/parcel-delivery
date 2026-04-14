package javacourse;

import com.fasterxml.jackson.databind.ObjectMapper;
import javacourse.config.TelegramBotConfig;
import javacourse.controller.CommandController;
import javacourse.controller.ConsoleController;
import javacourse.controller.ParcelProcess;
import javacourse.controller.TruckProcess;
import javacourse.factory.LoaderFactory;
import javacourse.repository.ParcelRepository;
import javacourse.service.ParcelHandler;
import javacourse.telegram.TelegramBot;
import javacourse.util.ParcelParser;
import javacourse.util.ParcelWriter;
import javacourse.util.TruckParser;
import javacourse.util.TruckWriter;
import lombok.extern.slf4j.Slf4j;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import java.io.InputStream;
import java.util.Objects;
import java.util.Properties;

@Slf4j
public class Main {
    public static void main(String[] args) {
        log.info("Starting...");
        Main.start();
    }

    private static void start() {
        ObjectMapper mapper = new ObjectMapper();
        ParcelHandler parcelHandler = new ParcelHandler(new ParcelRepository());
        TruckProcess truckProcess = new TruckProcess(new TruckParser(mapper), new ParcelWriter(), mapper);
        ParcelProcess parcelProcess = new ParcelProcess(new ParcelParser(parcelHandler), new LoaderFactory(), new TruckWriter(), mapper);
        CommandController commandController = new CommandController(parcelHandler, parcelProcess, truckProcess);

        Properties prop = new Properties();
        try (InputStream input = Main.class.getClassLoader().getResourceAsStream("application.properties")) {
            if (input == null) {
                log.error("File \"application.properties\" not found");
                return;
            }
            prop.load(input);

            if (Objects.equals(prop.getProperty("interface"), "telegram")) {
                TelegramBotConfig botConfig = TelegramBotConfig.builder().botName(prop.getProperty("bot.name")).token(prop.getProperty("bot.token")).build();
                TelegramBot telegramBot = new TelegramBot(botConfig, commandController);
                TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
                try{
                    telegramBotsApi.registerBot(telegramBot);
                } catch (TelegramApiException e){
                    log.error(e.getMessage());
                }
            } else if (Objects.equals(prop.getProperty("interface"), "console")) {
                ConsoleController consoleController = new ConsoleController(commandController);
                consoleController.listen();
            }
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }
}
