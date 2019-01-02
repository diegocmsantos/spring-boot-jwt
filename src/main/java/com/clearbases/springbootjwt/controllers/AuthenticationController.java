package com.clearbases.springbootjwt.controllers;

import com.clearbases.springbootjwt.config.JwtTokenUtil;
import com.clearbases.springbootjwt.model.ApiResponse;
import com.clearbases.springbootjwt.model.AuthToken;
import com.clearbases.springbootjwt.model.LoginUser;
import com.clearbases.springbootjwt.model.User;
import com.clearbases.springbootjwt.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/token")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/generate-token", method = RequestMethod.POST)
    public ApiResponse<AuthToken> register(@RequestBody LoginUser loginUser) throws AuthenticationException {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginUser.getUsername(), loginUser.getPassword()));
        final User user = userService.findOne(loginUser.getUsername());
        final String token = jwtTokenUtil.generateToken(authentication);
        return new ApiResponse<>(200, "success", new AuthToken(token, user.getUsername()));
    }

}
