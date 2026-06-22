package smart_workspace.auth.dto;

public record RegisterRequest( String companyName,
                              String firstName,
                              String lastName,
                              String email,
                              String password
) {

}
