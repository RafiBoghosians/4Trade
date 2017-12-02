package am.fourTrade.shoppingBackend.dto;



import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.hibernate.validator.constraints.NotBlank;

@Entity
public class Address implements Serializable  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// Address Class Private Fields
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	/*@Column(name = "user_id")
	private int userId;*/
	
	/*-----------------------*/
	//ManyToOne relationship so more than one address can belong to a single user
	
	@ManyToOne
	private User user;
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	/*-----------------------*/
	
	
	@Column(name = "address_line_one")
	@NotBlank(message = "Please enter your Address Line One!")
	private String addressLineOne;

	@Column(name = "address_line_two")
	@NotBlank(message = "Please enter your Address Line Two!")
	private String addressLineTwo;

	@Column(name = "city")
	@NotBlank(message = "Please enter your City!")
	private String city;

	@Column(name = "state")
	@NotBlank(message = "Please enter your State!")
	private String state;

	@Column(name = "country")
	@NotBlank(message = "Please enter your Country!")
	private String country;

	@Column(name = "postal_code")
	@NotBlank(message = "Please enter your Postal Code!")
	private String postalCode;

	// If the boolean value for billing is true, it means that
	// the address will be use as a communication address
	// we can use multiple shipping address for the same user

	@Column(name = "is_billing")
	private boolean billing;

	@Column(name = "is_shipping")
	private boolean shipping;

	// Address Class Setters and Getters
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	/*public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}*/

	public String getAddressLineOne() {
		return addressLineOne;
	}

	public void setAddressLineOne(String addressLineOne) {
		this.addressLineOne = addressLineOne;
	}

	public String getAddressLineTwo() {
		return addressLineTwo;
	}

	public void setAddressLineTwo(String addressLineTwo) {
		this.addressLineTwo = addressLineTwo;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public boolean isBilling() {
		return billing;
	}

	public void setBilling(boolean billing) {
		this.billing = billing;
	}

	public boolean isShipping() {
		return shipping;
	}

	public void setShipping(boolean shipping) {
		this.shipping = shipping;
	}

	@Override
	public String toString() {
		return "Address [id=" + id + ", user=" + user + ", addressLineOne=" + addressLineOne + ", addressLineTwo="
				+ addressLineTwo + ", city=" + city + ", state=" + state + ", country=" + country + ", postalCode="
				+ postalCode + ", billing=" + billing + ", shipping=" + shipping + "]";
	}

	 
	
	

}
