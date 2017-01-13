package theFridge.DAO;

import java.io.File;
import java.nio.file.FileSystems;
import java.nio.file.Path;

public class RedeemVoucherDAO {
	private static final String REDEEM_FILE = "RedeemVoucher.txt";
	private File dataFile;
	
	public RedeemVoucherDAO() {
		Path dPath = FileSystems.getDefault().getPath("theFridge.file", REDEEM_FILE);
		dataFile =  new File(dPath.toString());
	}
	
	public static void main(String[] args) {
		
	}

}
