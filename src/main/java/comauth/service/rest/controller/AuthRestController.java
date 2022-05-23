package comauth.service.rest.controller;

import comauth.service.dto.LoginRequest;
import comauth.service.dto.UserDTO;
import comauth.service.models.UserEntity;
import comauth.service.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import comauth.service.util.JwtUtil;

@RestController
public class AuthRestController {

	@Autowired
	private JwtUtil jwtUtil;

	@Autowired
	private UserService userService;

	@PostMapping("/auth/login")
	public ResponseEntity<String> login(@RequestBody LoginRequest dto) {
		UserEntity user = userService.findByUserName(dto.getUserName());

		if(user!=null && user.getPassword().equals(dto.getPassword())){
			String token = jwtUtil.generateToken(dto.getUserName());

			return new ResponseEntity<String>(token, HttpStatus.OK);
		}
		return new ResponseEntity<String>("Credenetial Invalid", HttpStatus.UNAUTHORIZED);


	}

	@PostMapping("/auth/register")
	public ResponseEntity<String> register(@RequestBody String userName) {
		// Persist user to some persistent storage
		System.out.println("Info saved...");

		return new ResponseEntity<String>("Registered", HttpStatus.OK);
	}

}
