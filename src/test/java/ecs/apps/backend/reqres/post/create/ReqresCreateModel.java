package ecs.apps.backend.reqres.post.create;

public class ReqresCreateModel {

	private String name;
	private String job;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getJob() {
		return job;
	}

	public void setJob(String job) {
		this.job = job;
	}

	public String toString() {
		return "\n" + "\n" + "{\n" + "    \"name\": \"" + name + "s\",\n" + "    \"job\": \"" + job + "\"\n" + "}";
	}

}
