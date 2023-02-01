package spring;

public class Egress {
	/*-필드-*/
	private String ipBlock;
	private String namespaceSelector;
	private String podSelector;
	private String port;
	/*-필드-*/
	
	/* -getter/setter- */
	public String getIpBlock() {
		return ipBlock;
	}
	public void setIpBlock(String ipBlock) {
		this.ipBlock = ipBlock;
	}
	public String getNamespaceSelector() {
		return namespaceSelector;
	}
	public void setNamespaceSelector(String namespaceSelector) {
		this.namespaceSelector = namespaceSelector;
	}
	public String getPodSelector() {
		return podSelector;
	}
	public void setPodSelector(String podSelector) {
		this.podSelector = podSelector;
	}
	public String getPort() {
		return port;
	}
	public void setPort(String port) {
		this.port = port;
	}
	/* -getter/setter- */
	
}
