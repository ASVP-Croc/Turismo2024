package users;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import elements.Request;

public abstract class AbstractUser {
	private final int id;
	private final String name;
	private final String surname;
	private String userName;
	private String email;
	private String phoneNumber;
	private Role role = Role.Tourist;
	private List<Action> actions;
	private static List<AbstractUser> users = new ArrayList<AbstractUser>();

	protected AbstractUser(int id, String name, String surname, String userName, String email, String phoneNumber) {
		this.id = id;
		this.name = name;
		this.surname = surname;
		this.userName = userName;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.actions = new ArrayList<>();
	}

	public Request sendRequest() throws IOException {
		return null;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getSurname() {
		return surname;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public List<Action> getActions() {
		return actions;
	}

	public void setActions(List<Action> actions) {
		this.actions = actions;
	}

	public static List<AbstractUser> getUsers() {
		return users;
	}

	public static boolean addUsers(AbstractUser user) {
		return AbstractUser.users.add(user);
	}
}