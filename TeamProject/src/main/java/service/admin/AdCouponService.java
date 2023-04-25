package service.admin;

import java.util.List;

import dao.admin.AdCouponDAO;
import vo.CouponVO;
import vo.GradeVO;
import vo.MemberVO;


public class AdCouponService {

	private AdCouponDAO coupondao;
	
	public AdCouponService(AdCouponDAO coupondao) {// DAO »£√‚
		this.coupondao = coupondao;
	}
	public List<CouponVO> selectCpnList(int start){
		return coupondao.selectCpnList(start);
	}
	public int getTotal() {
		return coupondao.getTotal();
	}
	public List<GradeVO> selectGrade() {
		return coupondao.selectGrade();
	}
	public int newCoupon(CouponVO vo) {
		return coupondao.newCoupon(vo);
	}
	public int deleteCpn(String no){
		return coupondao.deleteCpn(no);
	}
	public int getRimit(int no){
		return coupondao.getRimit(no);
	}
	public List<MemberVO> selectMbList(int limit) {
		return coupondao.selectMbList(limit);
	}
	public int insertUser(CouponVO vo){
		return coupondao.insertUser(vo);
	}
	public List<MemberVO> selectBir(){
		return coupondao.selectBir();
	}
	public int getBirCpn(){
		return coupondao.getBirCpn();
	}
	
}
