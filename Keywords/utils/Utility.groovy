package utils

import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.checkpoint.Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testcase.TestCase
import com.kms.katalon.core.testdata.TestData
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import java.util.Properties
import java.io.FileInputStream
import com.kms.katalon.core.configuration.RunConfiguration
import groovy.json.JsonSlurper

import internal.GlobalVariable

public class Utility {

	private static String getQTestProperties(String key) {
		Properties properties = new Properties()
		FileInputStream input = new FileInputStream("qtest.properties")
		try {
			properties.load(input)
			return properties.getProperty(key)
		} finally {
			input.close()
		}
	}

	@Keyword
	static Map getTestCaseMapping(String testCaseId) {
		try {
			def projectDir = System.getProperty("user.dir")
			def filePath = "${projectDir}/qtest_test_cases_mapping.json"
			def jsonFile = new File(filePath)

			if (!jsonFile.exists()) {
				println "❌ Mapping file not found at: $filePath"
				return null
			}

			def json = new JsonSlurper().parse(jsonFile)

			def match = json.find { it.katalon_test_cases_id == testCaseId }

			if (match) {
				println "✅ Mapping found for: ${testCaseId}"
				return [
					test_run_id: match.test_run_id,
					data       : match.data
				]
			} else {
				println "⚠️ No mapping found for: ${testCaseId}"
				return null
			}
		} catch (Exception e) {
			println "❌ Error while reading JSON mapping: ${e.message}"
			return null
		}
	}
}
