FROM adoptopenjdk/openjdk11:jdk-11.0.11_9-alpine-slim

ARG VERSION

RUN apk update && \
    apk add --no-cache jq wget unzip

RUN wget -q "https://github.com/Vacxe/google-play-cli-kt/releases/download/$VERSION/google-play-cli-$VERSION.zip" -O "google-play-cli.zip" && \
    unzip "google-play-cli.zip" && \
    rm "google-play-cli.zip" && \
    mv google-play-cli /usr/local/

RUN chmod +x /usr/local/google-play-cli

ENV PATH="${PATH}:/usr/local/google-play-cli/bin/"

RUN echo "CLI version:" && google-play-cli version
