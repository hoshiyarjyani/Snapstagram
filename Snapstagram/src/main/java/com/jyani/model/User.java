package com.jyani.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.jyani.enums.Role;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long userId;

	@NotBlank(message = "Username is required")
	@Column(unique = true)
	private String username;

	@NotBlank(message = "Email is required")
	@Email(message = "Invalid email format")
	@Column(unique = true)
	private String email;

	@NotBlank(message = "Password is required")
	@Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).*$", message = "Password must contain at least one lowercase letter, one uppercase letter, and one digit")
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	private String password;

	private String profilePicture;

	private String bio;

	private Role role;

	private LocalDateTime timestamp = LocalDateTime.now();

	@OneToMany(mappedBy = "following",cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<User> followers = new ArrayList<>();

    @OneToMany(mappedBy = "follower",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private List<User> following = new ArrayList<>();


}
