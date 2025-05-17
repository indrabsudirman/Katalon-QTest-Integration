import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testobject.TestObject as TestObject

import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile

import internal.GlobalVariable as GlobalVariable

import com.kms.katalon.core.annotation.BeforeTestCase
import com.kms.katalon.core.annotation.BeforeTestSuite
import com.kms.katalon.core.annotation.AfterTestCase
import com.kms.katalon.core.annotation.AfterTestSuite
import com.kms.katalon.core.context.TestCaseContext
import com.kms.katalon.core.context.TestSuiteContext

import groovy.json.JsonOutput
import groovy.json.JsonSlurper
import java.net.HttpURLConnection
import java.net.URL

import utils.APIHelper
import utils.DateTimeUtil
import utils.Utility
import constant.TestStatus

class LogListener {
	/**
	 * Executes before every test case starts.
	 * @param testCaseContext related information of the executed test case.
	 */
	@BeforeTestCase
	def sampleBeforeTestCase(TestCaseContext testCaseContext) {
		println "@BeforeTestCase"
		println testCaseContext.getTestCaseId()
		println testCaseContext.getTestCaseVariables()

		GlobalVariable.testcaseStartTime = DateTimeUtil.getQTestDateTimeFormat()

		println GlobalVariable.testcaseStartTime
	}

	/**
	 * Executes after every test case ends.
	 * @param testCaseContext related information of the executed test case.
	 */
	@AfterTestCase
	def sampleAfterTestCase(TestCaseContext testCaseContext) {
		println "@AfterTestCase"
		println testCaseContext.getTestCaseId()
		println testCaseContext.getTestCaseStatus()

		String testCaseName = testCaseContext.getTestCaseId()
		String testStatus = testCaseContext.getTestCaseStatus()
		String testRunId = ""

		String endTime = DateTimeUtil.getQTestDateTimeFormat()

		TestStatus status
		switch (testStatus) {
			case 'PASSED':
				status = TestStatus.PASSED
				break
			case 'FAILED':
				status = TestStatus.FAILED
				break
			default:
				status = TestStatus.SKIPPED
				break
		}

		def testCaseId = testCaseName.substring(testCaseName.lastIndexOf('TC-'))
		def mapping = CustomKeywords.'utils.Utility.getTestCaseMapping'(testCaseId)

		if (mapping != null) {
			// Bisa proses test_steps di sini atau update qTest langsung
			testRunId = mapping.test_run_id          // Output: 13232418
			println "TestRun Id is ${testRunId}"

			// Update status di dalam data
			mapping.data.exe_start_date = GlobalVariable.testcaseStartTime
			mapping.data.exe_end_date = endTime
			mapping.data.status = status.name() 
			mapping.data.test_step_logs.each { it.status = status.name() }

			// Convert ke JSON (string) untuk dijadikan payload
			def requestBody = JsonOutput.toJson(mapping.data)

			// (Opsional) print untuk verifikasi
			println JsonOutput.prettyPrint(requestBody)

			String qTestUrl = "${Utility.getQTestProperties("QTEST_BASEURL")}/api/v3/projects/${Utility.getQTestProperties("QTEST_PROJECT_ID")}/test-runs/${testRunId}/auto-test-logs"

			// Nanti dihapus
			println "linknya : ${qTestUrl}"

			Map body = mapping.data 

			def response = APIHelper.sendPostRequest(qTestUrl, body)

			// Parse dulu biar valid JSON
			def jsonResponse = new JsonSlurper().parseText(response.getResponseText())

			// Lalu pretty print
			println "Response from qTest API:\n" + JsonOutput.prettyPrint(JsonOutput.toJson(jsonResponse))
		} else {
			println "Tidak ada mapping ditemukan untuk: $testCaseId"
		}

	}

	/**
	 * Executes before every test suite starts.
	 * @param testSuiteContext: related information of the executed test suite.
	 */
	@BeforeTestSuite
	def sampleBeforeTestSuite(TestSuiteContext testSuiteContext) {
		println "@BeforeTestSuite"
		println testSuiteContext.getTestSuiteId()
	}

	/**
	 * Executes after every test suite ends.
	 * @param testSuiteContext: related information of the executed test suite.
	 */
	@AfterTestSuite
	def sampleAfterTestSuite(TestSuiteContext testSuiteContext) {
		println "@AfterTestSuite"
		println testSuiteContext.getTestSuiteId()
	}
}