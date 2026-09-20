# URL Shortener — Two Design Approaches

A comparison of two common URL-shortening strategies, implemented in Java.

## Approaches

### 1. Base62 Sequential Encoding
- Auto-incrementing counter encoded into base62 (a-z, A-Z, 0-9)
- Deterministic, no collisions possible
- O(1) duplicate detection via a reverse (URL → code) map
- Codes are predictable/guessable (not ideal if enumeration is a concern)

### 2. Random Code Generation
- Random 6-character code, retried on collision
- Non-predictable codes
- Duplicate detection is O(n) here (linear scan) — a known limitation;
  fixable with the same reverse-map trick used in the Base62 version

## Tradeoffs

| | Base62 | Random |
|---|---|---|
| Collision handling | None needed | Retry loop |
| Dedup lookup | O(1) | O(n) |
| Predictability | Sequential/guessable | Non-sequential |
| Code length growth | Grows with volume | Fixed length |

## Limitations (by design — this is a learning project)
- In-memory storage only (no persistence layer)
- Not thread-safe
- Single JVM instance (no distributed ID generation)

## How to run
javac src/*.java -d out
java -cp out Main

## Possible extensions
- Persist to a database (Postgres/Redis) instead of HashMap
- Add expiry/TTL for links
- Add analytics (click counts)
- Make thread-safe with ConcurrentHashMap + AtomicLong