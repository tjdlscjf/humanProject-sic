package Business;

import java.util.ArrayList;
import java.util.Scanner;

import DAO.seobasketDAO;
import DAO.seowonDAO;
import DTO.seobasketDTO;
import DTO.seowonDTO;

public class seowonINFIMPL implements seowonINF {
	private seowonDAO mydao = new seowonDAO();					//.getInstance();
	private Scanner in = new Scanner(System.in);
	private seobasketDTO basdto =  null;
	private seobasketDAO mybasdao = new seobasketDAO();

	@Override
	public void read() {
		ArrayList<seowonDTO> List = mydao.selectAll();
		System.out.println("����Դϴ�.");
		System.out.println("------------------------");
		for (int i = 0; i < List.size(); i++) {
			System.out.print("��ǰ��ȣ :"+List.get(i).getNo()+"\n");
			System.out.print("��ǰ�� :"+List.get(i).getName()+"\n");
			System.out.print("������ :"+List.get(i).getSize1()+"\n");
			System.out.print("���� :"+List.get(i).getPrice()+"\n");
			System.out.print("���� :"+List.get(i).getCnt()+"\n");
			System.out.println("------------------------");
		}
	}

	@Override
	public void insert() {
		read();
		basdto=new seobasketDTO();
		System.out.println("�α������ּ��� (ID�Է�)");
		String inputid = in.nextLine();
		basdto.setUserid(inputid);
		System.out.println("��ǰ�� ��ȣ�� �Է��ϼ���.");
		int inputnum = in.nextInt();
		in.nextLine();
		basdto.setSeono(inputnum);
		System.out.println("��ǰ�� �̸��� �����ϼ���.(�Է�)");
		String inputname = in.nextLine();
		basdto.setName(inputname);
		System.out.println("����� �����ϼ���.(�Է�)");
		String inputsize = in.nextLine();
		basdto.setSize2(inputsize);
		System.out.println("������ ������ �����ϼ���.(�Է�)");
		int inputcnt = in.nextInt();
		in.nextLine();
		mydao.updateCnt(inputnum, inputcnt);
		basdto.setCnt(inputcnt);
		mybasdao.insertOne(basdto);
		System.out.println("���Ű� �Ϸ�Ǿ����ϴ�. \n �����մϴ�.");
	}

	@Override
	public void list() {
		System.out.println("ID�� �Է����ּ���");
		seobasketDTO mybasket=new seobasketDTO();
		String basketid=in.nextLine();
		mybasket=mybasdao.selectOne(basketid);
		if(mybasket.getUserid() !=null) {
		System.out.println("ȸ������ ID:"+mybasket.getUserid());
		System.out.println("��ǰ��ȣ:"+mybasket.getSeono());
		System.out.println("��ǰ�̸� :"+mybasket.getName());
		System.out.println("������ :"+mybasket.getSize2());
		System.out.println("������ ���� :"+mybasket.getCnt());
		}else{
		System.out.println("�Էµ� id�� ���������� �����ϴ�.");
		}
	}


	@Override
	public void delete() {
		System.out.println("���� ��� �� ID�� �Է����ּ���.");
		String deleteid=in.nextLine();
		System.out.println("��ǰ���� �Է����ּ���.");
		String deletename=in.nextLine();
		mybasdao.delete(deleteid,deletename);
		System.out.println("���Ű� ��� �Ǿ����ϴ�.");
		
	}

}
