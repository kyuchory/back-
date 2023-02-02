package spring;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Policy {

	/*-필드-*/
	private String name; //***(규민) 추가, policy의 이름 
	private String namespace; //policy가 적용될 수 있는 namespace 범위 
	private Map<String, String> labels = null; //라벨이 여러개일테니 key,value로 나타내기 위해 Map사용

	private Egress egress;
	private Ingress ingress;
	
	private List<Pod> fromPods = new ArrayList<>(); 
	private List<Pod> toPods = new ArrayList<>();
	//위 두 list를 2차원 배열로 만들까 고민중 -> private List<Pod>[] pods = new ArrayList[2]; //2차원 배열(flag, pod)
	/*-필드-*/
	
	
	/* -getter/setter- */
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String getNamespace() {
		return namespace;
	}
	public void setNamespace(String namespace) {
		this.namespace = namespace;
	}
	
	public Ingress getIngress() {
		return ingress;
	}
	public void setIngress(Ingress ingress) {
		this.ingress = ingress;
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
	
	public List<Pod> getFromPods() {
		return fromPods;
	}
	public void setFromPods(List<Pod> fromPods) {
		this.fromPods = fromPods;
	}
	public List<Pod> getToPods() {
		return toPods;
	}
	public void setToPods(List<Pod> toPods) {
		this.toPods = toPods;
	}
	/* -(field)fromPods, toPods 리스트에 값을 채우는 method 작성*/
	public void insertFromPods(Pod insertPod) {
		this.fromPods.add(insertPod);
		insertPod.inserPolicy(this, FLAG.FROM);
	}
	
	public void insertToPods(Pod insertPod) {
		this.toPods.add(insertPod);
		insertPod.inserPolicy(this, FLAG.TO);
	}
	
	
	
	
	
	
	
}
