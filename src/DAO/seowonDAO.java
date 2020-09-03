package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import DTO.seowonDTO;

public class seowonDAO {
	private Connection conn = null;
	private String driver = "oracle.jdbc.driver.OracleDriver";
	private String url = "jdvc:oracle:thin:@localhost:1521:orcl1"; // 오라클 두번깔게되서 orcl1로설정하였습니다.
	private String id = "system";
	private String pwd = "1111";
	private ResultSet rs = null;
	
	//싱근톤
//	public static seowonDAO seoado = null;
//	private seowonDAO() {					//생성자
//	}
//	public static seowonDAO getInstance() {
//		if(seoado!=null) {
//			seoado=new seowonDAO();
//		}
//		return seoado;
//	}
	
	public Connection conn() {
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, id, pwd);
			return conn;
		} catch (Exception e) {
		}
		return conn;
	}

	public ArrayList<seowonDTO> selectAll() { // 고객이 보기 위한 seowon 물품들 리스트를 출력해준다.(interface 구현가서 하자!)
		ArrayList<seowonDTO> seoList = new ArrayList<>();
		String sql = "select * from seowon";
		Statement st = null;
		if (conn() != null) {
			try {
				st = conn.createStatement();
				rs = st.executeQuery(sql);
				while (rs.next()) {
					seowonDTO seodto = new seowonDTO();
					seodto.setNo(rs.getInt("no"));
					seodto.setName(rs.getString("name"));
					seodto.setSize1(rs.getString("size1"));
					seodto.setPrice(rs.getInt("price"));
					seodto.setCnt(rs.getInt("cnt"));
					seoList.add(seodto);
				}
			} catch (Exception e) {
				System.out.println("출력실패!");
			} finally {
				try {
					if (conn != null)
						conn.close();
					if (st != null)
						st.close();
				} catch (SQLException e) {
					// TODO: handle exception
				}
			}
		}
		return seoList;
	}

	public void updateCnt(int no,int cnt) { // 고객이 구매한 수량만큼 차감
		
		String sql = "update seowon set cnt=cnt-? where no=?"; // no가 기본키 설정되잇더라면 반복문을 사용할 필요가없다.
		PreparedStatement ppst = null;
		if (conn() != null) {
			try {
				ppst = conn.prepareStatement(sql);
				ppst.setInt(1, cnt);
				ppst.setInt(2, no);
				ppst.executeUpdate();
			}catch (Exception e) {
			} finally {
				try {
					if (ppst != null) ppst.close();
					if (conn != null) conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		}
		
	}
}
