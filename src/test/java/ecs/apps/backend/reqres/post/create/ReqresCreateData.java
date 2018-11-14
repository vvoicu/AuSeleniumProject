package ecs.apps.backend.reqres.post.create;

public class ReqresCreateData {

	
	
	public static String generateCreatePostBody() {
		ReqresCreateModel data = new ReqresCreateModel();
		data.setJob("jjjj");
		data.setName("nsdnasdas");
		return data.toString();
	}
}
