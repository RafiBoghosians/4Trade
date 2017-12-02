package am.fourTrade.shoppingBackend.dto;


import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Table(name = "user_detail")
public class User implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// User Class Private Fields
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "first_name")
	@NotBlank(message = "Please enter your First Name!")
	private String firstName;

	@Column(name = "last_name")
	@NotBlank(message = "Please enter your Last Name!")
	private String lastName;

	@Column(name = "email")
	@NotBlank(message = "Please enter your Email Address!")
	private String email;
	
	@Column(name = "contact_number")
	@NotBlank(message = "Please enter your Contact Number!")
	private String contactNumber;

	@Column(name = "role")
	private String role;

	@Column(name = "password")
	@NotBlank(message = "Please enter your Password!")
	private String password;

	
	/*----------------------------*/
	//Confirm Password
	//By default all domain entity types will be persisted unless they are annotated with @Transient,
	@Transient
	private String confirmPassword;

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}
	/* --------------------------*/
	
	private boolean enabled = true;
	
	/* ---------------- */
	//"user" came from Cart.java in order to have parent(User) and child(Cart) relationship
	//User class will take the ownership of the relation
	// CascadeType.ALL -> any operation that we are going to perform with user it will affect also child tables
	@OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
	private Cart cart;

	public Cart getCart() {
		return cart;
	}

	public void setCart(Cart cart) {
		this.cart = cart;
	}
	/* ---------------- */

	// User Class Setters and Getters
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email
				+ ", contactNumber=" + contactNumber + ", role=" + role + ", password=" + password + ", enabled="
				+ enabled + "]";
	}
}
