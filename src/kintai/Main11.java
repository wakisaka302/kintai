package practice_print4;

import java.util.Random;

public class Main11 {

	public static void main(String[] args) {
		String[] rare = {"☆：フシギダネ","☆：ヒトカゲ","☆：ポッポ","☆：オニスズメ","☆：ミニリュウ"};
		String[] srare = {"☆☆：フシギソウ","☆☆：リザード","☆☆：ピジョン","☆☆：オニドリル","☆☆：ハクリュー"};
		String[] ssrare = {"☆☆☆：フシギバナ","☆☆☆：リザードン","☆☆☆：ピジョット","☆☆☆：ミュウ","☆☆☆：ピカチュー"};
		for(int i=0;i<10;i++);
		Random rand = new Random();
		int a =rand.nextInt(100);
		if(0 <= a && a <= 70) {
			
			
			int x = rand.nextInt(rare.length);
			System.out.println(rare[x]);
			
		}else if(71 <= a && a <=90) {
			
			int y = rand.nextInt(srare.length);
			System.out.println(srare[y]);
			
		}else if(91 <= a && a <=99) {
			
			int z = rand.nextInt(ssrare.length);
			System.out.println(ssrare[z]);
		}
		
	}

}
