package spring;

import java.util.HashMap;
import java.util.Map;

public class Pod {

	/*-필드-*/
	private String name;
	private Map<String, String> labels = null; //라벨이 여러개일테니 key,value로 나타내기 위해 Map사용
	private String namespace;
	private String ip;
	private String port;
	private String service;
	private String endPoint;
	//... 등등 필요한 필드는 추가예정
	private Pod nextPod;
	private Policy policy;
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
		this.service = service;
		this.endPoint = endPoint;
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
	public String getNameSpace() {
		return namespace;
	}
	public void setNameSpace(String nameSpace) {
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
	/* -getter/setter- */
	
	
	/* -label put/remove- */
	public void putLabels(String key, String value) {
		labels.put(key, value);
	}
	
	public void removeLabels(String key) {
		labels.remove(key);
	}
	/* --- */
	
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
