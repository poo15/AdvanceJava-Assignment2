package com.nagarro.ImageUtility.Entity;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@Table(name = "User",uniqueConstraints={@UniqueConstraint(columnNames={"userName"})})
@DynamicUpdate
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int user_Id;
	@Column(length=50)
	private String userName;
	private String password;
	private String recoveryAns;
	
	
	
	@OneToMany(mappedBy="user")
	private Collection<Image> imagebooks = new ArrayList<Image>();
	
	public int getUser_Id() {
		return user_Id;
	}
	

	public void setUser_Id(int user_Id) {
		this.user_Id = user_Id;
	}
	public String getUserName() {
		return userName;
	}
	@Column(unique = true)
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getRecoveryAns() {
		return recoveryAns;
	}
	public void setRecoveryAns(String recoveryAns) {
		this.recoveryAns = recoveryAns;
	}
	

	public Collection<Image> getImagebooks() {
		return imagebooks;
	}

	public void setImagebooks(Collection<Image> imagebooks) {
		this.imagebooks = imagebooks;
	}


	@Override
	public String toString() {
		return "User [user_Id=" + user_Id + ", userName=" + userName + ", password=" + password + ", recoveryAns="
				+ recoveryAns + ", imagebooks=" + imagebooks + "]";
	}
	
	
}
