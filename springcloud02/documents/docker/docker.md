## zookeeper 容器
```shell script
docker run -it -d --name zk zookeeper:3.4
```
## consul 容器
```shell script
docker run -it -d --name consul -p 8500:8500 consul:1.6.1 agent -bind=0.0.0.0 -dev -ui -client=0.0.0.0
```