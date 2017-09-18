package poker;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Player {

	public String playid;   //玩家的id
    public String name;  //玩家的名字

    public Map<String, List<String>> playerPoker;  //定义变量用于存储玩家所有拿到的poker
    
    public List<String > playpokerlist;  //定义变量用于存储玩家一次拿到的poker
    
    
    public Player(String newPlayid, String newName) {
		
    	this.playid = newPlayid;
    	this.name = newName;
    	this.playerPoker = new HashMap<String,List<String>>();
    	this.playpokerlist = new ArrayList<String>();
	}
    
    
}
