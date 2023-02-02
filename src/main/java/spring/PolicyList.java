package spring;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class PolicyList {
private List<Policy> policyList = new ArrayList<>();
	
	/* -각종 policy들을 policyList에 insert,.. 메서드들 작성- */
	public void insert(Policy policy) {
		policyList.add(policy);
	}
	
	
	/*
	private String name; //***(규민) 추가, policy의 이름 
	private String namespace; //policy가 적용될 수 있는 namespace 범위 
	private Map<String, String> labels = null; //라벨이 여러개일테니 key,value로 나타내기 위해 Map사용

	private Egress egress;
	private Ingress ingress;
	
	private List<Pod> fromPods = new ArrayList<>(); 
	private List<Pod> toPods = new ArrayList<>();
	//위 두 list를 2차원 배열로 만들까 고민중 -> private List<Pod>[] pods = new ArrayList[2]; //2차원 배열(flag, pod)
	*/
	
	
	/* -policyList를 순회하며, 해당 policy의 조건과 맞는 pod를 찾는 method*/
	//만일 pod 하나가 추가된다면?
	//만일 policy 하나가 추가된다면?
	//지금의 코드는 하나가 갱신되면 모든 pod, policy를 다시 확인한다(비효율) 일단 이렇게 진행.
	public void selectPods(PodDao podDao) { // 이렇게 Dao class를 직접 써도 되는지에 대한 의문...**** (일다 controller에서 접근해 사용)
		List<Pod> middleProcessDaoList = new ArrayList<Pod>();
		List<Pod> ToPods = new ArrayList<Pod>();
		List<Pod> FromPods = new ArrayList<Pod>();
		
		PodDao middlePodDao = new PodDao(); //PodDao 객체를 여러 개 둬도 괜찮은지에 대한 의문...******
		
		for (Policy policy : policyList) {
			//policy 중첩해서 적용 (AND 연산을 하지 않음 -> 한 번에 동작x, 범위를 서서히 조여가는 방식(middlePodDao의 범위가 줄어든다)) )
			middleProcessDaoList = podDao.selectByNamespace(policy.getNamespace()); //namespace가 안나와있으면 다음 pod select 기준으로 넘어감 (selectByLabel)
			middlePodDao.setPods(middleProcessDaoList);
			
			middleProcessDaoList = middlePodDao.selectByLabel(policy.getLabels());
			middlePodDao.setPods(middleProcessDaoList);
			
			//--- 여기까지 하면 ingress, egress 조건 확인할 준비 완료 
			
			ToPods = middlePodDao.selectByEgressIngress(policy.getIngress(), middleProcessDaoList);
			//policy.insertPod //새로운 pod yaml이 들어오면..? 딱히 insertPod 안만들어도 될듯
			policy.setToPods(FromPods);
			
			middlePodDao.selectByEgressIngress(policy.getEgress(), middleProcessDaoList);
			ToPods = middlePodDao.selectByEgressIngress(policy.getIngress(), middleProcessDaoList);
			policy.setToPods(ToPods);
		}
	}
	
	/* -특정 policy 삭제
	public void delete(Policy policy) { //특정 policy 삭제
	
	}
	*/
}
