package spring;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Pod {

	/*-필드-*/
	private String name;
	private Map<String, String> labels = null; //라벨이 여러개일테니 key,value로 나타내기 위해 Map사용         //pod select 
	private String namespace;
	private String ip;
	private String port;
	private List<Policy>[] policies = new ArrayList[2]; //2차원 배열(flag, policy) 
	// -> policies[0] = FLAG.FROM(0), policies[1] = FLAG.TO(1)
	// -> policies[0][0] = this(pod)가 FLAG.FROM의 조건에 해당하는 네트워크 정책 1
	
	//private String service; - 일단 고려 x
	//private String endPoint; - 일단 고려 x
	//... 등등 필요한 필드는 추가예정
	
	//linked list를 위한 필드 
	//private Pod nextPod;
	
	
	/*-필드-*/
	
	/*-생성자-*/
	public Pod() {}
	
	public Pod(String name, Map<String, String> labels, String nameSpace, String ip, String port, String service,
			String endPoint) {
		super();
		this.name = name;
		this.labels = labels;
		this.namespace = nameSpace;
		this.ip = ip;
		this.port = port;
		//this.service = service;
		//this.endPoint = endPoint;
	}
	/*-생성자-*/
	
	/* -getter/setter- */
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Map<String, String> getLabels() {
		return labels;
	}
	public void setLabels(Map<String, String> labels) {
		this.labels = labels;
	}
	public String getNamespace() {
		return namespace;
	}
	public void setNamespace(String nameSpace) {
		this.namespace = nameSpace;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getPort() {
		return port;
	}
	public void setPort(String port) {
		this.port = port;
	}
	/*
	public String getService() {
		return service;
	}
	public void setService(String service) {
		this.service = service;
	}
	public String getEndPoint() {
		return endPoint;
	}
	public void setEndPoint(String endPoint) {
		this.endPoint = endPoint;
	}
	*/
	/* -getter/setter- */
	
	
	
	
	/* -(field)policies 리스트에 값을 채우는 method 작성*/
	//Policy.java의 insertFromPods method, insertToPods에서 사용합니다
	//한 policy의 대상이 되는 pod를 찾았을 때 해당 pod에도 어떠한 policy가 쓰이는지를 적어두는 것입니다
	public void inserPolicy(Policy policy, FLAG flag) {
		this.policies[flag.ordinal()].add(policy);  //flag가 FROM이라면 policies[0]에, flag가 TO라면 policies[1]에 policy가 저장됩니다
	}
	
	
	/* -label put/remove- */
	public void putLabels(String key, String value) {
		labels.put(key, value);
	}
	
	public void removeLabels(String key) {
		labels.remove(key);
	}
	/* --- */
	
	/*
	public void linkInsert(Pod nextPod) {
		this.nextPod = nextPod;
	}
	
	//내부에 네트워크폴리시를 가지게 됐을때 어떻게 연결해줄지
	//뭐 예를들면 파드 B로 가는 연결점을 만들어라
	/*
	 * 
	 if(this.policy){
	 	if(파드 B로 가는 연결점 open){
	 		this.nextPod = podB;
	 	}
	 }
	 * 
	 */
}
