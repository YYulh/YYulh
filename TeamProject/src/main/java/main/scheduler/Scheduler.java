package main.scheduler;

import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;

import service.admin.AdCouponService;
import vo.CouponVO;
import vo.MemberVO;

public class Scheduler {

	@Autowired
	AdCouponService couponService;
	
	public boolean autoUpdate() {
		
	
		List<MemberVO> mo = couponService.selectBir();
		int birCpn = couponService.getBirCpn();
		CouponVO vo = new CouponVO();
		
		for(int i = 0; i < mo.size(); i++) {
			
			 char[] charList =
				    {'1','2','3','4','5','6','7','8','9','0','A','B','C','D','E','F',
				     'G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V',
				     'W','X','Y','Z'};
				
				int charCnt = charList.length;
				
				Random rnd = new Random();
				
				StringBuffer buf = new StringBuffer();
				
				for (int j= 0; j < 12; j++) {
				    buf.append(charList[rnd.nextInt(charCnt)]);
				   }
				String serial = buf.toString();
		
				vo.setCpn_no(birCpn);
				
				vo.setMb_no(mo.get(i).getMb_no());
				
				vo.setCr_serial(serial);
			
			couponService.insertUser(vo);
		}
	    return true;
	}
}
