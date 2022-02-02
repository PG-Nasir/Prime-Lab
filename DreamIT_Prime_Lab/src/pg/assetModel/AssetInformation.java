package pg.assetModel;

public class AssetInformation {
	String userId="";
	String assetId="";
	String assetName="";
	String assetTypeId="";
	String location="";
	String brand="";
	String model="";
	String serialNo="";
	String status="";
	String condition="";
	String note="";
	String vendorId="";
	String poNumber="";
	String purchaseDate="";
	String purchaseValue="";
	String qty="";
	String marketValue="";
	String scrapValue="";
	String depreciationMethod="";
	String estimateLife="";
	String categoryName="";
	
	public AssetInformation() {
		
	}
	
	public AssetInformation(String AssetId,String AssetName,String CategoryName) {
		this.assetId=AssetId;
		this.assetName=AssetName;
		this.categoryName=CategoryName;
	}
	
	public String getAssetName() {
		return assetName;
	}
	public void setAssetName(String assetName) {
		this.assetName = assetName;
	}
	public String getAssetTypeId() {
		return assetTypeId;
	}
	public void setAssetTypeId(String assetTypeId) {
		this.assetTypeId = assetTypeId;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public String getSerialNo() {
		return serialNo;
	}
	public void setSerialNo(String serialNo) {
		this.serialNo = serialNo;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getCondition() {
		return condition;
	}
	public void setCondition(String condition) {
		this.condition = condition;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public String getVendorId() {
		return vendorId;
	}
	public void setVendorId(String vendorId) {
		this.vendorId = vendorId;
	}
	public String getPoNumber() {
		return poNumber;
	}
	public void setPoNumber(String poNumber) {
		this.poNumber = poNumber;
	}
	public String getPurchaseDate() {
		return purchaseDate;
	}
	public void setPurchaseDate(String purchaseDate) {
		this.purchaseDate = purchaseDate;
	}
	public String getPurchaseValue() {
		return purchaseValue;
	}
	public void setPurchaseValue(String purchaseValue) {
		this.purchaseValue = purchaseValue;
	}
	public String getQty() {
		return qty;
	}
	public void setQty(String qty) {
		this.qty = qty;
	}
	public String getMarketValue() {
		return marketValue;
	}
	public void setMarketValue(String marketValue) {
		this.marketValue = marketValue;
	}
	public String getScrapValue() {
		return scrapValue;
	}
	public void setScrapValue(String scrapValue) {
		this.scrapValue = scrapValue;
	}
	public String getDepreciationMethod() {
		return depreciationMethod;
	}
	public void setDepreciationMethod(String depreciationMethod) {
		this.depreciationMethod = depreciationMethod;
	}
	public String getEstimateLife() {
		return estimateLife;
	}
	public void setEstimateLife(String estimateLife) {
		this.estimateLife = estimateLife;
	}
	public String getAssetId() {
		return assetId;
	}
	public void setAssetId(String assetId) {
		this.assetId = assetId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	
	
}
