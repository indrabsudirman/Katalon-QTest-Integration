## 📱 Katalon Mobile Automation with qTest Integration

Automated mobile testing using [Katalon Studio](https://katalon.com/) with integrated test result reporting to [qTest](https://www.tricentis.com/products/unified-test-management-qtest).

This is a sample project (Android) how to integrate Katalon Studio with QTest (Test Management).

### 🎯 Project Goals

This project aims to:

- Automate mobile app testing with Katalon Studio
- Integrate with qTest for automatic test result updates

### 🔧 Technologies & Tools

- 📱 **Katalon Studio 10.x**
- ☁️ **qTest API Integration** You could use the qTest Swagger API at your qTest account. The link will like this : https://Subdomain.qtestnet.com/p/ID_Project/portal/project#tab=api-docs-manager change the `Subdomain` and `ID_Project` with your qTest account details
- I use [Blibli Android](https://play.google.com/store/apps/details?id=blibli.mobile.commerce&hl=id) app for example

## 🚀 Getting Started

### 1. Clone the repository and open in Katalon Studio

```bash
git clone git@github.com:indrabsudirman/Katalon-QTest-Integration.git
```

### 2. Create a qTest account

You can sign up for a free trail qTest account at [qTest](https://www.tricentis.com/software-testing-tool-trial-demo/qtest-trial).

### 3. Edit the qtest_example.properties file.

Rename the file to qtest.properties and edit the following properties:

- QTEST_BASEURL=https://Your_Subdomain.qtestnet.com
- QTEST_TOKEN=Bearer Your_Bearer_Token (Start with word Bearer)
- QTEST_PROJECT_ID=Your_ID_Project

### 4. Prepare the Android App for testing

I use [Blibli Android](https://play.google.com/store/apps/details?id=blibli.mobile.commerce&hl=id) app for testing, if everything OK you could use your own Android/iOS app

I Install the app first in my phone than I can use `adb` command to find the package name. I used this command:

```bash
adb shell pm list packages | grep blibli
```

### 4. Prepare the content of the qtest_test_cases_mapping.json file

In the value `test_run_id` you need to put the Test Run ID from qTest. To find the Test Run ID you can use the following steps:

- Open the Test Execution Tab in qTest
  ![image](https://github.com/user-attachments/assets/a58a3809-8419-4f2c-85f0-8ed1b2f7eea5)

- Right click on the Test Run ID as shown image above, that you want to copy the ID. The last url will like this : https://Your_Subdomain.qtestnet.com/p/44584/portal/project#tab=testexecution&object=3&id=**132323451**
- The Test Run ID will be 132323451
- Please note that, the `data` property is object that has `test_step_logs` array. It represent the test steps from qTest. The goal is to match the test steps from qTest with the test steps from Katalon Studio. So you can update the `status` in each test step. In my case, I create the test steps in this file using qTest API using Python script.

![0518 gif](https://github.com/user-attachments/assets/e3a90430-a3ae-4efd-9487-e75ba903f394)

[![Watch on YouTube](https://github.com/user-attachments/assets/da82eae9-b838-456a-9527-539992a1a9cc)](https://drive.google.com/file/d/12HwcNFkrh2G9N6NlSeUy8Gxvkw1cUXo-/view?usp=sharing)

See on the qTest, the date execution is same as the response
![image](https://github.com/user-attachments/assets/20c2d7b0-e296-4ede-a69b-2373b377ce90)

See the detail step
![image](https://github.com/user-attachments/assets/aaa0a2fc-52bc-4332-bdcf-1868ee389c2a)

