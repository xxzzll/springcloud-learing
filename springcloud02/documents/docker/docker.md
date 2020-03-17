## zookeeper 容器
```shell script
docker run -it -d --name zk zookeeper:3.4
```
## consul 容器
```shell script
# 要关闭防火墙，避免出现 Check "service:cloud-consul-payment-8006" HTTP request failed: Get http://10.10.10.113:8006/actuator/health: net/http: request canceled while waiting for connection (Client.Timeout exceeded while awaiting headers)
docker run -it -d --name consulserver -p 8500:8500 consul:1.6.1 agent -dev -bind=0.0.0.0 -ui -client=0.0.0.0
```