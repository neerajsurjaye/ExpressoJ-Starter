FROM ubuntu:20.04

RUN apt-get update && apt-get install -y openjdk-21-jre && apt-get install -y git && apt-get install -y maven

RUN git clone --branch release/v1.0.0 https://github.com/neerajsurjaye/ExpressoJ.git && \
    cd ExpressoJ && \
    mvn clean install

WORKDIR /app

COPY . .

RUN mvn clean install

CMD java -jar target/expressoj-demo-1.0-SNAPSHOT.jar