# nowprj

## Usage help

In order to use the sample project you need to have docker installed on your machine. Then follow the steps below:

* Build the docker image
```bash
docker build -t mytest .
```
This is just a plain gradle jdk21-alpine image, no other configuration is needed.

* Build application
```bash
docker run -v $(pwd):/mnt -p 9090:9090 -w /mnt mytest ./scripts/build.sh
```
This command runs the image and mounts the current directory in the container, then it runs the build script. A fat executable jar will be created in build/libs directory. 9090 port is exposed but not used

* Run application test
```bash
docker run -v $(pwd):/mnt -p 9090:9090 -w /mnt mytest ./scripts/tests.sh
```
This command runs application tests, report are visible in console. 9090 port is exposed but not used

HTML Report can be found in /mnt/build/reports/tests/test/index.html

!Note: In M1 systems volume may interfere with gradle test causing virtual machine crash. In this case use alternative Dockerfile that copy files in docker image.

```bash
docker build -t mytestm1 -f DockerfileM1 .
docker run -w /mnt mytestm1 ./scripts/tests.sh
```

* Run application 
```bash
docker run -v $(pwd):/mnt -p 9090:9090 -w /mnt mytest ./scripts/run.sh
```
This command starts application using spring-boot starter. 9090 port is exposed and can be called using this example call:
    
```bash
curl --location 'localhost:9090/api/v1/order' \
--header 'Content-Type: application/json' \
--data '{"order": {"items": [{"product_id": 1,"quantity": 1 }, {"product_id": 2,"quantity": 5},{"product_id":3,"quantity":1}]}}'
```

Calls rely on 3 items stored on local, non persistent, memory database (H2). The items id are: [1,2,3] and are created at startup-time. This is just for showing purpose and should never be used in real solutions.


