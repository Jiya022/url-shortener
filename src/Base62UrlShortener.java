import java.util.Map;
import java.util.HashMap;

public class Base62UrlShortener {
    private Map<String, String> codeToUrl = new HashMap<>(); 
    private Map<String, String> urlToCode = new HashMap<>();  

    private static final String CHARS =
            "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private static final String BASE_URL = "https://short.ly/";
    private long counter = 1;

    public String shorten(String longUrl) {
        if (urlToCode.containsKey(longUrl)) {
            return BASE_URL + urlToCode.get(longUrl);
        }

        String code = encodeBase62(counter); 
        counter++;                          

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
        while (id > 0) {
            int remainder = (int) (id % 62);
            sb.append(CHARS.charAt(remainder));
            id /= 62;
        }
        return sb.reverse().toString(); 
    }
}