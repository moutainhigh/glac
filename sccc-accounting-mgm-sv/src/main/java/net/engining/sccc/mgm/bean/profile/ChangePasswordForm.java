package net.engining.sccc.mgm.bean.profile;

import java.io.Serializable;

import org.hibernate.validator.constraints.NotBlank;

import net.engining.pg.support.meta.PropertyInfo;

public class ChangePasswordForm implements Serializable {
	private static final long serialVersionUID = 4936567826391896309L;

	@NotBlank
	@PropertyInfo(name="原密码", length=100)
	private String oldPassword;
	
	@NotBlank
	@PropertyInfo(name="新密码", length=100)
	private String newPassword;
	
	@PropertyInfo(name="确认密码", length=100)
	private String retypePassword;
	
	public String getOldPassword() {
		return oldPassword;
	}

	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	public String getRetypePassword() {
		return retypePassword;
	}

	public void setRetypePassword(String retypePassword) {
		this.retypePassword = retypePassword;
	}
}
