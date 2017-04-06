package testcases;

import org.testng.annotations.Test;

import utils.Log;


public class NewTest {
  @Test
  public void f() {
	  Log.info("NewTest");
  }
  
  @Test
  public void g() throws Exception{
	  Log.info("NewTest2");
  }
}
