import java.util.Map;
import java.util.HashMap;

public class Base62UrlShortener {
    // Dual HashMaps for O(1) lookup in BOTH directions
    private Map<String, String> codeToUrl = new HashMap<>();  // code -> long URL (for resolve)
    private Map<String, String> urlToCode = new HashMap<>();  // long URL -> code (for dedup)

    private static final String CHARS =
            "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private static final String BASE_URL = "https://short.ly/";
    private long counter = 1;  // auto-increment ID

    public String shorten(String longUrl) {
        // Idempotency: if already shortened, return existing code (O(1) now, not O(n))
        if (urlToCode.containsKey(longUrl)) {
            return BASE_URL + urlToCode.get(longUrl);
        }

        String code = encodeBase62(counter);  // encode the current ID
        counter++;                            // move to next ID — guarantees uniqueness

        codeToUrl.put(code, longUrl);
        urlToCode.put(longUrl, code);
        return BASE_URL + code;
    }

    public String resolve(String shortUrl) {
        String code = shortUrl.replace(BASE_URL, "");
        return codeToUrl.getOrDefault(code, "URL not found");
    }

    private String encodeBase62(long id) {
        StringBuilder sb = new StringBuilder();
        // Repeatedly divide by 62, mapping each remainder to a character
        while (id > 0) {
            int remainder = (int) (id % 62);
            sb.append(CHARS.charAt(remainder));
            id /= 62;
        }
        return sb.reverse().toString();  // reverse because we built it backwards
    }
}