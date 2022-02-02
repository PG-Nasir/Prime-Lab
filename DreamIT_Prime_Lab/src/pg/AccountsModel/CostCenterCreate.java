package pg.AccountsModel;

public class CostCenterCreate {
	String costCenterId="";
	String name="";
	
	public CostCenterCreate() {
		
	}
	
	
	
	public CostCenterCreate(String costCenterId, String name) {
		this.costCenterId = costCenterId;
		this.name = name;
	}



	public String getCostCenterId() {
		return costCenterId;
	}
	public void setCostCenterId(String costCenterId) {
		this.costCenterId = costCenterId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	
}
