public class Main {
    public static void main(String[] args) {
        System.out.println("=== Base62 Sequential Shortener ===");
        Base62UrlShortener base62 = new Base62UrlShortener();
        System.out.println(base62.shorten("https://www.google.com"));
        System.out.println(base62.shorten("https://www.github.com"));
        System.out.println(base62.shorten("https://www.linkedin.com"));
        System.out.println(base62.shorten("https://www.google.com")); // dedup test
        String s1 = base62.shorten("https://www.openai.com");
        System.out.println("Resolved: " + base62.resolve(s1));

        System.out.println("\n=== Random Code Shortener ===");
        RandomUrlShortener random = new RandomUrlShortener();
        System.out.println(random.shorten("https://www.google.com"));
        System.out.println(random.shorten("https://www.github.com"));
        System.out.println(random.shorten("https://www.linkedin.com"));
        System.out.println(random.shorten("https://www.google.com")); // dedup test
        String s2 = random.shorten("https://www.openai.com");
        System.out.println("Resolved: " + random.resolve(s2));
    }
}
