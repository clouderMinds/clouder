package hxiong.usermanager.info;

/**
 * 这是一个用户类，里面包含了用户的基本信息
 * 比如用户的帐号，用户的昵称等
 * @author 熊天成
 *
 */
public class UserInfo {
	private int uid;//用户的id
    private String account;//用户的帐号
    private String nickname;//用户的昵称
    private String other;//用户的描述
	public int getUid() {
		return uid;
	}
	public void setUid(int uid) {
		this.uid = uid;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getOther() {
		return other;
	}
	public void setOther(String other) {
		this.other = other;
	}
    
}
