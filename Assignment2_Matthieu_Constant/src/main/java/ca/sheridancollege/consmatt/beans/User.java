package ca.sheridancollege.consmatt.beans;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
	
	private String userName;
	private String encryptedPassword; //Creating constructors for User bean
	private Long userId;

}
