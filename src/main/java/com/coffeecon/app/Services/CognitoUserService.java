package com.coffeecon.app.Services;

import com.amazonaws.services.cognitoidp.AWSCognitoIdentityProvider;
import com.amazonaws.services.cognitoidp.model.*;
import com.coffeecon.app.Models.AuthModels.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.Map;

@Service
public class CognitoUserService {

    @Value("${app.aws.cognito.clientId}")
    private String clientId;

    @Value("${app.aws.cognito.userPoolId}")
    private String userPoolId;

    @Autowired
    private AWSCognitoIdentityProvider cognitoClient;


    public CognitoAuthToken authenticate(LoginRequestDTO login_req) {

        InitiateAuthRequest authRequest = new InitiateAuthRequest()
                .withAuthFlow(AuthFlowType.USER_PASSWORD_AUTH)
                .addAuthParametersEntry("USERNAME",login_req.getUsername())
                .addAuthParametersEntry("PASSWORD",login_req.getPassword())
                .withClientId(clientId);

        InitiateAuthResult authResult = cognitoClient.initiateAuth(authRequest);

        if (authResult.getChallengeName() != null && !authResult.getChallengeName().isEmpty() ) {
            throw new PasswordResetRequiredException("Password reset required");
        } else {
            AuthenticationResultType resultType = authResult.getAuthenticationResult();

            return new CognitoAuthToken() {{
                setIdToken(resultType.getIdToken());
                setAccessToken(resultType.getAccessToken());
                setRefreshToken(resultType.getRefreshToken());
            }};
        }
    }


    // refresh a token
    public CognitoAuthToken refreshToken(String refreshToken) {
        Map<String, String> authParams = new LinkedHashMap<String, String>() {{
            put("REFRESH_TOKEN", refreshToken);
        }};
        InitiateAuthRequest authRequest = new InitiateAuthRequest()
                .withAuthFlow(AuthFlowType.REFRESH_TOKEN_AUTH)
                .withClientId(clientId)
                .addAuthParametersEntry("REFRESH_TOKEN", refreshToken);
        InitiateAuthResult authResult = cognitoClient.initiateAuth(authRequest);
        AuthenticationResultType resultType = authResult.getAuthenticationResult();

        return new CognitoAuthToken() {{
            setIdToken(resultType.getIdToken());
            setAccessToken(resultType.getAccessToken());
            setRefreshToken(resultType.getRefreshToken());
        }};
    }


    public void signUp(RegisterRequestDTO register_req) {

        AttributeType emailAttr =
                new AttributeType().withName("email").withValue(register_req.getEmail());

        SignUpRequest signUpRequest = new SignUpRequest()
                .withClientId(clientId)
                .withUsername(register_req.getUsername())
                .withUserAttributes(emailAttr)
                .withPassword(register_req.getPassword());
        cognitoClient.signUp(signUpRequest);
    }

    public void confirmSignUp(ConfirmRegistrationDTO confirmation) {
        ConfirmSignUpRequest confirmSignUpRequest = new ConfirmSignUpRequest()
                .withClientId(clientId)
                .withUsername(confirmation.getUsername())
                .withConfirmationCode(confirmation.getConfirmationCode());
         cognitoClient.confirmSignUp(confirmSignUpRequest);
    }


    public void resendOtp(ResendOTPRequestDTO resend_req) {
            ResendConfirmationCodeRequest request = new ResendConfirmationCodeRequest()
                    .withClientId(clientId)
                            .withUsername(resend_req.getUsername());
            cognitoClient.resendConfirmationCode(request);
    }
}
