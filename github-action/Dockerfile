FROM ghcr.io/vacxe/google-play-cli:0.5.0

COPY entrypoint.sh /entrypoint.sh
COPY templates /templates

RUN find /templates -type d -exec chmod +x {} \;

RUN chmod +x /entrypoint.sh

ENTRYPOINT ["sh", "/entrypoint.sh"]
