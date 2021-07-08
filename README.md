# 說明

### 系統建置與執行
* 執行`docker build -t app .`產生Image
  > 建置的過程會比較久，大約3~5分鐘不等，或甚至更長
* 執行`docker run -p 8080:8080 -t app`
* 執行成功後，直接在瀏覽器輸入[http://localhost:8080/actuator/health](http://localhost:8080/actuator/health)
    測試是否有回應
  
### 使用轉址功能
* 在瀏覽器輸入[http://localhost:8080/reurl/<encdoedUrlPath>](http://localhost:8080/reurl/<encdoedUrlPath>) ，應會直接導到
[https://www.google.com](https://www.google.com)