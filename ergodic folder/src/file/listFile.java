package file;

import java.io.File;
import java.io.IOError;

public class listFile {

	public static void listAllFile( File dir)
	{
		if(!dir.exists())
		{
			throw new IllegalArgumentException("目录" + dir + "不存在");
			
		}
		if(!dir.isDirectory())
		{
			throw new IllegalArgumentException(dir + "不是目录");
		}
//		String[] filename = dir.list(); //返回字符串数组，直接显示的是名字，不包括子目录下面的内容.
//		for(String filename1 : filename)
//		{
//			System.out.println(dir+"\\" +filename1);
//		}
//		
		//
		File[] files = dir.listFiles();
		if(files!=null && files.length > 0)
		{
	   
for (File file : files) {
	if(file.isDirectory())
	{
		listAllFile(file);
	}
	else
	{
	System.out.println(file);
}}
		}
	}
	


}
