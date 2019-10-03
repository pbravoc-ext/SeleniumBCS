package cl.bcs.application.suite.execute;

import java.util.List;

import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;

import cl.bcs.application.driver.factory.FactoryExtentReport;
import cl.bcs.application.factory.util.ReadExcelImpl;
import cl.bcs.application.factory.util.Session;
import cl.bcs.application.factory.util.SpotExcel;
import cl.bcs.application.file.util.UtilesSelenium;
import cl.bcs.application.suite.Spot;
import cl.bcs.plataforma.Login;

public class SpotExecute {
	public static class ExtentReportsClass extends FactoryExtentReport{
		List<SpotExcel> usuario = null;
		@BeforeTest
		public void startReport() {
			UtilesSelenium.executeTest();
			configuracionInicialER();

			//Login.login();
			usuario = ReadExcelImpl.leerExcel();
			
		}

		
		@Test(priority=1)
		public void variacion0() {
			Session.getConfigDriver().logger = Session.getConfigDriver().extent.startTest(usuario.get(0).getVariacion());
			Login.login();
			Session.getConfigDriver().logger.log(LogStatus.PASS, usuario.get(0).getVariacion()+" Completado" , "");
		}
//		@Test(priority=2)
//		public void variacion1() {
//			Session.getConfigDriver().logger = Session.getConfigDriver().extent.startTest(usuario.get(1).getVariacion());
//			Assert.assertTrue(Spot.suiteSpot(usuario.get(1)));
//			Session.getConfigDriver().logger.log(LogStatus.PASS, usuario.get(1).getVariacion()+" Completado", "");
//		}
//		
//		@Test(priority=3)
//		public void variacion2() {
//			Session.getConfigDriver().logger = Session.getConfigDriver().extent.startTest(usuario.get(2).getVariacion());
//			Assert.assertTrue(Spot.suiteSpot(usuario.get(2)));
//			Session.getConfigDriver().logger.log(LogStatus.PASS, usuario.get(2).getVariacion()+" Completado", "");
//		}
//		
//		@Test(priority=4)
//		public void variacion3() {
//			Session.getConfigDriver().logger = Session.getConfigDriver().extent.startTest(usuario.get(3).getVariacion());
//			Assert.assertTrue(Spot.suiteSpot(usuario.get(3)));
//			Session.getConfigDriver().logger.log(LogStatus.PASS, usuario.get(3).getVariacion()+" Completado" , "");
//		}
//		
//		@Test(priority=5)
//		public void variacion4() {
//			Session.getConfigDriver().logger = Session.getConfigDriver().extent.startTest(usuario.get(4).getVariacion());
//			Assert.assertTrue(Spot.suiteSpot(usuario.get(4)));
//			Session.getConfigDriver().logger.log(LogStatus.PASS, usuario.get(4).getVariacion()+" Completado", "");
//		}
//		
//		@Test(priority=6)
//		public void variacion5() {
//			Session.getConfigDriver().logger = Session.getConfigDriver().extent.startTest(usuario.get(5).getVariacion());
//			Assert.assertTrue(Spot.suiteSpot(usuario.get(5)));
//			Session.getConfigDriver().logger.log(LogStatus.PASS, usuario.get(5).getVariacion()+" Completado", "");
//		}
//		
//		@Test(priority=7)
//		public void variacion6() {
//			Session.getConfigDriver().logger = Session.getConfigDriver().extent.startTest(usuario.get(6).getVariacion());
//			Assert.assertTrue(Spot.suiteSpot(usuario.get(6)));
//			Session.getConfigDriver().logger.log(LogStatus.PASS, usuario.get(6).getVariacion()+" Completado", "");
//		}
//		@Test(priority=8)
//		public void variacion7() {
//			Session.getConfigDriver().logger = Session.getConfigDriver().extent.startTest(usuario.get(7).getVariacion());
//			Assert.assertTrue(Spot.suiteSpot(usuario.get(7)));
//			Session.getConfigDriver().logger.log(LogStatus.PASS,usuario.get(7).getVariacion()+" Completado", "");
//		}
//		
//		@Test(priority=9)
//		public void variacion8() {
//			Session.getConfigDriver().logger = Session.getConfigDriver().extent.startTest(usuario.get(8).getVariacion());
//			Assert.assertTrue(Spot.suiteSpot(usuario.get(8)));
//			Session.getConfigDriver().logger.log(LogStatus.PASS, usuario.get(8).getVariacion()+" Completado", "");
//		}
//		
		@Test(priority=10)
		public void variacion9() {
			Session.getConfigDriver().logger = Session.getConfigDriver().extent.startTest(usuario.get(9).getVariacion());
			Assert.assertTrue(Spot.suiteSpot(usuario.get(9)));
			Session.getConfigDriver().logger.log(LogStatus.PASS, usuario.get(9).getVariacion()+" Completado", "");
		}
//		@Test(priority=11)
//		public void variacion10() {
//			Session.getConfigDriver().logger = Session.getConfigDriver().extent.startTest(usuario.get(10).getVariacion());
//			Assert.assertTrue(Spot.suiteSpot(usuario.get(10)));
//			Session.getConfigDriver().logger.log(LogStatus.PASS, usuario.get(10).getVariacion()+" Completado", "");
//		}
//		
//		@Test(priority=12)
//		public void variacion11() {
//			Session.getConfigDriver().logger = Session.getConfigDriver().extent.startTest(usuario.get(11).getVariacion());
//			Assert.assertTrue(Spot.suiteSpot(usuario.get(11)));
//			Session.getConfigDriver().logger.log(LogStatus.PASS, usuario.get(11).getVariacion()+" Completado", "");
//		}
//		
//		@Test(priority=13)
//		public void variacion12() {
//			Session.getConfigDriver().logger = Session.getConfigDriver().extent.startTest(usuario.get(12).getVariacion());
//			Assert.assertTrue(Spot.suiteSpot(usuario.get(12)));
//			Session.getConfigDriver().logger.log(LogStatus.PASS, usuario.get(12).getVariacion()+" Completado", "");
//		}
		
		@AfterMethod
		public void getResult(ITestResult result) throws Exception{
			configuracionFinalER(result);
		}

		@AfterTest
		public void endReport() {
			Session.getConfigDriver().extent.flush();
			Session.getConfigDriver().extent.close();
			Session.getConfigDriver().getWebDriver().quit();
		}
	}
}
