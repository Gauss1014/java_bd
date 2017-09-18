package poker;

import java.util.ArrayList;
import java.util.List;

public class Poker {
     public String[] color;  //定义变量用于存储poker花色
     public int[] number;   //定义变量用于存储poker的点数
     public String[] letter; //定义变量用于存储poker的A/J/Q/K
     public List<String> pokelist; //定义变量用于临时存储玩家拿到的poker
     public Poker(String[] newColor,int[] newNumber,String[] newLetter) {
    	 
    	 this.color = newColor;
    	 this.number = newNumber;
    	 this.letter = newLetter;
    	 this.pokelist = new ArrayList<String>();
	}
}
