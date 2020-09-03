package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import DTO.seobasketDTO;
import DTO.seowonDTO;

public class seobasketDAO {
	private Connection conn = null;// oracle 접속하기위한 연결 컨넥션.
	private String driver = "oracle.jdbc.driver.OracleDriver";
	private String url = "jdvc:oracle:thin:@localhost:1521:orcl1";
	private String id = "system";
	private String pwd = "1111";
	private ResultSet rs = null;
	
	//싱근톤
//	public static seobasketDAO bseoado = null;
//	private seobasketDAO {					//생성자
//	}
//	public static seowonDAO getInstance() {
//		if(bseoado!=null) {
//			bseoado=new seowonDAO();
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
	public ArrayList<seobasketDTO> selectALL() {
		ArrayList<seobasketDTO> baList = new ArrayList<>();
		String sql = "select * from seobasket";
		Statement st = null;
		if (conn() != null) {
			try {
				st = conn.createStatement();
				rs = st.executeQuery(sql);
				while (rs.next()) {
					seobasketDTO seobdto = new seobasketDTO();
					seobdto.setSeono(rs.getInt("seono"));
					seobdto.setUserid(rs.getString("userid"));
					seobdto.setSize2(rs.getString("size2"));
					seobdto.setCnt(rs.getInt("cnt"));
					
					baList.add(seobdto);
				}
			} catch (Exception e) {
				System.out.println("출력실패!");
			} finally {
				try {
					if (conn != null) conn.close();
					if (st != null) st.close();
				} catch (SQLException e) {
					// TODO: handle exception
				}
			}
		}
		return baList;
	}
	public void insertOne(seobasketDTO s) {
		String sql="insert into seobasket values(?,?,?,?,?)";
		PreparedStatement ppst= null;
		if(conn()!=null) {
			try {
				ppst=conn.prepareStatement(sql);
				ppst.setInt(1, s.getSeono());
				ppst.setString(2,s.getUserid());
				ppst.setString(3, s.getName());
				ppst.setString(4, s.getSize2());
				ppst.setInt(5, s.getCnt());
				ppst.executeUpdate();
				
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
	
		
	}

	public seobasketDTO selectOne(String id) { // id 입력시 해당id 구매내역 확인.
		String sql= "select * from seobasket where userid=?";
		PreparedStatement ppst= null;
		seobasketDTO seodto = new seobasketDTO();
		if(conn()!=null) {
			try {
				ppst=conn.prepareStatement(sql);
				ppst.setString(1, id);
				rs=ppst.executeQuery();
				if (rs.next()) {
					seodto.setUserid(rs.getString("userid"));
					seodto.setSeono(rs.getInt("seono"));
					seodto.setName(rs.getString("name"));
					seodto.setSize2(rs.getString("size2"));
					seodto.setCnt(rs.getInt("cnt"));
					
				}
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("출력 실패");
			}finally {
				try {
					if (conn != null) conn.close();
					if (ppst != null) ppst.close();
				} catch (Exception e) {
					System.out.println("닫기 실패");
				}
			}
		}
		return seodto;
	}
	public void delete(String id,String name) {// 고객이 구매를 취소하는경우!! 장바구니에 해당 정보를 삭제!
		String sql= "delete from seobasket where userid=? and name=?";		//id 물품 입력시  삭제가능 !
		PreparedStatement ppst= null;
		if(conn()!=null) {
			try {
				ppst=conn.prepareStatement(sql);
				ppst.setString(1,id);
				ppst.setString(2,name);
				ppst.executeUpdate();
			} catch (Exception e) {
			}finally {
				try {
					if (conn != null) conn.close();
					if (ppst != null) ppst.close();
				} catch (Exception e) {
					System.out.println("닫기 실패 ");
				}
			}
		}
		
		
	}

}
