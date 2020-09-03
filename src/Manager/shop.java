package Manager;

import java.util.Scanner;
import Business.seowonINFIMPL;

public class shop {
	private Scanner in = new Scanner(System.in);
	private seowonINFIMPL ser= new seowonINFIMPL();
	
	
	public shop() {
		int selnum = -1; 
		while (true) {
			menu();
			System.out.println("선택하세요>>");
			selnum = in.nextInt();
			in.nextLine();
			switch (selnum) {
			case 1: seowonList();  break;
			case 2: buy();  break;
			case 3: cancelbuying(); break;
			case 4: mybasket();  break;
			default:
				System.out.println("로그아웃");
				break;
			}
		}

	}

	public void menu() {
		System.out.println("1.상품보기");
		System.out.println("2.구매하기");
		System.out.println("3.구매취소");
		System.out.println("4.구매한 물품보기");
		System.out.println("5.로그아웃");
	}

	public void seowonList() {
		ser.read();
	}

	public void buy() {
		ser.insert();
	}

	public void cancelbuying() {
		ser.delete();
	}

	public void mybasket() {
		ser.list();
	}
}
