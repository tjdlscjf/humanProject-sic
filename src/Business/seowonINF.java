package Business;

public interface seowonINF {
		
		public void read();		// 물품보기 
		public void insert();	// 장바구니 추가				Create
		public void list();		//장바구니  목록보기 			Read
//		public void update();	// seowon 물건 수량 갱신 		Update  	
//								// 고객에게는 리스트에 확인 가능 하오니 서비스단에 입력x
		public void delete();	// 고객구입취소(장바구니 삭제) 	Delete
}
