package utils

import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.testobject.RequestObject
import com.kms.katalon.core.testobject.RestRequestObjectBuilder
import com.kms.katalon.core.testobject.impl.HttpTextBodyContent
import com.kms.katalon.core.testobject.TestObjectProperty
import com.kms.katalon.core.testobject.ConditionType
import groovy.json.JsonOutput

import utils.Utility

class APIHelper {

	static def sendPostRequest(String url, Map body) {

		String qTestToken = Utility.getQTestProperties("QTEST_TOKEN")

		List<TestObjectProperty> headers = [
			new TestObjectProperty('Content-Type', ConditionType.EQUALS, 'application/json')
		]

		headers.add(new TestObjectProperty('Authorization', ConditionType.EQUALS, "${qTestToken}"))

		RequestObject request = new RestRequestObjectBuilder()
				.withRestUrl(url)
				.withHttpHeaders(headers)
				.withTextBodyContent(JsonOutput.toJson(body))
				.withRestRequestMethod("POST")
				.build()

		return WS.sendRequest(request)
	}
}
