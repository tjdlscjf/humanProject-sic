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
	private String url = "jdvc:oracle:thin:@localhost:1521:orcl1"; // ����Ŭ �ι���ԵǼ� orcl1�μ����Ͽ����ϴ�.
	private String id = "system";
	private String pwd = "1111";
	private ResultSet rs = null;
	
	//�̱���
//	public static seowonDAO seoado = null;
//	private seowonDAO() {					//������
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

	public ArrayList<seowonDTO> selectAll() { // ���� ���� ���� seowon ��ǰ�� ����Ʈ�� ������ش�.(interface �������� ����!)
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
				System.out.println("��½���!");
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

	public void updateCnt(int no,int cnt) { // ���� ������ ������ŭ ����
		
		String sql = "update seowon set cnt=cnt-? where no=?"; // no�� �⺻Ű �������մ���� �ݺ����� ����� �ʿ䰡����.
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
