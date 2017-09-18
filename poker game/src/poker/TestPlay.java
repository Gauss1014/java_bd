package poker;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;

import javax.print.attribute.standard.RequestingUserName;

public class TestPlay {

	public Map<String, Player> tplayermap;  //定义变量用于存储玩家

	public TestPlay() {
		this.tplayermap = new HashMap<String, Player>();
	}


   /**
    * 定义function用于两两比较牌的大小
    * 
    * @param value1 第一个传进来的牌
    * @param value2 第二个传进来的牌
    * @param inPoker 传入poker的属性
    * @return
    */
	public int coparevalue(String value1, String value2, Poker inPoker) {
		String[] number = { "1", "2", "3", "4", "5", "6", "7", "8", "9" };
		String tmpvalue1 = value1.substring(0, 2);  //获取第一个poker的花色
		String tmpvalue2 = value2.substring(0, 2);  //获取第二个poker的花色

		String v1 = value1.substring(2, 3); //获取第一个poker的数字或者字母
		String v2 = value2.substring(2, 3); //获取第二个poker的花色或者字母

		//比较那个牌的数字或者字母大
		if ((v1.equals("A")
				&& (v2.equals("J") || v2.equals("K") || v2.equals("Q") || Arrays.asList(number).contains(v2)))
				|| (v1.equals("K") && (v2.equals("Q") || v2.equals("J") || Arrays.asList(number).contains(v2)))
				|| (v1.equals("Q") && (v2.equals("J") || Arrays.asList(number).contains(v2)))
				|| (v1.equals("J") && (Arrays.asList(number).contains(v2)))
				|| ((Arrays.asList(number).contains(v1) && Arrays.asList(number).contains(v2))
						&& ((v1.equals("1") && (!v1.equals(v2)))
								|| ((Integer.parseInt(v1) > Integer.parseInt(v2)) && (!v2.equals("1")))))) {

			return 1; //如果第一个牌大就返回1.
		//如果点数或者字母相同，就要判断花色
		} else if (v1.equals(v2)) {
			if ((tmpvalue1.equals("黑桃") && tmpvalue2.equals("红桃")) || (tmpvalue1.equals("黑桃") && tmpvalue2.equals("梅花"))
					|| (tmpvalue1.equals("黑桃") && tmpvalue2.equals("方块"))
					|| (tmpvalue1.equals("红桃") && tmpvalue2.equals("梅花"))
					|| (tmpvalue1.equals("红桃") && tmpvalue2.equals("方块"))
					|| (tmpvalue1.equals("梅花") && tmpvalue2.equals("方块"))) {
				//如果第一个大，那就要返回1.
				return 1;
			}

			else {
				return 2;
			}
		} else {
			return 2;
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		TestPlay testPlay = new TestPlay();

		//创建两个新玩家
		Player player1 = new Player("10001", "小明");  
		Player player2 = new Player("10002", "小红");

		//添加两个新玩家
		testPlay.tplayermap.put("1", player1);
		testPlay.tplayermap.put("2", player2);

		System.out.println("共有两个玩家");
		System.out.println("玩家---" + player1.name);

		System.out.println("玩家---" + player2.name);

		System.out.println("开始排牌......");
		System.out.println();

		String[] color = { "方块", "黑桃", "梅花", "红桃" };
		int[] number = { 2, 3, 4, 5, 6, 7, 8, 9, 10 };
		String[] letter = { "A", "J", "Q", "K" };

		Poker poker = new Poker(color, number, letter);

		Random random = new Random();
		int colorlen = poker.color.length;
		int numberlen = poker.number.length;
		int letterlen = poker.letter.length;

		//分别给两个玩家发牌
		for (int n = 1; n < 3; n++) {
			poker.pokelist.clear();  //清空临时存储发牌的list，为下一个玩家发牌作准备

			Integer tnn = n;

			//每个玩家各发5张牌
			for (int i = 0; i < 5; i++) {
				String record = ""; //用于存储发到的第一张牌

				do {
					//生成一张完整的牌
					StringBuilder stringBuilder = new StringBuilder();
					//获取花色
					stringBuilder.append(poker.color[random.nextInt(colorlen)]);
					
					//生成随机数，获取数字或者字母
					int key = random.nextInt(2) + 1;
					switch (key) {
					case 1:
						stringBuilder.append(poker.number[random.nextInt(numberlen)]);
						break;
					case 2:
						stringBuilder.append(poker.letter[random.nextInt(letterlen)]);
						break;
					}

					//临时变量存储完整的牌
					record = stringBuilder.toString();

					//排除第一个玩家拿到的牌
					if ((tnn.equals(2) && player1.playerPoker.get("1").contains(record))) {

						//判断存储临时的牌的list中是否有牌.
						if (poker.pokelist.size() > 0) {
							record = poker.pokelist.get(0);
							continue;
						}
					}
				} while (poker.pokelist.contains(record));  //如果已经发了牌就再重新生成.

				poker.pokelist.add(record); //将新生成的5张牌添加到list里面

			}

			//将5张牌添加给每个玩家
			switch (n) {
			case 1:
				player1.playpokerlist.addAll(poker.pokelist);
				player1.playerPoker.put("1", player1.playpokerlist);
				break;

			case 2:
				player2.playpokerlist.addAll(poker.pokelist);
				player2.playerPoker.put("1", player2.playpokerlist);

				break;
			}
		}

		System.out.println("玩家---" + player1.name + "拿到的牌如下：");
		for (String tpoker1 : player1.playerPoker.get("1")) {
			System.out.println(tpoker1);
		}
		
		System.out.println();

		System.out.println("玩家---" + player2.name + "拿到的牌如下：");

		for (String tpoker2 : player2.playerPoker.get("1")) {
			System.out.println(tpoker2);
		}

		//将玩家的牌进行两两比较，并进行排序。
		for (int nm = 1; nm < 3; nm++)

		{
			String tn = "1";
			String pn = nm + "";
			Player tplayer12 = testPlay.tplayermap.get(pn);
			for (int i = 0; i < tplayer12.playerPoker.get(tn).size() - 1; i++) {
				for (int j = i + 1; j < tplayer12.playerPoker.get(tn).size(); j++)

				{

					//maxvalue,如果为1， 那返回的就是第一个牌大
					int maxvalue = testPlay.coparevalue(tplayer12.playerPoker.get(tn).get(i),
							tplayer12.playerPoker.get(tn).get(j), poker);
					Integer tmaxvalue = maxvalue;

					String temp1 = tplayer12.playerPoker.get(tn).get(i);
					String temp2 = tplayer12.playerPoker.get(tn).get(j);

				    //用冒泡法进行位置的更换，从而将最大的牌排到最后
					if (tmaxvalue.equals(1)) {

						String temp3 = tplayer12.playerPoker.get(tn).get(j);
						tplayer12.playerPoker.get(tn).remove(j);
						tplayer12.playerPoker.get(tn).add(j, tplayer12.playerPoker.get(tn).get(i));
						tplayer12.playerPoker.get(tn).remove(i);
						tplayer12.playerPoker.get(tn).add(i, temp3);

					}
				}
			}
		}
		System.out.println();
		//取出玩家中最大的牌
		System.out.println("玩家---" + player1.name + "拿到最大的牌是：  " + player1.playerPoker.get("1").get(4));

		System.out.println("玩家---" + player2.name + "拿到最大的牌是：  " + player2.playerPoker.get("1").get(4));
		
		System.out.println();
		//判断哪个玩家拿到的牌最大.
		int maxpoker = testPlay.coparevalue(player1.playerPoker.get("1").get(4), player2.playerPoker.get("1").get(4), poker);
		if(maxpoker == 1)
		{
		    System.out.println("玩家 --" + player1.name +"拿到的牌最大..." + player1.name + "赢！！");
		}
		else
		{
			System.out.println("玩家 -- " + player2.name +"拿到的牌最大..." + player2.name + "赢！！");
		}

	}

}
