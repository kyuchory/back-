package spring;

import java.util.ArrayList;
import java.util.Map;

public class IngressEgressPolicy {
	private String ipBlock; // pod select 기준 1
	private Map<String, String> namespaceSelectorLabel; // pod select 기준 2 (label)
	private Map<String, String> podSelectorLabel; //pod select 기준 3 (label)
	private String port; //ingress/egress를 통해 연결될 때의 포트(추가정보) 
	
	/*-필드-*/
	/* -> 한꺼번에 PolicyList에서 담당 
	public void PodSelect(ArrayList<Pod> fromPod, ArrayList<Pod> toPod) {
		PolicyList.add(fromPod, toPod); //
	}
	*/
	
	/* -getter/setter- */
	public String getIpBlock() {
		return ipBlock;
	}
	public void setIpBlock(String ipBlock) {
		this.ipBlock = ipBlock;
	}
	
	public Map<String, String> getNamespaceSelectorLabel() {
		return namespaceSelectorLabel;
	}
	public void setNamespaceSelectorLabel(Map<String, String> namespaceSelectorLabel) {
		this.namespaceSelectorLabel = namespaceSelectorLabel;
	}
	public Map<String, String> getPodSelectorLabel() {
		return podSelectorLabel;
	}
	public void setPodSelectorLabel(Map<String, String> podSelectorLabel) {
		this.podSelectorLabel = podSelectorLabel;
	}
	public String getPort() {
		return port;
	}
	public void setPort(String port) {
		this.port = port;
	}
	/* -getter/setter- */
}
