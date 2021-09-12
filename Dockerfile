#### Stage 1: Build the application
FROM openjdk:11-jdk-slim as build

# Set the current working directory inside the image
WORKDIR /app

# Copy gradle executable to the image
COPY gradlew .
COPY gradle gradle
COPY build.gradle .

# Set permission to execute file
RUN chmod +x gradlew

# Prepare and install dos2unix to make gradlew file accessible
RUN apt-get update && apt-get install dos2unix
RUN dos2unix gradlew

RUN apt-get -qq update
RUN apt-get -qq -y install curl

# Copy the project source
COPY src src

COPY src/main/script/wait-for-it.sh wait-for-it.sh
RUN chmod +x wait-for-it.sh && dos2unix wait-for-it.sh

COPY src/main/script/start.sh start.sh
RUN chmod +x start.sh && dos2unix start.sh

# Package the application
RUN ./gradlew bootJar
RUN mkdir -p build/dependency && (cd build/dependency; jar -xf ../libs/*.jar)

#### Stage 2: A minimal docker image with command to run the app
FROM openjdk:11-jre-slim

# Set the current working directory inside the image
WORKDIR /app

ARG DEPENDENCY=/app/build/dependency

# Copy project dependencies from the build stage
COPY --from=build ${DEPENDENCY}/BOOT-INF/lib ./lib
COPY --from=build ${DEPENDENCY}/META-INF ./META-INF
COPY --from=build ${DEPENDENCY}/BOOT-INF/classes .

COPY --from=build /app/wait-for-it.sh ./wait-for-it.sh
COPY --from=build /app/start.sh ./start.sh

ENTRYPOINT ["./start.sh"]