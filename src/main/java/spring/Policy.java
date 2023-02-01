package spring;

import java.util.Map;

public class Policy {

	/*-필드-*/
	private String namespace;
	private Map<String, String> labels = null; //라벨이 여러개일테니 key,value로 나타내기 위해 Map사용
	//private podSelector ->이건 라벨을 포함하는 정보 아닌가?
	private Egress egress;
	/*-필드-*/
	
	/* -getter/setter- */
	public String getNameSpace() {
		return namespace;
	}
	public void setNameSpace(String nameSpace) {
		this.namespace = nameSpace;
	}
	public Map<String, String> getLabels() {
		return labels;
	}
	public void setLabels(Map<String, String> labels) {
		this.labels = labels;
	}
	public Egress getEgress() {
		return egress;
	}
	public void setEgress(Egress egress) {
		this.egress = egress;
	}
	/* -getter/setter- */
}
