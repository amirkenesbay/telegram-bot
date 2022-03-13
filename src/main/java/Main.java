import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

public class Main {
    private static final String PROXY_HOST = "143.244.182.139" /* proxy host */;
    private static final Integer PROXY_PORT = 3784 /* proxy port */;
    private static final String TOKEN = "5254634820:AAH0nhcT7cwL4MvPhDtCj2h_qgyTbmxOFbw";
    private static final String USERNAME = "KTZHTestBot";
    public static void main(String[] args) {
        try {
            // Create the TelegramBotsApi object to register your bots
            TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);

            // Set up Http proxy
            DefaultBotOptions botOptions = new DefaultBotOptions();

            botOptions.setProxyHost(PROXY_HOST);
            botOptions.setProxyPort(PROXY_PORT);
            // Select proxy type: [HTTP|SOCKS4|SOCKS5] (default: NO_PROXY)
            botOptions.setProxyType(DefaultBotOptions.ProxyType.SOCKS5);

            // Register your newly created AbilityBot
            MySuperBot mySuperBot = new MySuperBot(botOptions);
            telegramBotsApi.registerBot(mySuperBot);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }

    }
}
