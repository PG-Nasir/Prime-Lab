package pg.dao;

import java.util.List;

import pg.assetModel.AssetInformation;
import pg.assetModel.VendorInformation;

public interface AssetDAO {
	boolean isAssetExist(AssetInformation v);
	boolean saveAsset(AssetInformation v);
	List<VendorInformation> getVendorList();
	List<AssetInformation> getAssetList();
}
