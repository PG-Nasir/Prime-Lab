package pg.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pg.assetModel.AssetInformation;
import pg.assetModel.VendorInformation;
import pg.dao.AssetDAO;


@Service
public class AssetServiceImpl implements AssetService{

	@Autowired
	private AssetDAO assetDAO;
	
	@Override
	public boolean isAssetExist(AssetInformation v) {
		// TODO Auto-generated method stub
		return assetDAO.isAssetExist(v);
	}

	@Override
	public boolean saveAsset(AssetInformation v) {
		// TODO Auto-generated method stub
		return assetDAO.saveAsset(v);
	}

	@Override
	public List<VendorInformation> getVendorList() {
		// TODO Auto-generated method stub
		return assetDAO.getVendorList();
	}

	@Override
	public List<AssetInformation> getAssetList() {
		// TODO Auto-generated method stub
		return assetDAO.getAssetList();
	}

}
