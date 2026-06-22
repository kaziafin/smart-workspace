package smart_workspace.auth.dto;

public record LoginRequest(
        String email,
        String password
) {
}
