package Entity;

public class UserProfile {
	private String id;
	private long mysqlId;
	private String profile;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public long getMysqlId() {
		return mysqlId;
	}
	public void setMysqlId(long mysqlId) {
		this.mysqlId = mysqlId;
	}
	public String getProfile() {
		return profile;
	}
	public void setProfile(String profile) {
		this.profile = profile;
	}
}
