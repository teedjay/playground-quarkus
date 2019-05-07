package quarkus.jwt;

import java.time.LocalDateTime;
import java.util.Set;

public class ProtectedData {
    public String username;
    public Set<String> groups;
    public Set<String> claimNames;
    public String timestamp = LocalDateTime.now().toString();
}
