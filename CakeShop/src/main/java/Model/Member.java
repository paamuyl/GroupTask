package Model;
// Generated 2020�~11��13�� �U��3:46:48 by Hibernate Tools 5.4.21.Final

import java.util.HashSet;
import java.util.Set;

/**
 * Member generated by hbm2java
 */
public class Member implements java.io.Serializable {

	private Integer memberId;
	private String account;
	private String password;
	private String memberName;
	private String gender;
	private String birthday;
	private String email;
	private String address;
	private String phoneNumber;
	private Set<Orderlist> orderlists = new HashSet<Orderlist>(0);

	public Member() {
	}

	public Member(Integer memberId, String account, String password, String memberName, String gender, String birthday,
			String email, String address, String phoneNumber) {
		super();
		this.memberId = memberId;
		this.account = account;
		this.password = password;
		this.memberName = memberName;
		this.gender = gender;
		this.birthday = birthday;
		this.email = email;
		this.address = address;
		this.phoneNumber = phoneNumber;
	}

	public Member(String account, String password, String memberName, String gender, String birthday, String email, String address,
			String phoneNumber, Set<Orderlist> orderlists) {
		this.account = account;
		this.password = password;
		this.memberName = memberName;
		this.gender = gender;
		this.birthday = birthday;
		this.email=email;
		this.address = address;
		this.phoneNumber = phoneNumber;
		this.orderlists = orderlists;
	}

	public Integer getMemberId() {
		return this.memberId;
	}

	public void setMemberId(Integer memberId) {
		this.memberId = memberId;
	}

	public String getAccount() {
		return this.account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getMemberName() {
		return this.memberName;
	}

	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}

	public String getGender() {
		return this.gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getBirthday() {
		return this.birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email=email;
	}
	
	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhoneNumber() {
		return this.phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public Set<Orderlist> getOrderlists() {
		return this.orderlists;
	}

	public void setOrderlists(Set<Orderlist> orderlists) {
		this.orderlists = orderlists;
	}

}