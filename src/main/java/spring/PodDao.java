package spring;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class PodDao {
	
	private List<Pod> pods = new ArrayList<>();
	private boolean flag = false;
	

	public List<Pod> getPods() {
		return pods;
	}

	public void setPods(List<Pod> pods) { //(규민) 추가 - PolicyList.java에서 사용됩니다만, 옳지 못한 구조라면 바꾸겠습니다
		this.pods = pods;
	}
	
	/* -각종 파드들을 분류하고 꺼내는 메서드들 작성- */
	
	/*
	 * //제가 알고 있는 바로는, 이름을 기준으로는 pod를 선택하지 않을 것 같습니다. 우리의 관리 용도(pod 구분 용도)로 사용합니다. (network policy에는 x)
	public Pod selectByPod(String name) {
		//이름을 매개변수로 주었을때 pod들중 해당되는 pod 리턴
		for (Pod pod : pods) {
			if(pod.getName().equals(name)) {
				return pod;
			}
		}
		return null;
	}
	*/
	

	//public List<Pod> selectByIp(List<ip 객체 or string(ip)->처리한 무언가(*.*.*.*) > Ips, List<Pod> pods)
	//public List<Pod> selectByIpBlock(String Ip, List<Pod> pods)
	
	public List<Pod> selectByLabel(Map<String, String> labels, List<Pod> pods) {
		//label을 매개변수로 주었을때 pod들중 해당되는 pod 리턴
		Map<String, String> podMap;  
		boolean flag = false;
		
		List<Pod> returnList = new ArrayList<Pod>();
		
		// 1:1 or 1 : n 허용(매개변수(policy) label: (pod의)label)
		
		for (Pod pod : pods) { //각각의 pod에 대해 검증 label 검증 
			//key값이 없는데, 특정 key값을 통해 값을 조회하려 하면 null을 반환합니다
			podMap = (Map<String, String>) pod.getLabels().entrySet();
			
			for (Map.Entry<String, String> label : labels.entrySet()) {
				if(!podMap.containsKey(label.getKey()) || !podMap.get(label.getKey()).equals(label.getValue())){
					flag = false;
					break;
				}else {
					flag = true;
				}
			}
			if(flag == true) { //해당 pod는 이 label에 일치 (or 포함)
				returnList.add(pod);
			}
		}
		
		if(returnList.isEmpty()) {
			//return null; //123줄 주석 참고  
			//PolicyList.java에서 범위를 좁혀 가므로, 만일 만족하는 게 없다면 기존의 입력된 pods를 유지하고 return한다 
			this.flag = false;
			return pods;
		}
		this.flag = true;
		return returnList;
	}
	

	public List<Pod> selectByLabel(Map<String, String> labels) {
		return selectByLabel(labels, this.pods);
	}
	
	
	
	public List<Pod> selectByNamespace(String namespace, List<Pod> pods) {
		//namespace을 매개변수로 주었을때 pod들중 해당되는 pod 리턴
		List<Pod> returnList = new ArrayList<Pod>();
		
		for (Pod pod : pods) {
			if(namespace.equals(pod.getNamespace())) {
				returnList.add(pod);
			}
		}
		if(returnList.isEmpty()) {
			//return null; //123줄 주석 참고
			this.flag = false;
			return pods;
		}
		this.flag = true;
		return returnList;
	}
	
	public List<Pod> selectByNamespace(String namespace) {
		return selectByNamespace(namespace, this.pods);
	}
		
	
	public List<Pod> selectByPort(String port, List<Pod> pods) {
		//port번호를 매개변수로 주었을때 pod들중 해당되는 pod 리턴
		List<Pod> returnList = new ArrayList<Pod>();
		for (Pod pod : pods) {
			if(pod.getPort().equals(port)) {
				returnList.add(pod);
			}
		}
		if(returnList.isEmpty()) {
			//return null; //123줄 주석 참고
			this.flag = false; //returnList가 pods 전체일 수 있으므로 구분
			return pods;
		}
		this.flag = true;
		return returnList;
	}
	
	// **********************왜 return null하지 않는가에 대한 설명 ******************************
	//(selectByEgressIngress와 달리) selectBySort같은 메소드에서는 flag를 write만 하지, read하지 않는다. 
	//왜냐하면 그것들은 pod를 select하는 마지막 조건이 아니기 때문이다. 범위를 좁혀 나가는 게 우리의 목적이다.
	//이 조건에 해당 안될 수도 있지만 다음 조건에는 해당될 수 있지 않은가? 그렇기에 이 조건에 해당되지 않더라도 지금까지는 null 반환이 아닌 pods(매개변수)를 반환한 것이다.
	
	//하지만 egress, ingress에서는 실제로 pod를 select하는 마지막 조건이다.
	//그러므로 egress, ingress에 의해 pod를 select할 때에는 조건이 맞지 않으면 null을 반환한다.
	
	//이렇게, '조건이 맞지 않을 때 점검한 모든 pod 반환'을 '~null 반환'으로 수정하기 위해 flag를 도입한 것이다.
	// ************************************************************************************
	
	public List<Pod> selectByEgressIngress(IngressEgressPolicy egressIngress, List<Pod> pods) {
		// namespaceSelector , podSelectorLabel, port에 따라 
		//PolicyList.java의 selectPods와 동작이 같습니다. (그곳에서의 코드를 단순하게 만들고 싶어 이곳엣 작업합니다. 
		//즉, selectoPods 메소드에서는 'egress조건에 걸리는 pod 선택해줘' 하면 이 메소드를 통과하기만 하면 pod를 얻어오는 것입니다.)
		this.flag = false;
		List<Pod> returnList = new ArrayList<Pod>();
		returnList = selectByLabel(egressIngress.getNamespaceSelectorLabel(), pods);
		returnList = selectByLabel(egressIngress.getPodSelectorLabel(), returnList);
		//returnList = selectByIp(egress.getIpBlock(), returnList); //ip블록은 일단 제친다 - 구현하더다도 이 class의 selectByIpBlock 메소드부터 구현해야 함 
		returnList = selectByPort(egressIngress.getPort(), returnList);
		if (flag == true) { //조건에 따라 어떠한 pod들이 select되었다
			return returnList;
		}
		else { //조건에 따라 어무런 pod도 select되지 못했다 
			return null; //아무런 pod도 해당 영역에 없음 
		}
	}
	
	public List<Pod> selectByEgressIngress(IngressEgressPolicy egressIngress) { //사용을 권항하지 않습니다. 이 egress 조건에 의한 pod select는 맨 마지막에 사용되기 때문입니다
		return selectByEgressIngress(egressIngress, this.pods);
	}
	
}
