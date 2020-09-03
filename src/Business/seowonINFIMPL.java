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
		System.out.println("목록입니다.");
		System.out.println("------------------------");
		for (int i = 0; i < List.size(); i++) {
			System.out.print("제품번호 :"+List.get(i).getNo()+"\n");
			System.out.print("제품명 :"+List.get(i).getName()+"\n");
			System.out.print("사이즈 :"+List.get(i).getSize1()+"\n");
			System.out.print("가격 :"+List.get(i).getPrice()+"\n");
			System.out.print("수량 :"+List.get(i).getCnt()+"\n");
			System.out.println("------------------------");
		}
	}

	@Override
	public void insert() {
		read();
		basdto=new seobasketDTO();
		System.out.println("로그인해주세요 (ID입력)");
		String inputid = in.nextLine();
		basdto.setUserid(inputid);
		System.out.println("제품의 번호를 입력하세요.");
		int inputnum = in.nextInt();
		in.nextLine();
		basdto.setSeono(inputnum);
		System.out.println("제품의 이름을 선택하세요.(입력)");
		String inputname = in.nextLine();
		basdto.setName(inputname);
		System.out.println("사이즈를 선택하세요.(입력)");
		String inputsize = in.nextLine();
		basdto.setSize2(inputsize);
		System.out.println("구매할 수량을 선택하세요.(입력)");
		int inputcnt = in.nextInt();
		in.nextLine();
		mydao.updateCnt(inputnum, inputcnt);
		basdto.setCnt(inputcnt);
		mybasdao.insertOne(basdto);
		System.out.println("구매가 완료되었습니다. \n 감사합니다.");
	}

	@Override
	public void list() {
		System.out.println("ID를 입력해주세요");
		seobasketDTO mybasket=new seobasketDTO();
		String basketid=in.nextLine();
		mybasket=mybasdao.selectOne(basketid);
		if(mybasket.getUserid() !=null) {
		System.out.println("회원님의 ID:"+mybasket.getUserid());
		System.out.println("물품번호:"+mybasket.getSeono());
		System.out.println("물품이름 :"+mybasket.getName());
		System.out.println("사이즈 :"+mybasket.getSize2());
		System.out.println("구매한 수량 :"+mybasket.getCnt());
		}else{
		System.out.println("입력된 id의 구매정보가 없습니다.");
		}
	}


	@Override
	public void delete() {
		System.out.println("구매 취소 할 ID와 입력해주세요.");
		String deleteid=in.nextLine();
		System.out.println("물품명을 입력해주세요.");
		String deletename=in.nextLine();
		mybasdao.delete(deleteid,deletename);
		System.out.println("구매가 취소 되었습니다.");
		
	}

}
