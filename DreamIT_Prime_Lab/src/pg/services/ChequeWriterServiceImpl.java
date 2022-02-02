package pg.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pg.ChequeWriter.ChequeWriter;
import pg.dao.AssetDAO;
import pg.dao.ChequeWriterDAO;

@Service
public class ChequeWriterServiceImpl implements ChequeWriterService{

	@Autowired
	private ChequeWriterDAO cwDAO;
	
	@Override
	public List<ChequeWriter> getBankList() {
		// TODO Auto-generated method stub
		return cwDAO.getBankList();
	}

	@Override
	public boolean saveChequeWriter(ChequeWriter v) {
		// TODO Auto-generated method stub
		return cwDAO.saveChequeWriter(v);
	}

	@Override
	public List<ChequeWriter> getChequeList() {
		// TODO Auto-generated method stub
		return cwDAO.getChequeList();
	}

	@Override
	public List<ChequeWriter> getChequeWriterDetails(String writerId) {
		// TODO Auto-generated method stub
		return cwDAO.getChequeWriterDetails(writerId);
	}

	@Override
	public boolean addNewBank(ChequeWriter v) {
		// TODO Auto-generated method stub
		return cwDAO.addNewBank(v);
	}

}
