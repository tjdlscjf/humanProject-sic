package Test;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import DAO.seobasketDAO;
import DAO.seowonDAO;
import DTO.seobasketDTO;
import DTO.seowonDTO;

public class seowonTest {

	seowonDAO mydao = null;
	seobasketDAO basdao = null;

	@Before
	public void loading() {
		mydao = new seowonDAO();
		basdao = new seobasketDAO();
	}

	// seobasket Create ��ٱ��� �Է��׽�Ʈ
	@Test
	public void insertOneTest() {
		seobasketDTO qwe = new seobasketDTO();
		qwe.setSeono(2);
		qwe.setUserid("aa");
		qwe.setName("ccc");
		qwe.setSize2("290");
		qwe.setCnt(5);
		basdao.insertOne(qwe);

	}

	// seowon Read ���� �� �� �ִ� ����Ʈ �׽�Ʈ
	@Test
	public void selectAllTEst() {
		ArrayList<seowonDTO> mydto = mydao.selectAll();
		for (int i = 0; i < mydto.size(); i++) {
			System.out.println("------------------------");
			System.out.println(mydto.get(i).getNo());
			System.out.println(mydto.get(i).getName());
			System.out.println(mydto.get(i).getSize1());
			System.out.println(mydto.get(i).getPrice());
			System.out.println(mydto.get(i).getCnt());

		}
	}

	// ���� ������ ������ŭ �����Ǵ� �޼��� Update
	@Test
	public void updatecnt() {
		mydao.updateCnt(1, 5);
		
	}

	// seobasket Delete ��ٱ��� ���ϴ� ���� ����
	@Test
	public void deleteTest() {
		basdao.delete("aa", "��������ȭ");
	}
	
	//�Էµ� ���̵��� ���������� �����ش�.
	@Test
	public void basketselectOneTest() {
		seobasketDTO seodta=new seobasketDTO();
		seodta=basdao.selectOne("aa");
		System.out.println(seodta.getUserid());
		System.out.println(seodta.getSeono());
		System.out.println(seodta.getName());
		System.out.println(seodta.getSize2());
		System.out.println(seodta.getCnt());
		
	}

}
