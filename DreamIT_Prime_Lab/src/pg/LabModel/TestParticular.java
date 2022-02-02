package pg.LabModel;

public class TestParticular {
	String particularId="";
	String particularName="";
	
	public TestParticular() {
		
	}
	
	public TestParticular(String particularId, String particularName) {
		this.particularId = particularId;
		this.particularName = particularName;
	}
	
	
	public String getParticularId() {
		return particularId;
	}
	public void setParticularId(String particularId) {
		this.particularId = particularId;
	}
	public String getParticularName() {
		return particularName;
	}
	public void setParticularName(String particularName) {
		this.particularName = particularName;
	}
	
	
}
