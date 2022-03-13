import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class MySuperBot extends TelegramLongPollingBot {

    private static final String TOKEN = "5254634820:AAH0nhcT7cwL4MvPhDtCj2h_qgyTbmxOFbw";
    private static final String USERNAME = "KTZHTestBot";

    public MySuperBot(DefaultBotOptions options) {
        super(options);
    }

    @Override
    public String getBotUsername() {
        return USERNAME;
    }

    @Override
    public String getBotToken() {
        return TOKEN;
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.getMessage() != null && update.getMessage().hasText()) {
            Long chatId = update.getMessage().getChatId();
            try {
                execute(new SendMessage(Long.toString(chatId),"HI " + update.getMessage().getText()));
            } catch (TelegramApiException e){
                e.printStackTrace();
            }
        }
    }
}
