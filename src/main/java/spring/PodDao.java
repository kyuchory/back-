package spring;

import java.util.ArrayList;
import java.util.List;

public class PodDao {
	
	private List<Pod> pods = new ArrayList<>();
	
	/* -각종 파드들을 분류하고 꺼내는 메서드들 작성- */
	public Pod selectByPodName(String name) {
		//이름을 매개변수로 주었을때 pod들중 해당되는 pod 리턴
		for (Pod pod : pods) {
			if(pod.getName().equals(name)) {
				return pod;
			}
			else {
				return null;
			}
		}
		return null;
	}
	
	/* -각종 파드들을 분류하고 꺼내는 메서드들 작성- */
}
