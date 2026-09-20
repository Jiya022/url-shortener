import java.util.Map;
import java.util.HashMap;
import java.util.Random;

public class RandomUrlShortener {
    private Map<String, String> store = new HashMap<>();
    private static final String CHARS =
            "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private static final int CODE_LENGTH = 6;
    private static final String BASE_URL = "https://short.ly/";
    private static final Random RAND = new Random();

    public String shorten(String longUrl) {
        for (Map.Entry<String, String> e : store.entrySet()) {
            if (e.getValue().equals(longUrl)) {
                return BASE_URL + e.getKey();
            }
        }
        String code;
        do {
            code = generateCode();
        } while (store.containsKey(code));

        store.put(code, longUrl);
        return BASE_URL + code;
    }

    public String resolve(String shortUrl) {
        String code = shortUrl.replace(BASE_URL, "");
        return store.getOrDefault(code, "URL not found");
    }

    private String generateCode() {
        StringBuilder sb = new StringBuilder(CODE_LENGTH);
        for (int i = 0; i < CODE_LENGTH; i++) {
            sb.append(CHARS.charAt(RAND.nextInt(CHARS.length())));
        }
        return sb.toString();
    }
}