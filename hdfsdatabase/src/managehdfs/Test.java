package managehdfs;

import connentdb.FileTableSQL;

public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Test test=new Test();
        test.showMessage();
	}
    
	
	public void showMessage()
	{
		boolean realpath=FileTableSQL.insertFile("hadoop", "hadoop",1, "hadoop", "hadoop");
		System.out.println(realpath);
	}
}
