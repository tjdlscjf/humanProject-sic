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
			System.out.println("�����ϼ���>>");
			selnum = in.nextInt();
			in.nextLine();
			switch (selnum) {
			case 1: seowonList();  break;
			case 2: buy();  break;
			case 3: cancelbuying(); break;
			case 4: mybasket();  break;
			default:
				System.out.println("�α׾ƿ�");
				break;
			}
		}

	}

	public void menu() {
		System.out.println("1.��ǰ����");
		System.out.println("2.�����ϱ�");
		System.out.println("3.�������");
		System.out.println("4.������ ��ǰ����");
		System.out.println("5.�α׾ƿ�");
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
