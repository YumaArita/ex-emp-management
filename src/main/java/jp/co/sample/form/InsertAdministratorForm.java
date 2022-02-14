package jp.co.sample.form;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class InsertAdministratorForm{
	
	@NotBlank(message="名前は必須項目です")
	private String name;
	@Size(min=1, max=127, message="メールは1文字以上127文字以内で記載してください")
	@Email(message="Eメールの形式が不正です")
	private String mailAddress;
	@Size(min=1, max=127, message="メールは1文字以上127文字以内で記載してください")
	private String password;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMailAddress() {
		return mailAddress;
	}

	public void setMailAddress(String mailAddress) {
		this.mailAddress = mailAddress;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "InsertAdministrator [name=" + name + ", mailAddress=" + mailAddress + ", password=" + password + "]";
	}
	
	

}
