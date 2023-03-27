FROM adoptopenjdk/openjdk11:jdk-11.0.11_9-alpine-slim
RUN apk update && \
    apk add --no-cache jq \
    apk add --no-cache unzip
COPY /build/distributions/google-play-cli /usr/local/
RUN chmod +x /usr/local/google-play-cli