package spring;

import java.util.HashMap;
import java.util.Map;

public class Pod {

	/*-필드-*/
	private String name;
	private Map<String, String> labels = null; //라벨이 여러개일테니 key,value로 나타내기 위해 Map사용
	private String nameSpace;
	private String ip;
	//... 등등 필요한 필드는 추가예정
	/*-필드-*/
	


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
		return nameSpace;
	}
	public void setNameSpace(String nameSpace) {
		this.nameSpace = nameSpace;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
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
}
