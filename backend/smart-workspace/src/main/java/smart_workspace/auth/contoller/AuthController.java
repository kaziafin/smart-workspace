package smart_workspace.auth.contoller;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import smart_workspace.auth.dto.LoginRequest;
import smart_workspace.auth.dto.RegisterRequest;
import smart_workspace.auth.service.AuthService;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
      private  final AuthService authService;

       @PostMapping("/register")
      public String register(@RequestBody RegisterRequest request){
         authService.register(request);
         return  "User Registered successfully";
      }

    @PostMapping("/login")
    public String login(@RequestBody LoginRequest request) {

        return authService.login(request);
    }


}
