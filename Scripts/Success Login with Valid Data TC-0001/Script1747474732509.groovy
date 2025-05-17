import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testng.keyword.TestNGBuiltinKeywords as TestNGKW
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys

Mobile.startExistingApplication('blibli.mobile.commerce')

boolean letBlibliUseYourData = Mobile.verifyElementExist(findTestObject('letBlibliUseYourDataPopUp'), 0, FailureHandling.OPTIONAL)

if (letBlibliUseYourData) {
	Mobile.delay(3, FailureHandling.STOP_ON_FAILURE)

	Mobile.tap(findTestObject('letBlibliUseYourDataPopUp'), 5)
}

boolean skipTextView = Mobile.verifyElementExist(findTestObject('skipTextView'), 0, FailureHandling.OPTIONAL)

if (skipTextView) {
	Mobile.delay(3, FailureHandling.STOP_ON_FAILURE)

	Mobile.tap(findTestObject('skipTextView'), 5)
}

Mobile.tap(findTestObject('startNowButton'), 5)

CustomKeywords.'utils.Validation.safeVerifyElementExist'(findTestObject("loginButton"))

CustomKeywords.'utils.Validation.safeVerifyElementExist'(findTestObject("bottomNavigationForYou"))

CustomKeywords.'utils.Validation.safeVerifyElementExist'(findTestObject("bottomNavigationBlibliBonus"))

CustomKeywords.'utils.Validation.safeVerifyElementExist'(findTestObject("bottomNavigationBag"))

CustomKeywords.'utils.Validation.safeVerifyElementExist'(findTestObject("bottomNavigationOrderList"))

CustomKeywords.'utils.Validation.safeVerifyElementExist'(findTestObject("bottomNavigationAccount"))

// CLick Login Button

Mobile.tap(findTestObject('loginButton'), 5)

Mobile.delay(20, FailureHandling.STOP_ON_FAILURE)

boolean userNameLoggedin = Mobile.verifyElementExist(findTestObject('textViewUserNameLoggedIn'), 0, FailureHandling.OPTIONAL)

if (!userNameLoggedin) {
	
	// Field Credential Account
	
	Mobile.tap(findTestObject('loginWithGoogle'), 5)
	
	Mobile.tap(findTestObject('textGmailAccount'), 5)
	
	Mobile.tap(findTestObject('buttonLanjutkan'), 5)
	
	Mobile.tap(findTestObject('buttonLanjutKeBlibli'), 5)
} else {
	
	// Validate Login Success
	
	print "Masuk di Else"
	
	CustomKeywords.'utils.Validation.safeVerifyElementExist'(findTestObject("textViewUserNameLoggedIn"))
	
	CustomKeywords.'utils.Validation.safeVerifyElementExist'(findTestObject("textViewBlibliPay"))
}




Mobile.closeApplication()

