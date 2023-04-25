package service.member;

import dao.member.JoinDAO;
import vo.JoinVO;
import vo.OrderVO;

public class JoinService {

		private JoinDAO joindao;
		
		public JoinService(JoinDAO joindao) {// DAO »£√‚
			this.joindao = joindao;
		}

		public int insert(JoinVO vo) {
			return joindao.insert(vo);
		}
		public int kakaoInsert(JoinVO vo) {
			return joindao.kakaoInsert(vo);
		}
		public String checkId(String mb_id) {
			return joindao.checkId(mb_id);
		}
		public int selectLastMb() {
			return joindao.selectLastMb();
		}
		public int joinCpn(OrderVO ord) {
			return joindao.joinCpn(ord);
		}
		public int selectCpnNo() {
			return joindao.selectCpnNo();
		}
		

		
		
}
