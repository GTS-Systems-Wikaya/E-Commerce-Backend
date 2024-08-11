package projet.gtssystem.auth;

import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import projet.gtssystem.Security.JwtService;
import projet.gtssystem.User.*;
import projet.gtssystem.email.EmailService;

import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.HashMap;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final TokenRepository tokenRepository;
    private final EmailService emailService;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    public void register(RegistrationRequest request) throws MessagingException {
        var user= User.builder()
                .email(request.getEmail())
                .nom(request.getNom())
                .prenom(request.getPrenom())
                .role(Role.USER)
                .addresse(request.getAdresse())
                .numTel(request.getNumTel())
                .password(passwordEncoder.encode(request.getPassword()))
                .enabled(false)
                .build();
        userRepository.save(user);
        //send validation email
        sendValidationEmail(user);
    }

    private void sendValidationEmail(User user) throws MessagingException {
        var newToken=generateAndSaveActivationToken(user);
        //send mail logic
        emailService.sendValidationEmail(user.getEmail(),newToken,"Mail de confirmation","code de vérification");
    }

    private String generateAndSaveActivationToken(User user) {
        String generatedValue=generateActivationCode(6);
        var token = Token.builder()
                .token(generatedValue)
                .createdAt(LocalDateTime.now())
                .expiresAt(LocalDateTime.now().plusMinutes(15))
                .user(user)
                .build();
        tokenRepository.save(token);
        return generatedValue;
    }

    private String generateActivationCode(int length) {
        String characters = "0123456789";
        StringBuilder codeBuilder = new StringBuilder();

        SecureRandom secureRandom = new SecureRandom();

        for (int i = 0; i < length; i++) {
            int randomIndex = secureRandom.nextInt(characters.length());
            codeBuilder.append(characters.charAt(randomIndex));
        }

        return codeBuilder.toString();
    }

    @Transactional
    public void activateAccount(String token) throws MessagingException {
        Token savedToken = tokenRepository.findByToken(token)
                .orElseThrow(() -> new RuntimeException("Invalid token"));
        if (LocalDateTime.now().isAfter(savedToken.getExpiresAt())) {
            tokenRepository.delete(savedToken);
            sendValidationEmail(savedToken.getUser());
            throw new RuntimeException("Activation token has expired. A new token has been send to the same email address");
        }

        var user = userRepository.findById(savedToken.getUser().getUserId())
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        user.setEnabled(true);
        userRepository.save(user);

        savedToken.setValidatedAt(LocalDateTime.now());
        tokenRepository.save(savedToken);
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        String email= request.getEmail();
        String password= request.getPassword();
        User user=userRepository.findByEmail(email).orElseThrow(()->new SecurityException("User not found !"));
        System.out.println(user.getPassword());
        System.out.println(password);
        if(!passwordEncoder.matches(password, user.getPassword()))
            return AuthenticationResponse.builder()
                .token("Wrong password!")
                .build();
        if(!user.isEnabled())
            return AuthenticationResponse.builder()
                    .token("Account is disabled!")
                    .build();
        var jwtToken= jwtService.generateToken(user.getUsername());
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    public void forgetPassword(String email, String newPassword) {
        User user=userRepository.findByEmail(email).orElseThrow(()-> new SecurityException("User not found !"));
        user.setPassword(passwordEncoder.encode(newPassword));
        userRepository.save(user);
    }

    public boolean sendForgetPasswordMail(String email) throws MessagingException {
        User user=userRepository.findByEmail(email).orElse(null);
        if(user==null) return false;

        emailService.sendValidationEmail(email
                ,"http://localhost:4200/forgot-password?email="+email
                ,"Changement de mot de passe"
                ,"Lien de changement de mot de passe : ");
        return true;
    }
}
