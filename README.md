# 說明

## 系統建置與執行
* 執行`docker-compose -f docker-compose/docker-compose.yaml -p iptracer up`建置Image，並啟動DB與應用程式的Container
  > 建置的過程會比較久，大約3~5分鐘不等，或甚至更長
* 執行成功後，直接在瀏覽器輸入[http://localhost:8080/actuator/health](http://localhost:8080/actuator/health)
    測試是否有回應
* 執行`docker-compose -f docker-compose/docker-compose.yaml -p iptracer stop`停止應用程式與DB; \
  `docker-compose -f docker-compose/docker-compose.yaml -p iptracer start`啟動應用程式與DB。
  
## 使用轉址功能
* 在瀏覽器輸入[http://localhost:8080/reurl/<encdoedUrlPath>](http://localhost:8080/reurl/<encdoedUrlPath>) ，應會直接導到
[https://www.google.com](https://www.google.com)