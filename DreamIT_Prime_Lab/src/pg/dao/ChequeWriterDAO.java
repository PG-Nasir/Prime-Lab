package pg.dao;

import java.util.List;

import pg.ChequeWriter.ChequeWriter;

public interface ChequeWriterDAO {
	List<ChequeWriter> getBankList();
	boolean saveChequeWriter(ChequeWriter v);
	List<ChequeWriter> getChequeList();
	List<ChequeWriter> getChequeWriterDetails(String writerId);
	boolean addNewBank(ChequeWriter v);
}
