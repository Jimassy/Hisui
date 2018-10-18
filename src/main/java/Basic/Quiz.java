package Basic;

public class Quiz extends App{

	App app = new App();
	String q1 = "Q．Hisuiのアイコンのシルエットの鳥の名前はなんでしょう？\n1.カワセミ\n2.ツバメ\n3.ハチドリ\n4.トンビ";
	String q2 = "問題2";
	String q3 = "問題3";

	public void play(){

		int qnumber = (int)(Math.random() * 3) +1;
		String qdisp = null;

		switch (qnumber) {

			case 1:
				String a1 = "2";
				qdisp = q1;
				break;

			case 2:
				String a2 = "3";
				qdisp = q2;
				break;

			case 3:
				String a3 = "4";
				qdisp = q3;
				break;
		}

		app.objMsgCh.sendMesssage(qdisp).queue();

		//ここからsleepしたりhogeる...
	}

}
