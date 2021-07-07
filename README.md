# 說明

* 執行`docker build -t app .`產生Image
  > 建置的過程會比較久，大約3~5分鐘不等，或甚至更長
* 執行`docker run -p 8080:8080 -t app`
* 執行成功後，直接在瀏覽器輸入[http://localhost:8080/actuator/health](http://localhost:8080/actuator/health)
    測試是否有回應