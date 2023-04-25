package dao.member;

import org.apache.ibatis.session.SqlSession;

import vo.JoinVO;
import vo.OrderVO;

public class JoinDAO {
	private SqlSession sqlSession;
	
	public JoinDAO(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}
	
		public int insert(JoinVO vo) {
			return sqlSession.insert("join.insert",vo);
		}
		public int kakaoInsert(JoinVO vo) {
			return sqlSession.insert("join.kakaoInsert",vo);
		}
		public String checkId(String mb_id) {
			return sqlSession.selectOne("join.checkId",mb_id);
		}
		public int selectLastMb() {
			return sqlSession.selectOne("join.selectLastMb");
		}
		public int joinCpn(OrderVO ord) {
			return sqlSession.insert("join.joinCpn",ord);
		}
		public int selectCpnNo() {
			return sqlSession.selectOne("join.selectCpnNo");
		}	
}
